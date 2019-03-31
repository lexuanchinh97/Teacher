package com.teacher.vn.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teacher.vn.R;
import com.teacher.vn.model.Teacher;

import java.util.List;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.ViewHolder>{
    private Context context;
    List<Teacher> data;
    private TeacherAdapter.IClickListener listener;
    public TeacherAdapter(Context context){
        this.context=context;
    }
    public void setListener(IClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<Teacher>data){
        this.data=data;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TeacherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_teacher,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherAdapter.ViewHolder viewHolder, int position) {
        Teacher teacher=data.get(position);
        if(teacher!=null){
            Glide.with(context).load("https://img.freepik.com/free-vector/lovely-world-teachers-day-composition-with-flat-design_23-2147895190.jpg?size=338&ext=jpg").into(viewHolder.img_avatar);
            viewHolder.txt_name.setText(teacher.getName());
            viewHolder.txt_address.setText(teacher.getAddress());
            viewHolder.txt_job.setText(teacher.getJob());
            viewHolder.txt_salary.setText(teacher.getSalary().toString());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name,txt_address,txt_salary,txt_job;
        ImageView img_avatar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name=itemView.findViewById(R.id.txt_name);
            txt_address=itemView.findViewById(R.id.txt_address);
            txt_salary=itemView.findViewById(R.id.txt_salary);
            txt_job=itemView.findViewById(R.id.txt_job);
            img_avatar=itemView.findViewById(R.id.img_avatar);
        }
    }
    public interface IClickListener {
        void onItemClick(Teacher teacher);
    }
}
