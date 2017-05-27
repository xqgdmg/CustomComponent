package com.test.basecustomview.utils;

/**
 * Created by Chris on 2017/5/27.
 */

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;

import java.text.DecimalFormat;

/**
 * 自定义表格数据格式化
 **/
public class CustomYAxisValueFormatter implements YAxisValueFormatter {


    protected DecimalFormat mFormat;

    public CustomYAxisValueFormatter() {
        mFormat = new DecimalFormat("0.####");
    }

    @Override
    public String getFormattedValue(float value, YAxis yAxis) {
        return String.format("%s", mFormat.format(value));
    }
}
