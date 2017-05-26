package com.test.basecustomview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Chris on 2017/5/26.
 * 交叉布局
 */
public class SwitchLayout extends ViewGroup{
    private boolean isStartLeft = false;

    public SwitchLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     *  测量孩子
     * */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec); // sdk 中的方法
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     *  给孩子设置布局
     * */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        //遍历每个孩子
        int top = 0;

        for (int i = 0; i < getChildCount(); i ++) {
            View child = getChildAt(i);
            //布局孩子//将四个孩子布局在一起
            int left = 0;

            if (isStartLeft ) {
                if (i % 2 == 0) {
                    //孩子摆左边
                    left = 0;
                } else {
                    //i是奇数
                    //孩子摆右边
                    left = getMeasuredWidth() - child.getMeasuredWidth();
                }
            } else {
                //从右边开始摆
                if (i % 2 == 0) {
                    //孩子摆右边
                    left = getMeasuredWidth() - child.getMeasuredWidth();
                } else {
                    //i是奇数
                    //孩子摆左边
                    left = 0;
                }
            }

            int right = left + child.getMeasuredWidth();
            int bottom = top + child.getMeasuredHeight();
            child.layout(left, top, right, bottom); // View 中的方法
            //更新top,布局下一个孩子
            top += child.getMeasuredHeight();
        }

    }

    /**
     * 实现布局的切换
     */
    public void revert() {
        isStartLeft = !isStartLeft;

//		invalidate();//触发重新绘制
        requestLayout();//请求重新布局 onLayout
    }
}
