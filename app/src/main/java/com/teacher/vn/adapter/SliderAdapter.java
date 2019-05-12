package com.teacher.vn.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.teacher.vn.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    public SliderAdapter(Context context){
        this.context = context;
    }

    //Array
    public int[] slider_images ={
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground
    };
    public String[] slide_heading = {
            "Tìm kiếm gia sư nhanh chóng",
            "Nhiều sự lựa chon",
            "Liên hệ trực tiếp"
    };
    public String[] slide_desc ={
            "Ứng dụng tìm kiếm gia sư trực tiếp không cần phải thông qua bất cứ trung gian nào",
            "Có nhiều gia sư chất lượng cho bạn lựa chọn",
            "Liên hệ bằng cách gọi điện cho gia sư bạn đã chọn"

    };

    @Override
    public int getCount() {
        return slide_heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView  slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeadingView = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescView =(TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slider_images[position]);
        slideHeadingView.setText(slide_heading[position]);
        slideDescView.setText(slide_desc[position]);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container,int position,Object object){
        container.removeView((RelativeLayout) object);
    }


}
