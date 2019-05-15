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
import com.teacher.vn.utils.CheckInternet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    EditText edt_username,edt_phone,edt_email,edt_password,edt_address,edt_confirmpassword;
    String userName, phone, email, password, address,confirmPassword;
    Button btn_sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        edt_username=findViewById(R.id.edt_username);
        edt_password=findViewById(R.id.edt_password);
        edt_confirmpassword=findViewById(R.id.edt_confirmpassword);
        edt_email=findViewById(R.id.edt_email);
        edt_phone=findViewById(R.id.edt_phone);
        edt_address=findViewById(R.id.edt_address);
        btn_sign_up=findViewById(R.id.btn_sign_up);

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckInternet.isNetworkAvailable(SignupActivity.this)) {
                    userName = edt_username.getText().toString();
                    phone = edt_phone.getText().toString();
                    password = edt_password.getText().toString();
                    address = edt_address.getText().toString();
                    email = edt_email.getText().toString();
                    confirmPassword=edt_confirmpassword.getText().toString();

                    if (!isNullOrEmpty(userName) && userName.length() > 5) {
                        if (!isNullOrEmpty(phone) && isNumeric(phone) && phone.length() == 10) {
                            if (!isNullOrEmpty(email) && isValid(email)) {
                                if (!isNullOrEmpty(address)) {
                                    if (!isNullOrEmpty(password) && password.length() > 5 && confirmPassword.equals(password)) {
                                        callApi(userName, password, phone, email, address);
                                    } else {
                                        Toast.makeText(SignupActivity.this, "Password không hợp lệ!", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(SignupActivity.this, "Address không hợp lệ!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(SignupActivity.this, "Email không hợp lệ!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignupActivity.this, "Phone không hợp lệ!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignupActivity.this, "Username không hợp lệ!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignupActivity.this, "Vui lòng kiểm tra mạng!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    private static boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    private static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
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
                    }else{
                        Toast.makeText(SignupActivity.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }
}
