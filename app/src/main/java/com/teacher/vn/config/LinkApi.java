package com.teacher.vn.config;

import com.teacher.vn.model.ProfileCustomer;
import com.teacher.vn.model.ResponseSignin;
import com.teacher.vn.model.SigninCustomer;
import com.teacher.vn.model.TeacherResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LinkApi {
    @POST("customers/signin")
    Call<ResponseSignin> signin(@Body SigninCustomer loginUser);

    @GET("docs/teachers/search")
    Call<TeacherResponse> search(@Query("subjectId") int subjectId
            ,@Query("subjectId") int districtId, @Query("gender") int gender,@Query("classId") int classId);
    @GET("customers/profile")
    Call<ProfileCustomer> profile(@Header("Authorization") String token);
}
