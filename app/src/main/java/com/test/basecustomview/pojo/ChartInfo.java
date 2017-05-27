package com.test.basecustomview.pojo;

import java.util.List;

/**
 * Created by Chris on 2017/5/27.
 */
public class ChartInfo {
    private String xValue;
    private float yValue;

    public ChartInfo(String xValue, float yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    public String getxValue() {
        return xValue;
    }

    public void setxValue(String xValue) {
        this.xValue = xValue;
    }

    public float getyValue() {
        return yValue;
    }

    public void setyValue(float yValue) {
        this.yValue = yValue;
    }
}
