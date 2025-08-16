package com.acme.xxlightingcontrol.lib.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

public abstract class BaseDialogDataFragment extends DialogFragment {

    protected View view;

    public BaseDialogDataFragment() {

    }

    public abstract void setupComponent();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewBinding();
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        setupComponent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getFragmentLayout() != 0) {
            view = inflater.inflate(getFragmentLayout(), container, false);
        } else {
            if (getViewFromViewBinding() != null) {
                view = getViewFromViewBinding();
            }
        }
        return view;
    }

    protected abstract View getViewFromViewBinding();

    protected void initViewBinding() {

    }

    protected void init() {
        initData();
        initViews();
        initAdapters();
        initListeners();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    protected abstract int getFragmentLayout();

    /**
     * Init some datas
     */
    protected abstract void initData();

    protected abstract void initViews();

    /**
     * Init some listeners
     */
    protected abstract void initListeners();

    /**
     * Init some adapters
     */
    protected abstract void initAdapters();
}
