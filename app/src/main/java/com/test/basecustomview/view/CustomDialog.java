package com.test.basecustomview.view;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.test.basecustomview.R;


/**
 * DialogFragment 不管布局是什么样的，这个 Dialog 都是居中的
 *
 */
public class CustomDialog extends DialogFragment {

    private MyListener MyListener;
    private Button btnCancel;
    private Button btnConfirm;
    private TextView tvContent;
    private LinearLayout layoutBottom;

    public void setListener(MyListener listener) {
        this.MyListener = listener;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(true);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.transparent);

        Window window = getDialog().getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT; // LayoutParams --> ViewGroup 是可以的
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT; // LayoutParams --> FrameLayout 是可以的...甚至其他莫名其妙的 LinearLayout,TableLayout 都可以
        layoutParams.gravity = Gravity.BOTTOM;
        window.setAttributes(layoutParams);

         // 设置 Dialog 的位置
        View view = inflater.inflate(R.layout.dialog_ar_land, container, false);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnConfirm = (Button) view.findViewById(R.id.btnConfirm);
        tvContent = (TextView) view.findViewById(R.id.tv_content);
        layoutBottom = (LinearLayout) view.findViewById(R.id.layout_bottom);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyListener.cancel();
                dismiss();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyListener.confirm();
                dismiss();
            }
        });

        return view;
    }

    public interface MyListener {
        void cancel();

        void confirm();
    }
}
