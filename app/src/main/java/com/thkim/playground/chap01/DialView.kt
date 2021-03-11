package com.thkim.playground.chap01

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.thkim.playground.R
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

/**
 * Created by Taehyung Kim on 2021-03-11
 * @JvmOverloads : The Kotlin compiler to generate overloads for this function that substitute default parameter values.
 */
class DialView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // current radius of the circle.
    private var radius = 0.0f                   // Radius of the circle.

    // current speed of the fan.
    private var fanSpeed = FanSpeed.OFF         // The active selection.

    // position variable which will be used to draw label and indicator circle position
    // X, Y.
    private val pointPosition: PointF = PointF(0.0f, 0.0f)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.create("", Typeface.BOLD)
    }

    /**
     * The onSizeChanged() method is called any time the view's size changes,
     * including the first time it is drawn when the layout is inflated.
     * Override onSizeChanged() to calculate positions, dimensions, and any other values
     * related to your custom view's size,
     * instead of recalculating them every time you draw.
     * In this case you use onSizeChanged() to calculate the current radius of the dial's circle element.
     */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = (min(width, height) / 2.0 * 0.8).toFloat()
    }

    /**
     * calculates the X, Y coordinates on the screen
     * for the text label and current indicator (0, 1, 2, or 3),
     * given the current FanSpeed position and radius of the dial.
     */
    private fun PointF.computeXYForSpeed(pos: FanSpeed, radius: Float) {
        // Angles are in radians.
        val startAngle = Math.PI * (9 / 8.0)
        val angle = startAngle + pos.ordinal * (Math.PI / 4)
        x = (radius * cos(angle)).toFloat() + width / 2
        y = (radius * sin(angle)).toFloat() + height / 2
    }

    /**
     * render the view on the screen with the Canvas and Paint classes.
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Set dial background color to green if selection not off.
        paint.color = if (fanSpeed == FanSpeed.OFF) Color.GRAY else Color.GREEN

        // Draw the dial.
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, paint)

        // Draw the indicator circle.
        val markerRadius = radius + RADIUS_OFFSET_INDICATOR
        pointPosition.computeXYForSpeed(fanSpeed, markerRadius)
        paint.color = Color.BLACK
        canvas.drawCircle(pointPosition.x, pointPosition.y, radius / 12, paint)

        // Draw the text labels.
        val labelRadius = radius + RADIUS_OFFSET_LABEL
        for (i in FanSpeed.values()) {
            pointPosition.computeXYForSpeed(i, labelRadius)
            val label = resources.getString(i.label)
            canvas.drawText(label, pointPosition.x, pointPosition.y, paint)
        }
    }
}

/**
 * to represent the available fan speeds.
 */
private enum class FanSpeed(val label: Int) {
    OFF(R.string.fan_off),
    LOW(R.string.fan_low),
    MEDIUM(R.string.fan_medium),
    HIGH(R.string.fan_high);
}

/**
 * You'll use these as part of drawing the dial indicators and labels.
 */
private const val RADIUS_OFFSET_LABEL = 30
private const val RADIUS_OFFSET_INDICATOR = -35