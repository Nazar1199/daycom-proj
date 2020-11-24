package com.example.daycom;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategorysActivity extends Activity {
    RecyclerView recycler;
    private ArrayList<ProductType> categoryItems = null;
    LinearLayoutManager manager = new LinearLayoutManager(getBaseContext());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorys);
        //**********GET LIST FROM DATABASE**************
        NetworkService.getInstance().getAPI().getCategorys().enqueue(new Callback<ArrayList<ProductType>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductType>> call, Response<ArrayList<ProductType>> response) {
                categoryItems = response.body();
                CategoryAdapter adapter = new CategoryAdapter(R.layout.category_list_item, categoryItems);
                recycler = (RecyclerView) findViewById(R.id.recyclerOnCategory);
                recycler.setLayoutManager(manager);
                DividerItemDecoration divider = new DividerItemDecoration(recycler.getContext(),manager.getOrientation());
                recycler.addItemDecoration(divider);
                recycler.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<ArrayList<ProductType>> call, Throwable t) {
            }
        });
    }
}