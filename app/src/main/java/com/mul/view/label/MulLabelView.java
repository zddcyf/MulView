package com.mul.view.label;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.IdRes;

import com.mul.utils.MulStringUtils;
import com.mul.view.label.bean.MulLabelBean;

import java.util.List;

/**
 * Created by Admin on 2017/10/25.
 */

public class MulLabelView extends RelativeLayout {
    private ITagItemListener listener;

    private ViewTreeObserver viewTreeObserver;
    private boolean mInitialized = false;
    private int mWidth;
    private int mHeight;

    private int textColor;
    private int textSize;
    private int textDrawable;
    private int textPaddTB; // 上下的padding值
    private int textPaddLR; // 左右的padding值
    private int tagtMarginL; // 子view距离左边的距离
    private int tagtMarginT; // 子view距离上边的距离

    private List<MulLabelBean> tagBeans;

    public MulLabelView(Context context) {
        this(context, null);
    }

    public MulLabelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MulLabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        viewTreeObserver = getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (!mInitialized) {
                    mInitialized = true;
                    initView();
                }
            }
        });
    }

    /**
     * 控件发生变化时获取控件的宽高
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    private void initView() {
        if (!mInitialized)
            return;
        removeAllViews();
        // 总长度
        float total = getPaddingLeft() + getPaddingRight();
        @IdRes int index = 1001; // 起始位置
        int pindex = index; // 相对起始位置
        if (MulStringUtils.isNotEmpty(tagBeans)) {
            for (final MulLabelBean bean : tagBeans) {
                TextView textView = new TextView(getContext());
                textView.setId(index);
                textView.setText(bean.getTagContent());
                textView.setTextColor(bean.getTextColor());
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, bean.getTextSize());
                textView.setBackgroundResource(bean.getTextDrawable());
                textView.setPadding(textPaddLR, textPaddTB, textPaddLR, textPaddTB);
                textView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (null != listener) {
                            listener.tagItemClick(bean);
                        }
                    }
                });
                // 子控件的高度
                float tagWidth = textView.getPaint().measureText(bean.getTagContent()) + textPaddLR * 2;
                LayoutParams params = new LayoutParams(-2, -2);
                if (mWidth <= total + tagWidth + textPaddLR) {
                    params.addRule(RelativeLayout.BELOW, pindex);
                    params.topMargin = tagtMarginT;
                    pindex = index;
                    total = getPaddingLeft() + getPaddingRight();
                } else {
                    params.addRule(RelativeLayout.ALIGN_TOP, pindex);
                    params.addRule(RelativeLayout.RIGHT_OF, index - 1);
                    if (index > 1001) {
                        params.leftMargin = tagtMarginL;
                        total += tagtMarginL;
                    }
                }
                total += tagWidth;
                addView(textView, params);
                index++;
            }
        }
    }

    public List<MulLabelBean> getTagBeans() {
        return tagBeans;
    }

    public void setTagBeans(List<MulLabelBean> tagBeans) {
        this.tagBeans = tagBeans;
        initView();
    }

    /**
     * @param tagBeans
     * @param textPaddTB  上下的padding值
     * @param textPaddLR  左右的padding值
     * @param tagtMarginL 子view距离左边的距离
     * @param tagtMarginT 子view距离上边的距离
     */
    public void setTagBeans(List<MulLabelBean> tagBeans, int textPaddTB, int textPaddLR, int tagtMarginL, int tagtMarginT) {
        this.tagBeans = tagBeans;
        this.textPaddLR = textPaddLR;
        this.textPaddTB = textPaddTB;
        this.tagtMarginL = tagtMarginL;
        this.tagtMarginT = tagtMarginT;
        initView();
    }

    public void setTagItemListener(ITagItemListener listener) {
        this.listener = listener;
    }

    public interface ITagItemListener {
        void tagItemClick(MulLabelBean bean);
    }
}
