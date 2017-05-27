package com.test.basecustomview.pojo;

import java.util.List;

/**
 * Created by Chris on 2017/5/27.
 */
public class ChartInfo {
    private float xValue;
    private float yValue;

    public ChartInfo(float xValue, float yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    public float getxValue() {
        return xValue;
    }

    public void setxValue(float xValue) {
        this.xValue = xValue;
    }

    public float getyValue() {
        return yValue;
    }

    public void setyValue(float yValue) {
        this.yValue = yValue;
    }
}
