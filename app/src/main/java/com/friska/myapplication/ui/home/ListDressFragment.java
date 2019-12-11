package com.friska.myapplication.ui.home;


import android.os.Bundle;
/*import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;*/
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.friska.myapplication.R;
import com.friska.myapplication.adapter_recyclerview.ListDressAdapter;
import com.friska.myapplication.model_recyclerview.ListDressModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListDressFragment extends Fragment {
    private Toolbar toolbar;
    List<ListDressModel> listmain= new ArrayList<>();
    ListDressAdapter madapter;
    RecyclerView recyclerView;


    public ListDressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        toolbar = getActivity().findViewById(R.id.toolbar);
        addData();
        setupRecycle();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_dress, container, false);

    }

    private void setupRecycle() {
        RecyclerView mrecycle = (RecyclerView) getActivity().findViewById(R.id.recycler_idDress);
        madapter = new ListDressAdapter (getContext(),listmain);
        mrecycle.setLayoutManager(new GridLayoutManager(getContext(),1));
        mrecycle.setAdapter(madapter);
    }

    private void addData() {
        listmain = new ArrayList<>();
        listmain.add(new ListDressModel("Casual","Long",R.drawable.baliwedding));
        listmain.add(new ListDressModel("Formal","Short",R.drawable.baliwedding));
        listmain.add(new ListDressModel("Authentic","Medium",R.drawable.baliwedding));
        listmain.add(new ListDressModel("Long Dress","Long",R.drawable.baliwedding));
    }


}
