package com.bintonet.android.kotlindashboard.customviews;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.bintonet.android.kotlindashboard.R;

/**
 * CustomView for drawing the score in the form of a progress ring
 */

public class ProgressCircle extends View {

    private final RectF mOval = new RectF();
    private float mSweepAngle = 0;
    private int startAngle = 90;

    float mEndAngle = 1.0f;

    float shaderCx, shaderCy;
    int shaderColor0 = Color.YELLOW;
    int shaderColor1 = Color.RED;

    Paint progressPaint = new Paint();

    Paint incompletePaint = new Paint();

    private float strokeWidth = 4.0f;

    public ProgressCircle(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ProgressCircle,
                0, 0);

        int incompleteColor;

        try {

            strokeWidth = a.getDimension(R.styleable.ProgressCircle_strokeWidth, 15.0f);
            incompleteColor = a.getColor(R.styleable.ProgressCircle_incompleteProgressColor, 0xff000000);
        } finally {
            a.recycle();
        }

        progressPaint.setStrokeWidth(strokeWidth);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setShader(new SweepGradient(shaderCx, shaderCy, shaderColor0, shaderColor1));

        incompletePaint.setColor(incompleteColor);
        incompletePaint.setStrokeWidth(strokeWidth);
        incompletePaint.setStyle(Paint.Style.STROKE);
        incompletePaint.setFlags(Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float w, h;

        w = getWidth();
        h = getHeight();
        shaderCx = w/2;
        shaderCy = h/2;

        mOval.set(strokeWidth / 2, strokeWidth / 2, w - (strokeWidth / 2), w - (strokeWidth / 2));
        canvas.drawArc(mOval, -startAngle, (mSweepAngle * 360), false,
                progressPaint);

        mOval.set(strokeWidth / 2, strokeWidth / 2, w - (strokeWidth / 2), w - (strokeWidth / 2));
        canvas.drawArc(mOval, mSweepAngle * 360- startAngle, 360 - (mSweepAngle * 360), false,
                incompletePaint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }


    public void setProgress(float progress) {
        if (progress > 1.0f || progress < 0) {
            throw new RuntimeException("Value must be between 0 and 1: " + progress);
        }

        mEndAngle = progress;

        this.invalidate();
    }

    public void startAnimation() {
        ValueAnimator anim = ValueAnimator.ofFloat(mSweepAngle, mEndAngle);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ProgressCircle.this.mSweepAngle = (Float) valueAnimator.getAnimatedValue();
                ProgressCircle.this.invalidate();
            }
        });
        anim.setDuration(500);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.start();

    }

}

