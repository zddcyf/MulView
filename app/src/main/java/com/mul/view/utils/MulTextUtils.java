package com.mul.view.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

/**
 * Created by Admin on 2017/8/18.
 */

public class MulTextUtils {

    public static void textDrawableLeft(Context context, TextView textView, int drawableId, int drawablePadd, String direction) {
        Drawable drawable= context.getResources().getDrawable(drawableId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        switch (direction) {
            case "left":
                textView.setCompoundDrawables(drawable, null, null, null);
                break;
            case "top":
                textView.setCompoundDrawables(null, drawable, null, null);
                break;
            case "right":
                textView.setCompoundDrawables(null, null, drawable, null);
                break;
            case "bottom":
                textView.setCompoundDrawables(null, null, null, drawable);
                break;
        }
        textView.setCompoundDrawablePadding(drawablePadd);
    }
}
