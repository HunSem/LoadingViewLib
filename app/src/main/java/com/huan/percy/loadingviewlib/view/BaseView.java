package com.huan.percy.loadingviewlib.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by Percy on 2017/3/11.
 */

public abstract class BaseView extends View {
    public BaseView(Context context) {
        super(context);
        initPaint();
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }


    protected abstract void initPaint();

    protected abstract void OnAnimationUpdate(ValueAnimator valueAnimator);

    protected abstract void OnAnimationRepeat(Animator animator);

    protected abstract int OnStopAnim();

    protected abstract int SetAnimRepeatCount();

    protected abstract int SetAnimRepeatMode();

    protected abstract void AnimIsRunning();

    public ValueAnimator mValueAnimator;

    public void startAnim() {
        stopAnim();
        startViewAnim(0f, 1f, 1200);
    }

    public void startAnim(int time) {
        stopAnim();
        startViewAnim(0f, 1f, time);
    }

    public void stopAnim() {
        if (mValueAnimator != null) {
            clearAnimation();

            mValueAnimator.setRepeatCount(0);
            mValueAnimator.cancel();
            mValueAnimator.end();
            if (OnStopAnim() == 0) {
                mValueAnimator.setRepeatCount(0);
                mValueAnimator.cancel();
                mValueAnimator.end();
            }
        }
    }

    public ValueAnimator startViewAnim(float startF, final float endF, long time) {
        mValueAnimator = ValueAnimator.ofFloat(startF, endF);
        mValueAnimator.setDuration(time);
        mValueAnimator.setInterpolator(new LinearInterpolator());

        mValueAnimator.setRepeatCount(SetAnimRepeatCount());

        if(ValueAnimator.RESTART == SetAnimRepeatMode()) {
            mValueAnimator.setRepeatMode(ValueAnimator.RESTART);
        } else if (ValueAnimator.REVERSE == SetAnimRepeatMode()) {
            mValueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        }

        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                OnAnimationUpdate(animation);
            }
        });

        mValueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
                OnAnimationRepeat(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
       if(!mValueAnimator.isRunning()) {
           AnimIsRunning();
           mValueAnimator.start();
       }

       return mValueAnimator;
    }

    public int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public float getFontWidth(Paint paint, String str) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.width();
    }

    public float getFontHeight(Paint paint, String str) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.height();
    }

    public float getFontHeight(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.descent - fm.ascent;
    }
}
