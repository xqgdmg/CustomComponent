package com.test.basecustomview.utils;

import android.graphics.Color;
import android.graphics.Matrix;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.test.basecustomview.MyApplication;
import com.test.basecustomview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 图表工具
 * 这个已经不是最新的版本了
 **/
public class ChartUtils {


    /**
     * 初始化图表
     *
     * @param chart 原始图表
     * @return 初始化后的图表
     */
    public static LineChart initChart(LineChart chart) {

        chart.setDescription("");
        // 没有数据的时候，显示“暂无数据”
        chart.setNoDataText("暂无数据");
        // 可拖曳
        chart.setDragEnabled(true);
        // 不显示表格颜色
        chart.setDrawGridBackground(false);
        chart.setBackgroundColor(Color.parseColor("#ffffff"));
        chart.setDrawMarkerViews(true);
        chart.setTouchEnabled(true); // 设置是否可以触摸
        // 不可以缩放
        chart.setScaleEnabled(false);
        // 不显示y轴右边的值
        chart.getAxisRight().setEnabled(false);
        chart.setPinchZoom(true);

        // 不显示图例
        Legend legend = chart.getLegend();
        legend.setEnabled(false);
        // 向左偏移15dp，抵消y轴向右偏移的30dp
        chart.setExtraLeftOffset(-10);
        XAxis xAxis = chart.getXAxis();
        xAxis.setSpaceBetweenLabels(2);

        xAxis.setAvoidFirstLastClipping(true);
        // 不显示x轴
        xAxis.setDrawAxisLine(true);
        // 设置x轴数据的位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(10);
        xAxis.setGridColor(Color.parseColor("#d8d8d8"));
        // 设置x轴数据偏移量
        xAxis.setYOffset(-12);

        YAxis yAxis = chart.getAxisLeft();
        yAxis.setValueFormatter(new CustomYAxisValueFormatter());
        // 不显示y轴
        yAxis.setDrawAxisLine(false);
        // 设置y轴数据的位置
        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        // 不从y轴发出横向直线
        yAxis.setDrawGridLines(true);
        yAxis.setTextColor(Color.BLACK);
        yAxis.setTextSize(12);
        // 设置y轴数据偏移量
        yAxis.setXOffset(20);
        yAxis.setYOffset(-3);
        yAxis.setAxisMinValue(0);

        chart.invalidate();
        return chart;
    }

    private static int getColorRes(int colorResId) {
        return MyApplication.getInstance().getResources().getColor(colorResId);
    }

    /**
     * 设置图表数据
     *
     * @param chart     图表
     * @param yValues   数据
     * @param lineColor 折线颜色
     */
    public static void setChartData(LineChart chart, List<Entry> yValues, List<String> xValues, int lineColor) {
        LineDataSet lineDataSet;

        if (xValues.size() > 5) {
            Matrix matrix = new Matrix();
            //x轴缩放1.5倍
            matrix.postScale(2.5f, 1f);
            //在图表动画显示之前进行缩放
            chart.getViewPortHandler().refresh(matrix, chart, false);
            //x轴执行动画
            chart.animateX(1500);
            chart.moveViewToX(0);
        }

        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
            lineDataSet = (LineDataSet) chart.getData().getDataSetByIndex(0);
            lineDataSet.setYVals(yValues);
            chart.getLineData().setXVals(xValues);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            lineDataSet = new LineDataSet(yValues, "");
            // 设置曲线颜色
            lineDataSet.setColor(getColorRes(lineColor));
            // 设置折线
            lineDataSet.setMode(LineDataSet.Mode.LINEAR);
            lineDataSet.setCircleColor(getColorRes(lineColor));
            lineDataSet.setLineWidth(2);
            lineDataSet.setCircleRadius(4);
            // 不显示坐标点的小圆点
            lineDataSet.setDrawCircles(true);
            // 显示坐标点的数据
            lineDataSet.setDrawValues(true);
            lineDataSet.setValueTextSize(10f);
            lineDataSet.setValueTextColor(Color.parseColor("#ff0000"));
            lineDataSet.setValueFormatter(new CustomValueFormatter());
            // 不显示定位线
            lineDataSet.setHighlightEnabled(false);

            LineData data = new LineData(xValues, lineDataSet);
            chart.setData(data);
            chart.invalidate();
        }
    }

    /**
     * 更新图表
     *
     * @param chart
     * @param YValues 纵坐标
     * @param xValues 横轴
     */
    public static void notifyDataSetChanged(LineChart chart, List<Entry> YValues, List<String> xValues) {
        notifyDataSetChanged(chart, YValues, xValues, R.color.blue);
    }

    /**
     * 更新图表
     *
     * @param chart
     * @param YValues   纵坐标
     * @param xValues   横轴
     * @param lineColor 折线颜色
     */
    public static void notifyDataSetChanged(LineChart chart, List<Entry> YValues, List<String> xValues, int lineColor) {
        chart.invalidate();
        setChartData(chart, YValues, xValues, lineColor);
    }

    private static List<Entry> getYData() {
        List<Entry> values = new ArrayList<>();
        values.add(new Entry(10, 0));
        values.add(new Entry(100, 1));
        values.add(new Entry(222, 2));
        values.add(new Entry(34, 3));
        values.add(new Entry(400, 4));
        values.add(new Entry(500, 5));
        values.add(new Entry(1000, 6));
        values.add(new Entry(800, 7));
        values.add(new Entry(900, 8));
        values.add(new Entry(900.5f, 9));
        values.add(new Entry(850.5f, 10));
        return values;
    }

    private static List<String> getXData() {
        List<String> values = new ArrayList<>();
        values.add("2016.07");
        values.add("2016.08");
        values.add("2016.09");
        values.add("2016.10");
        values.add("2016.11");
        values.add("2016.12");
        values.add("2017.01");
        values.add("2017.02");
        values.add("2017.03");
        values.add("2017.04");
        values.add("2017.05");
        return values;
    }


}
