package com.acme.xxlightingcontrol.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.acme.xxlightingcontrol.R;

/**
 * @author jx9@msn.com
 */
public class LightBrightnessView extends LinearLayout {

    private boolean specialColorViewHidden;

    public LightBrightnessView(Context context) {
        super(context);
    }

    public LightBrightnessView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LightBrightnessView, 0, 0);
        try {
            specialColorViewHidden = ta.getBoolean(R.styleable.LightBrightnessView_specialColorHidden, false);
            LayoutInflater.from(context).inflate(R.layout.light_brightness_view, this, true);
            LinearLayout specialColorView = findViewById(R.id.specialColorView);
            specialColorView.setVisibility(specialColorViewHidden ? View.GONE : View.VISIBLE);
        } finally {
            ta.recycle();
        }
    }

    public LightBrightnessView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LightBrightnessView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public boolean isSpecialColorViewHidden() {
        return specialColorViewHidden;
    }

    public void setSpecialColorViewHidden(boolean specialColorViewHidden) {
        this.specialColorViewHidden = specialColorViewHidden;
    }
}
