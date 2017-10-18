package com.example.muslimbeibytuly.myfeed.Components.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.muslimbeibytuly.myfeed.R;

/**
 * Created by muslimbeibytuly on 10/9/17.
 */

public class MainTabFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_tab_fragment, container, false);
    }
}
