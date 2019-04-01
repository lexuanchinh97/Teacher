package com.teacher.vn.feature;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teacher.vn.R;
import com.teacher.vn.adapter.TeacherAdapter;
import com.teacher.vn.config.CallApi;
import com.teacher.vn.model.Teacher;
import com.teacher.vn.model.TeacherResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class SaveFragment extends Fragment {
    private RecyclerView rv_teacher;
    private TeacherAdapter adapter;
    private List<Teacher> teachers;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_save, container, false);
        rv_teacher=view.findViewById(R.id.rv_teacher);
        SharedPreferences preferences=getActivity().getSharedPreferences("token",MODE_PRIVATE);
        String username=preferences.getString("username","");
        callApi(username);
        if(teachers == null) {
            setUpListView();
            callApi(username);

        } else {
            //there is already data? screen must be rotating or tab switching
            adapter.setData(teachers);
        }
        return view;
    }

    private void callApi(String username) {
        CallApi.getInstance().getPost(username).enqueue(new Callback<TeacherResponse>() {
            @Override
            public void onResponse(Call<TeacherResponse> call, Response<TeacherResponse> response) {
                if(response.body()!=null){
                    teachers=response.body().getData();
                    adapter.setData(teachers);
                }
            }

            @Override
            public void onFailure(Call<TeacherResponse> call, Throwable t) {

            }
        });
    }
    private void setUpListView() {
        teachers=new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv_teacher.setLayoutManager(mLayoutManager);
        adapter=new TeacherAdapter(getActivity());
        adapter.setData(teachers);
        adapter.setListener(new TeacherAdapter.IClickListener() {
            @Override
            public void onItemClick(Teacher teacher) {
                Intent intent=new Intent(getActivity(),TeacherDetailActivity.class);
                intent.putExtra("id",teacher.getId());
                startActivity(intent);
            }
        });
        this.rv_teacher.setAdapter(adapter);
    }
}
