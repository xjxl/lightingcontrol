package com.acme.xxlightingcontrol.view.main;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.acme.xxlightingcontrol.R;
import com.acme.xxlightingcontrol.databinding.ActivityMainBinding;
import com.acme.xxlightingcontrol.lib.base.FragmentContainerActivity;
import com.acme.xxlightingcontrol.view.main.home.HomeFragment;
import com.acme.xxlightingcontrol.view.main.light.LightFragment;
import com.acme.xxlightingcontrol.view.main.mode.ModeFragment;
import com.acme.xxlightingcontrol.view.main.product.ProductFragment;
import com.acme.xxlightingcontrol.view.main.settings.SettingsFragment;

/**
 * @author jx9@msn.com
 */
public class MainActivity extends FragmentContainerActivity implements View.OnClickListener {

    private ActivityMainBinding activityMainBinding;

    private int homeRbId;

    private int lightRbId;

    private int productRbId;

    private int modeRbId;

    private int settingsRbId;

    private int voiceRbId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setTheme(R.style.AT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        WindowInsetsControllerCompat windowInsetsController = new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
        windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars());
    }

    @Override
    public void setupComponent() {

    }

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected void init() {
        super.init();
        activityMainBinding.homeRb.callOnClick();
    }

    @Override
    protected View getView() {
        return activityMainBinding.getRoot();
    }

    @Override
    protected void initViews() {
        activityMainBinding.homeRb.setChecked(true);
    }

    @Override
    protected void initListeners() {
        activityMainBinding.homeRb.setOnClickListener(this);
        activityMainBinding.lightRb.setOnClickListener(this);
        activityMainBinding.productRb.setOnClickListener(this);
        activityMainBinding.modeRb.setOnClickListener(this);
        activityMainBinding.settingsRb.setOnClickListener(this);
        activityMainBinding.voiceRb.setOnClickListener(this);
    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void destroyView() {

    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == activityMainBinding.homeRb.getId()) {
            switchFragment(R.id.main_container, HomeFragment.newInstance(), HomeFragment.TAG);
        }
        if (viewId == activityMainBinding.lightRb.getId()) {
            switchFragment(R.id.main_container, LightFragment.newInstance(), LightFragment.TAG);
        }
        if (viewId == activityMainBinding.productRb.getId()) {
            switchFragment(R.id.main_container, ProductFragment.newInstance(), ProductFragment.TAG);
        }
        if (viewId == activityMainBinding.modeRb.getId()) {
            switchFragment(R.id.main_container, ModeFragment.newInstance(), ModeFragment.TAG);
        }
        if (viewId == activityMainBinding.settingsRb.getId()) {
            switchFragment(R.id.main_container, SettingsFragment.newInstance(), SettingsFragment.TAG);
        }
//        if (viewId == activityMainBinding.voiceRb.getId()) {
//        }
    }
}