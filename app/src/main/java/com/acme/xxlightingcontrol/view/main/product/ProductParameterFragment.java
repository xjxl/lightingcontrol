package com.acme.xxlightingcontrol.view.main.product;

import android.os.Bundle;
import android.view.View;

import com.acme.xxlightingcontrol.databinding.ProductParameterFragmentBinding;
import com.acme.xxlightingcontrol.dto.ProductDto;
import com.acme.xxlightingcontrol.lib.base.BaseFragment;

/**
 * @author jx9@msn.com
 */
public class ProductParameterFragment extends BaseFragment {

    private ProductParameterFragmentBinding productParameterFragmentBinding;

    public static final String ARG_OBJECT = "object";

    public static ProductParameterFragment newInstance() {
        ProductParameterFragment fragment = new ProductParameterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setupComponent() {

    }

    @Override
    protected int getFragmentLayout() {
        return 0;
    }

    @Override
    protected View getViewFromViewBinding() {
        return productParameterFragmentBinding.getRoot();
    }

    @Override
    protected void initViewBinding() {
        productParameterFragmentBinding = productParameterFragmentBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        Bundle args = getArguments();
        productParameterFragmentBinding.testTV.setText(((ProductDto)args.getSerializable(ARG_OBJECT)).getName());
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
}