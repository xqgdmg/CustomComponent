package com.test.basecustomview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.test.basecustomview.R;

/**
 * Created by Chris on 2017/5/26.
 * 滑动开关
 */
public class SwitchToggleView extends View {

    private Bitmap mSwitchBackground;
    private Bitmap mSwitchButton;
    private Paint mPaint;
    private boolean isClose = true;

    private OnSwitchListener mOnSwitchListener;

    public SwitchToggleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mSwitchBackground = BitmapFactory
                .decodeResource(getResources(), R.drawable.switch_background);
        mSwitchButton = BitmapFactory.decodeResource(getResources(), R.drawable.switch_button);

        setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //改变滑动开关的状态
                isClose = !isClose;
                //step 4: 如果外界有监听，通知外界发生开关事件
                if (mOnSwitchListener != null) {
                    mOnSwitchListener.onSwitch(isClose);
                }

                //触发重新绘制
                invalidate();
            }
        });


    }

    /**
     * 自己设置自己的宽高，不去使用父容器的期望
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //设置自己的宽高为背景图片的宽高
        setMeasuredDimension(mSwitchBackground.getWidth(), mSwitchBackground.getHeight());

    }

    /**
     * 父容器布局滑动开关（View 没有设置自己布局的功能）
     */
    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画背景图片
        canvas.drawBitmap(mSwitchBackground, 0, 0, mPaint);

        if (isClose ) {
            //画滑块
            canvas.drawBitmap(mSwitchButton, 0, 0, mPaint);
        } else {
            //如果滑动开关是打开的
            //画滑块
            int left = mSwitchBackground.getWidth() - mSwitchButton.getWidth();
            canvas.drawBitmap(mSwitchButton, left, 0, mPaint); // 左边多出
        }
    }

    //step1 定义接口
    public interface OnSwitchListener {
        //step2 是什么事件，声明什么回调函数
        void onSwitch(boolean isClose);
    }

    //step3 提供设置监听的函数，谁要来监听事件，谁就调用该方法
    public void setOnSwitchListener(OnSwitchListener l) {
        mOnSwitchListener = l;
    }

}

