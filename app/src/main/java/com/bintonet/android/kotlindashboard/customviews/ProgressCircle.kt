package com.bintonet.android.kotlindashboard.customviews

import android.animation.ValueAnimator
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.SweepGradient
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

import com.bintonet.android.kotlindashboard.R

/**
 * CustomView for drawing the score in the form of a progress ring
 */

class ProgressCircle(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val mOval = RectF()
    private var mSweepAngle = 0f
    private val startAngle = 90

    internal var mEndAngle = 1.0f

    internal var shaderCx: Float = 0.toFloat()
    internal var shaderCy: Float = 0.toFloat()
    internal var shaderColor0 = Color.YELLOW
    internal var shaderColor1 = Color.RED

    internal var progressPaint = Paint()

    internal var incompletePaint = Paint()

    private var strokeWidth = 4.0f

    init {

        val a = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.ProgressCircle,
                0, 0)

        val incompleteColor: Int

        try {

            strokeWidth = a.getDimension(R.styleable.ProgressCircle_strokeWidth, 15.0f)
            incompleteColor = a.getColor(R.styleable.ProgressCircle_incompleteProgressColor, -0x1000000)
        } finally {
            a.recycle()
        }

        progressPaint.strokeWidth = strokeWidth
        progressPaint.style = Paint.Style.STROKE
        progressPaint.flags = Paint.ANTI_ALIAS_FLAG
        progressPaint.shader = SweepGradient(shaderCx, shaderCy, shaderColor0, shaderColor1)

        incompletePaint.color = incompleteColor
        incompletePaint.strokeWidth = strokeWidth
        incompletePaint.style = Paint.Style.STROKE
        incompletePaint.flags = Paint.ANTI_ALIAS_FLAG

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val w: Float
        val h: Float

        w = width.toFloat()
        h = height.toFloat()
        shaderCx = w / 2
        shaderCy = h / 2

        mOval.set(strokeWidth / 2, strokeWidth / 2, w - strokeWidth / 2, w - strokeWidth / 2)
        canvas.drawArc(mOval, (-startAngle).toFloat(), mSweepAngle * 360, false,
                progressPaint)

        mOval.set(strokeWidth / 2, strokeWidth / 2, w - strokeWidth / 2, w - strokeWidth / 2)
        canvas.drawArc(mOval, mSweepAngle * 360 - startAngle, 360 - mSweepAngle * 360, false,
                incompletePaint)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }


    fun setProgress(progress: Float) {
        if (progress > 1.0f || progress < 0) {
            throw RuntimeException("Value must be between 0 and 1: " + progress)
        }

        mEndAngle = progress

        this.invalidate()
    }

    fun startAnimation() {
        val anim = ValueAnimator.ofFloat(mSweepAngle, mEndAngle)
        anim.addUpdateListener { valueAnimator ->
            this@ProgressCircle.mSweepAngle = valueAnimator.animatedValue as Float
            this@ProgressCircle.invalidate()
        }
        anim.duration = 500
        anim.interpolator = AccelerateDecelerateInterpolator()
        anim.start()

    }

}

