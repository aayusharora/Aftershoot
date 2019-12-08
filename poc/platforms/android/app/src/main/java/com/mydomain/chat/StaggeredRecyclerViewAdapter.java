package com.mydomain.chat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class StaggeredRecyclerViewAdapter extends RecyclerView.Adapter<StaggeredRecyclerViewAdapter.ViewHolder> {


    private static final String TAG="StaggeredRecyclerView";
    private ArrayList<String> imageUrls;
    private Context context;


    StaggeredRecyclerViewAdapter(Context context, ArrayList<String> imageUrls) {
        this.imageUrls=imageUrls;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_grid_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {

        Log.d(TAG, "onBindViewHolder: called.");

        RequestOptions requestOptions=new RequestOptions()
                .placeholder(R.mipmap.ic_launcher);

        Glide.with(context)
                .load(imageUrls.get(i))
                .apply(requestOptions)
                .into(viewHolder.imageView);

        viewHolder.imageView.setOnClickListener(v -> {

            Log.d(TAG, "onClick: clicked " + imageUrls.get(i));
            Toast.makeText(context, imageUrls.get(i), Toast.LENGTH_LONG).show();
        });

    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView=itemView.findViewById(R.id.imageView);
        }


    }
}
