package com.robotlab.expeditions2.activity.deviceList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.robotlab.expeditions2.model.Devices;

import java.util.List;

public class DeviceAdapter  extends RecyclerView.Adapter<DeviceAdapter.ViewModel> {
    private Context context;
    private List<Devices> devicesList;


    public DeviceAdapter(Context context, List<Devices> devicesList) {
        this.context = context;
        this.devicesList = devicesList;
    }

    @NonNull
    @Override
    public ViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewModel holder, int position) {

    }

    @Override
    public int getItemCount() {
        return devicesList.size();
    }

    public class ViewModel extends RecyclerView.ViewHolder {

        public ViewModel(@NonNull View itemView) {
            super(itemView);
        }
    }
}
