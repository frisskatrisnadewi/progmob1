package com.friska.myapplication.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
//import android.support.annotation.Nullable;
//import android.support.annotation.NonNull;
//import android.support.v4.app.Fragment;
//import android.arch.lifecycle.Observer;
//import android.arch.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.friska.myapplication.DressListActivity;
import com.friska.myapplication.HomeActivity;
import com.friska.myapplication.R;
import com.friska.myapplication.adapter_view_pager.View_Pager_Adapter;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;
    private ImageView img1;
    private ImageView imgDress;
    private TextView txt;
    ViewPager viewPager;
    CarouselView carouselView;
    int[] co = {R.drawable.cdres5, R.drawable.cdres4, R.drawable.cdres3};


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
/*        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        imgDress = (ImageView) root.findViewById(R.id.imr1);
        txt = (TextView) root.findViewById(R.id.tvd1);
        carouselView = (CarouselView)root.findViewById(R.id.v_flipper);
        carouselView.setPageCount(co.length);
        carouselView.setImageListener(imageListener);

/*        viewPager = (ViewPager) root.findViewById(R.id.v_flipper);
        View_Pager_Adapter viewPagerAdapter = new View_Pager_Adapter(requireContext());
        viewPager.setAdapter(viewPagerAdapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),2000,4000);*/

        /*        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        /*img1 = (ImageView) getActivity().findViewById(R.id.imagebig);
        Glide.with(this).load(R.drawable.bridesmaid).into(img1);*/
        click();
        return root;
    }

    ImageListener imageListener = new ImageListener(){
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(co[position]);

        }
    };

    private void click(){
        imgDress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentLogin = new Intent(requireContext(), DressListActivity.class);
                startActivity(intentLogin);
/*              ListDressFragment fragment2 = new ListDressFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/

/*                ListWeddingFragment homeFragment = new ListWeddingFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,homeFragment)
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();*/


            }
        });
    }

    @Override
    public void onClick(View v) {

    }

/*    private class MyTimerTask extends TimerTask{
        @Override
        public void run() {
            req.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem()==0){
                        viewPager.setCurrentItem(1);
                    }else if(viewPager.getCurrentItem()==1){
                        viewPager.setCurrentItem(2);
                    }else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });


        }
    }*/

}