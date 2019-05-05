package com.teacher.vn.authentication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.teacher.vn.MainActivity;
import com.teacher.vn.R;
import com.teacher.vn.config.CallApi;
import com.teacher.vn.model.BaseResponse;
import com.teacher.vn.model.ChangePassword;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText edt_oldpass,edt_newpass;
    Button btn_change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        edt_oldpass=findViewById(R.id.edt_oldpass);
        edt_newpass=findViewById(R.id.edt_newpass);
        btn_change=findViewById(R.id.btn_change);
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_oldpass.getText().toString().isEmpty()){
                    Toast.makeText(ChangePasswordActivity.this, "Vui lòng nhập mật khẩu cũ", Toast.LENGTH_SHORT).show();
                }else if(edt_newpass.getText().toString().isEmpty()){
                    Toast.makeText(ChangePasswordActivity.this, "Vui lòng nhập mật khẩu mới", Toast.LENGTH_SHORT).show();
                }else callApi(new ChangePassword(edt_oldpass.getText().toString(),edt_newpass.getText().toString()));
            }
        });

    }

    private void callApi(ChangePassword changePassword) {
        SharedPreferences preferences=getSharedPreferences("token",MODE_PRIVATE);
        String token=preferences.getString("token","");
        CallApi.getInstance().changePassword("Bearer "+token,changePassword).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(response.body()!=null){
                    if(response.body().getStatus()==200){
                        Toast.makeText(ChangePasswordActivity.this, "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ChangePasswordActivity.this, MainActivity.class));
                    }else
                        Toast.makeText(ChangePasswordActivity.this, "Mật khẩu cũ không chính xác", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(ChangePasswordActivity.this, "Mật khẩu cũ không chính xác", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }
}
