package com.mul.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.mul.view.view.switch_view.MulSwitchView;

public class MainActivity extends AppCompatActivity {
    private MulSwitchView mulSwitchView;

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
    }
}
