package org.androidtown.myapplication;

/**
 * Created by sj971 on 2018-06-05.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.List;

public class cardAdapter extends RecyclerView.Adapter<cardAdapter.ViewHolder> {

    private FirebaseStorage storage;
    StorageReference spaceRef;

    Context context;
    List<item> items;
    int item_layout;

    public cardAdapter(Context context, List<item> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, null);
        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final item item = items.get(position);
        final int POSITION = position;

        Drawable drawable = ContextCompat.getDrawable(context, item.getPhoto());
        holder.image.setBackground(drawable);
        holder.title.setText(item.getTitle());

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, item.getTitle(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, HistoryActivity.class);

                String title = item.getTitle();

                //String type = item.getWithwho();

                //i.putExtra("Check_type", type);

                String withwho = item.getWithwho();
                String UserId = item.getUserID();
                int didNum = item.getDidNum();
                int willNum = item.getWillNum();
                String type = item.getType();

                Bundle bundle = new Bundle();
                bundle.putInt("INDEX", POSITION + 1);
                bundle.putString("Check_type", withwho);
                bundle.putString("ID", UserId);

                /*
                bundle.putString("Title", title);
                bundle.putInt("Did",didNum);
                bundle.putInt("Will", willNum);
                bundle.putString("Type", type);
                */

                i.putExtras(bundle);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            cardview = (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}