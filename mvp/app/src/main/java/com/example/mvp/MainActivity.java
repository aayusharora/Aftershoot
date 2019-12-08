/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.example.mvp;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="MainActivity";
    private static final int NUM_COLUMNS=2;

    private ArrayList<String> imageUrls=new ArrayList<>();

    FrameLayout frameLayout;
    TextView tvTakePhoto, tvImportPhotos;
    LinearLayout linearLayout;
    ImageView ivGallery, ivCamera;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // enable Cordova apps to be started in the background
        Bundle extras=getIntent().getExtras();
        if (extras != null && extras.getBoolean("cdvStartInBackground", false)) {
            moveTaskToBack(true);
        }

        /* Set by <content src="index.html" /> in config.xml
        loadUrl(launchUrl);*/

        //initImageBitmap();
        //initRecyclerView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        }

        frameLayout=findViewById(R.id.fragment_container);
        viewPager=findViewById(R.id.viewPager);
        tabLayout=findViewById(R.id.tabLayout);
        tvImportPhotos=findViewById(R.id.tvImportPhotos);
        tvTakePhoto=findViewById(R.id.tvTakePhoto);
        linearLayout=findViewById(R.id.linearLayout);


        ivCamera=findViewById(R.id.ivCamera);
        ivGallery=findViewById(R.id.ivGallery);

        ivGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ivGallery.setVisibility(View.GONE);
                ivCamera.setVisibility(View.GONE);
                tvImportPhotos.setVisibility(View.GONE);
                tvTakePhoto.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);

                viewPager.setVisibility(View.VISIBLE);
                tabLayout.setVisibility(View.VISIBLE);
                TabAdapter adapter=new TabAdapter(getSupportFragmentManager());
                adapter.addFragment(new GoodPhotosFragment(), "Good Photos");
                adapter.addFragment(new BadPhotosFragment(), "Bad Photos");
                adapter.addFragment(new ObjectsFragment(), "Objects");
                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager);
            }
        });

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
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        StaggeredRecyclerViewAdapter staggeredRecyclerViewAdapter=new StaggeredRecyclerViewAdapter(MainActivity.this, imageUrls);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(staggeredRecyclerViewAdapter);
    }


}