package com.acme.xxlightingcontrol.view.main.mode;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.acme.xxlightingcontrol.adapter.ModeAdapter;
import com.acme.xxlightingcontrol.common.Constants;
import com.acme.xxlightingcontrol.component.DaggerPresenterComponent;
import com.acme.xxlightingcontrol.databinding.FragmentModeBinding;
import com.acme.xxlightingcontrol.dto.ModeDto;
import com.acme.xxlightingcontrol.lib.annotation.Progress;
import com.acme.xxlightingcontrol.lib.base.BaseFragment;
import com.acme.xxlightingcontrol.lib.listener.OnRecyclerItemClickListener;
import com.acme.xxlightingcontrol.lib.xutil.XSharedPreferences;
import com.acme.xxlightingcontrol.module.PresenterModule;
import com.acme.xxlightingcontrol.prosenter.ModePresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author jx9@msn.com
 */
public class ModeFragment extends BaseFragment implements ModeView {

    public static final String TAG = "mode";

    public static final String TITLE = "模式";

    public static final int MODE_ITEM_COLUMNS = 3;

    private FragmentModeBinding fragmentModeBinding;

    private ModeAdapter modeAdapter;

    private final List<ModeDto> modes = new ArrayList<>();

    @Inject
    ModePresenter modePresenter;

    @Inject
    XSharedPreferences sharedPreferences;

    public static ModeFragment newInstance() {
        ModeFragment fragment = new ModeFragment();
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
        return fragmentModeBinding.getRoot();
    }

    @Override
    protected void initViewBinding() {
        fragmentModeBinding = fragmentModeBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initData() {

    }

    @Progress(isDialogAble = false)
    public void requestData() {
        modePresenter.getModes();
    }

    @Override
    public void onResume() {
        super.onResume();
        requestData();
    }

    @Override
    protected void initViews() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(6, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        fragmentModeBinding.modeRv.setLayoutManager(staggeredGridLayoutManager);
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initAdapters() {
        modeAdapter = new ModeAdapter(getActivity(), modes, new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                modeAdapter.notifyDataSetChanged();
            }
        });
        fragmentModeBinding.modeRv.setAdapter(modeAdapter);
    }

    @Override
    public void success(int code, String msg, String tag, Object o) {
        if (Constants.MODES.equals(tag)) {
            loadModes((List<ModeDto>) o);
        }
    }

    public void loadModes(List<ModeDto> modes) {
        preDownloadImage(modes);
        this.modes.clear();
        this.modes.addAll(modes);
        modeAdapter.notifyDataSetChanged();
    }

    public void preDownloadImage(List<ModeDto> modes) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (ModeDto modeDto : modes) {
                    Picasso.with(getActivity()).load(modeDto.getIconClick()).fetch();
                }
            }
        }).start();
    }

    @Override
    public void failure(int code, String msg, String tag) {

    }

    @Override
    public void destroyView() {

    }

}
