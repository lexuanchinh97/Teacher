package com.teacher.vn.feature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.teacher.vn.R;
import com.teacher.vn.config.CallApi;
import com.teacher.vn.model.Teacher;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherDetailActivity extends AppCompatActivity {
    TextView txt_name,txt_gender,txt_university,txt_job,txt_address;
    TextView txt_class,txt_subject,txt_district,txt_time,txt_number;
    Teacher teacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_detail);
        txt_name=findViewById(R.id.txt_name);
        txt_gender=findViewById(R.id.txt_gender);
        txt_university=findViewById(R.id.txt_university);
        txt_job=findViewById(R.id.txt_job);
        txt_address=findViewById(R.id.txt_address);
        txt_class=findViewById(R.id.txt_class);
        txt_subject=findViewById(R.id.txt_subject);
        txt_district=findViewById(R.id.txt_district);
        txt_time=findViewById(R.id.txt_time);
        txt_number=findViewById(R.id.txt_number);
        int id=1;
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            id = extras.getInt("id");
        }
        callApi(id);
    }

    private void callApi(int id) {
        CallApi.getInstance().findOneTeacher(id).enqueue(new Callback<Teacher>() {
            @Override
            public void onResponse(Call<Teacher> call, Response<Teacher> response) {
                if(response.body()!=null){
                    teacher=response.body();
                    txt_name.setText(teacher.getName());
                    if(teacher.getGender()==1){
                        txt_gender.setText("Nam");
                    }else txt_gender.setText("Nữ");
                    txt_university.setText(teacher.getUniversity());
                    txt_job.setText(teacher.getJob());
                    txt_address.setText(teacher.getAddress());
                    txt_time.setText(teacher.getTime());
                    txt_number.setText(teacher.getNumber().toString());
                }
            }

            @Override
            public void onFailure(Call<Teacher> call, Throwable t) {

            }
        });
    }
}
