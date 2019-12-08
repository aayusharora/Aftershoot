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
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {



    FrameLayout frameLayout;
    TextView tvTakePhoto, tvImportPhotos;
    LinearLayout linearLayout;
    ImageView ivGallery, ivCamera;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @RequiresApi(api=Build.VERSION_CODES.KITKAT)
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, android.R.color.background_light));// set status background white
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


}