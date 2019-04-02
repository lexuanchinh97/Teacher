package com.teacher.vn.feature;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teacher.vn.R;
import com.teacher.vn.authentication.SigninActivity;
import com.teacher.vn.config.CallApi;
import com.teacher.vn.model.Customer;
import com.teacher.vn.model.ProfileCustomer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {
    Customer customer;
    ConstraintLayout logout;
    TextView txt_username,txt_phone,txt_address,txt_email;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        txt_username=view.findViewById(R.id.txt_username);
        txt_address=view.findViewById(R.id.txt_address);
        txt_phone=view.findViewById(R.id.edt_phone);
        txt_email=view.findViewById(R.id.edt_email);
        logout=view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SigninActivity.class));
            }
        });
        SharedPreferences preferences=getActivity().getSharedPreferences("token",MODE_PRIVATE);
        String token=preferences.getString("token","");
        callApi(token);
        return view;
    }

    private void callApi(String token) {
        CallApi.getInstance().profile("Bearer "+token).enqueue(new Callback<ProfileCustomer>() {
            @Override
            public void onResponse(Call<ProfileCustomer> call, Response<ProfileCustomer> response) {
                if(response.body()!=null){
                    customer=response.body().getData();
                    txt_address.setText(customer.getAddress());
                    txt_email.setText(customer.getEmail());
                    txt_phone.setText(customer.getPhone());
                    txt_username.setText(customer.getUsername());
                }
            }

            @Override
            public void onFailure(Call<ProfileCustomer> call, Throwable t) {

            }
        });
    }
}
