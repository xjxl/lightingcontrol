package com.acme.xxlightingcontrol.view.main.light;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.acme.xxlightingcontrol.R;
import com.acme.xxlightingcontrol.adapter.GridSpacingItemDecoration;
import com.acme.xxlightingcontrol.adapter.LightAdapter;
import com.acme.xxlightingcontrol.databinding.FragmentLightBinding;
import com.acme.xxlightingcontrol.dto.LightDto;
import com.acme.xxlightingcontrol.lib.base.BaseFragment;
import com.acme.xxlightingcontrol.lib.listener.OnRecyclerItemClickListener;
import com.acme.xxlightingcontrol.lib.xutil.XSharedPreferences;
import com.acme.xxlightingcontrol.view.main.home.HomeView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author jx9@msn.com
 */
public class LightFragment extends BaseFragment implements HomeView {

    public static final String TAG = "light";

    public static final String TITLE = "照明";

    private FragmentLightBinding fragmentLightBinding;
    public static final int LIGHT_ITEM_COLUMNS = 4;

    private GridLayoutManager gridLayoutManager;

    private LightAdapter lightAdapter;


    @Inject
    XSharedPreferences sharedPreferences;

    public static LightFragment newInstance() {
        LightFragment fragment = new LightFragment();
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
        return fragmentLightBinding.getRoot();
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void initViewBinding() {
        fragmentLightBinding = fragmentLightBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initViews() {
        gridLayoutManager = new GridLayoutManager(getActivity(), LIGHT_ITEM_COLUMNS,
                GridLayoutManager.VERTICAL, false);
        fragmentLightBinding.lightRv.setHasFixedSize(true);
        fragmentLightBinding.lightRv.setLayoutManager(gridLayoutManager);
        fragmentLightBinding.lightRv.addItemDecoration(new GridSpacingItemDecoration(4, 10, true));
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initAdapters() {
        List<LightDto> lightDtos = new ArrayList<>();
        lightDtos.add(new LightDto("照明", 1));
        lightDtos.add(new LightDto("广告灯", 0));
        lightDtos.add(new LightDto("Pml", 0));
        lightDtos.add(new LightDto("展柜灯具", 0));
        lightDtos.add(new LightDto("楼梯灯", 0));
        lightAdapter = new LightAdapter(getActivity(), lightDtos, new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
            }
        }, R.layout.item_light);
        fragmentLightBinding.lightRv.setAdapter(lightAdapter);
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
