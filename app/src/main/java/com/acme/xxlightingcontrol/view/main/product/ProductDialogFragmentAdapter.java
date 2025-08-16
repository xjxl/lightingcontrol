package com.acme.xxlightingcontrol.view.main.product;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.acme.xxlightingcontrol.dto.ProductDto;

import java.util.List;

/**
 * @author jx9@msn.com
 */
public class ProductDialogFragmentAdapter extends FragmentStateAdapter {

    private List<ProductDto> productDtos;

    public ProductDialogFragmentAdapter(@NonNull Fragment fragment, List<ProductDto> productDtos) {
        super(fragment);
        this.productDtos = productDtos;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new ProductParameterFragment();
        Bundle args = new Bundle();
        args.putSerializable(ProductParameterFragment.ARG_OBJECT, productDtos.get(position));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return productDtos.size();
    }

}
