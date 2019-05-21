package com.teacher.vn.config;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CallApi {
    public static LinkApi instance=null;
    //  public static String BASE_URL = "http://192.168.1.169:8080/api/";
    public static String BASE_URL = "http://teacher.us-east-1.elasticbeanstalk.com/api/";
   // public static String BASE_URL = "https://apieteacher.herokuapp.com/api/";
    public static LinkApi getInstance() {
        if (instance == null) {
            synchronized (CallApi.class) {
                if (instance == null) {
                    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                    httpClient.writeTimeout(15 * 60 * 1000, TimeUnit.MILLISECONDS);
                    httpClient.readTimeout(60 * 1000, TimeUnit.MILLISECONDS);
                    httpClient.connectTimeout(20 * 1000, TimeUnit.MILLISECONDS);

                    OkHttpClient client = httpClient.build();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(client)
                            .build();
                    instance = retrofit.create(LinkApi.class);
                }
            }
        }
        return instance;
    }
}
