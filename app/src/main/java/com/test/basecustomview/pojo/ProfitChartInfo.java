package com.test.basecustomview.pojo;

import java.util.List;

/**
 * Created by Chris on 2017/5/27.
 */
public class ProfitChartInfo  {

    public DataBean data;
    public Object mapData;

    public static class DataBean {

        public float maxProfit;
        public List<ProfitChartListBean> profitChartList;

    }

    public static class ProfitChartListBean {
        public String dateTime;
        public float profitTotal;
    }
}
