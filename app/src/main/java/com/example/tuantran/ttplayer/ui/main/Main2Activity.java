package com.example.tuantran.ttplayer.ui.main;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.data.model.SongModel;
import com.example.tuantran.ttplayer.ui.Offline.local.HomeFragment;
import com.example.tuantran.ttplayer.ui.Online.network.NetWorkFragment;
import com.example.tuantran.ttplayer.ui.base.activity.BaseActivity;
import com.example.tuantran.ttplayer.ui.base.fragment.BaseFragment;
import com.example.tuantran.ttplayer.ui.setting.SettingFragment;
import com.ncapdevi.fragnav.FragNavController;
import com.ncapdevi.fragnav.FragNavTransactionOptions;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

public class Main2Activity extends BaseActivity implements IMain, OnTabReselectListener, OnTabSelectListener, BaseFragment.FragNavListener, FragNavController.RootFragmentListener {

    @BindView(R.id.sliding_layout)
    SlidingUpPanelLayout slidingLayout;

    @BindView(R.id.bottomBar)
    BottomBar bottomBarMain;

    @BindView(R.id.contentContainer)
    FrameLayout contentMain;

    @BindView(R.id.layout_bottom_bar)
    LinearLayout layoutBottomBar;


    HomeFragment homeFragment = HomeFragment.newInstance();
    NetWorkFragment wifiTransferFragment = NetWorkFragment.newInstance();
    SettingFragment settingsFragment = SettingFragment.getInstance();

    private final int TAB_LOCAL_SONG = FragNavController.TAB1;
    private final int TAB_NETWORK = FragNavController.TAB2;
    private final int TAB_SETTINGS = FragNavController.TAB3;
    private List<Fragment> fragments = new ArrayList<>();

    List<SongModel> data = new ArrayList<>();
    public FragNavController mNavController;

    @Override
    protected int getContentView() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        initBottomBar(savedInstanceState);
        initSlidingLayout();
    }

    @Override
    protected void initControls() {
        bottomBarMain.setOnTabSelectListener(this);
        bottomBarMain.setOnTabReselectListener(this);
    }

    @Override
    public int getNumberOfRootFragments() {
        return 3;
    }

    @NotNull
    @Override
    public Fragment getRootFragment(int i) {
        switch (i) {

            case TAB_LOCAL_SONG:
                return homeFragment;

            case TAB_NETWORK:
                return wifiTransferFragment;

            case TAB_SETTINGS:
                return settingsFragment;

            default:
                return homeFragment;
        }
    }

    @Override
    public void onTabReSelected(int tabId) {
        if (mNavController.isRootFragment()) {
            return;
        }

        FragNavTransactionOptions.Builder builder = new FragNavTransactionOptions.Builder();
        builder.setPopExitAnimation(R.anim.pop_exit);
        builder.setPopEnterAnimation(R.anim.pop_enter);
        FragNavTransactionOptions fragNavTransactionOptions = builder.build();
        mNavController.clearStack(fragNavTransactionOptions);
    }

    @Override
    public void onTabSelected(int tabId) {
        switch (tabId) {
            case R.id.tab_local_songs:
                mNavController.switchTab(TAB_LOCAL_SONG);
                break;

            case R.id.tab_network:
                mNavController.switchTab(TAB_NETWORK);
                break;

            case R.id.tab_settings:
                mNavController.switchTab(TAB_SETTINGS);
                break;
        }
    }

    @Override
    public void pushFragment(Fragment fragment) {
        mNavController.pushFragment(fragment);
    }

    @Override
    public void popFragment() {

    }

    @Override
    public void showDialogFragment(DialogFragment dialogFragment) {

    }

    @Override
    public void showBottomSheetDialogFragment(BottomSheetDialogFragment bottomSheetDialogFragment) {
        mNavController.showDialogFragment(bottomSheetDialogFragment);
    }

    @Override
    public void startActivity(Class clazz) {

    }

    private void initBottomBar(Bundle savedInstanceState) {

        fragments.add(homeFragment);
        fragments.add(wifiTransferFragment);
        fragments.add(settingsFragment);
        mNavController = new FragNavController(getSupportFragmentManager(), R.id.contentContainer);
        mNavController.setFragmentHideStrategy(FragNavController.HIDE);
        mNavController.setCreateEager(true);
        mNavController.setRootFragments(fragments);

        mNavController.initialize(TAB_LOCAL_SONG, savedInstanceState);

        for (int i = 0; i < bottomBarMain.getTabCount(); i++) {
            BottomBarTab tab = bottomBarMain.getTabAtPosition(i);
            tab.setGravity(Gravity.CENTER);
        }

    }

    private void initSlidingLayout() {

    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Objects.requireNonNull(mNavController.getCurrentStack()).size() > 1) {
                FragNavTransactionOptions.Builder builder = new FragNavTransactionOptions.Builder();
                builder.allowStateLoss(true);
                FragNavTransactionOptions fragNavTransactionOptions = builder.build();
                mNavController.popFragment(fragNavTransactionOptions);
            } else {
                super.onBackPressed();
            }
        }
    }
}
