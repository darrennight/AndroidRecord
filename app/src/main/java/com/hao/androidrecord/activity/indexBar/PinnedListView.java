package com.hao.androidrecord.activity.indexBar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;


public class PinnedListView extends ListView {

	public interface ICover {
		public boolean isCover();

		public String getCoverText();
	}

	private OnScrollListener mOnScrollListener;

	private TextView mCover;

	private TipScrollBar mScrollBar;

	public PinnedListView(Context context) {
		this(context, null);
	}

	public PinnedListView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PinnedListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		init(context);
	}

	public void setCoverTextView(TextView cover) {
		this.mCover = cover;
	}

	public void setTipScrollBar(TipScrollBar scrollBar) {
		mScrollBar = scrollBar;

		if (mScrollBar != null) {
			mScrollBar.setOnScrollBarChangeListener(new TipScrollBar.OnScrollBarChangeListener() {

				@Override
				public void onScrollChanged(int percent) {
					float per = percent * 1.0f / 100;
					if (getAdapter().getCount() > 0) {
						int position = (int) ((getAdapter().getCount() - 1) * per);
						setSelection(position);

						Object o = getAdapter().getItem(position);
						if (o instanceof ICover) {

							ICover cover = (ICover) o;
							mScrollBar.setTipLetter(cover.getCoverText());
						}
					}
				}
			});
		}
	}

	private void init(Context context) {
		super.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if (mOnScrollListener != null) {
					mOnScrollListener.onScrollStateChanged(view, scrollState);
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				if (mOnScrollListener != null) {
					mOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
				}

				if (mCover != null) {

					int position = pointToPosition(0, mCover.getHeight());
					if (position != ListView.INVALID_POSITION) {
						if (getAdapter().getItem(position) instanceof ICover) {

							ICover item = (ICover) getAdapter().getItem(position);
							if (item.isCover()) {
								View currView = getChildAt(position - firstVisibleItem);
								final int topMargin = currView.getTop();
								FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mCover.getLayoutParams();
								params.topMargin = topMargin - mCover.getHeight();
								mCover.setLayoutParams(params);
								if (position == 0) {
									// 初始化时，第一个
									mCover.setText(((ICover) getAdapter().getItem(position)).getCoverText());
								} else {
									// 替换的时候
									mCover.setText(((ICover) getAdapter().getItem(position - 1)).getCoverText());
								}
							} else {
								FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mCover.getLayoutParams();
								params.topMargin = 0;
								mCover.setLayoutParams(params);
								mCover.setText(((ICover) getAdapter().getItem(position)).getCoverText());
							}
						}
					}
				}

			}
		});
	}

	@Override
	public void setOnScrollListener(OnScrollListener l) {
		this.mOnScrollListener = l;
	}

	@Override
	public void computeScroll() {
		if (mScrollBar != null && !mScrollBar.isTouchMode()) {
			int percent = (int) (computeVerticalScrollOffset() * 100.0f / (computeVerticalScrollRange() - computeVerticalScrollExtent()));
			mScrollBar.setScrollBarPosition(percent);
		}

		super.computeScroll();
	}
}
