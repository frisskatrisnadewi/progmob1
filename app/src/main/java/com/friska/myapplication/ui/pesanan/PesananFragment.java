package com.friska.myapplication.ui.pesanan;

import android.os.Bundle;
//import android.support.v7.widget.CardView;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
//import android.support.annotation.Nullable;
//import android.support.annotation.NonNull;
//import android.support.v4.app.Fragment;
//import android.arch.lifecycle.Observer;
//import android.arch.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.friska.myapplication.R;
import com.friska.myapplication.adapter_recyclerview.ListDressAdapter;
import com.friska.myapplication.model_recyclerview.ListDressModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PesananFragment extends Fragment {

    private PesananViewModel dashboardViewModel;
    List<ListDressModel> listmain = new ArrayList<>();
    ListDressAdapter madapter;
    RecyclerView mrecycle;
    SearchView searchView;
    CardView cv_pesanan;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       /* dashboardViewModel =
                ViewModelProviders.of(this).get(PesananViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_pesanan, container, false);
        /*final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        cv_pesanan = root.findViewById(R.id.pesanan_cardview);
        mrecycle = root.findViewById(R.id.recycler_pesanan);
        addData();
        setupRecycle();

        return root;
    }

    public void addData(){
        listmain = new ArrayList<>();
        listmain.add(new ListDressModel("Black Luxury Long Dress", "Long Dress", R.drawable.longdress));
        listmain.add(new ListDressModel("Fresh Colour Dress", "Short", R.drawable.cdres3));
        listmain.add(new ListDressModel("Trendy Beauty Dress", "Short", R.drawable.cdres2));

    }

    public void setupRecycle(){
        madapter = new ListDressAdapter(requireContext(), listmain);
        mrecycle.setLayoutManager(new GridLayoutManager(requireContext(), 1));
        mrecycle.setAdapter(madapter);
    }
}