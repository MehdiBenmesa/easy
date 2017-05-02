package dz.easy.androidclient;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Mon pc on 30/03/2017.
 */

public class NoteFragment extends Fragment {

    public NoteFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_note, container, false);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Make sure that container activity implement the callback interface
    }
}
