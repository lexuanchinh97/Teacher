package com.teacher.vn.config;

import com.teacher.vn.model.BaseResponse;
import com.teacher.vn.model.ChangePassword;
import com.teacher.vn.model.Customer;
import com.teacher.vn.model.CustomerPost;
import com.teacher.vn.model.CustomerPostResponse;
import com.teacher.vn.model.ProfileCustomer;
import com.teacher.vn.model.ResponseSignin;
import com.teacher.vn.model.SigninCustomer;
import com.teacher.vn.model.Teacher;
import com.teacher.vn.model.TeacherResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LinkApi {
    @POST("customers/signin")
    Call<ResponseSignin> signin(@Body SigninCustomer loginUser);

    @POST("customers/signup")
    Call<BaseResponse> signup(@Body Customer customer);

    @GET("docs/teachers/search")
    Call<TeacherResponse> search(@Query("subjectId") int subjectId
            ,@Query("subjectId") int districtId, @Query("gender") int gender,@Query("classId") int classId);
    @GET("customers/profile")
    Call<ProfileCustomer> profile(@Header("Authorization") String token);

    @GET("docs/teachers/{id}")
    Call<Teacher> findOneTeacher(@Path("id") int id);

    @GET("docs/teachers/get-post")
    Call<TeacherResponse> getPost(@Query("username") String username);

    @POST("docs/teachers/create")
    Call<CustomerPostResponse> customerPost(@Body CustomerPost request);

    @POST("customers/change-password")
    Call<BaseResponse> changePassword(@Header("Authorization") String token, @Body ChangePassword changePassword);

}
