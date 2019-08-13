package com.mul.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

//    private FrameLayout mFrameLayout;

    @Deprecated
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
//        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.cv_custom_title);
//        mFrameLayout = getWindow().getDecorView().findViewById(android.R.id.content);
//        View view = View.inflate(this, getLayoutId(), null);
//        mFrameLayout.addView(view);
        initView();
    }

    protected abstract @LayoutRes
    int getLayoutId();

    protected abstract void initView();
}
