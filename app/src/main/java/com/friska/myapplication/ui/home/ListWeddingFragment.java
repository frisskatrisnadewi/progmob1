package com.friska.myapplication.ui.home;


import android.os.Bundle;
/*import android.support.v4.app.Fragment;*/
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.friska.myapplication.R;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListWeddingFragment extends Fragment {


    public ListWeddingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_wedding, container, false);
    }

}
