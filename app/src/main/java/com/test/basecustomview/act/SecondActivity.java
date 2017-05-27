package com.test.basecustomview.act;

import android.app.Activity;
import android.os.Bundle;

import com.github.mikephil.charting.data.Entry;
import com.test.basecustomview.R;
import com.test.basecustomview.pojo.ChartInfo;
import com.test.basecustomview.view.CustomChartView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 2017/5/26.
 * MpAndroidChart 绘制表格
 */
public class SecondActivity extends Activity{

    private CustomChartView customChatView;
    private ArrayList<ChartInfo> chartData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initView();

         // 模拟数据
        mockData();

        // 图标数据
        showChart(chartData);

    }

    private void mockData() {
        chartData = new ArrayList<ChartInfo>();
        ChartInfo chartInfo1 = new ChartInfo(1.0f,11.00f);
        chartData.add(chartInfo1);
        ChartInfo chartInfo2 = new ChartInfo(2.0f,12.00f);
        chartData.add(chartInfo2);
        ChartInfo chartInfo3 = new ChartInfo(3.0f,13.00f);
        chartData.add(chartInfo3);
        ChartInfo chartInfo4 = new ChartInfo(4.0f,14.00f);
        chartData.add(chartInfo4);
        ChartInfo chartInfo5 = new ChartInfo(5.0f,15.00f);
        chartData.add(chartInfo5);
        ChartInfo chartInfo6 = new ChartInfo(6.0f,16.00f);
        chartData.add(chartInfo6);
        ChartInfo chartInfo7 = new ChartInfo(7.0f,17.00f);
        chartData.add(chartInfo7);
        ChartInfo chartInfo8 = new ChartInfo(8.0f,18.00f);
        chartData.add(chartInfo8);
    }

    private void initView() {
        customChatView = (CustomChartView) findViewById(R.id.customChatView);
    }

    private void showChart(List<ChartInfo> list) {

        if (list == null){
            return;
        }

        if (list.size() <= 0){
            return;
        }

        customChatView.showLoading();

         // 实际上是坐标点上的 x,y 轴（正真坐标上显示的值是自动变化的）
        List<Entry> yValuesData = new ArrayList<Entry>();
        List<String> xValuesData = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {
            yValuesData.add(new Entry(list.get(i).getyValue(), i));
            xValuesData.add(list.get(i).getxValue() + "");
        }

         // 开始绘制
        customChatView.canvasChart(yValuesData, xValuesData, R.color.blue);

        customChatView.hideLoading();
    }
}
