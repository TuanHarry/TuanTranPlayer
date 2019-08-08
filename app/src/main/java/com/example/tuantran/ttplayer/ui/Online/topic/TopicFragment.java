package com.example.tuantran.ttplayer.ui.Online.topic;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tuantran.ttplayer.Application;
import com.example.tuantran.ttplayer.Contanst;
import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.data.model.ChuDeModel;
import com.example.tuantran.ttplayer.data.model.ChuDeTheLoaiModel;
import com.example.tuantran.ttplayer.data.model.TheLoaiModel;
import com.example.tuantran.ttplayer.data.network.APIService;
import com.example.tuantran.ttplayer.data.network.Dataservice;
import com.example.tuantran.ttplayer.ui.Online.playlistonline.ListFragment;
import com.example.tuantran.ttplayer.ui.base.fragment.BaseFragment;
import com.example.tuantran.ttplayer.utils.glide.ImageHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopicFragment extends BaseFragment {


    @BindView(R.id.horizontalscroll)
    HorizontalScrollView horizontalScrollView;
    @BindView(R.id.tv_viewmore_playlist )
    TextView tvViewMore;

    public TopicFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic, container, false);
        ButterKnife.bind(this, view);
        initControls();
        return view;
    }


    private void initControls() {
        GetData();
        tvViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<ChuDeTheLoaiModel> call = dataservice.getListChuDeTheLoai();
        call.enqueue(new Callback<ChuDeTheLoaiModel>() {
            @Override
            public void onResponse(Call<ChuDeTheLoaiModel> call, Response<ChuDeTheLoaiModel> response) {
                ChuDeTheLoaiModel chuDeTheLoaiModel = response.body();
                List<ChuDeModel> listChude = new ArrayList<>();
                List<TheLoaiModel> listTheloai = new ArrayList<>();


                if (chuDeTheLoaiModel != null) {
                    listChude.addAll(chuDeTheLoaiModel.getChuDe());
                }
                if (chuDeTheLoaiModel != null) {
                    listTheloai.addAll(chuDeTheLoaiModel.getTheLoai());
                }

                LinearLayout linearLayout = new LinearLayout(Application.Context);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);


                // xet kich co layout
                int WIDTH = 580;
                int HEIGHT = 300;
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(WIDTH,HEIGHT);
                layoutParams.setMargins(10,20,10,20);
                for(int i = 0; i < listChude.size() ; i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10); // bo goc
                    ImageView img = new ImageView(getActivity());
                    img.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(listChude.get(i).getHinhChuDe()!= null){
                        ImageHelper.load(getActivity(),img,listChude.get(i).getHinhChuDe());
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(img);
                    linearLayout.addView(cardView);

                    final int finalI = i;

                    //TODO set onclick topic music
                    img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String id = listChude.get(finalI).getIDChuDe();
                            String nameChude = listChude.get(finalI).getTenChuDe();
                            AllTopicFragment fragment = AllTopicFragment.getInstance(id,nameChude);
                            getFragNav().pushFragment(fragment);
                        }
                    });


                }

                for(int k = 0; k < listTheloai.size() ; k++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10); // bo goc
                    ImageView img = new ImageView(getActivity());
                    img.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(listTheloai.get(k).getHinhTheLoai()!= null){
                        ImageHelper.load(getActivity(),img,listTheloai.get(k).getHinhTheLoai());
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(img);
                    linearLayout.addView(cardView);


                    //TODO set onclick for type music
                    final int finalK = k;
                    img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String id = listTheloai.get(finalK).getIDTheLoai();
                            String name = listTheloai.get(finalK).getTenTheLoai();
                            String urlImg = listTheloai.get(finalK).getHinhTheLoai();
                            getFragNav().pushFragment(ListFragment.newInstance(id, Contanst.INTENT_THELOAI,name,urlImg));
                        }
                    });

                }

                tvViewMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AllTopicFragment fragment =  AllTopicFragment.getInstanceAllTopic(listTheloai);
                        getFragNav().pushFragment(fragment);
                    }
                });

                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<ChuDeTheLoaiModel> call, Throwable t) {

            }
        });

    }


}
