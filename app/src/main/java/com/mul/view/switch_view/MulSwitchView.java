package com.mul.view.switch_view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mul.view.R;

/**
 * Created by Admin on 2018/1/2.
 * 自定义开关按钮
 */

public class MulSwitchView extends RelativeLayout implements View.OnClickListener {
    private ISwitchBtnListener listener; // 按钮的点击事件

    private int switchLeftBg; // 按钮在左侧时的背景
    private int switchRightBg; // 按钮在右侧时的背景
    private int switchBtnLeftBg; // 按钮在左侧时的图片
    private int switchBtnRightBg; // 按钮在右侧是的图片
    private int switchBtnWH; // 内部按钮的宽高
    private int switchBtnPadd; // 内部按钮距离上下左右的边距
    private boolean switchOpenOrClose; // 开关是否默认开启
    private boolean switchAniOpenOrClose; // 动画的控制器
    private int switchAniTime; // 开关位移的时间

    private ImageView switchLeftImageBg;
    private ImageView switchRightImageBg;
    private ImageView switchBtnLeftImageBg;
    private ImageView switchBtnRightImageBg;
    private AnimatorSet openAs;
    private AnimatorSet closeAs;

    public MulSwitchView(Context context) {
        this(context, null);
    }

    public MulSwitchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MulSwitchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MulSwitchView);
        switchLeftBg = typedArray.getResourceId(R.styleable.MulSwitchView_switchLeftBg, R.mipmap.ic_launcher);
        switchRightBg = typedArray.getResourceId(R.styleable.MulSwitchView_switchRightBg, R.mipmap.ic_launcher);
        switchBtnLeftBg = typedArray.getResourceId(R.styleable.MulSwitchView_switchBtnLeftBg, R.mipmap.ic_launcher);
        switchBtnRightBg = typedArray.getResourceId(R.styleable.MulSwitchView_switchBtnRightBg, R.mipmap.ic_launcher);
        /**
         * getDimension(); 此方法会转换成dp或者sp对应的值 返回的是float类型
         * getDimensionPixelOffset(); 此方法会转换成dp或者sp对应的值 返回的是int类型
         * getDimensionPixelSize();此方法会不管什么类型都会乘以density
         */
        switchBtnWH = typedArray.getDimensionPixelOffset(R.styleable.MulSwitchView_switchBtnWH, 30);
        switchBtnPadd = typedArray.getDimensionPixelOffset(R.styleable.MulSwitchView_switchBtnPadd, -1);

        switchOpenOrClose = typedArray.getBoolean(R.styleable.MulSwitchView_switchOpenOrClose, false);
        switchAniTime = typedArray.getInt(R.styleable.MulSwitchView_switchAniTime, 5000);
        typedArray.recycle();
        intiView();
    }

    /**
     * 初始化view
     */
    private void intiView() {
        inflate(this.getContext(), R.layout.mul_switch_view, this);
        this.setLayoutParams(new LayoutParams(-1, -2));
        switchLeftImageBg = findViewById(R.id.switchLeftImageBg);
        switchRightImageBg = findViewById(R.id.switchRightImageBg);
        switchBtnLeftImageBg = findViewById(R.id.switchBtnLeftImageBg);
        switchBtnRightImageBg = findViewById(R.id.switchBtnRightImageBg);
        switchBtnLeftImageBg.getLayoutParams().width = switchBtnWH;
        switchBtnRightImageBg.getLayoutParams().width = switchBtnWH;
        // 设置资源
        switchLeftImageBg.setImageResource(switchLeftBg);
        switchRightImageBg.setImageResource(switchRightBg);
        switchBtnLeftImageBg.setImageResource(switchBtnLeftBg);
        switchBtnRightImageBg.setImageResource(switchBtnRightBg);

        // 设置图片距离内部的距离
        if (switchBtnPadd != -1 && switchBtnPadd != 0) {
            switchBtnLeftImageBg.setPadding(switchBtnPadd, switchBtnPadd, switchBtnPadd, switchBtnPadd);
            switchBtnRightImageBg.setPadding(switchBtnPadd, switchBtnPadd, switchBtnPadd, switchBtnPadd);
        }
        switchAniOpenOrClose = switchOpenOrClose;
        setSwitch();
        setOnClickListener(this);
    }

    private void setSwitch() {
        LayoutParams leftParams = (LayoutParams) switchBtnLeftImageBg.getLayoutParams();
        LayoutParams rightParams = (LayoutParams) switchBtnRightImageBg.getLayoutParams();
        if (switchOpenOrClose) {
            leftParams.addRule(RelativeLayout.ALIGN_LEFT);
            rightParams.addRule(RelativeLayout.ALIGN_LEFT);
            leftParams.addRule(RelativeLayout.ALIGN_RIGHT, R.id.switchRightImageBg);
            rightParams.addRule(RelativeLayout.ALIGN_RIGHT, R.id.switchRightImageBg);
            switchBtnRightImageBg.setVisibility(VISIBLE);
            switchRightImageBg.setVisibility(VISIBLE);
        } else {
            switchBtnRightImageBg.setVisibility(INVISIBLE);
            switchRightImageBg.setVisibility(INVISIBLE);
            leftParams.addRule(RelativeLayout.ALIGN_LEFT, R.id.switchLeftImageBg);
            rightParams.addRule(RelativeLayout.ALIGN_LEFT, R.id.switchLeftImageBg);
            leftParams.addRule(RelativeLayout.ALIGN_RIGHT);
            rightParams.addRule(RelativeLayout.ALIGN_RIGHT);
        }
        switchBtnLeftImageBg.setLayoutParams(leftParams);
        switchBtnRightImageBg.setLayoutParams(rightParams);
    }

    /**
     * 开启按钮不支持手动调用。可以在布局中设置默认
     */
    private void open() {
        switchBtnRightImageBg.setVisibility(VISIBLE);
        switchRightImageBg.setVisibility(VISIBLE);
        if (null != closeAs) {
            closeAs.end();
            closeAs.cancel();
        }
        // 左边点的动画
        float translationX = switchBtnLeftImageBg.getTranslationX();
        Log.e("translationX", switchBtnLeftImageBg.getTranslationX() + "");
        ObjectAnimator leftPointTrans = ObjectAnimator.ofFloat(switchBtnLeftImageBg, "translationX", translationX != 0.0 ? 0 - switchBtnWH : 0, switchAniOpenOrClose ? translationX != 0.0 ? 0 : 0 - switchBtnWH : switchBtnWH);
        ObjectAnimator leftPointAlpha = ObjectAnimator.ofFloat(switchBtnLeftImageBg, "alpha", 1f, 0f);
        // 右边点的动画
        ObjectAnimator rightPointTrans = ObjectAnimator.ofFloat(switchBtnRightImageBg, "translationX", translationX != 0.0 ? 0 - switchBtnWH : 0, switchAniOpenOrClose ? translationX != 0.0 ? 0 : 0 - switchBtnWH : switchBtnWH);
        ObjectAnimator rightPointAlpha = ObjectAnimator.ofFloat(switchBtnRightImageBg, "alpha", 0f, 1f);
        // 左边背景的动画
//        ObjectAnimator leftBgTrans = ObjectAnimator.ofFloat(switchLeftImageBg, "translationX", translationX != 0.0 ? 0 - switchBtnWH : 0, switchAniOpenOrClose ? translationX != 0.0 ? 0 : 0 - switchBtnWH : switchBtnWH);
        ObjectAnimator leftBgAlpha = ObjectAnimator.ofFloat(switchLeftImageBg, "alpha", 1f, 0f);
        // 右边背景的动画
//        ObjectAnimator rightBgTrans = ObjectAnimator.ofFloat(switchRightImageBg, "translationX", translationX != 0.0 ? 0 - switchBtnWH : 0, switchAniOpenOrClose ? translationX != 0.0 ? 0 : 0 - switchBtnWH : switchBtnWH);
        ObjectAnimator rightBgAlpha = ObjectAnimator.ofFloat(switchRightImageBg, "alpha", 0f, 1f);

        openAs = new AnimatorSet();
        openAs.play(leftPointTrans).with(leftPointAlpha).with(rightPointTrans).with(rightPointAlpha)
                .with(leftBgAlpha).with(rightBgAlpha);
//                .with(leftBgTrans).with(leftBgAlpha).with(rightBgTrans).with(rightBgAlpha);
        openAs.setDuration(switchAniTime);
        openAs.start();
    }

    /**
     * 关闭按钮不支持手动调用。可以在布局中设置默认
     */
    private void close() {
        switchBtnRightImageBg.setVisibility(VISIBLE);
        switchRightImageBg.setVisibility(VISIBLE);
        if (null != openAs) {
            openAs.end();
            openAs.cancel();
        }
        // 左边点的动画
        float translationX = switchBtnLeftImageBg.getTranslationX();
        ObjectAnimator leftPointTrans = ObjectAnimator.ofFloat(switchBtnLeftImageBg, "translationX", switchAniOpenOrClose ? translationX == 0.0 ? 0 : 0 - switchBtnWH : switchBtnWH, translationX == 0.0 ? 0 - switchBtnWH : 0);
        ObjectAnimator leftPointAlpha = ObjectAnimator.ofFloat(switchBtnLeftImageBg, "alpha", 0f, 1f);
        // 右边点的动画
        ObjectAnimator rightPointTrans = ObjectAnimator.ofFloat(switchBtnRightImageBg, "translationX", switchAniOpenOrClose ? translationX == 0.0 ? 0 : 0 - switchBtnWH : switchBtnWH, translationX == 0.0 ? 0 - switchBtnWH : 0);
        ObjectAnimator rightPointAlpha = ObjectAnimator.ofFloat(switchBtnRightImageBg, "alpha", 1f, 0f);
        // 左边背景的动画
//        ObjectAnimator leftBgTrans = ObjectAnimator.ofFloat(switchLeftImageBg, "translationX", switchAniOpenOrClose ? translationX == 0.0 ? 0 : 0 - switchBtnWH : switchBtnWH, translationX == 0.0 ? 0 - switchBtnWH : 0);
        ObjectAnimator leftBgAlpha = ObjectAnimator.ofFloat(switchLeftImageBg, "alpha", 0f, 1f);
        // 右边背景的动画
//        ObjectAnimator rightBgTrans = ObjectAnimator.ofFloat(switchRightImageBg, "translationX", switchAniOpenOrClose ? translationX == 0.0 ? 0 : 0 - switchBtnWH : switchBtnWH, translationX == 0.0 ? 0 - switchBtnWH : 0);
        ObjectAnimator rightBgAlpha = ObjectAnimator.ofFloat(switchRightImageBg, "alpha", 1f, 0f);

        closeAs = new AnimatorSet();
        closeAs.play(leftPointTrans).with(leftPointAlpha).with(rightPointTrans).with(rightPointAlpha)
                .with(leftBgAlpha).with(rightBgAlpha);
//                .with(leftBgTrans).with(leftBgAlpha).with(rightBgTrans).with(rightBgAlpha);
        closeAs.setDuration(switchAniTime);
        closeAs.start();
    }

    @Override
    public void onClick(View view) {
        if (null != listener) {
            if (switchOpenOrClose) {
                close();
                switchOpenOrClose = false;
            } else {
                open();
                switchOpenOrClose = true;
            }
            listener.switchBtn(switchOpenOrClose);
        }
    }

    /**
     * 是否选中状态
     *
     * @return
     */
    public boolean isChecked() {
        return switchOpenOrClose;
    }

    public void setChecked(boolean switchOpenOrClose) {
        this.switchOpenOrClose = switchOpenOrClose;
        this.switchAniOpenOrClose = switchOpenOrClose;
        setSwitch();
        postInvalidate();
    }

    public void setDuration(int switchAniTime) {
        this.switchAniTime = switchAniTime;
    }

    public interface ISwitchBtnListener {
        void switchBtn(boolean switchOpenOrClose);
    }

    public void setSwitchBtnClick(ISwitchBtnListener listener) {
        this.listener = listener;
    }
}
