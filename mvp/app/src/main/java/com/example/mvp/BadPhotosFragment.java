package com.example.mvp;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BadPhotosFragment extends Fragment {


    public BadPhotosFragment() {
        // Required empty public constructor
    }

    private static final String TAG="MainActivity";
    private static final int NUM_COLUMNS=2;

    private ArrayList<String> imageUris=new ArrayList<>();
    private ArrayList<String> tvImageClassifiers=new ArrayList<>();
    private View parentHolder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentHolder=inflater.inflate(R.layout.fragment_bad_photos, container, false);
        assert getArguments() != null;
        ArrayList<String> arrayList=getArguments().getStringArrayList("bad");
        ArrayList<String> classifierList=getArguments().getStringArrayList("classifier");
        assert arrayList != null;
        assert classifierList != null;

        imageUris.addAll(arrayList);
        initRecyclerView();

        return parentHolder;
    }


    private void initRecyclerView() {

        Log.d(TAG, "initRecyclerView: initializing staggered recyclerView");
        RecyclerView recyclerView=parentHolder.findViewById(R.id.recyclerView);
        StaggeredRecyclerViewAdapter staggeredRecyclerViewAdapter=new StaggeredRecyclerViewAdapter(getActivity(), imageUris);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(staggeredRecyclerViewAdapter);
        staggeredRecyclerViewAdapter.notifyDataSetChanged();
    }


}
