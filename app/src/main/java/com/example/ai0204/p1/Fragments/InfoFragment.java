package com.example.ai0204.p1.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.ai0204.p1.R;

/**
 * Fragment that display information about an selected Income/Expense
 * @author Matthias Falk
 */
public class InfoFragment extends Fragment {
    private View view;
    private TextView tvInfo;
    private String info;

    public InfoFragment() {}

    /**
     * basic onCreateView method
     * calls initialize() and sets the text in tvInfo
     * @param savedInstanceState
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_info, container, false);
        initialize();
        tvInfo.setText(info);
        return view;
    }

    /**
     * Initializes all the components used in the fragment
     */
    public void initialize(){
        tvInfo = view.findViewById(R.id.tvInfo);
    }

    /**
     * sets the String info
     * @param info
     */
    public void setInfo(String info){
        this.info = info;
    }
}
