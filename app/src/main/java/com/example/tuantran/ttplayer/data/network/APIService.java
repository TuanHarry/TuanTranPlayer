package com.example.tuantran.ttplayer.data.network;

public class APIService {
    private static String base_url = "https://mlalala.000webhostapp.com/Server/";

    // ket hop
    public static Dataservice getService(){
        // Khoi tao nhung phuong thuc trong dataservice
        return  APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
