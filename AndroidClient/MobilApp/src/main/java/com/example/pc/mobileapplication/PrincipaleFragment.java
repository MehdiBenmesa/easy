package com.example.pc.mobileapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;


public class PrincipaleFragment extends Fragment {
    /*public boolean isTwoPane(){
        View view = getActivity().findViewById(R.id.fragment);
        return (view != null);
    }
    List<Bien> liste;
    private android.support.v4.app.FragmentTransaction fragmentTransaction;
    private Button btn;*/
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_principale, container, false);



        return v;//inflater.inflate(R.layout.fragment_d, container, false);


    }
}