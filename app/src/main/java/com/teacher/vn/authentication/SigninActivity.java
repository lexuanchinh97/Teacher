package com.teacher.vn.authentication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.teacher.vn.MainActivity;
import com.teacher.vn.R;
import com.teacher.vn.config.CallApi;
import com.teacher.vn.model.ResponseSignin;
import com.teacher.vn.model.SigninCustomer;
import com.teacher.vn.utils.CheckInternet;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninActivity extends AppCompatActivity {
    private Button btn_sign_in;
    private TextView tv_sign_up;
    private EditText edt_username,edt_password;
    ResponseSignin responseSignin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        btn_sign_in=findViewById(R.id.btn_sign_in);
        tv_sign_up=findViewById(R.id.tv_sign_up);
        edt_username=findViewById(R.id.edt_username);
        edt_password=findViewById(R.id.edt_password);
        tv_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SigninActivity.this,SignupActivity.class));
            }
        });
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckInternet.isNetworkAvailable(SigninActivity.this)) {
                    callApi(edt_username.getText().toString(), edt_password.getText().toString());
                } else {
                    Toast.makeText(SigninActivity.this, "Vui lòng kiểm tra mạng!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void callApi(String username,String password) {
        CallApi.getInstance().signin(new SigninCustomer(username,password)).enqueue(new Callback<ResponseSignin>() {
            @Override
            public void onResponse(Call<ResponseSignin> call, Response<ResponseSignin> response) {
                if(response.code()==200){
                    if(response.body()!=null){
                        responseSignin=response.body();
                        if(responseSignin.getStatus()==200) {
                            startActivity(new Intent(SigninActivity.this,MainActivity.class));
                        }
                    }else Toast.makeText(SigninActivity.this, "Username hoặc password không chính xác", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(SigninActivity.this, "Username hoặc password không chính xác", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseSignin> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveShareReference();
    }

    private void saveShareReference() {
        SharedPreferences preferences=getApplicationContext().getSharedPreferences("token",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        if(responseSignin!=null) {
            editor.putString("token", responseSignin.getResult().getToken());
            editor.putString("username", responseSignin.getResult().getUsername());
        }else editor.putString("token","");
        editor.commit();
        //  Toast.makeText(this, responseSignin.getResult().getToken(), Toast.LENGTH_SHORT).show();
    }
}
