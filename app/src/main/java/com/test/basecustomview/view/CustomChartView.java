package com.test.basecustomview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.test.basecustomview.R;
import com.test.basecustomview.pojo.ProfitChartInfo;
import com.test.basecustomview.utils.ChartUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 自定义表格
 **/
public class CustomChartView extends FrameLayout {

    private final LineChart lineChart;
    private Animation loadingAnimation;
    private Context mContext;
    private ImageView ivLoading;

    public CustomChartView(Context context) {
        this(context, null);
    }

    public CustomChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mInflater.inflate(R.layout.view_custom_chart, this);
        ivLoading = (ImageView) view.findViewById(R.id.ivLoading);
        lineChart = (LineChart) view.findViewById(R.id.lineChart);

        initChart();
    }

    private void initChart() {
        loadingAnimation = AnimationUtils.loadAnimation(mContext, R.anim.custom_loading);
        LinearInterpolator interpolator = new LinearInterpolator();
        loadingAnimation.setInterpolator(interpolator);
    }

    /**
     * 显示加载框
     */
    public void showLoading() {
        ivLoading.startAnimation(loadingAnimation);
    }

    /**
     * 隐藏加载框
     */
    public void hideLoading() {
        ivLoading.clearAnimation();
//        goneView(ivLoading);
    }

    /**
     * 画表格
     *
     * @param yValuesData Y轴数据
     * @param xValuesData X轴数据
     * @param lineColor   线的颜色
     */
    public void canvasChart(List<Entry> yValuesData, List<String> xValuesData, int lineColor) {
        ChartUtils.initChart(lineChart);
        ChartUtils.notifyDataSetChanged(lineChart, yValuesData, xValuesData, lineColor);
    }


    /**
     * 例子
     * 获取Y轴数据
     *
     * @param profitChartList
     * @return
     */
    public List<Entry> getYValuesData(List<ProfitChartInfo.ProfitChartListBean> profitChartList) {
        List<Entry> values = new ArrayList<>();
        for (int c = 0; c < profitChartList.size(); c++) {
            ProfitChartInfo.ProfitChartListBean bean = profitChartList.get(c);
            values.add(new Entry(bean.profitTotal, c));
        }
        return values;
    }

    /**
     * 例子
     * x轴数据处理
     *
     * @return x轴数据
     */
    public List<String> getXValuesData(List<ProfitChartInfo.ProfitChartListBean> profitChartList) {
        List<String> xValues = new ArrayList<>();
        for (ProfitChartInfo.ProfitChartListBean bean : profitChartList) {
            xValues.add(bean.dateTime);
        }
        return xValues;
    }

}
