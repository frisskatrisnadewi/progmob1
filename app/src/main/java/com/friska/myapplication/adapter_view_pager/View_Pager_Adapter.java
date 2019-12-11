package com.friska.myapplication.adapter_view_pager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.friska.myapplication.R;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class View_Pager_Adapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images = {R.drawable.cdres2,R.drawable.cdres3,R.drawable.cdres4,R.drawable.cdres1};

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    public View_Pager_Adapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.courusel_layout,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageViewcarousel);
        imageView.setImageResource(images[position]);
        ViewPager vp = (ViewPager) container;
        vp.addView(view,0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull View container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
