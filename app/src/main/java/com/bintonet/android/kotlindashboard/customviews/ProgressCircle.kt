package com.bintonet.android.kotlindashboard.customviews

import android.animation.ValueAnimator
import android.content.Context
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
 * CustomView for drawing the circle for the score
 */

class ProgressCircle(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val mOval = RectF()
    private var mSweepAngle = 0f
    private var mEndAngle = 1.0f

    private var shaderCx: Float = 0.toFloat()
    private var shaderCy: Float = 0.toFloat()
    private var shaderColor0 = Color.YELLOW
    private var shaderColor1 = Color.RED

    private var progressPaint = Paint()

    private var strokeWidth = 4.0f

    init {

        // here we load any custom attrs set in the layout xml
        val a = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.ProgressCircle,
                0, 0)

        try {
            //we store them here as variables for use later
            strokeWidth = a.getDimension(R.styleable.ProgressCircle_strokeWidth, 4.0f)
        } finally {
            a.recycle()
        }

        // building the paint object for the circle
        progressPaint.strokeWidth = strokeWidth
        progressPaint.style = Paint.Style.STROKE
        progressPaint.flags = Paint.ANTI_ALIAS_FLAG
        progressPaint.shader = SweepGradient(shaderCx, shaderCy, shaderColor0, shaderColor1)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // set some dimensions for the circle (width and height of the canvas)
        val w: Float = width.toFloat()
        val h: Float = height.toFloat()

        //these are used for the colour gradient
        shaderCx = w / 2
        shaderCy = h / 2

        //now we set the Rectf coordinates
        mOval.set(strokeWidth / 2, strokeWidth / 2, w - strokeWidth / 2, w - strokeWidth / 2)

        //draw the circle now
        // startAngle : I am not sure why but 0 is at the right of the screen, so 270 is starting at the top
        // sweepangle, how far round we want the arc to go
        // usecenter, do we want the arc to go to the centre of the circle, if yes then the result would look like a pie chart
        // finally the paint we want to use, in our case the progress paint we defined earlier
        canvas.drawArc(mOval, 270F, mSweepAngle * 360, false, progressPaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }


    // function we call from the mainview to set how far round the arc should go
    fun setProgress(progress: Float) {
        if (progress > 1.0f || progress < 0) {
            throw RuntimeException("Value must be between 0 and 1: " + progress)
        }
        mEndAngle = progress

        this.invalidate()
    }

    // simple animation function to be called after we set the progress to redraw the arc
    //  but have it draw over a set amount of time
    fun startAnimation() {
        val anim = ValueAnimator.ofFloat(mSweepAngle, mEndAngle)
        anim.addUpdateListener { valueAnimator ->
            this@ProgressCircle.mSweepAngle = valueAnimator.animatedValue as Float
            this@ProgressCircle.invalidate()
        }
        anim.duration = 300
        anim.interpolator = AccelerateDecelerateInterpolator()
        anim.start()

    }

}

