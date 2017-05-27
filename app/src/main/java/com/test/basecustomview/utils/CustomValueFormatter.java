package com.test.basecustomview.utils;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

/**
 * 自定义表格数据格式化
 * 格式化图表上显示的 Y 轴数据
 **/
public class CustomValueFormatter implements ValueFormatter {

    protected DecimalFormat mFormat;

     // 最多显示4位小数
    public CustomValueFormatter() {
        mFormat = new DecimalFormat("0.####");
    }

     // 每个值后面加个 π
    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return String.format("%sπ", mFormat.format(entry.getVal()));
    }
}
