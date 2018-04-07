package com.avinashbarfa.homemade.Adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.avinashbarfa.homemade.Data.CategoriesData;
import com.avinashbarfa.homemade.MainActivity;
import com.avinashbarfa.homemade.R;
import com.avinashbarfa.homemade.ShowProducts;

import java.util.List;

/**
 * Created by Avinash Barfa on 3/29/2018.
 */

public class MyCategoriesAdapter extends RecyclerView.Adapter<MyCategoriesAdapter.ViewHolder>{

    private List<CategoriesData> categoryLists;
    private Context context;

    public MyCategoriesAdapter(List<CategoriesData> categoryLists, Context context) {
        this.categoryLists = categoryLists;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CategoriesData categoriesData = categoryLists.get(position);

        holder.txtCategoryName.setText(categoriesData.getCategoryName());
        holder.txtCategorySubTitle.setText(categoriesData.getCategorySubTitle());
        holder.categoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , ShowProducts.class);
                intent.putExtra("category_id" , categoriesData.getCategoryId());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return categoryLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtCategoryName;
        public TextView txtCategorySubTitle;
        public LinearLayout categoryLayout;


        public ViewHolder(View itemView) {
            super(itemView);

            txtCategoryName = (TextView)itemView.findViewById(R.id.txtCategoryName);
            txtCategorySubTitle = (TextView)itemView.findViewById(R.id .txtCategorySubTitle);
            categoryLayout = (LinearLayout) itemView.findViewById(R.id.categoryLayout);
        }
    }


}
