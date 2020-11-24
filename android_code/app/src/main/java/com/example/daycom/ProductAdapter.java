package com.example.daycom;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private int itemLay;
    private ArrayList<Product> productList;

    public ProductAdapter(int item, ArrayList<Product> list){
        itemLay=item;
        productList=list;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(itemLay, viewGroup, false);
        ProductViewHolder myHolder = new ProductViewHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder productViewHolder, int i) {
        TextView name=productViewHolder.name;
        TextView price=productViewHolder.price;
        ImageView img=productViewHolder.img;
        TextView description = productViewHolder.description;
        description.setText(productList.get(i).getDescription());
        name.setText(productList.get(i).getName());
        price.setText(priceToString(productList.get(i).getPrice()));
        Picasso.with(img.getContext()).load(productList.get(i).getPicture()).into(img);
        WindowManager WM = (WindowManager)price.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display D = WM.getDefaultDisplay();
        Point size = new Point();
        D.getSize(size);
        int imgW = img.getWidth(), priceW = price.getWidth(), displayW = size.x;
        priceW=142+32;imgW=80+8;
        description.setMaxWidth(displayW-(priceW+imgW));
    }
    public String priceToString(int p){
        String s="";
        while (p > 999) {
            for(int i=0;i<3;i++){
                s=p%10+s;
                p=p/10;
            }
            s=" "+s;
        }
        s=String.valueOf(p)+s;
        return s+"р.";
    }
    @Override
    public int getItemCount() {
        return productList==null?0:productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name;
        public TextView price;
        public ImageView img;
        public TextView description;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            description = (TextView)itemView.findViewById(R.id.prod_description);
            img =(ImageView)itemView.findViewById(R.id.prod_img);
            name = (TextView)itemView.findViewById(R.id.prod_name);
            price = (TextView)itemView.findViewById(R.id.prod_price);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(),productList.get(getAdapterPosition()).getName()+v.getWidth(),Toast.LENGTH_LONG).show();
        }
    }
}
