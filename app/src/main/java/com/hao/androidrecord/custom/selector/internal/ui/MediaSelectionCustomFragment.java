package com.hao.androidrecord.custom.selector.internal.ui;

import android.content.Context;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.hao.androidrecord.R;
import com.hao.androidrecord.custom.selector.internal.entity.Album;
import com.hao.androidrecord.custom.selector.internal.entity.Item;
import com.hao.androidrecord.custom.selector.internal.entity.SelectionSpec;
import com.hao.androidrecord.custom.selector.internal.model.AlbumMediaCollection;
import com.hao.androidrecord.custom.selector.internal.model.SelectedItemCollection;
import com.hao.androidrecord.custom.selector.internal.ui.adapter.AlbumMediaCustomAdapter;
import com.hao.androidrecord.custom.selector.internal.ui.widget.MediaGridInset;
import com.hao.androidrecord.custom.selector.internal.utils.UIUtils;
import com.kennyc.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;


public class MediaSelectionCustomFragment extends Fragment implements
        AlbumMediaCollection.AlbumMediaCallbacks, AlbumMediaCustomAdapter.CheckStateListener,
        AlbumMediaCustomAdapter.OnMediaClickListener {

    public static final String EXTRA_ALBUM = "extra_album";

    private final AlbumMediaCollection mAlbumMediaCollection = new AlbumMediaCollection();
    private RecyclerView mRecyclerView;
    private AlbumMediaCustomAdapter mAdapter;
    private SelectionProvider mSelectionProvider;
    private AlbumMediaCustomAdapter.CheckStateListener mCheckStateListener;
    private AlbumMediaCustomAdapter.OnMediaClickListener mOnMediaClickListener;

    private MultiStateView stateView;

    public static MediaSelectionCustomFragment newInstance(Album album) {
        MediaSelectionCustomFragment fragment = new MediaSelectionCustomFragment();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_ALBUM, album);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SelectionProvider) {
            mSelectionProvider = (SelectionProvider) context;
        } else {
            throw new IllegalStateException("Context must implement SelectionProvider.");
        }
        if (context instanceof AlbumMediaCustomAdapter.CheckStateListener) {
            mCheckStateListener = (AlbumMediaCustomAdapter.CheckStateListener) context;
        }
        if (context instanceof AlbumMediaCustomAdapter.OnMediaClickListener) {
            mOnMediaClickListener = (AlbumMediaCustomAdapter.OnMediaClickListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_media_selection_custom, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        stateView = view.findViewById(R.id.msv_album_video);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Album album = getArguments().getParcelable(EXTRA_ALBUM);

        mAdapter = new AlbumMediaCustomAdapter(getContext(),
                mSelectionProvider.provideSelectedItemCollection(), mRecyclerView);
        mAdapter.registerCheckStateListener(this);
        mAdapter.registerOnMediaClickListener(this);
        mRecyclerView.setHasFixedSize(true);

        int spanCount;
        SelectionSpec selectionSpec = SelectionSpec.getInstance();
        if (selectionSpec.gridExpectedSize > 0) {
            spanCount = UIUtils.spanCount(getContext(), selectionSpec.gridExpectedSize);
        } else {
            spanCount = selectionSpec.spanCount;
        }
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));

        int spacing = getResources().getDimensionPixelSize(R.dimen.custom_media_grid_spacing);
        mRecyclerView.addItemDecoration(new MediaGridInset(spanCount, spacing, false));
        mRecyclerView.setAdapter(mAdapter);
        mAlbumMediaCollection.onCreate(getActivity(), this);
        mAlbumMediaCollection.load(album, selectionSpec.capture);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mAlbumMediaCollection.onDestroy();
    }



    @Override
    public void onAlbumMediaLoad(Cursor cursor) {
//        mAdapter.swapCursor(cursor);
        initData(cursor);
    }

    private void initData(Cursor cursor){
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        List<Item> list = new ArrayList<>();
        if (cursor!=null){
            while (cursor.moveToNext()){
                final Item item = Item.valueOf(cursor);
                if (item.width == 0 || item.height == 0){
                    try {
                        retriever.setDataSource(requireContext(),item.uri);
                        String tempWidth = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);
                        String tempHeight = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);
                        if (tempWidth!=null && !TextUtils.isEmpty(tempWidth)){
                            item.width = Integer.parseInt(tempWidth);
                        }
                        if (tempHeight!=null && !TextUtils.isEmpty(tempHeight)){
                            item.height = Integer.parseInt(tempHeight);
                        }
                    }catch (Exception e){

                    }


                }
                list.add(item);
            }
        }
        if (!list.isEmpty()){
            mAdapter.initData(list);
            stateView.setViewState(MultiStateView.ViewState.CONTENT);
        }


    }

    @Override
    public void onAlbumMediaReset() {
        mAdapter.swapCursor(null);
    }

    @Override
    public void onUpdate() {
        // notify outer Activity that check state changed
        if (mCheckStateListener != null) {
            mCheckStateListener.onUpdate();
        }
    }

    @Override
    public void onMediaClick(Album album, Item item, int adapterPosition) {
        if (mOnMediaClickListener != null) {
            mOnMediaClickListener.onMediaClick((Album) getArguments().getParcelable(EXTRA_ALBUM),
                    item, adapterPosition);
        }
    }

    public interface SelectionProvider {
        SelectedItemCollection provideSelectedItemCollection();
    }
}