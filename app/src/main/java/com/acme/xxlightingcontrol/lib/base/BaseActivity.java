package com.acme.xxlightingcontrol.lib.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.acme.xxlightingcontrol.R;
import com.acme.xxlightingcontrol.databinding.BaseActivityBinding;
import com.acme.xxlightingcontrol.lib.common.Constants;
import com.acme.xxlightingcontrol.lib.exception.ActivityCreateException;

/**
 * @author jx9@msn.com
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Toolbar toolbar;

    protected ActionBar actionBar;

    protected TextView titleTV;

    protected Intent gintent;

    protected String title;

    protected Boolean isDisplayHomeAsUpEnabled = true;

    protected boolean v;

    protected boolean h;

    protected BaseActivityBinding baseActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayout() != 0) {
            setContentView(getLayout());
        }
        if (getView() != null) {
            setContentView(getView());
        }
        setupComponent();

        init();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public abstract void setupComponent();

    /**
     * Every fragment has to inflate a layout in the onCreateView method. We have added this method to
     * avoid duplicate all the inflate code in every fragment. You only have to return the layout to
     * inflate in this method when extends BaseFragment.
     */
    protected abstract int getLayout();

    protected abstract View getView();

    protected abstract void initViews();

    /**
     * Init some listeners
     */
    protected abstract void initListeners();

    /**
     * Init some adapters
     */
    protected abstract void initAdapters();

    /**
     * Init some datas
     */
    protected abstract void initData();

    /**
     * Init
     */
    protected void init() {
        initIntent();
        initData();
        initTitle();
        initToolBar();
        initViews();
        initAdapters();
        initListeners();
    }

    protected void initIntent() {
        gintent = getIntent();
    }

    protected void initTitle() {
        if (title == null) {
            title = gintent.getStringExtra(Constants.TITLE);
        }
    }

    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar == null) {
            return;
        }

//        titleTV = (TextView) toolbar.findViewById(R.id.title_tv);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();

        if (actionBar != null) {
            setXTitle(title);
        }
    }

    public void startActivityH(Class cls) {
        h = true;
        v = false;
        Intent intent = new Intent();

        if (cls != null) {
            intent.setClass(this, cls);
        }

        startActivity(intent);
    }

    public void startActivityH(Intent intent, Class cls) {
        h = true;
        v = false;

        if (intent == null) {
            intent = new Intent();
        }

        if (cls != null) {
            intent.setClass(this, cls);
        }

        startActivity(intent);
    }

    public void startActivityForResultH(Intent intent, Class cls, int requestCode) {
        v = true;
        h = false;

        if (intent == null) {
            intent = new Intent();
        }

        if (cls != null) {
            intent.setClass(this, cls);
        }

        startActivityForResult(intent, requestCode);
    }

    public void startActivityV(Class cls) {
        v = true;
        h = false;
        Intent intent = new Intent();

        if (cls != null) {
            intent.setClass(this, cls);
        }

        startActivity(intent);
    }

    public void startActivityV(Intent intent, Class cls) {
        v = true;
        h = false;

        if (intent == null) {
            intent = new Intent();
        }

        if (cls != null) {
            intent.setClass(this, cls);
        }

        startActivity(intent);
    }

    public void startActivityForResultV(Intent intent, Class cls, int requestCode) {
        v = true;
        h = false;

        if (intent == null) {
            intent = new Intent();
        }

        if (cls != null) {
            intent.setClass(this, cls);
        }

        startActivityForResult(intent, requestCode);
    }

    public void startActivityX(Class cls) {
        v = false;
        h = false;

        Intent intent = new Intent();

        if (cls != null) {
            intent.setClass(this, cls);
        }

        startActivity(intent);
    }

    public void startActivityForResultX(Class cls, int requestCode) {
        v = false;
        h = false;

        Intent intent = new Intent();

        if (cls != null) {
            intent.setClass(this, cls);
        }

        startActivityForResult(intent, requestCode);
    }

    public void startActivityXI(Intent intent, Class cls) {
        v = false;
        h = false;

        if (intent == null) {
            intent = new Intent();
        }

        if (cls != null) {
            intent.setClass(this, cls);
        }

        startActivity(intent);
    }

    public void startActivityForResultXI(Intent intent, Class cls, int requestCode) {
        v = false;
        h = false;

        if (intent == null) {
            intent = new Intent();
        }

        if (cls != null) {
            intent.setClass(this, cls);
        }

        startActivityForResult(intent, requestCode);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        // Handle your other action bar items...
        return super.onOptionsItemSelected(item);
    }

    public String getXTitle() {
        return this.title;
    }

    public void setXTitle(String title) {
        if (titleTV != null) {
            titleTV.setText(title);
        } else {
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(isDisplayHomeAsUpEnabled);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (v) {
            overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
        }

        if (h) {
            overridePendingTransition(R.anim.slede_in_right, R.anim.slide_out_right);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (v) {
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
        }

        if (h) {
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroyView();
    }

    public abstract void destroyView();
}