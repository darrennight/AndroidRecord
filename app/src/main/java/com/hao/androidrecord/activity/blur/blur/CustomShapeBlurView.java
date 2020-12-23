package com.hao.androidrecord.activity.blur.blur;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;

import com.hao.androidrecord.util.ViewUtils;


/**
 * Created by mmin18 on 9/27/16.
 */
public class CustomShapeBlurView extends RealtimeBlurView {
	Paint mPaint;
	RectF mRectF;
	Paint shadowPaint;
	private int round;
	private int arrowHeight;

	public CustomShapeBlurView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mRectF = new RectF();
		round = ViewUtils.INSTANCE.dp2px(8);
		arrowHeight = ViewUtils.INSTANCE.dp2px(10);

		shadowPaint = new Paint();
		shadowPaint.setColor(Color.parseColor("#80F5F8F8"));
		shadowPaint.setShadowLayer(1f, 0, 0, Color.parseColor("#F5F8F8"));

	}

	/**
	 * Custom oval shape
	 */
	@Override
	protected void drawBlurredBitmap(Canvas canvas, Bitmap blurredBitmap, int overlayColor) {


		if (blurredBitmap != null) {
			mRectF.right = getMeasuredWidth();
			mRectF.bottom = getMeasuredHeight()-arrowHeight;

			//画矩形
			mPaint.reset();
			mPaint.setAntiAlias(true);
			BitmapShader shader = new BitmapShader(blurredBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
			Matrix matrix = new Matrix();
			matrix.postScale(mRectF.width() / blurredBitmap.getWidth(), (mRectF.height()+arrowHeight) / blurredBitmap.getHeight());
			shader.setLocalMatrix(matrix);
			mPaint.setShader(shader);
			canvas.drawRoundRect(mRectF,round,round,mPaint);



			//画三角形（这里是基于path路径的绘制）
			Path path = new Path();
			path.moveTo((mRectF.right/2)-(arrowHeight/2f), mRectF.bottom);
			path.lineTo((mRectF.right / 2), mRectF.bottom+(arrowHeight));
			path.lineTo((mRectF.right/2)+(arrowHeight/2f), mRectF.bottom);
			path.close();
			canvas.drawPath(path, mPaint);



			mPaint.reset();
			mPaint.setAntiAlias(true);
			mPaint.setColor(overlayColor);
			canvas.drawRoundRect(mRectF, round, round, mPaint);

//			path.moveTo((mRectF.right/2)-(arrowHeight/2f), mRectF.bottom);
//			path.lineTo((mRectF.right / 2), mRectF.bottom+(arrowHeight));
//			path.lineTo((mRectF.right/2)+(arrowHeight/2f), mRectF.bottom);
//			path.close();
			canvas.drawPath(path, mPaint);





			canvas.drawRoundRect(mRectF,round,round,shadowPaint);
//			path.moveTo((mRectF.right/2)-(arrowHeight/2f), mRectF.bottom);
//			path.lineTo((mRectF.right / 2), mRectF.bottom+(arrowHeight));
//			path.lineTo((mRectF.right/2)+(arrowHeight/2f), mRectF.bottom);
//			path.close();
			canvas.drawPath(path, shadowPaint);

		}
	}
}