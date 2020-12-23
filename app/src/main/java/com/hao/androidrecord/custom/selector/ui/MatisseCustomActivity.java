package com.hao.androidrecord.custom.selector.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.hao.androidrecord.R;
import com.hao.androidrecord.activity.BaseActivity;
import com.hao.androidrecord.custom.SettingPermissonDialog;
import com.hao.androidrecord.custom.selector.internal.entity.Album;
import com.hao.androidrecord.custom.selector.internal.entity.Item;
import com.hao.androidrecord.custom.selector.internal.entity.SelectionSpec;
import com.hao.androidrecord.custom.selector.internal.model.AlbumCollection;
import com.hao.androidrecord.custom.selector.internal.model.SelectedItemCollection;
import com.hao.androidrecord.custom.selector.internal.ui.MediaSelectionCustomFragment;
import com.hao.androidrecord.custom.selector.internal.ui.adapter.AlbumMediaCustomAdapter;
import com.hao.androidrecord.custom.selector.internal.ui.adapter.AlbumsAdapter;
import com.hao.androidrecord.custom.selector.internal.ui.widget.AlbumsSpinner;
import com.hao.androidrecord.custom.selector.internal.utils.MediaStoreCompat;
import com.hao.androidrecord.util.GetPathFromUri;
import com.hao.androidrecord.util.LocalVideoInfo;
import com.hao.androidrecord.util.RxBus;
import com.hao.androidrecord.util.toast.ToastUtil;

import io.reactivex.rxjava3.functions.Consumer;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

/**
 * Main Activity to display albums and media content (images/videos) in each album
 * and also support media selecting operations.
 */
