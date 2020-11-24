package com.example.daycom;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private int litLay;
    private ArrayList<ProductType> itemList;

    public CategoryAdapter(int category_list_item, ArrayList<ProductType> categoryItems) {
        litLay=category_list_item;
        itemList=categoryItems;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(litLay, viewGroup, false);
        CategoryViewHolder myHolder = new CategoryViewHolder(view);

        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        TextView item = categoryViewHolder.categoryView;
        item.setText(itemList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return itemList==null?0:itemList.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView categoryView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryView = (TextView) itemView.findViewById(R.id.list_item);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Log.v("Click", "Selected "+ String.valueOf(itemList.get(getAdapterPosition()).getType())+" category ("+itemList.get(getAdapterPosition()).getName()+")");
            Intent intent = new Intent(v.getContext(),ProductsActivity.class);
            intent.putExtra("CODE",itemList.get(getAdapterPosition()).getType().toString());
            v.getContext().startActivity(intent);
        }
    }
}
