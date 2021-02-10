package com.robotlab.expeditions2.activity.deviceList;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.robotlab.expeditions2.R;
import com.robotlab.expeditions2.databinding.StudentListLayoutBinding;
import com.robotlab.expeditions2.model.Devices;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewModel> {
    private Context context;
    private List<Devices> devicesList;
    private StudentListLayoutBinding binding;


    public StudentAdapter(Context context, List<Devices> devicesList) {
        this.context = context;
        this.devicesList = devicesList;
    }

    @NonNull
    @Override
    public ViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = StudentListLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewModel(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewModel holder, int position) {
        Devices devices = devicesList.get(position);
        holder.bind(devices);
    }

    @Override
    public int getItemCount() {
        return devicesList.size();
    }

    public class ViewModel extends RecyclerView.ViewHolder {
        StudentListLayoutBinding binding;
        public ViewModel(StudentListLayoutBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        private void bind(Devices devices){
            binding.nameTextView.setText(devices.getName());
            if(devices.getOnline()){
                binding.nameTextView.setTextColor(context.getResources().getColor(R.color.text_color));
                binding.statusImageView.setImageResource(R.drawable.ic_icon_online);
            }else{
                binding.nameTextView.setTextColor(context.getResources().getColor(R.color.text_color_50));
                binding.statusImageView.setImageResource(R.drawable.ic_icon_offonline);
            }
        }
    }
}
