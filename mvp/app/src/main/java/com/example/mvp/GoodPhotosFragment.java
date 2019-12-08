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


public class GoodPhotosFragment extends Fragment {

    public GoodPhotosFragment() {
        // Required empty public constructor
    }

    private static final String TAG="MainActivity";
    private static final int NUM_COLUMNS=2;

    private ArrayList<String> imageUrls=new ArrayList<>();
    private View parentHolder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentHolder=inflater.inflate(R.layout.fragment_good_photos, container, false);
        initImageBitmap();
        initRecyclerView();
        return parentHolder;
    }

    private void initImageBitmap() {

        Log.d(TAG, "initImageBitmaps. Preparing Bitmaps");

        //add image urls here

        //imageUrls.add(""); add image link

        imageUrls.add("https://i.pinimg.com/474x/31/fa/eb/31faeb821bbe720e36a14d86e6c7e6cf.jpg");
        imageUrls.add("https://i.pinimg.com/736x/10/12/a7/1012a70fa0dab96a72af29f23ae481b6.jpg");
        imageUrls.add("https://static3.cbrimages.com/wordpress/wp-content/uploads/2019/09/worthy-captain-american-endgame.jpg");
        imageUrls.add("https://cdn.vox-cdn.com/thumbor/513EibMf4LscNq34noXEt_qG1dU=/0x0:2048x858/1200x800/filters:focal(682x242:1008x568)/cdn.vox-cdn.com/uploads/chorus_image/image/63325419/3f18412f_4bbb_433d_82f4_4c50208b2531_brb1740_trbcomp_v4111171.0.jpg");
        imageUrls.add("https://static.independent.co.uk/s3fs-public/thumbnails/image/2018/01/11/09/black-widow.jpg?w968h681");
        imageUrls.add("https://terrigen-cdn-dev.marvel.com/content/prod/1x/online_char_avengehonor_series_hawkeye_v1_lg_1.jpg");
        imageUrls.add("https://i.pinimg.com/736x/76/fb/cc/76fbccf3696a63cb84581e1027e6d8a7.jpg");
        imageUrls.add("https://static3.srcdn.com/wordpress/wp-content/uploads/2019/01/iron-man-thanos.jpg");


    }


    private void initRecyclerView() {

        Log.d(TAG, "initRecyclerView: initializing staggered recyclerView");
        RecyclerView recyclerView=parentHolder.findViewById(R.id.recyclerView);
        StaggeredRecyclerViewAdapter staggeredRecyclerViewAdapter=new StaggeredRecyclerViewAdapter(getActivity(), imageUrls);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(staggeredRecyclerViewAdapter);
    }


}
