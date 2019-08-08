package com.example.tuantran.ttplayer.ui.Online.banner;


import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.tuantran.ttplayer.Application;
import com.example.tuantran.ttplayer.MediaController;
import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.data.model.QuangCaoModel;
import com.example.tuantran.ttplayer.data.network.APIService;
import com.example.tuantran.ttplayer.data.network.Dataservice;
import com.example.tuantran.ttplayer.ui.adapter.BannerAdapter;
import com.example.tuantran.ttplayer.ui.base.BaseFragment;

import java.util.List;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BannerFragment extends BaseFragment {


    @BindView(R.id.default_indicator)
    CircleIndicator circleIndicator;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tv_title_banner)
    TextView titleBanner;

    BannerAdapter bannerAdapter;
    Runnable runnable;
    Handler handler;
    int currentItem;
    public BannerFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_banner;
    }

    @Override
    protected void initControls() {
        super.initControls();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<QuangCaoModel>> callback = dataservice.getDataBanner();
        callback.enqueue(new Callback<List<QuangCaoModel>>() {
            @Override
            public void onResponse(Call<List<QuangCaoModel>> call, Response<List<QuangCaoModel>> response) {
                List<QuangCaoModel> dataBanner = response.body();
                bannerAdapter = new BannerAdapter(getActivity(),dataBanner);
                viewPager.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(viewPager);
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        currentItem = viewPager.getCurrentItem();
                        currentItem++;
                        if(currentItem >= viewPager.getAdapter().getCount()){
                            currentItem = 0;
                        }
                        viewPager.setCurrentItem(currentItem,true);
                        handler.postDelayed(runnable, 3000);
                    }
                };
                handler.postDelayed(runnable,3000);
            }

            @Override
            public void onFailure(Call<List<QuangCaoModel>> call, Throwable t) {

            }
        });


    }

}
