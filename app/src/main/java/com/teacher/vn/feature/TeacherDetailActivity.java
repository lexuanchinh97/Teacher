package com.teacher.vn.feature;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.teacher.vn.R;
import com.teacher.vn.config.CallApi;
import com.teacher.vn.model.Teacher;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherDetailActivity extends AppCompatActivity {
    private TextView txt_name,txt_gender,txt_university,txt_job,txt_address;
    private TextView txt_class,txt_subject,txt_district,txt_time,txt_number;
    private Teacher teacher;
    private Button btn_phone;
    private String phone="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_detail);
        txt_name=findViewById(R.id.edt_name);
        txt_gender=findViewById(R.id.txt_gender);
        txt_university=findViewById(R.id.txt_university);
        txt_job=findViewById(R.id.edt_job);
        txt_address=findViewById(R.id.txt_address);
        txt_class=findViewById(R.id.txt_class);
        txt_subject=findViewById(R.id.txt_subject);
        txt_district=findViewById(R.id.txt_district);
        txt_time=findViewById(R.id.txt_time);
        txt_number=findViewById(R.id.txt_number);
        btn_phone=findViewById(R.id.btn_phone);
        btn_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });
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
                String listSubject="";
                String listClass="";
                if(response.body()!=null){

                    teacher=response.body();
                    for(int i=0;i<teacher.getSubjects().size();i++){
                        if(i+1!=teacher.getSubjects().size()){
                            listSubject+=teacher.getSubjects().get(i).getName()+", ";
                        }else listSubject+=teacher.getSubjects().get(i).getName();

                    }
                    for(int i=0;i<teacher.getClassMate().size();i++){
                        if(i+1!=teacher.getClassMate().size()){
                            listClass+=teacher.getClassMate().get(i).getName()+", ";
                        }else listClass+=teacher.getClassMate().get(i).getName();
                    }
                    txt_subject.setText(listSubject);
                    txt_class.setText(listClass);
                    txt_name.setText(teacher.getName());
                    if(teacher.getGender()==1){
                        txt_gender.setText("Nam");
                    }else txt_gender.setText("Nữ");
                    txt_university.setText(teacher.getUniversity());
                    txt_job.setText(teacher.getJob());
                    txt_address.setText(teacher.getAddress());
                    txt_time.setText(teacher.getTime());
                    txt_number.setText(teacher.getNumber().toString());
                    txt_district.setText(teacher.getDistrict().getName());
                    phone=teacher.getPhone();
                }
            }

            @Override
            public void onFailure(Call<Teacher> call, Throwable t) {

            }
        });
    }
}
