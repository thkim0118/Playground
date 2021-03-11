package com.thkim.playground.chap01

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.accessibility.AccessibilityNodeInfo
import androidx.core.content.withStyledAttributes
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
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

    private var fanSpeedLowColor = 0
    private var fanSpeedMediumColor = 0
    private var fanSpeedMaxColor = 0

    init {
        // Setting the view's isClickable property to true enables that view to accept user input.
        isClickable = true

        context.withStyledAttributes(attrs, R.styleable.DialView) {
            fanSpeedLowColor = getColor(R.styleable.DialView_fanColor1, 0)
            fanSpeedMediumColor = getColor(R.styleable.DialView_fanColor2, 0)
            fanSpeedMaxColor = getColor(R.styleable.DialView_fanColor3, 0)
        }

        updateContentDescription()

        // This strategy enables the greatest amount of backward compatibility in your app.
        // Notice now that the phrase "Double-tap to activate" is now
        // either "Double-tap to change" (if the fan speed is less than high or 3) or
        // "Double-tap to reset" (if the fan speed is already at high or 3).
        // Note that the prompt "Double-tap to..." is supplied by the TalkBack service itself.
        ViewCompat.setAccessibilityDelegate(this, object : AccessibilityDelegateCompat() {
            override fun onInitializeAccessibilityNodeInfo(
                host: View,
                info: AccessibilityNodeInfoCompat
            ) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                val customClick = AccessibilityNodeInfoCompat.AccessibilityActionCompat(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    context.getString(if (fanSpeed != FanSpeed.HIGH) R.string.change else R.string.reset)
                )
                info.addAction(customClick)
            }
        })

    }

    override fun performClick(): Boolean {
        // The call to super.performClick() must happen first,
        // which enables accessibility events as well as calls onClickListener().
        if (super.performClick()) return true

        fanSpeed = fanSpeed.next()
//        contentDescription = resources.getString(fanSpeed.label)
        updateContentDescription() // contentDescription -> TalkBack.

        invalidate() // This tells the Android system to call the onDraw() method to redraw the view.
        return true
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
//        paint.color = if (fanSpeed == FanSpeed.OFF) Color.GRAY else Color.GREEN
        paint.color = when (fanSpeed) {
            FanSpeed.OFF -> Color.GRAY
            FanSpeed.LOW -> fanSpeedLowColor
            FanSpeed.MEDIUM -> fanSpeedMediumColor
            FanSpeed.HIGH -> fanSpeedMaxColor
        }


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

    fun updateContentDescription() {
        contentDescription = resources.getString(fanSpeed.label)
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

    fun next() = when (this) {
        OFF -> LOW
        LOW -> MEDIUM
        MEDIUM -> HIGH
        HIGH -> OFF
    }
}

/**
 * You'll use these as part of drawing the dial indicators and labels.
 */
private const val RADIUS_OFFSET_LABEL = 30
private const val RADIUS_OFFSET_INDICATOR = -35