package com.test.mylibrary.new_utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 圆形进度条
 *
 * @author zyf
 */
public class OrderChargeCircleProgressBar extends View {

    /**
     * 底层圆形颜色
     */
    private int bottomCircleColor = 0xff9B9B9B;
    /**
     * 上层圆形颜色
     */
    private int topCircleColor = 0xff28C650;
    /**
     * View的尺寸
     */
    private int measuredHeight = 0;
    private int measuredWidth = 0;

    /**
     * 画笔
     */
    private Paint paint;
    /**
     * 进度
     */
    private int progress;

    /**
     * 最大进度
     */
    private int max = 1000;
    /**
     * 画圆的区域
     */
    private RectF rectF;


    public void setMax(int max) {
        this.max = max;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public OrderChargeCircleProgressBar(Context context) {
        super(context);
        init(context);
    }

    public OrderChargeCircleProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public OrderChargeCircleProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context) {
        paint = new Paint();
        paint.setAntiAlias(true);
        rectF = new RectF(0, 0, 0, 0);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measuredHeight = getMeasuredHeight();
        measuredWidth = getMeasuredWidth();
        rectF.left = 5;
        rectF.top = 5;
        rectF.right = measuredWidth - 5;
        rectF.bottom = measuredHeight - 5;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //底层圆
        paint.setColor(bottomCircleColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.drawArc(rectF, 0, 360, false, paint);
        //画进度圆
        paint.setColor(topCircleColor);
        canvas.drawArc(rectF, 0, progress * 360 / max, false, paint);
    }
}
