package com.hao.androidrecord.custom.selector.internal.ui.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hao.androidrecord.R;
import com.hao.androidrecord.custom.selector.internal.entity.Album;
import com.hao.androidrecord.custom.selector.internal.entity.Item;
import com.hao.androidrecord.custom.selector.internal.entity.SelectionSpec;
import com.hao.androidrecord.custom.selector.internal.model.SelectedItemCollection;


public class AlbumMediaCustomAdapter extends
        RecyclerViewCursorAdapter<RecyclerView.ViewHolder>{


    private static final int VIEW_TYPE_CAPTURE = 0x01;
    private static final int VIEW_TYPE_MEDIA = 0x02;
    private final SelectedItemCollection mSelectedCollection;
    private final Drawable mPlaceholder;
    private SelectionSpec mSelectionSpec;
    private CheckStateListener mCheckStateListener;
    private OnMediaClickListener mOnMediaClickListener;
    private RecyclerView mRecyclerView;
    private int mImageResize;
    private Context context;


    public AlbumMediaCustomAdapter(Context context, SelectedItemCollection selectedCollection, RecyclerView recyclerView) {
        super(null);
        this.context = context;
        mSelectionSpec = SelectionSpec.getInstance();
        mSelectedCollection = selectedCollection;

        TypedArray ta = context.getTheme().obtainStyledAttributes(new int[]{R.attr.item_placeholder});
//        mPlaceholder = ta.getDrawable(0);
        mPlaceholder = ContextCompat.getDrawable(context,R.drawable.img_default_hold);
        ta.recycle();

        mRecyclerView = recyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_CAPTURE) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album_capture, parent, false);
            CaptureViewHolder holder = new CaptureViewHolder(v);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getContext() instanceof OnPhotoCapture) {
                        ((OnPhotoCapture) v.getContext()).capture();
                    }
                }
            });
            return holder;
        }else if (viewType == VIEW_TYPE_MEDIA){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_media_grid, parent, false);
            return new MediaViewHolder(v);
        }else {
            return null;
        }


    }

    @Override
    protected void onBindViewHolderItem(RecyclerView.ViewHolder holder, Item item, int position) {
//        super.onBindViewHolder(holder, item, position);
        if (holder instanceof MediaViewHolder) {

            MediaViewHolder mediaViewHolder = (MediaViewHolder) holder;



            SelectionSpec.getInstance().imageEngine.loadThumbnail(context, getImageResize(context),
                    mPlaceholder, mediaViewHolder.mCover, item.uri);

            mediaViewHolder.mDuration.setText(DateUtils.formatElapsedTime(item.duration / 1000));

            if (item.width>= 2160 && item.height>=3840){
                mediaViewHolder.m4KFlag.setVisibility(View.VISIBLE);
            }else if (item.width>= 3840 && item.height>=2160){
                mediaViewHolder.m4KFlag.setVisibility(View.VISIBLE);
            }else {
                mediaViewHolder.m4KFlag.setVisibility(View.INVISIBLE);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnMediaClickListener.onMediaClick(null,item,holder.getAdapterPosition());

                }
            });


        }
    }



    @Override
    protected void onBindViewHolder(final RecyclerView.ViewHolder holder, Cursor cursor) {
       /* if (holder instanceof CaptureViewHolder) {
            CaptureViewHolder captureViewHolder = (CaptureViewHolder) holder;
            Drawable[] drawables = captureViewHolder.mHint.getCompoundDrawables();
            TypedArray ta = holder.itemView.getContext().getTheme().obtainStyledAttributes(
                    new int[]{R.attr.capture_textColor});
            int color = ta.getColor(0, 0);
            ta.recycle();

            for (int i = 0; i < drawables.length; i++) {
                Drawable drawable = drawables[i];
                if (drawable != null) {
                    final Drawable.ConstantState state = drawable.getConstantState();
                    if (state == null) {
                        continue;
                    }

                    Drawable newDrawable = state.newDrawable().mutate();
                    newDrawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                    newDrawable.setBounds(drawable.getBounds());
                    drawables[i] = newDrawable;
                }
            }
            captureViewHolder.mHint.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
        } else */
    }




    @Override
    public int getItemViewType(int position) {
        return position==0 ? VIEW_TYPE_CAPTURE : VIEW_TYPE_MEDIA;
    }

    @Override
    protected int getItemViewType(int position, Cursor cursor) {
        return 0;
    }

    public void registerCheckStateListener(CheckStateListener listener) {
        mCheckStateListener = listener;
    }


    public void registerOnMediaClickListener(OnMediaClickListener listener) {
        mOnMediaClickListener = listener;
    }




    private int getImageResize(Context context) {
        if (mImageResize == 0) {
            RecyclerView.LayoutManager lm = mRecyclerView.getLayoutManager();
            int spanCount = ((GridLayoutManager) lm).getSpanCount();
            int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
            int availableWidth = screenWidth - context.getResources().getDimensionPixelSize(
                    R.dimen.custom_media_grid_spacing) * (spanCount - 1);
            mImageResize = availableWidth / spanCount;
            mImageResize = (int) (mImageResize * mSelectionSpec.thumbnailScale);
        }
        return mImageResize;
    }

    public interface CheckStateListener {
        void onUpdate();
    }

    public interface OnMediaClickListener {
        void onMediaClick(Album album, Item item, int adapterPosition);
    }

    public interface OnPhotoCapture {
        void capture();
    }

    private static class MediaViewHolder extends RecyclerView.ViewHolder {

        private ImageView mCover;
        private TextView mDuration;
        private TextView m4KFlag;
        MediaViewHolder(View itemView) {
            super(itemView);
            mCover = itemView.findViewById(R.id.iv_video_cover);
            mDuration = itemView.findViewById(R.id.tv_duration);
            m4KFlag = itemView.findViewById(R.id.tv_4k);
        }
    }

    private static class CaptureViewHolder extends RecyclerView.ViewHolder {


        CaptureViewHolder(View itemView) {
            super(itemView);

        }
    }

}