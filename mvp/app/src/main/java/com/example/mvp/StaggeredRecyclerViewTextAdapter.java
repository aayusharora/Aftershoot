package com.example.mvp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class StaggeredRecyclerViewTextAdapter extends RecyclerView.Adapter<StaggeredRecyclerViewTextAdapter.ViewHolder> {


    private static final String TAG="StaggeredRecyclerView";
    private ArrayList<String> imageUrls;
    private ArrayList<String> tvImageClassfiers;
    private Context context;


    StaggeredRecyclerViewTextAdapter(Context context, ArrayList<String> imageUrls, ArrayList<String> tvImageClassfiers) {
        this.context=context;
        this.imageUrls=imageUrls;
        this.tvImageClassfiers=tvImageClassfiers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_grid_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {

        Log.d(TAG, "onBindViewHolder: called.");

        RequestOptions requestOptions=new RequestOptions()
                .placeholder(R.mipmap.ic_launcher);

        Glide.with(context)
                .load(imageUrls.get(i))
                .apply(requestOptions)
                .into(viewHolder.imageView);


        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: clicked " + imageUrls.get(i));
                Toast.makeText(context, imageUrls.get(i), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView=itemView.findViewById(R.id.imageView);
        }


    }
}
