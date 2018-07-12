package com.example.mahmoud.task1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapterSouq extends RecyclerView.Adapter<CustomAdapterSouq.BackingItemRowHolder> {
    ArrayList<SouqItems> items;
    Context mcontext;
    String flag;

    public CustomAdapterSouq(Context mcontext, ArrayList<SouqItems> items, String flag) {
        this.mcontext = mcontext;
        this.items = items;
        this.flag = flag;
    }

    @Override
    public CustomAdapterSouq.BackingItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_info, null);
        CustomAdapterSouq.BackingItemRowHolder ALRH = new CustomAdapterSouq.BackingItemRowHolder(v);
        return ALRH;

    }

    @Override
    public void onBindViewHolder(CustomAdapterSouq.BackingItemRowHolder holder, int position) {
        holder.name.setText(items.get(position).getName());
        if (!items.get(position).getImage().equals("")) {
            Picasso.with(mcontext).load(items.get(position).getImage()).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    public class BackingItemRowHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView imageView;
        FrameLayout frameLayout;

        public BackingItemRowHolder(View itemView) {
            super(itemView);
            frameLayout = (FrameLayout) itemView.findViewById(R.id.frame);
            name = (TextView) itemView.findViewById(R.id.txt);
            imageView = (ImageView) itemView.findViewById(R.id.img);

            if (flag.equals("1")) {
                frameLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent mIntent = new Intent(mcontext, ForItemActivity.class);
                        mIntent.putExtra("id", items.get(getAdapterPosition()).getId());
                        mIntent.putExtra("name", items.get(getAdapterPosition()).getName());

                        mcontext.startActivity(mIntent);
                    }
                });
            }
        }

    }

}



