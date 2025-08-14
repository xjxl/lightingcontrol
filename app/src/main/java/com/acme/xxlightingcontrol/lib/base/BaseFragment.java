package com.acme.xxlightingcontrol.lib.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.acme.xxlightingcontrol.lib.common.Constants;

/**
 * @author jx9@msn.com
 */
public abstract class BaseFragment extends Fragment {

    protected static final String ARG_TITLE = "title";

    protected static final String ARG_INDEX = "index";

    protected FragmentManager mFm;

    protected ActionBar mActionBar;

    protected String mTitle;

    protected int mIndex;

    protected View view;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewBinding();
        mFm = getActivity().getSupportFragmentManager();
        mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (getArguments() != null) {
            mTitle = getArguments().getString(Constants.TITLE);
            mIndex = getArguments().getInt(Constants.INDEX, -1);
        }
        setHasOptionsMenu(true);
        setupComponent();
    }

    public abstract void setupComponent();

    public int getShowIndex() {
        return getArguments().getInt(Constants.INDEX, -1);
    }

    public String getTitle() {
        return getArguments().getString(Constants.TITLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getFragmentLayout() != 0) {
            view = inflater.inflate(getFragmentLayout(), container, false);
        } else {
            if (getViewFromViewBinding() != null) {
                view = getViewFromViewBinding();
            }
        }

        return view;
    }

    /**
     * Every fragment has to inflate a layout in the onCreateView method. We have added this method to
     * avoid duplicate all the inflate code in every fragment. You only have to return the layout to
     * inflate in this method when extends BaseFragment.
     */
    protected abstract int getFragmentLayout();

    protected abstract View getViewFromViewBinding();

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

    /**
     * Init
     */
    protected void init() {
        initData();
        initViews();
        initAdapters();
        initListeners();
    }

    protected void initViewBinding() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    protected FragmentManager getFm() {
        return this.mFm;
    }

    protected <T extends View> T getViewById(View view, int id) {
        return (T) view.findViewById(id);
    }

    protected <T extends View> T getViewById(int id) {
        return (T) view.findViewById(id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        destroyView();
    }

    public abstract void destroyView();
}
