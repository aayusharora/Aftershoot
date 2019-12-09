package com.example.mvp;

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

    private static final String TAG="StaggeredRecyclerViewAd";

    private ArrayList<String> mNames;
    private ArrayList<String> mImageUris;
    private Context mContext;

    StaggeredRecyclerViewTextAdapter(Context context, ArrayList<String> names, ArrayList<String> imageUrls) {
        mNames=names;
        mImageUris=imageUrls;
        mContext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_grid_item_text, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        RequestOptions requestOptions=new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);

        Glide.with(mContext)
                .load(mImageUris.get(position))
                .apply(requestOptions)
                .into(holder.image);

        holder.name.setText(mNames.get(position));

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mNames.get(position));
                Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mImageUris.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;

        ViewHolder(View itemView) {
            super(itemView);
            this.image=itemView.findViewById(R.id.imageViewGrid);
            this.name=itemView.findViewById(R.id.tvImageClassfier);
        }
    }
}
