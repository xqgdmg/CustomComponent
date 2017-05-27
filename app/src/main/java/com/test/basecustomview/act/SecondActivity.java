package com.test.basecustomview.act;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    private Button mBtnNext;

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

    /*
     * 真正显示的在图标上的值是y值
     */
    private void mockData() {
        chartData = new ArrayList<ChartInfo>();
        ChartInfo chartInfo1 = new ChartInfo("1900.01",11.001111111111f);
        chartData.add(chartInfo1);
        ChartInfo chartInfo2 = new ChartInfo("1900.02",12.00f);
        chartData.add(chartInfo2);
        ChartInfo chartInfo3 = new ChartInfo("1900.03",13.01f);
        chartData.add(chartInfo3);
        ChartInfo chartInfo4 = new ChartInfo("1900.04",14.00f);
        chartData.add(chartInfo4);
        ChartInfo chartInfo5 = new ChartInfo("1900.05",15.00f);
        chartData.add(chartInfo5);
        ChartInfo chartInfo6 = new ChartInfo("1900.06",16.00f);
        chartData.add(chartInfo6);
        ChartInfo chartInfo7 = new ChartInfo("1900.07",17.00f);
        chartData.add(chartInfo7);
        ChartInfo chartInfo8 = new ChartInfo("1900.08",18.00f);
        chartData.add(chartInfo8);
    }

    private void initView() {
        customChatView = (CustomChartView) findViewById(R.id.customChatView);
        mBtnNext = (Button) findViewById(R.id.btnNext);

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this,ThirdActivity.class));
            }
        });
    }

    private void showChart(List<ChartInfo> list) {

        if (list == null){
            return;
        }

        if (list.size() <= 0){
            return;
        }

        customChatView.showLoading();

         // 实际上是坐标点上的 x,y 轴（真正坐标上显示的值是自动变化的）
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
