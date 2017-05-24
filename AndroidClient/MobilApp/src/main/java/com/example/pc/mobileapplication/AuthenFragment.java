package com.example.pc.mobileapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class AuthenFragment extends Fragment {

     private Button btn;
    private Button btn2;
    private android.support.v4.app.FragmentTransaction fragmentTransaction;
    private android.support.v4.app.FragmentTransaction fragmentTransaction2;// getActivity().getSupportFragmentManager().beginTransaction();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_authen2, container, false);
        btn = (Button)v.findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InscriptionFragment inscr = new InscriptionFragment();
                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainL, inscr);
                fragmentTransaction.commit();
            }
        });
       // v = inflater.inflate(R.layout.fragment_authen2, container, false);

        btn2 = (Button)v.findViewById(R.id.button);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrincipaleFragment acc = new PrincipaleFragment();
                fragmentTransaction2 = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.mainL, acc);
                fragmentTransaction2.commit();

            }
        });
        // Inflate the layout for this fragment
        return v;
    }




}
