package com.avinashbarfa.homemade.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avinashbarfa.homemade.Data.CategoriesList;
import com.avinashbarfa.homemade.R;

import java.util.List;

/**
 * Created by Avinash Barfa on 3/29/2018.
 */

public class MyCategoriesAdapter extends RecyclerView.Adapter<MyCategoriesAdapter.ViewHolder>{

    private List<CategoriesList> categoryLists;
    private Context context;

    public MyCategoriesAdapter(List<CategoriesList> categoryLists, Context context) {
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
        CategoriesList categoriesList = categoryLists.get(position);

        holder.txtCategoryName.setText(categoriesList.getCategoryName());
        holder.txtCategorySubTitle.setText(categoriesList.getCategorySubTitle());
    }

    @Override
    public int getItemCount() {
        return categoryLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtCategoryName;
        public TextView txtCategorySubTitle;


        public ViewHolder(View itemView) {
            super(itemView);

            txtCategoryName = (TextView)itemView.findViewById(R.id.txtCategoryName);
            txtCategorySubTitle = (TextView)itemView.findViewById(R.id .txtCategorySubTitle);
        }
    }
}
