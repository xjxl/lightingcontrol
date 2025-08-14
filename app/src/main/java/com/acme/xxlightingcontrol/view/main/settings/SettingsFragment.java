package com.acme.xxlightingcontrol.view.main.settings;

import android.os.Bundle;
import android.view.View;

import com.acme.xxlightingcontrol.BuildConfig;
import com.acme.xxlightingcontrol.component.DaggerPresenterComponent;
import com.acme.xxlightingcontrol.databinding.FragmentSettingsBinding;
import com.acme.xxlightingcontrol.lib.base.BaseFragment;
import com.acme.xxlightingcontrol.module.PresenterModule;
import com.acme.xxlightingcontrol.view.main.home.HomeView;

/**
 * @author jx9@msn.com
 */
public class SettingsFragment extends BaseFragment implements HomeView {

    public static final String TAG = "settings";

    public static final String TITLE = "设置";

    private FragmentSettingsBinding fragmentSettingsBinding;

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setupComponent() {
        DaggerPresenterComponent.builder().presenterModule(new PresenterModule(this)).
                build().inject(this);
    }

    @Override
    protected int getFragmentLayout() {
        return 0;
    }

    @Override
    protected View getViewFromViewBinding() {
        return fragmentSettingsBinding.getRoot();
    }

    @Override
    protected void initViewBinding() {
       fragmentSettingsBinding = fragmentSettingsBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initViews() {
        fragmentSettingsBinding.versionTv.setText(BuildConfig.VERSION_NAME);
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initAdapters() {

    }

    @Override
    public void destroyView() {

    }

    @Override
    public void success(int code, String msg, String tag, Object o) {

    }

    @Override
    public void failure(int code, String msg, String tag) {

    }
}
