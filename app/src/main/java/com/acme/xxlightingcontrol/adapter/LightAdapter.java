package com.acme.xxlightingcontrol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.acme.xxlightingcontrol.R;
import com.acme.xxlightingcontrol.dto.LightDto;
import com.acme.xxlightingcontrol.lib.listener.OnRecyclerItemClickListener;

import java.util.List;

/**
 * @author jx9@msn.com
 */
public class LightAdapter extends RecyclerView.Adapter<LightAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public View lightStatusBgV;

        public ImageView lightStatusIV;

        public TextView lightNameTV;

        public TextView lightStatusTV;

        public ViewHolder(View v) {
            super(v);
            lightStatusBgV = v.findViewById(R.id.lightStatusBgV);
            lightStatusIV = v.findViewById(R.id.lightStatusIV);
            lightNameTV = v.findViewById(R.id.lightNameTV);
            lightStatusTV = v.findViewById(R.id.lightStatusTV);
        }

        @Override
        public void onClick(View view) {

        }

    }

    private Context mContext;

    private List<LightDto> lightDtos;

    private OnRecyclerItemClickListener mRecyclerItemClickListener;

    private int resource;
    public LightAdapter(Context context, List<LightDto> lightDtos,
                        OnRecyclerItemClickListener recyclerItemClickListener, int resource) {
        this.mContext = context;
        this.lightDtos = lightDtos;
        this.mRecyclerItemClickListener = recyclerItemClickListener;
        this.resource = resource;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(resource, parent, false);
        final ViewHolder vh = new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerItemClickListener.onItemClick(v, vh.getAdapterPosition());
                LightDto lightDto = lightDtos.get(vh.getAdapterPosition());
                int lightStatus = lightDto.getStatus() == 0 ? 1 : 0;
                lightDto.setStatus(lightStatus);
                if (lightStatus == 0) {
                    vh.lightStatusTV.setText("关");
                    vh.lightStatusBgV.setBackgroundResource(R.drawable.light_item_bg_off);
                    vh.lightStatusIV.setImageResource(R.drawable.light_item_off);
                }
                if (lightStatus == 1) {
                    vh.lightStatusTV.setText("开");
                    vh.lightStatusBgV.setBackgroundResource(R.drawable.light_item_bg_on);
                    vh.lightStatusIV.setImageResource(R.drawable.light_item_on);
                }
            }
        });

        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final LightDto lightDto = lightDtos.get(position);
        holder.lightNameTV.setText(lightDto.getName());
        int lightStatus = lightDto.getStatus();
        if (lightStatus == 0) {
            holder.lightStatusTV.setText("关");
            holder.lightStatusBgV.setBackgroundResource(R.drawable.light_item_bg_off);
            holder.lightStatusIV.setImageResource(R.drawable.light_item_off);
        }
        if (lightStatus == 1) {
            holder.lightStatusTV.setText("开");
            holder.lightStatusBgV.setBackgroundResource(R.drawable.light_item_bg_on);
            holder.lightStatusIV.setImageResource(R.drawable.light_item_on);
        }
    }


    @Override
    public int getItemCount() {
        return lightDtos.size();
    }
}
