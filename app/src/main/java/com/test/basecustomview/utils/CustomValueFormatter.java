package com.test.basecustomview.utils;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

/**
 * 自定义表格数据格式化
 **/
public class CustomValueFormatter implements ValueFormatter {

    protected DecimalFormat mFormat;

    public CustomValueFormatter() {
        mFormat = new DecimalFormat("0.####");
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return String.format("%sπ", mFormat.format(entry.getVal()));
    }
}
