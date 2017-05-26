package com.test.basecustomview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.test.basecustomview.view.SwitchToggleView;
import com.test.basecustomview.view.SwitchLayout;

public class MainActivity extends AppCompatActivity {

    private SwitchToggleView mSwitchToggleView;
    private SwitchLayout mSwitchLayout;
    private Button mBtnRevert;
    private Button mBtnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 滑动开关，背景是一张图片，滑块遮挡在上面，滑块在左边的时候挡住开字既是关闭状态，滑块在右边的时候挡住关字，既是开启的状态
        switchButton();

         // 交叉布局
        switchLayout();

        goNextDemo();

    }

    private void goNextDemo() {
        mBtnNext = (Button) findViewById(R.id.btnNext);
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     *  交叉布局
     * */
    private void switchLayout() {
        mSwitchLayout = (SwitchLayout) findViewById(R.id.switchLayout);
        mBtnRevert = (Button) findViewById(R.id.btnRevert);

        mBtnRevert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwitchLayout.revert(); // 调用 View 中的方法
            }
        });
    }

    /**
     *  滑动开关
     * */
    private void switchButton() {
        mSwitchToggleView = (SwitchToggleView) findViewById(R.id.switch_toggle);
        //监听开关事件
        mSwitchToggleView.setOnSwitchListener(new SwitchToggleView.OnSwitchListener() {
            @Override
            public void onSwitch(boolean isClose) {
                String text = isClose? "关闭" : "打开";
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
