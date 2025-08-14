package com.acme.xxlightingcontrol.lib.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

import com.acme.xxlightingcontrol.databinding.BaseDialogFragmentBinding;

public abstract class BaseDialogFragment extends DialogFragment {

    protected View view;

    protected BaseDialogFragmentBinding baseDialogFragmentBinding;

    public BaseDialogFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        baseDialogFragmentBinding = baseDialogFragmentBinding.inflate(getLayoutInflater());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(getFragmentLayout(), container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    protected abstract int getFragmentLayout();

    protected abstract void initView();
}
