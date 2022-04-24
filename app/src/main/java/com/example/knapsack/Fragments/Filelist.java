package com.example.knapsack.Fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.knapsack.MyAdapter;
import com.example.knapsack.R;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Filelist#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Filelist extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static String path = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Filelist() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Filelist.
     */
    // TODO: Rename and change types and number of parameters
    public static Filelist newInstance(String param1, String param2) {
        Filelist fragment = new Filelist();
        Bundle args = new Bundle();
        args.putString(path, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(path);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_filelist,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        TextView noFilesText = view.findViewById(R.id.nofiles_textview);

        if(mParam1=="spiderman")
        {
            Toast.makeText(getActivity(),mParam1,Toast.LENGTH_SHORT).show();
            mParam1 = Environment.getExternalStorageDirectory().toString();
        }
        Toast.makeText(getActivity(),mParam1,Toast.LENGTH_SHORT).show();
        File root = new File(mParam1);
        File[] filesAndFolders = root.listFiles();

        if(filesAndFolders==null || filesAndFolders.length ==0){
            noFilesText.setVisibility(View.VISIBLE);
            return view;
        }

        noFilesText.setVisibility(View.INVISIBLE);
        FragmentManager activity;
        activity= getActivity().getSupportFragmentManager();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(new MyAdapter(getActivity().getApplicationContext(),filesAndFolders, activity));
        return view;
    }
}