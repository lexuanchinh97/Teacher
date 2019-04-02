package com.teacher.vn.authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.teacher.vn.R;
import com.teacher.vn.config.CallApi;
import com.teacher.vn.model.BaseResponse;
import com.teacher.vn.model.Customer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    EditText edt_username,edt_phone,edt_email,edt_password,edt_address;
    Button btn_sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        edt_username=findViewById(R.id.edt_username);
        edt_password=findViewById(R.id.edt_password);
        edt_email=findViewById(R.id.edt_email);
        edt_phone=findViewById(R.id.edt_phone);
        edt_address=findViewById(R.id.edt_address);
        btn_sign_up=findViewById(R.id.btn_sign_up);

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callApi(edt_username.getText().toString(),edt_password.getText().toString(),
                        edt_phone.getText().toString(),edt_email.getText().toString(),edt_address.getText().toString());
            }
        });
    }

    private void callApi(String username, String password, String phone, String email,String address) {
        Customer customer=new Customer();
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setUsername(username);
        customer.setPhone(phone);
        customer.setPassword(password);
        CallApi.getInstance().signup(customer).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(response.body()!=null){
                   if(response.body().getStatus()==200){
                       Toast.makeText(SignupActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(SignupActivity.this,SigninActivity.class));
                   }

                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }
}
