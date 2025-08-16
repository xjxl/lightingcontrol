package com.acme.xxlightingcontrol.view.main.product;

import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager2.widget.ViewPager2;

import com.acme.xxlightingcontrol.R;
import com.acme.xxlightingcontrol.component.DaggerPresenterComponent;
import com.acme.xxlightingcontrol.databinding.FragmentDialogProductBinding;
import com.acme.xxlightingcontrol.dto.ProductDto;
import com.acme.xxlightingcontrol.lib.annotation.Progress;
import com.acme.xxlightingcontrol.lib.base.BaseDialogDataFragment;
import com.acme.xxlightingcontrol.module.PresenterModule;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

/**
 * @author jx9@msn.com
 */
public class ProductDialogFragment extends BaseDialogDataFragment implements ProductDialogView {

    private FragmentDialogProductBinding fragmentDialogProductBinding;

    private ProductDialogFragmentAdapter productDialogFragmentAdapter;

    private TabLayout tabLayout;

    private ViewPager2 viewPager;

    private ImageView imageView;

    private XDialogListener mListener;

    private List<ProductDto> productDtos;

    public static ProductDialogFragment newInstance(XDialogListener listener, List<ProductDto> productDtoList) {
        ProductDialogFragment fragment = new ProductDialogFragment();
        fragment.setCancelable(false);
        fragment.mListener = listener;
        fragment.productDtos = productDtoList;
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
        return fragmentDialogProductBinding.getRoot();
    }

    @Override
    protected void initViewBinding() {
        fragmentDialogProductBinding = fragmentDialogProductBinding.inflate(getLayoutInflater());
    }

    @Progress(isDialogAble = false)
    public void requestData() {
//        productPresenter.getProducts();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        tabLayout = fragmentDialogProductBinding.tabLayout;
        viewPager = fragmentDialogProductBinding.pager;
        imageView = fragmentDialogProductBinding.closeIV;
        View root = tabLayout.getChildAt(0);
        if (root instanceof LinearLayout) {
            root.setBackgroundResource(R.color.transparent);
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(getResources().getColor(R.color.transparent));
            drawable.setSize(2, 1);
            ((LinearLayout) root).setDividerPadding(10);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }

    }

    @Override
    protected void initListeners() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductDialogFragment.this.dismissAllowingStateLoss();
            }
        });
    }

    @Override
    protected void initAdapters() {
        productDialogFragmentAdapter = new ProductDialogFragmentAdapter(this, productDtos);
        if (productDtos.size() > 15) {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
        viewPager.setAdapter(productDialogFragmentAdapter);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(productDtos.get(position).getName())
        ).attach();
    }

    @Override
    public void onStart() {
        super.onStart();
        this.getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        this.getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void success(int code, String msg, String tag, Object o) {

    }

    @Override
    public void failure(int code, String msg, String tag) {

    }

    public interface XDialogListener {

        void onDialogPositiveClick(ProductDialogFragment dialog);

        void setNegativeClick(ProductDialogFragment dialog);

    }
}
