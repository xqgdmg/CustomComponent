package com.test.basecustomview.act;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.test.basecustomview.R;
import com.test.basecustomview.view.CustomDialog;

public class ThirdActivity extends AppCompatActivity {

    private Button btnShowDialogFragment;
    private Button btnShowDialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_acvtivity);

        initView();

        initListener();


    }

    private void initListener() {
        btnShowDialogFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogFragment();
            }
        });

        btnShowDialog1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog1();
            }
        });
    }

    /*
     * 这里导的是 v7 包 里面的 AlertDialog
     * 这样显示也是默认居中的
     */
    private void showDialog1() {
        new AlertDialog.Builder(ThirdActivity.this)
                .setTitle("自定义布局")
                .setView(R.layout.dialog_ar_land).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ThirdActivity.this,"点击确定",Toast.LENGTH_SHORT).show();
            }
        })
        .show();


}

    private void initView() {
        btnShowDialogFragment = (Button) findViewById(R.id.btnShowDialogFragment);
        btnShowDialog1 = (Button) findViewById(R.id.btnShowDialog1);
    }

    private void showDialogFragment() {
        CustomDialog customDialog = new CustomDialog();

        customDialog.setListener(new CustomDialog.MyListener() {
            @Override
            public void cancel() {
                Toast.makeText(ThirdActivity.this,"cancel",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void confirm() {
                Toast.makeText(ThirdActivity.this,"confirm",Toast.LENGTH_SHORT).show();
            }
        });
        customDialog.show(getFragmentManager(), "customDialog");
    }
}
