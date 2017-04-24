package com.gavinfinancialgroup.googleplacesapi.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gavinfinancialgroup.googleplacesapi.Next5Days.List5;
import com.gavinfinancialgroup.googleplacesapi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 4/22/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    ArrayList<List5> list5s;

    public CustomAdapter(List<List5> list5s) {
        this.list5s = (ArrayList<List5>) list5s;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        return  customViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.time.setText(list5s.get(position).getDtTxt());
        holder.description.setText(list5s.get(position).getWeather().get(0).getDescription());
    }

    @Override
    public int getItemCount() {
        return list5s.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView time, description;
        public CustomViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.time_tv);
            description = (TextView) itemView.findViewById(R.id.description_tv);
        }
    }
}
