package com.example.tuantran.ttplayer.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuantran.ttplayer.Contanst;
import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.data.model.QuangCaoModel;
import com.example.tuantran.ttplayer.data.model.event.SendIDEventBus;
import com.example.tuantran.ttplayer.ui.Online.playlistonline.ListFragment;
import com.example.tuantran.ttplayer.ui.main.Main2Activity;
import com.example.tuantran.ttplayer.utils.glide.ImageHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class BannerAdapter extends PagerAdapter {

    private Context context;
    private List<QuangCaoModel> list;
    Main2Activity main;

    public BannerAdapter(Context context, List<QuangCaoModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    // tra ve view
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }


    // Dinh hinh object
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        main = (Main2Activity) context;
        LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.line_banner, null);

        ImageView imgBackground = view.findViewById(R.id.imageviewbackground);
        ImageView imgSongbanner = view.findViewById(R.id.imgbanner);
        TextView tvTitleBanner = view.findViewById(R.id.tv_title_banner);
        TextView tvContainBanner = view.findViewById(R.id.tv_contain_banner);

        ImageHelper.load(context,imgBackground,list.get(position).getHinhAnh());
        ImageHelper.load(context,imgSongbanner,list.get(position).getHinhBaiHat());
        tvTitleBanner.setText(list.get(position).getTenBaiHat());
        tvContainBanner.setText(list.get(position).getNoiDung());

        // Setonclick
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = list.get(position).getIDQuangCao();
                String name = list.get(position).getTenBaiHat();
                String img = list.get(position).getHinhAnh();
                main.mNavController.pushFragment(ListFragment.newInstance(id,Contanst.INTENT_BANNER, name,img));
            }
        });

        container.addView(view);
        return view;
    }

    // Destroy


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
