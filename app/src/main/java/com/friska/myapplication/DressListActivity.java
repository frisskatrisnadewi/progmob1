package com.friska.myapplication;

import android.content.Intent;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.friska.myapplication.adapter_recyclerview.ListDressAdapter;
import com.friska.myapplication.model_recyclerview.ListDressModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DressListActivity extends AppCompatActivity {
    private Toolbar toolbar;
    List<ListDressModel> listmain = new ArrayList<>();
    ListDressAdapter madapter;
    RecyclerView recyclerView;
    SearchView searchView;
    CardView cv_dress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dress_list);

        cv_dress = findViewById(R.id.dress_cardview);
        toolbar = findViewById(R.id.toolbar);

        addData();
        setupRecycle();
    }

/*    private void rv_cclick(){
        cv_dress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(getApplicationContext(), DressListDetailActivity.class);
                startActivity(intentLogin);
            }
        });
    }*/
    private void addData() {
        listmain = new ArrayList<>();
        listmain.add(new ListDressModel("Black Luxury Long Dress", "Long Dress", R.drawable.longdress));
        listmain.add(new ListDressModel("Fresh Colour Dress", "Short", R.drawable.cdres3));
        listmain.add(new ListDressModel("Trendy Beauty Dress", "Short", R.drawable.cdres2));
    }

    private void setupRecycle() {
        RecyclerView mrecycle = (RecyclerView) findViewById(R.id.recycler_dressactivity);
        madapter = new ListDressAdapter(this, listmain);
        mrecycle.setLayoutManager(new GridLayoutManager(this, 1));
        mrecycle.setAdapter(madapter);
    }

    //search


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        final MenuItem menuItem = menu.findItem(R.id.search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (!searchView.isIconified()){
                    searchView.setIconified(true);
                }
                menuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                final List<ListDressModel> filtermodel=filter(listmain,s);
                madapter.setfilter(filtermodel);
                return true;
            }
        });

        return true;

    }

    private List<ListDressModel>filter (List<ListDressModel> pl,String s){
        s=s.toLowerCase();
        final List<ListDressModel>filteredModelList=new ArrayList<>();
        for (ListDressModel model:pl){
            final String text= model.getJudulDress().toLowerCase();
            if (text.contains(s)){
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }
}
