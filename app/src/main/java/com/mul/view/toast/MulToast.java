package com.mul.view.toast;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;

import com.mul.utils.MulScreenUtils;
import com.mul.view.R;

public class MulToast extends Toast {
    private AppCompatTextView toastText;

    public MulToast(Context context) {
        super(context);
    }

    public static MulToast makeText(Context context, Object data) {
        return makeText(context, data, -1);
    }

    public static MulToast makeText(Context context, Object data, int bgID) {
        MulToast toast = new MulToast(context);
        View view = View.inflate(context, R.layout.mul_toast, null);
        view.setBackgroundResource(bgID == -1 ? R.drawable.mul_toast_default_bg : bgID);
        toast.toastText = view.findViewById(R.id.toastText);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) toast.toastText.getLayoutParams();
        layoutParams.setMargins(MulScreenUtils.dp2px(25), MulScreenUtils.dp2px(12), MulScreenUtils.dp2px(25), MulScreenUtils.dp2px(17));
        toast.toastText.setText(String.format("%s", data));
        toast.setView(view);
        return toast;
    }

    public void setTextContent(Object data) {
        toastText.setText(String.format("%s", data));
    }
}
