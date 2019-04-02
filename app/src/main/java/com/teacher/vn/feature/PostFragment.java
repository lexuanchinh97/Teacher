package com.teacher.vn.feature;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.teacher.vn.R;
import com.teacher.vn.config.CallApi;
import com.teacher.vn.model.CustomerPost;
import com.teacher.vn.model.CustomerPostResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class PostFragment extends Fragment {
    private Spinner spn_district,spn_gender;
    private EditText edt_name,edt_email,edt_phone,edt_job,edt_university,edt_salary,edt_address,edt_number,edt_time;
    private CheckBox chk_class1,chk_class2,chk_class3,chk_class4,chk_class5,chk_class6
            ,chk_class7,chk_class8,chk_class9,chk_class10,chk_class11,chk_class12;
    private CheckBox chk_toan,chk_hoa,chk_ly,chk_anh,chk_van,chk_sinh,chk_tiengviet;
    private Button btn_post;
    private int districtId=-1;
    private int gender=-1;
    private String listClass="",listSubject="";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        btn_post=view.findViewById(R.id.btn_post);
        spn_district =  view.findViewById(R.id.spn_district);
        spn_gender =  view.findViewById(R.id.spn_gender);
        edt_name =  view.findViewById(R.id.edt_name);
        edt_email =  view.findViewById(R.id.edt_email);
        edt_phone =  view.findViewById(R.id.edt_phone);
        edt_job =  view.findViewById(R.id.edt_job);
        edt_university =  view.findViewById(R.id.edt_university);
        edt_salary =  view.findViewById(R.id.edt_salary);
        edt_address =  view.findViewById(R.id.edt_address);
        edt_time =  view.findViewById(R.id.edt_time);
        edt_number =  view.findViewById(R.id.edt_number);

        chk_class1 =  view.findViewById(R.id.chk_class1);
        chk_class2 =  view.findViewById(R.id.chk_class2);
        chk_class3 =  view.findViewById(R.id.chk_class3);
        chk_class4 =  view.findViewById(R.id.chk_class4);
        chk_class5 =  view.findViewById(R.id.chk_class5);
        chk_class6 =  view.findViewById(R.id.chk_class6);
        chk_class7 =  view.findViewById(R.id.chk_class7);
        chk_class8 =  view.findViewById(R.id.chk_class8);
        chk_class9 =  view.findViewById(R.id.chk_class9);
        chk_class10 =  view.findViewById(R.id.chk_class10);
        chk_class11 =  view.findViewById(R.id.chk_class11);
        chk_class12 =  view.findViewById(R.id.chk_class12);

        chk_toan =  view.findViewById(R.id.chk_toan);
        chk_hoa =  view.findViewById(R.id.chk_hoa);
        chk_ly =  view.findViewById(R.id.chk_ly);
        chk_anh =  view.findViewById(R.id.chk_anh);
        chk_van =  view.findViewById(R.id.chk_van);
        chk_sinh =  view.findViewById(R.id.chk_sinh);
        chk_tiengviet =  view.findViewById(R.id.chk_tiengviet);

        List<String> listDistrict=new ArrayList<>();
        listDistrict.add("Chọn Quận");
        listDistrict.add("Quận 1");
        listDistrict.add("Quận 2");
        listDistrict.add("Quận 3");
        listDistrict.add("Quận 4");
        listDistrict.add("Quận 5");
        listDistrict.add("Quận 6");
        listDistrict.add("Quận 7");
        listDistrict.add("Quận 8");
        listDistrict.add("Quận 9");
        listDistrict.add("Quận 10");
        listDistrict.add("Quận 11");
        listDistrict.add("Quận 12");

        List<String> listGender=new ArrayList<>();
        listGender.add("Chọn giới tính");
        listGender.add("Nam");
        listGender.add("Nữ");
        ArrayAdapter<String> adapterDictrict = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,listDistrict);
        ArrayAdapter<String> adapterGender = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,listGender);
        spn_district.setAdapter(adapterDictrict);
        spn_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0){
                    districtId=i;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spn_gender.setAdapter(adapterGender);
        spn_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0){
                    if(i==1){
                        gender=i;
                    }else gender=0;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_name.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Vui lòng nhập tên", Toast.LENGTH_SHORT).show();
                }else if(gender<0){
                    Toast.makeText(getActivity(), "Vui lòng giới tính", Toast.LENGTH_SHORT).show();
                }else if(edt_email.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Vui lòng email", Toast.LENGTH_SHORT).show();
                }else if(edt_phone.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Vui lòng phone", Toast.LENGTH_SHORT).show();
                }else if(edt_job.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Vui lòng job", Toast.LENGTH_SHORT).show();
                }else if(edt_university.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Vui lòng university", Toast.LENGTH_SHORT).show();
                }else if(edt_salary.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Vui lòng salary", Toast.LENGTH_SHORT).show();
                }else if(edt_time.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Vui lòng thời gian dạy", Toast.LENGTH_SHORT).show();
                }else if(edt_number.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Vui lòng số buổi dạy trong tuần", Toast.LENGTH_SHORT).show();
                }else if(districtId<0){
                    Toast.makeText(getActivity(), "Vui lòng quận", Toast.LENGTH_SHORT).show();
                }else if(edt_address.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Vui lòng address", Toast.LENGTH_SHORT).show();
                }else if(!chk_class1.isChecked() && !chk_class2.isChecked()&& !chk_class3.isChecked()&& !chk_class4.isChecked()
                        && !chk_class5.isChecked()&& !chk_class6.isChecked()&& !chk_class7.isChecked()&& !chk_class8.isChecked()
                        && !chk_class9.isChecked()&& !chk_class10.isChecked()&& !chk_class11.isChecked()&& !chk_class12.isChecked()){
                    Toast.makeText(getActivity(), "Vui lòng chọn lớp", Toast.LENGTH_SHORT).show();
                }else if(!chk_toan.isChecked()&& !chk_hoa.isChecked()&& !chk_ly.isChecked()
                        && !chk_anh.isChecked()&& !chk_van.isChecked()&& !chk_sinh.isChecked()&& !chk_tiengviet.isChecked()){
                    Toast.makeText(getActivity(), "Vui lòng chọn môn", Toast.LENGTH_SHORT).show();
                }else{
                    CustomerPost customerPost=new CustomerPost();
                    customerPost.setName(edt_name.getText().toString());
                    customerPost.setGender(gender);
                    customerPost.setEmail(edt_email.getText().toString());
                    customerPost.setPhone(edt_phone.getText().toString());
                    customerPost.setJob(edt_job.getText().toString());
                    customerPost.setUniversity(edt_university.getText().toString());
                    customerPost.setSalary(Integer.parseInt(edt_salary.getText().toString()));
                    customerPost.setDistrictId(districtId);
                    customerPost.setAddress(edt_address.getText().toString());
                    customerPost.setTime(edt_time.getText().toString());
                    customerPost.setNumber(Integer.parseInt(edt_number.getText().toString()));
                    SharedPreferences preferences=getActivity().getSharedPreferences("token",MODE_PRIVATE);
                    String username=preferences.getString("username","");
                    customerPost.setUsername(username);
                    if(chk_class1.isChecked()){
                        listClass+=",1";
                    }
                    if(chk_class2.isChecked()){
                        listClass+=",2";
                    }
                    if(chk_class3.isChecked()){
                        listClass+=",3";
                    }
                    if(chk_class4.isChecked()){
                        listClass+=",4";
                    }
                    if(chk_class5.isChecked()){
                        listClass+=",5";
                    }
                    if(chk_class6.isChecked()){
                        listClass+=",6";
                    }
                    if(chk_class7.isChecked()){
                        listClass+=",7";
                    }
                    if(chk_class8.isChecked()){
                        listClass+=",8";
                    }
                    if(chk_class9.isChecked()){
                        listClass+=",9";
                    }
                    if(chk_class10.isChecked()){
                        listClass+=",10";
                    }
                    if(chk_class11.isChecked()){
                        listClass+=",11";
                    }
                    if(chk_class12.isChecked()){
                        listClass+=",12";
                    }
                  //  Toast.makeText(getActivity(), listClass.substring(1), Toast.LENGTH_SHORT).show();

                    customerPost.setListClassId(listClass.substring(1));
                    listClass="";

                    if(chk_toan.isChecked()){
                        listSubject+=",1";
                    }
                    if(chk_tiengviet.isChecked()){
                        listSubject+=",2";
                    }
                    if(chk_ly.isChecked()){
                        listSubject+=",3";
                    }
                    if(chk_hoa.isChecked()){
                        listSubject+=",4";
                    }
                    if(chk_anh.isChecked()){
                        listSubject+=",5";
                    }
                    if(chk_sinh.isChecked()){
                        listSubject+=",6";
                    }
                    if(chk_van.isChecked()){
                        listSubject+=",7";
                    }
                    customerPost.setListSubjectId(listSubject.substring(1));
                    listSubject="";
                    callApi(customerPost);
                }
            }
        });




        return view;
    }

    private void callApi(CustomerPost customerPost) {
        CallApi.getInstance().customerPost(customerPost).enqueue(new Callback<CustomerPostResponse>() {
            @Override
            public void onResponse(Call<CustomerPostResponse> call, Response<CustomerPostResponse> response) {
                if(response.body()!=null){
                    if(response.body().getStatus()==200){
                        Toast.makeText(getActivity(), "post thành công", Toast.LENGTH_SHORT).show();
                    }else Toast.makeText(getActivity(), "post ko thành công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CustomerPostResponse> call, Throwable t) {

            }
        });
    }


}
