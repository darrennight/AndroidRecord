package com.hao.androidrecord.omnipotent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hao.androidrecord.R;


public class FragmentA extends Fragment {
    int count = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_omnipotent,null);
        view.findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                //发送数据，并接收返回值
                String s = OmnipotentManager.getInstance().invokeInterface("btn1",count,String.class);
                Toast.makeText(getActivity(),s,Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


}
