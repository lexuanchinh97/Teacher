package com.teacher.vn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teacher.vn.adapter.TeacherAdapter;
import com.teacher.vn.config.CallApi;
import com.teacher.vn.model.Teacher;
import com.teacher.vn.model.TeacherResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    RecyclerView rv_teacher;
    TeacherAdapter adapter;
    List<Teacher> teachers;
    int subjectId=-1;
    int districtId=-1;
    int gender=-1;
    int classId=-1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rv_teacher=view.findViewById(R.id.rv_teacher);
        callApi(subjectId, districtId, gender, classId);
        if(teachers == null) {
            setUpListView();
            callApi(subjectId, districtId, gender, classId);

        } else {
            //there is already data? screen must be rotating or tab switching
            adapter.setData(teachers);
        }
        return view;
    }

    private void callApi(int subjectId, int districtId, int gender, int classId) {
        CallApi.getInstance().search(subjectId,districtId,gender,classId).enqueue(new Callback<TeacherResponse>() {
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
