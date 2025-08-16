package com.acme.xxlightingcontrol.view.main.product;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.acme.xxlightingcontrol.adapter.GridSpacingItemDecoration;
import com.acme.xxlightingcontrol.adapter.ProductAdapter;
import com.acme.xxlightingcontrol.common.Constants;
import com.acme.xxlightingcontrol.common.MessageConstants;
import com.acme.xxlightingcontrol.component.DaggerPresenterComponent;
import com.acme.xxlightingcontrol.databinding.FragmentProductBinding;
import com.acme.xxlightingcontrol.dto.ProductDto;
import com.acme.xxlightingcontrol.lib.annotation.Progress;
import com.acme.xxlightingcontrol.lib.base.BaseFragment;
import com.acme.xxlightingcontrol.lib.listener.OnRecyclerItemClickListener;
import com.acme.xxlightingcontrol.lib.net.udp.UDPClient;
import com.acme.xxlightingcontrol.lib.xutil.XSharedPreferences;
import com.acme.xxlightingcontrol.lib.xutil.XToast;
import com.acme.xxlightingcontrol.module.PresenterModule;
import com.acme.xxlightingcontrol.prosenter.ProductPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author jx9@msn.com
 */
public class ProductFragment extends BaseFragment implements ProductView {

    public static final String TAG = "product";

    public static final String TITLE = "产品";

    private FragmentProductBinding fragmentProductBinding;

    public static final int PRODUCT_ITEM_COLUMNS = 3;

    private GridLayoutManager gridLayoutManager;

    private ProductAdapter productAdapter;

    private final List<ProductDto> products = new ArrayList<>();

    @Inject
    ProductPresenter productPresenter;

    @Inject
    XSharedPreferences sharedPreferences;

    public static ProductFragment newInstance() {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setupComponent() {
        DaggerPresenterComponent.builder().presenterModule(new PresenterModule(this)).build().inject(this);
    }

    @Override
    protected int getFragmentLayout() {
        return 0;
    }

    @Override
    protected View getViewFromViewBinding() {
        return fragmentProductBinding.getRoot();
    }

    @Override
    protected void initData() {

    }

    @Progress(isDialogAble = false)
    public void requestData() {
        productPresenter.getProducts();
    }

    @Override
    protected void initViewBinding() {
        fragmentProductBinding = fragmentProductBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initViews() {
        gridLayoutManager = new GridLayoutManager(getActivity(), PRODUCT_ITEM_COLUMNS,
                GridLayoutManager.VERTICAL, false);
        fragmentProductBinding.productRv.setHasFixedSize(true);
        fragmentProductBinding.productRv.setLayoutManager(gridLayoutManager);
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initAdapters() {
        productAdapter = new ProductAdapter(getActivity(), products, new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                ProductDto productDto = products.get(position);
                XToast.showShortMsg(getActivity(), productDto.getName());
                UDPClient.getInstance().sendMessage(MessageConstants.PRODUCT + productDto.getType().toString());
            }
        });
        fragmentProductBinding.productRv.addItemDecoration(new GridSpacingItemDecoration(3, 20, true));
        fragmentProductBinding.productRv.setAdapter(productAdapter);
    }

    @Override
    public void success(int code, String msg, String tag, Object o) {
        if (Constants.PRODUCTS.equals(tag)) {
            loadProducts((List<ProductDto>) o);
        }
    }

    public void loadProducts(List<ProductDto> products) {
        this.products.clear();
        this.products.addAll(products);
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        requestData();
    }

    @Override
    public void failure(int code, String msg, String tag) {

    }

    @Override
    public void destroyView() {

    }

}
