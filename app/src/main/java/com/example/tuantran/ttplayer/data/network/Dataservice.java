package com.example.tuantran.ttplayer.data.network;


import com.example.tuantran.ttplayer.data.model.AlbumModel;
import com.example.tuantran.ttplayer.data.model.ChuDeModel;
import com.example.tuantran.ttplayer.data.model.ChuDeTheLoaiModel;
import com.example.tuantran.ttplayer.data.model.PlayListOnlineModel;
import com.example.tuantran.ttplayer.data.model.QuangCaoModel;
import com.example.tuantran.ttplayer.data.model.SongModel;
import com.example.tuantran.ttplayer.data.model.TheLoaiModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {

    @GET("SongBanner.php")
    Call<List<QuangCaoModel>> getDataBanner();

    @GET("randomplaylist.php")
    Call<List<PlayListOnlineModel>> getPlayList();

    @GET("APIChudeTheLoai.php")
    Call<ChuDeTheLoaiModel> getListChuDeTheLoai();

    @GET("APIAlbum.php")
    Call<List<AlbumModel>> getAlbum();

    @GET("APIFavoriteSong.php")
    Call<List<SongModel>> getBaiHatFavorite();

    @FormUrlEncoded
    @POST("APIDanhSachBaiHat.php")
    Call<List<SongModel>> getListSongBanner(@Field("IDQuangCao") String id);

    @FormUrlEncoded
    @POST("APIDanhSachBaiHat.php")
    Call<List<SongModel>> getListSongPlaylist(@Field("IDPlayList") String id);

    @GET("APIDanhSachPlaylist.php")
    Call<List<PlayListOnlineModel>> getAllPlayList();

    @FormUrlEncoded
    @POST("APIDanhSachBaiHat.php")
    Call<List<SongModel>> getTheLoai(@Field("IDTheLoai") String id);

    @GET("APIDanhSachChuDe.php")
    Call<List<ChuDeModel>> getAllListChuDe();

    @FormUrlEncoded
    @POST("APIChuDeTheoTheLoai.php")
    Call<List<TheLoaiModel>> getTheLoaiTheoChuDe(@Field("IDChuDe") String idChuDe);

    @GET("APIAllAlbum.php")
    Call<List<AlbumModel>> getAllAlbum();

    @FormUrlEncoded
    @POST("APIDanhSachBaiHat.php")
    Call<List<SongModel>> getBaiHatTheoAlbum(@Field("IDAlbum") String idAlbum);

    @FormUrlEncoded
    @POST("APIUpdateLuotThich.php")
    Call<String> updateLuotThich(@Field("LuotThich") String luotThich, @Field("IDBaiHat") String idBaiHat);

}
