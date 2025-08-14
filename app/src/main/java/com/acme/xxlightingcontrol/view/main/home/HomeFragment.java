package com.acme.xxlightingcontrol.view.main.home;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.acme.xxlightingcontrol.R;
import com.acme.xxlightingcontrol.adapter.GridSpacingItemDecoration;
import com.acme.xxlightingcontrol.adapter.LightAdapter;
import com.acme.xxlightingcontrol.databinding.FragmentHomeBinding;
import com.acme.xxlightingcontrol.dto.LightDto;
import com.acme.xxlightingcontrol.lib.base.BaseFragment;
import com.acme.xxlightingcontrol.lib.listener.OnRecyclerItemClickListener;
import com.acme.xxlightingcontrol.lib.xutil.XSharedPreferences;

import net.time4j.calendar.ChineseCalendar;
import net.time4j.format.expert.ChronoFormatter;
import net.time4j.format.expert.PatternType;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

/**
 * @author jx9@msn.com
 */
public class HomeFragment extends BaseFragment implements HomeView {

    public static final String TAG = "home";

    public static final String TITLE = "首页";

    private FragmentHomeBinding fragmentHomeBinding;

    public static final int HOME_LIGHT_ITEM_COLUMNS = 2;

    private GridLayoutManager gridLayoutManager;

    private LightAdapter lightAdapter;

    private Thread updateDateTimeThread;

    private ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));

    private static final ChronoFormatter<ChineseCalendar> cfwumd = ChronoFormatter.ofPattern(
            "E  U年   MMMMd",
            PatternType.CLDR,
            Locale.SIMPLIFIED_CHINESE,
            ChineseCalendar.axis());

    private String time;

    private String date;

    private String chineseDate;

    @Inject
    XSharedPreferences sharedPreferences;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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
        return fragmentHomeBinding.getRoot();
    }

    @Override
    protected void initData() {
        getNow();
    }

    @Override
    protected void initViewBinding() {
        fragmentHomeBinding = fragmentHomeBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initViews() {
        gridLayoutManager = new GridLayoutManager(getActivity(), HOME_LIGHT_ITEM_COLUMNS,
                GridLayoutManager.VERTICAL, false);
        fragmentHomeBinding.homeLightRv.setHasFixedSize(true);
        fragmentHomeBinding.homeLightRv.setLayoutManager(gridLayoutManager);

        updateDateTimeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    try {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateDateTime();
                            }
                        });
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        updateDateTimeThread.start();
    }

    private void getNow() {
        LocalDateTime now = LocalDateTime.now().atZone(zonedDateTime.getZone()).toLocalDateTime();
        time = now.format(DateTimeFormatter.ofPattern("HH:mm"));
        date = now.format(DateTimeFormatter.ofPattern("M月d日"));
        chineseDate = cfwumd.print(ChineseCalendar.nowInSystemTime());
    }

    private void updateDateTime() {
        getNow();
        fragmentHomeBinding.timeTv.setText(time);
        fragmentHomeBinding.dateTv.setText(date);
        fragmentHomeBinding.chineseDateTv.setText(chineseDate);
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initAdapters() {
        List<LightDto> lightDtos = new ArrayList<>();
        lightDtos.add(new LightDto("照明", 1));
        lightDtos.add(new LightDto("广告灯", 0));
        lightAdapter = new LightAdapter(getActivity(), lightDtos, new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
            }
        }, R.layout.item_light_home);
        fragmentHomeBinding.homeLightRv.addItemDecoration(new GridSpacingItemDecoration(2, 10, true));
        fragmentHomeBinding.homeLightRv.setAdapter(lightAdapter);
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
