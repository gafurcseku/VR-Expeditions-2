package com.robotlab.expeditions2.activity.categorie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.robotlab.expeditions2.R;
import com.robotlab.expeditions2.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewModel> {

    private Context context;
    private List<Category> categoryList;
    private int row_index = 0;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorie_list_layout,parent,false);
        return new ViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewModel holder, final int position) {
        Category category = categoryList.get(position);
        holder.titleTextView.setText(category.getName());
        if(row_index == position){
            holder.selectionImageView.setVisibility(View.VISIBLE);
        }else {
            holder.selectionImageView.setVisibility(View.GONE);
        }

        holder.rootViewLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                //categoryList.get(position).setSelected(true);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewModel extends RecyclerView.ViewHolder {
        private LinearLayoutCompat rootViewLinearLayout;
        private AppCompatImageView selectionImageView;
        private AppCompatTextView titleTextView;

        public ViewModel(@NonNull View itemView) {
            super(itemView);
            this.rootViewLinearLayout = itemView.findViewById(R.id.rootViewLinearLayout);
            this.selectionImageView = itemView.findViewById(R.id.selectionImageView);
            this.titleTextView = itemView.findViewById(R.id.titleTextView);
        }
    }
}
