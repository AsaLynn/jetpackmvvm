package com.zxn.mvvm.demo;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


/**
 * Created by zxn on 2019-4-2 16:46:35.
 */
public class MainFrag extends Fragment {
    private static final String ARG_PARAM1 = "param1";

    private String mParam1;


    public MainFrag() {
    }

    public static MainFrag newInstance(String param1) {
        MainFrag fragment = new MainFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public static MainFrag newInstance() {
        MainFrag fragment = new MainFrag();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

}
