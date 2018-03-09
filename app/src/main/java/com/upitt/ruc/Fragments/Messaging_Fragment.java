package com.upitt.ruc.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.upitt.ruc.R;

/**
 * Created by omkar on 3/9/2018.
 */

public class Messaging_Fragment extends Fragment {

    public static Fragment newInstance()
    {
        return new Messaging_Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_messaging,container,false);

        return rootView;

    }
}
