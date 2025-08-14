package com.acme.xxlightingcontrol.lib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

import com.acme.xxlightingcontrol.R;

/**
 * @author jx9@msn.com
 */
public class XButton extends AppCompatButton {

    private boolean preActionAble;

    private String preActionText;

    private String originText;

    public XButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.XButton, 0, 0);
        try {
            preActionAble = ta.getBoolean(R.styleable.XButton_preActionAble, false);
            preActionText = ta.getString(R.styleable.XButton_preActionText);
            originText = getText().toString();
        } finally {
            ta.recycle();
        }
    }

    public boolean isPreActionAble() {
        return preActionAble;
    }

    public void setPreActionAble(boolean preActionAble) {
        this.preActionAble = preActionAble;
        this.setEnabled(!this.preActionAble);

        if (this.preActionAble) {
            if (preActionText.isEmpty()) {
                preActionText = getContext().getString(R.string.preActionText);
            }
            setText(preActionText);
        } else {
            setText(originText);
        }
        invalidate();
        requestLayout();
    }

    public String getPreActionText() {
        return preActionText;
    }

    public void setPreActionText(String preActionText) {
        this.preActionText = preActionText;
        if (this.preActionAble) {
            setText(this.preActionText);
        } else {
            setText(originText);
        }
        invalidate();
        requestLayout();
    }

    public String getOriginText() {
        return originText;
    }

    public void setOriginText(String originText) {
        this.originText = originText;
    }
}
