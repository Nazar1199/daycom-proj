package com.example.daycom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsActivity extends Activity{
    RecyclerView recycler;
    private ArrayList<Product> productsItems = null;
    LinearLayoutManager manager = new LinearLayoutManager(getBaseContext());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        //**********GET LIST FROM DATABASE**************
        Intent in = getIntent();
        String S = in.getExtras().getString("CODE");
        int i = Integer.parseInt(S);

        NetworkService.getInstance().getAPI().getProducts(i).enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                productsItems = response.body();
                ProductAdapter adapter = new ProductAdapter(R.layout.product_list_item, productsItems);
                recycler = (RecyclerView) findViewById(R.id.recyclerOnProduct);
                recycler.setLayoutManager(manager);
                DividerItemDecoration divider = new DividerItemDecoration(recycler.getContext(),manager.getOrientation());
                recycler.addItemDecoration(divider);
                recycler.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

            }
        });
    }
}
