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

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    FrameLayout frameLayout;
    TextView tvTakePhoto, tvImportPhotos;
    LinearLayout linearLayout;
    ImageView ivGallery, ivCamera;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static final int REQUEST_CAMERA=2520, RESULT_LOAD_IMAGE=2521;
    private ArrayList<String> goodImagesUriArrayList, tvClassfierArrayList;

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

        goodImagesUriArrayList=new ArrayList<>();
        tvClassfierArrayList=new ArrayList<>();


        ivCamera=findViewById(R.id.ivCamera);
        ivGallery=findViewById(R.id.ivGallery);

        ivGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMAGE);

            }
        });

    }

    /*public void dialogShowPhoto() {

        String takePhoto=getString(R.string.take_photo);
        String chooseFromLibrary=getString(R.string.import_photos);
        String cancel="cancel";
        String addPhoto="add photo";
        final CharSequence[] items={takePhoto, chooseFromLibrary, cancel};
        android.app.AlertDialog.Builder builder=new android.app.AlertDialog.Builder(this);
        builder.setTitle(addPhoto);
        final String finalTakephoto=takePhoto;
        final String finalChooseFromLibrary=chooseFromLibrary;
        final String finalCancel=cancel;
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @RequiresApi(api=Build.VERSION_CODES.JELLY_BEAN_MR2)
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals(finalTakephoto)) {
                    Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals(finalChooseFromLibrary)) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent,"Select Picture"), 2521);
                } else if (items[item].equals(finalCancel)) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }*/

    @RequiresApi(api=Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*if (requestCode == REQUEST_CAMERA && resultCode == Activity.RESULT_OK) {
            //selectedImageWork.setAlpha(1f);
            Bitmap photo=(Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
            // selectedImage.setImageBitmap(photo);
            Matrix mat=new Matrix();
            mat.postRotate(Integer.parseInt("270"));
            assert photo != null;
            Bitmap bMapRotate=Bitmap.createBitmap(photo, 0, 0, photo.getWidth(), photo.getHeight(), mat, true);
            //selectedImageWork.setImageBitmap(bMapRotate);


        }*/ // For Camera

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {

            ivGallery.setVisibility(View.GONE);
            ivCamera.setVisibility(View.GONE);
            tvImportPhotos.setVisibility(View.GONE);
            tvTakePhoto.setVisibility(View.GONE);
            linearLayout.setVisibility(View.GONE);
            viewPager.setVisibility(View.VISIBLE);
            tabLayout.setVisibility(View.VISIBLE);
            TabAdapter adapter=new TabAdapter(getSupportFragmentManager());

            for (int i=0; i < Objects.requireNonNull(data.getClipData()).getItemCount(); i++) {

                goodImagesUriArrayList.add(data.getClipData().getItemAt(i).getUri().toString()); // All the images URIs retrieved
                tvClassfierArrayList.add(String.valueOf(i));
            }


            GoodPhotosFragment goodPhotosFragment=new GoodPhotosFragment();
            BadPhotosFragment badPhotosFragment=new BadPhotosFragment();
            ObjectsFragment objectsFragment=new ObjectsFragment();

            Bundle goodPhotosBundle=new Bundle();
            goodPhotosBundle.putStringArrayList("good", goodImagesUriArrayList);
            goodPhotosFragment.setArguments(goodPhotosBundle);

            Bundle badPhotosBundle=new Bundle();
            badPhotosBundle.putStringArrayList("bad", goodImagesUriArrayList);// change imageUriList For Bad Photos
            badPhotosFragment.setArguments(badPhotosBundle);

            Bundle objectsBundle=new Bundle();
            objectsBundle.putStringArrayList("objects", goodImagesUriArrayList);// change imageUriList For objects
            objectsFragment.setArguments(objectsBundle);


            adapter.addFragment(goodPhotosFragment, "Good Photos");
            adapter.addFragment(badPhotosFragment, "Bad Photos");
            adapter.addFragment(objectsFragment, "Objects");


            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);

        }
    }

}