@RuntimePermissions
public class MatisseCustomActivity extends BaseActivity implements
        AlbumCollection.AlbumCallbacks, AdapterView.OnItemSelectedListener,
        MediaSelectionCustomFragment.SelectionProvider, View.OnClickListener,
        AlbumMediaCustomAdapter.CheckStateListener, AlbumMediaCustomAdapter.OnMediaClickListener,
        AlbumMediaCustomAdapter.OnPhotoCapture {

    private final AlbumCollection mAlbumCollection = new AlbumCollection();
    private MediaStoreCompat mMediaStoreCompat;
    private SelectedItemCollection mSelectedCollection = new SelectedItemCollection(this);
    private SelectionSpec mSpec;

    private AlbumsSpinner mAlbumsSpinner;
    private AlbumsAdapter mAlbumsAdapter;
    private View mContainer;
    private View mEmptyView;
    //200M
    private static final int VIDEO_LIMITE_SIZE = 200*1024*1024;

    private SettingPermissonDialog settingDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // programmatically set theme before super.onCreate()
        mSpec = SelectionSpec.getInstance();
        super.onCreate(savedInstanceState);
        if (!mSpec.hasInited) {
            setResult(RESULT_CANCELED);
            finish();
            return;
        }
        setContentView(R.layout.activity_custom_matisse);

        if (mSpec.needOrientationRestriction()) {
            setRequestedOrientation(mSpec.orientation);
        }

        if (mSpec.capture) {
            mMediaStoreCompat = new MediaStoreCompat(this);
            if (mSpec.captureStrategy == null)
                throw new RuntimeException("Don't forget to set CaptureStrategy.");
            mMediaStoreCompat.setCaptureStrategy(mSpec.captureStrategy);
        }



        mContainer = findViewById(R.id.container);
        mEmptyView = findViewById(R.id.empty_view);


        mSelectedCollection.onCreate(savedInstanceState);


        mAlbumsAdapter = new AlbumsAdapter(this, null, false);
        mAlbumsSpinner = new AlbumsSpinner(this);
        mAlbumsSpinner.setOnItemSelectedListener(this);

        mAlbumsSpinner.setAdapter(mAlbumsAdapter);

        mAlbumCollection.onCreate(this, this);
        mAlbumCollection.onRestoreInstanceState(savedInstanceState);
        mAlbumCollection.loadAlbums();


        findViewById(R.id.iv_close_album).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /*RxBus.Companion.getRxBus().toObservable(BusCloseActivity.class)
                .subscribe(new Consumer<BusCloseActivity>() {
                    @Override
                    public void accept(BusCloseActivity busCloseActivity) throws Throwable {
                        if ("MatisseCustomActivity".equalsIgnoreCase(busCloseActivity.getName())){
                            finish();
                        }

                    }
                });*/


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mSelectedCollection.onSaveInstanceState(outState);
        mAlbumCollection.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAlbumCollection.onDestroy();
        mSpec.onCheckedListener = null;
        mSpec.onSelectedListener = null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mAlbumCollection.setStateCurrentSelection(position);
        mAlbumsAdapter.getCursor().moveToPosition(position);
        Album album = Album.valueOf(mAlbumsAdapter.getCursor());
        if (album.isAll() && SelectionSpec.getInstance().capture) {
            album.addCaptureCount();
        }
        onAlbumSelected(album);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onAlbumLoad(final Cursor cursor) {
        mAlbumsAdapter.swapCursor(cursor);
        // select default album.
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {

            @Override
            public void run() {
                cursor.moveToPosition(mAlbumCollection.getCurrentSelection());
                mAlbumsSpinner.setSelection(MatisseCustomActivity.this,
                        mAlbumCollection.getCurrentSelection());
                Album album = Album.valueOf(cursor);
                if (album.isAll() && SelectionSpec.getInstance().capture) {
                    album.addCaptureCount();
                }
                onAlbumSelected(album);
            }
        });
    }

    @Override
    public void onAlbumReset() {
        mAlbumsAdapter.swapCursor(null);
    }

    private void onAlbumSelected(Album album) {
        if (album.isAll() && album.isEmpty()) {
            mContainer.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.VISIBLE);
        } else {
            mContainer.setVisibility(View.VISIBLE);
            mEmptyView.setVisibility(View.GONE);
            Fragment fragment = MediaSelectionCustomFragment.newInstance(album);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment, MediaSelectionCustomFragment.class.getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    @Override
    public void onUpdate() {
        // notify bottom toolbar that check state changed.

        if (mSpec.onSelectedListener != null) {
            mSpec.onSelectedListener.onSelected(
                    mSelectedCollection.asListOfUri(), mSelectedCollection.asListOfString());
        }
    }


    @Override
    public void onMediaClick(Album album, Item item, int adapterPosition) {

        long size = item.size/1024;
        long bitrate = (size*8/(item.duration/1000));
        if (bitrate>=40000){
            ToastUtil.INSTANCE.showShortToast(MatisseCustomActivity.this,"暂不支持4K视频");
            return;
        }else {
            if (item.width>= 2160 && item.height>=3840){
                ToastUtil.INSTANCE.showShortToast(MatisseCustomActivity.this,"暂不支持4K视频");
                return;
            }else if (item.width>= 3840 && item.height>=2160){
                ToastUtil.INSTANCE.showShortToast(MatisseCustomActivity.this,"暂不支持4K视频");
                return;
            }
        }


        //跳转到发布界面
        if (item.duration < 5000){
            ToastUtil.INSTANCE.showShortToast(MatisseCustomActivity.this,"视频小于5秒请重新选择");
            return;
        }

        LocalVideoInfo info = GetPathFromUri.getLocalVideoInfo(this,item.uri);



    }


    @Override
    public SelectedItemCollection provideSelectedItemCollection() {
        return mSelectedCollection;
    }

    @Override
    public void capture() {
        MatisseCustomActivityPermissionsDispatcher.showCameraRequestWithPermissionCheck(MatisseCustomActivity.this);
    }

    @NeedsPermission({Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO})
    public void showCameraRequest(){
//        LiteCameraActivity.Companion.launch(MatisseCustomActivity.this);
    }
    @OnPermissionDenied({Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO})
    public void cameraDenied(){}

    @OnNeverAskAgain({Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO})
    public void cameraNeverAskAgain(){
        if (settingDialog == null){
            settingDialog = new SettingPermissonDialog(MatisseCustomActivity.this,"");
            settingDialog.setSureClickListener(new SettingPermissonDialog.SureClickListener() {
                @Override
                public void sureClick() {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(Uri.parse("package:$packageName"));
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                    startActivityForResult(intent, 2000);
                }

                @Override
                public void cancel() {

                }
            });
        }
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MatisseCustomActivityPermissionsDispatcher.onRequestPermissionsResult(this,requestCode, grantResults);
    }

    /**
     * 查找视频文件
     *
     * @param context
     * @return
     */
    public  void getVideoList(Context context) {
        Log.e("=======album","start");
        // MediaStore.Video.Thumbnails.DATA:视频缩略图的文件路径
        String[] thumbColumns = {MediaStore.Video.Thumbnails.DATA,
                MediaStore.Video.Thumbnails.VIDEO_ID};
        // 视频其他信息的查询条件
        String[] mediaColumns = {MediaStore.Video.Media._ID, MediaStore.Video.Media.SIZE,
                MediaStore.Video.Media.DATA, MediaStore.Video.Media.TITLE,
                MediaStore.Video.Media.MIME_TYPE,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.DATE_TAKEN,
                MediaStore.Video.Media.WIDTH,
                MediaStore.Video.Media.HEIGHT
        };

        Cursor cursor = context.getContentResolver().query(MediaStore.Video.Media
                        .EXTERNAL_CONTENT_URI,
                mediaColumns, null, null, null);

        if (cursor == null) {
            return;
        }
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor
                        .getColumnIndex(MediaStore.Video.Media._ID));
                Cursor thumbCursor = context.getContentResolver().query(
                        MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI,
                        thumbColumns, MediaStore.Video.Thumbnails.VIDEO_ID
                                + "=" + id, null, null);

                if (thumbCursor.moveToFirst()) {
                    Log.e("========albumthum",""+thumbCursor.getString(thumbCursor.getColumnIndex(MediaStore.Video.Thumbnails.DATA)));
                }
                /*info.setFilePath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media
                        .DATA)));
                info.setMimeType(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE)));
                info.setTitle(cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Downloads.TITLE)));
                info.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Downloads.DURATION)));
                info.setTakeTime(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Downloads.DATE_TAKEN)));
                info.setVideoSize(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Downloads.SIZE)));*/

                Log.e("========albumw",""+cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.WIDTH)));
                Log.e("========albumh",""+cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.HEIGHT)));

            } while (cursor.moveToNext());
        }

        Log.e("=======album","end");
    }
}