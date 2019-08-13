package com.mul;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.mul.view.R;
import com.mul.view.label.MulLabelView;
import com.mul.view.label.bean.MulLabelBean;
import com.mul.view.switch_view.MulSwitchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MulLabelView.ITagItemListener {
    private MulSwitchView mulSwitchView;
    private MulLabelView mulLabelView;
    List<MulLabelBean> MulLabelBeans = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mulSwitchView = findViewById(R.id.switchView);
        mulSwitchView.setSwitchBtnClick(new MulSwitchView.ISwitchBtnListener() {
            @Override
            public void switchBtn(boolean switchOpenOrClose) {
                if (switchOpenOrClose) {
                    Toast.makeText(MainActivity.this, switchOpenOrClose + "", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, switchOpenOrClose + "", Toast.LENGTH_LONG).show();
                }
            }
        });
        initMulLabelView();
    }

    private void initMulLabelView() {
        mulLabelView = findViewById(R.id.tagView);
        for (int i = 0; i < 15; i++) {
            MulLabelBean tagBean = new MulLabelBean();
            tagBean.setTagContent("测试" + i);
            tagBean.setTagAllContent("测试" + i);
            tagBean.setTextColor(R.color.colorAccent);
            tagBean.setTextDrawable(R.drawable.mul_label_view_bg);
            tagBean.setTextSize(12);
            MulLabelBeans.add(tagBean);
        }
        mulLabelView.setTagBeans(MulLabelBeans, 10, 20, 20, 28);
        mulLabelView.setTagItemListener(this);
    }

    @Override
    public void tagItemClick(MulLabelBean bean) {

    }
}
