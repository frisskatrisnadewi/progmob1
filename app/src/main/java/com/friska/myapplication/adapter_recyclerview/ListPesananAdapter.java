package com.friska.myapplication.adapter_recyclerview;

import android.content.Context;
import android.content.Intent;
/*import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;*/
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.friska.myapplication.DressListDetailActivity;
import com.friska.myapplication.R;
import com.friska.myapplication.model_recyclerview.ListDressModel;
import com.friska.myapplication.model_recyclerview.ListPesananModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ListPesananAdapter extends RecyclerView.Adapter <ListPesananAdapter.MainViewHolder>{
    private Context context;
    private List<ListPesananModel> pesananData;
    private List<ListPesananModel> mDatafull;

    public ListPesananAdapter(Context context, List<ListPesananModel>pesananData ){
        this.context = context;
        this.pesananData = pesananData;
        this.mDatafull=mDatafull;
    }

    ListPesananAdapter(List<ListPesananModel>pesananData){
        this.pesananData=pesananData;
        mDatafull= new ArrayList<>(pesananData);
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater minflater = LayoutInflater.from(context);
        view = minflater.inflate(R.layout.view_design_pesanan_recycler,viewGroup,false);
        return new ListPesananAdapter.MainViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder mainViewHolder, int i) {
        mainViewHolder.tv_nama_pesanan.setText(pesananData.get(i).getJudulPesanan());
        mainViewHolder.img_pesanan.setImageResource(pesananData.get(i).getThumbnailPesanan());
        mainViewHolder.tv_status_pesanan.setText(pesananData.get(i).getStatusPesanan());
        mainViewHolder.cv_pesanan.setOnClickListener(new View.OnClickListener() {
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
        return 0;
    }


public class MainViewHolder extends RecyclerView.ViewHolder {

    TextView tv_nama_pesanan,tv_status_pesanan;
    ImageView img_pesanan;
    CardView cv_pesanan;

    public MainViewHolder(View view) {
        super(view);
        tv_nama_pesanan = itemView.findViewById(R.id.txt_nama_pesanan);
        tv_status_pesanan = itemView.findViewById(R.id.txt_status_pesanan);
        cv_pesanan = itemView.findViewById(R.id.pesanan_cardview);
        img_pesanan= itemView.findViewById(R.id.img_cv_pesanan);

    }
}

/*    public void setfilter(List<ListDressModel> listitem){
        dressData = new ArrayList<>();
        dressData.addAll(listitem);
        notifyDataSetChanged();
    }*/


    }
