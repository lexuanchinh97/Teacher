package com.teacher.vn.config;

import com.teacher.vn.model.ResponseSignin;
import com.teacher.vn.model.SigninCustomer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LinkApi {
    @POST("customers/signin")
    Call<ResponseSignin> signin(@Body SigninCustomer loginUser);
}
