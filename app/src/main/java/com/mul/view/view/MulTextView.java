package com.mul.view.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mul.view.R;
import com.mul.view.utils.MulTextUtils;

/**
 * Created by ZDD on 2016/12/13.
 * 发布中文本的的条目
 */

public class MulTextView extends RelativeLayout {
    private Context context;

    private TextView leftText;
    private TextView rightText;
    private View line;

    // 左边数据
    private String leftTextStr;
    private int leftTextColor;
    private int leftTextSize;
    private boolean isLeftDrawable;
    private int leftDrawable;
    private int leftDrawablePadd;
    // 右边数据
    private String rightTextStr;
    private int rightTextColor;
    private int rightTextSize;
    private boolean isRightDrawable;
    private int rightDrawable;
    private int rightDrawablePadd;
    // 线的显示与隐藏
    private boolean isLine;

    public MulTextView(Context context) {
        this(context, null);
    }

    public MulTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MulTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MulTextView);
        // 左边属性
        leftTextStr = typedArray.getString(R.styleable.MulTextView_leftText);
        leftTextColor = typedArray.getColor(R.styleable.MulTextView_leftTextColor, Color.parseColor("#333333"));
        leftTextSize = typedArray.getInteger(R.styleable.MulTextView_leftTextSize, 18);
        isLeftDrawable = typedArray.getBoolean(R.styleable.MulTextView_isLeftDrawable, false);
        leftDrawable = typedArray.getResourceId(R.styleable.MulTextView_leftDrawable, R.mipmap.ic_launcher);
        leftDrawablePadd = typedArray.getDimensionPixelSize(R.styleable.MulTextView_leftDrawablePadd, 11);
        // 右边属性
        rightTextStr = typedArray.getString(R.styleable.MulTextView_rightText);
        rightTextColor = typedArray.getColor(R.styleable.MulTextView_rightTextColor, Color.parseColor("#333333"));
        rightTextSize = typedArray.getInteger(R.styleable.MulTextView_rightTextSize, 18);
        isRightDrawable = typedArray.getBoolean(R.styleable.MulTextView_isRightDrawable, false);
        rightDrawable = typedArray.getResourceId(R.styleable.MulTextView_rightDrawable, R.mipmap.ic_launcher);
        rightDrawablePadd = typedArray.getDimensionPixelSize(R.styleable.MulTextView_rightDrawablePadd, 11);
        // 线
        isLine = typedArray.getBoolean(R.styleable.MulTextView_isLine, true);

        typedArray.recycle();
        this.context = context;
        initView();
    }

    private void initView() {
        inflate(context, R.layout.mul_text_view, this);
        leftText = findViewById(R.id.leftText);
        rightText = findViewById(R.id.rightText);
        line = findViewById(R.id.line);
        setLeftText();
        setRightText();
        if (isLine) {
            line.setVisibility(VISIBLE);
        } else {
            line.setVisibility(GONE);
        }
    }

    /**
     * 设置左边数据
     */
    private void setLeftText() {
        leftText.setText(leftTextStr);
        leftText.setTextColor(leftTextColor);
        leftText.setTextSize(leftTextSize);
        if (isLeftDrawable) {
            MulTextUtils.textDrawableLeft(context, leftText, leftDrawable, leftDrawablePadd, "left");
        }
    }

    /**
     * 设置右边数据
     */
    private void setRightText() {
        rightText.setText(rightTextStr);
        rightText.setTextColor(rightTextColor);
        rightText.setTextSize(rightTextSize);
        if (isRightDrawable) {
            MulTextUtils.textDrawableLeft(context, rightText, rightDrawable, rightDrawablePadd, "right");
        }
    }
}
