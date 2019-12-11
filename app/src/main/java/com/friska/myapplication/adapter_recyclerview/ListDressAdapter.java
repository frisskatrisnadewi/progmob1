package com.friska.myapplication.adapter_recyclerview;

import android.content.Context;
import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.CardView;
//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.friska.myapplication.DressListActivity;
import com.friska.myapplication.DressListDetailActivity;
import com.friska.myapplication.R;
import com.friska.myapplication.model_recyclerview.ListDressModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ListDressAdapter extends RecyclerView.Adapter<ListDressAdapter.MainViewHolder> {

    private Context context;
    private List<ListDressModel> dressData;
    private List<ListDressModel> mDatafull;

    public ListDressAdapter(Context context, List<ListDressModel> dressData){
        this.context = context;
        this.dressData = dressData;
        this.mDatafull=mDatafull;
    }

    ListDressAdapter(List<ListDressModel>dressData){
        this.dressData=dressData;
        mDatafull= new ArrayList<>(dressData);
    }


    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater minflater = LayoutInflater.from(context);
        view = minflater.inflate(R.layout.view_design_dress_recycler,viewGroup,false);
        return new MainViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder mainViewHolder, int i) {
        mainViewHolder.tv_nama_dress.setText(dressData.get(i).getJudulDress());
        mainViewHolder.img_dress.setImageResource(dressData.get(i).getThumbnailDress());
        mainViewHolder.tv_kategori_dress.setText(dressData.get(i).getKategoriDress());
        mainViewHolder.cv_dress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intnt = new Intent(context, DressListDetailActivity.class);
                //passing data ke deskripsiactivity
                //start sctivity
                context.startActivity(intnt);

            }
        });


    }

    @Override
    public int getItemCount() {
        return dressData.size();
    }


    public class MainViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nama_dress,tv_kategori_dress;
        ImageView img_dress;
        CardView cv_dress;

        public MainViewHolder(View view) {
            super(view);
            tv_nama_dress = itemView.findViewById(R.id.txt_nama_dress);
            tv_kategori_dress = itemView.findViewById(R.id.txt_dsc_dress);
            cv_dress = itemView.findViewById(R.id.dress_cardview);
            img_dress= itemView.findViewById(R.id.img_cv_dress);

        }
    }

    public void setfilter(List<ListDressModel> listitem){
        dressData = new ArrayList<>();
        dressData.addAll(listitem);
        notifyDataSetChanged();
    }
}
