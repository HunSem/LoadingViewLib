package com.huan.percy.loadingviewlib.view;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by Percy on 2017/3/11.
 */

public class SimleView extends BaseView {

    private Paint mPaint;

    private float mWidth = 0f;
    private float mEyeWidth = 0f;

    private float mPadding = 0f;
    private float startAngle = 0f;
    private boolean isSmile = false;
    RectF mRectF = new RectF();

    public SimleView(Context context) {
        super(context);
    }

    public SimleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SimleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SimleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (getMeasuredWidth() > getHeight()) {
            mWidth = getMeasuredHeight();
        } else {
            mWidth = getMeasuredWidth();
        }
        mPadding = dip2px(10);
        mEyeWidth = dip2px(8);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mRectF = new RectF(mPadding, mPadding, mWidth - mPadding, mWidth - mPadding);
        mPaint.setStyle(Paint.Style.STROKE);

        canvas.drawArc(mRectF, startAngle, 180, false, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        if (isSmile) {
            canvas.drawCircle(mPadding + mEyeWidth + mEyeWidth / 2, mWidth / 3, mEyeWidth, mPaint);
            canvas.drawCircle(mWidth - mPadding - mEyeWidth - mEyeWidth / 2, mWidth / 3, mEyeWidth, mPaint);
        }

    }

    @Override
    protected void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(dip2px(8));

    }

    public void setViewColor(int color) {
        mPaint.setColor(color);
        postInvalidate();
    }


    float mAnimationValue = 0f;

    @Override
    protected void OnAnimationUpdate(ValueAnimator valueAnimator) {
        mAnimationValue = (float) valueAnimator.getAnimatedValue();
        if(mAnimationValue < 0.5) {
            isSmile = false;
            startAngle = 720 * mAnimationValue;
        } else {
            startAngle = 720;
            isSmile = true;
        }

        invalidate();
    }

    @Override
    protected void OnAnimationRepeat(Animator animator) {

    }

    @Override
    protected int OnStopAnim() {
        isSmile = false;
        mAnimationValue = 0f;
        startAngle = 0f;
        return 0;
    }

    @Override
    protected int SetAnimRepeatCount() {
        return ValueAnimator.INFINITE;
    }

    @Override
    protected int SetAnimRepeatMode() {
        return ValueAnimator.RESTART;
    }

    @Override
    protected void AnimIsRunning() {

    }
}
