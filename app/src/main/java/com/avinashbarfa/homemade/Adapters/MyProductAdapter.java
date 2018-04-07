package com.avinashbarfa.homemade.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avinashbarfa.homemade.Data.CategoriesData;
import com.avinashbarfa.homemade.Data.ProductData;
import com.avinashbarfa.homemade.R;

import java.util.List;

/**
 * Created by Avinash Barfa on 4/7/2018.
 */

public class MyProductAdapter extends  RecyclerView.Adapter<MyProductAdapter.ViewHolder>{

    private List<ProductData> productLists;
    private Context context;

    public MyProductAdapter(List<ProductData> productLists, Context context) {
        this.productLists = productLists;
        this.context = context;
    }

    @Override
    public MyProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list,parent,false);
        return new MyProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyProductAdapter.ViewHolder holder, int position) {
        ProductData productData = productLists.get(position);

        holder.txtProductName.setText(productData.getProductName());
        holder.txtProductPrice.setText(productData.getProductPrice());
    }

    @Override
    public int getItemCount() {
        return productLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtProductName;
        public TextView txtProductPrice;

        public ViewHolder(View itemView) {
            super(itemView);

            txtProductName = (TextView)itemView.findViewById(R.id.txtProductName);
            txtProductPrice = (TextView)itemView.findViewById(R.id .txtProductPrice);
        }
    }
}
