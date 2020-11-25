package com.robotlab.expeditions2.activity.deviceList;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.robotlab.expeditions2.model.Devices;

import java.util.ArrayList;
import java.util.List;

public class StudentFragmentViewModel extends ViewModel {
    private Context context;

    public StudentFragmentViewModel(Context context) {
        this.context = context;
    }

    private final MutableLiveData<List<Devices>> studentLiveData = new MutableLiveData<>();

    public void getStudent(){
        studentLiveData.setValue(getDummy());
    }

    public MutableLiveData<List<Devices>> getStudentLiveData(){
        return studentLiveData;
    }


    private List<Devices> getDummy(){
        List<Devices> list = new ArrayList<>();

        for (int i=0 ; i< 100 ; i++){
            if( i%2 == 0 )
                list.add(new Devices(1,"RobotLab - AD"+i, false));
            else
                list.add(new Devices(1,"RobotLab - AD"+i, true));
        }
        return list;
    }
}
