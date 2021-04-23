package com.hao.androidrecord.activity.indexBar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.hao.androidrecord.R;


public class TipScrollBar extends FrameLayout {

	public interface OnScrollBarChangeListener {

		public void onScrollChanged(int percent);
	}

	private View mBar;
	private ImageView mScrollBar;
	private TextView mScrollTip;

	private int minScrollPosition, maxScrollPosition;

	private int mScrollBarLeft, mScrollBarTop, mScrollBarRight;
	private int mScrollBarHeight;

	private int mScrollTipLeft, mScrollTipRight, mScrollTipBottom;
	private int mScrollTipHeight;

	private int downPosition;

	private boolean isTouchMode = false;

	private OnScrollBarChangeListener mOnScrollBarChangeListener;

	public TipScrollBar(Context context) {
		this(context, null);
	}

	public TipScrollBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TipScrollBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		init(context);
	}

	/**
	 * 设置提示字母
	 * 
	 * @param letter
	 */
	public void setTipLetter(String letter) {
		if (letter != null && letter.length() == 1) {
			mScrollTip.setText(letter);
		}
	}

	public void setScrollBarPosition(int percent) {
		int deltaY = (int) ((maxScrollPosition - minScrollPosition) * percent * 1.0f / 100);
		mScrollBarTop = minScrollPosition + deltaY;

		layoutViews();
	}

	private void layoutViews() {
		if (mScrollBarTop < minScrollPosition) {
			mScrollBarTop = minScrollPosition;
		} else if (mScrollBarTop + mScrollBarHeight > maxScrollPosition) {
			mScrollBarTop = maxScrollPosition - mScrollBarHeight;
		}

		mScrollBar.layout(mScrollBarLeft, mScrollBarTop, mScrollBarRight, mScrollBarTop + mScrollBarHeight);

		mScrollTipBottom = mScrollBarTop + mScrollBarHeight;
		mScrollTip.layout(mScrollTipLeft, mScrollTipBottom - mScrollTipHeight, mScrollTipRight, mScrollTipBottom);
	}
	@SuppressLint("ClickableViewAccessibility")
	private void init(Context context) {
		inflate(context, R.layout.country_scroll_bar, this);

		mBar = findViewById(R.id.v_scroll_bar);
		mScrollBar = (ImageView) findViewById(R.id.iv_scroll_bar);
		mScrollTip = (TextView) findViewById(R.id.tv_scroll_tip);

		mScrollBar.setOnTouchListener(new OnTouchListener() {


			@Override
			public boolean onTouch(View v, MotionEvent event) {

				int y = (int) event.getY();
				int deltaY = 0;

				switch (event.getAction() & MotionEvent.ACTION_MASK) {
				case MotionEvent.ACTION_DOWN:

					downPosition = y;
					mScrollTip.setVisibility(View.VISIBLE);

					isTouchMode = true;

					break;

				case MotionEvent.ACTION_MOVE:
					deltaY = y - downPosition;

					mScrollBarTop += deltaY;
					layoutViews();

					if (mOnScrollBarChangeListener != null) {
						int length = maxScrollPosition - minScrollPosition - mScrollBarHeight;
						int percent = (int) ((mScrollBarTop - minScrollPosition) * 100.0f / length);
						mOnScrollBarChangeListener.onScrollChanged(percent);
					}

					break;
				case MotionEvent.ACTION_CANCEL:
				case MotionEvent.ACTION_UP:
					downPosition = 0;

					mScrollTip.setVisibility(View.INVISIBLE);

					isTouchMode = false;
					break;
				default:
					break;
				}
				return true;
			}
		});

	}

	public OnScrollBarChangeListener getOnScrollBarChangeListener() {
		return mOnScrollBarChangeListener;
	}

	public void setOnScrollBarChangeListener(OnScrollBarChangeListener onScrollBarChangeListener) {
		this.mOnScrollBarChangeListener = onScrollBarChangeListener;
	}

	public boolean isTouchMode() {
		return isTouchMode;
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		minScrollPosition = mBar.getTop();
		maxScrollPosition = mBar.getBottom();

		mScrollBarLeft = mScrollBar.getLeft();
		mScrollBarTop = mScrollBar.getTop();
		mScrollBarRight = mScrollBar.getRight();
		mScrollBarHeight = mScrollBar.getHeight();

		mScrollTipLeft = mScrollTip.getLeft();
		mScrollTipRight = mScrollTip.getRight();
		mScrollTipHeight = mScrollTip.getHeight();

		mScrollTipBottom = mScrollBar.getBottom();
		mScrollTip.layout(mScrollTipLeft, mScrollTipBottom - mScrollTipHeight, mScrollTipRight, mScrollTipBottom);

	}
}
