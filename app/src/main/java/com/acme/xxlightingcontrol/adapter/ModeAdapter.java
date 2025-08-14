package com.acme.xxlightingcontrol.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.acme.xxlightingcontrol.R;
import com.acme.xxlightingcontrol.dto.ModeDto;
import com.acme.xxlightingcontrol.lib.listener.OnRecyclerItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jx9@msn.com
 */
public class ModeAdapter extends RecyclerView.Adapter<ModeAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView modeIV;

        public TextView modeNameTV;

        public RecyclerView childRV;

        public ModeChildAdapter modeChildAdapter;

        public ViewHolder(View v) {
            super(v);
            modeNameTV = v.findViewById(R.id.mode_name_tv);
            modeIV = v.findViewById(R.id.mode_image_iv);
            childRV = v.findViewById(R.id.child_mode_rv);
        }

        @Override
        public void onClick(View view) {

        }

    }

    private int selectedPosition = -1;

    private Context mContext;

    private List<ModeDto> modeDtos;

    private OnRecyclerItemClickListener mRecyclerItemClickListener;

    public ModeAdapter(Context context, List<ModeDto> modeDtos,
                       OnRecyclerItemClickListener recyclerItemClickListener) {
        this.mContext = context;
        this.modeDtos = modeDtos;
        this.mRecyclerItemClickListener = recyclerItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mode, parent, false);
        final ViewHolder vh = new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = vh.getAdapterPosition();
                mRecyclerItemClickListener.onItemClick(v, vh.getAdapterPosition());
            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ModeDto modeDto = modeDtos.get(position);
        holder.modeNameTV.setText(modeDto.getName());
        if (selectedPosition == position) {
            Picasso.with(mContext).load(modeDto.getIconClick()).into(holder.modeIV);
        } else {
            Picasso.with(mContext).load(modeDto.getIcon()).into(holder.modeIV);
        }
        if (modeDto.getChildren() != null && !modeDto.getChildren().isEmpty()) {
            List<ModeDto> childModeList = modeDto.getChildren();
            holder.modeChildAdapter.setModeDtos(childModeList);
            holder.modeChildAdapter.setmRecyclerItemClickListener(new OnRecyclerItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    holder.modeChildAdapter.notifyDataSetChanged();
                }
            });
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            holder.childRV.setLayoutManager(linearLayoutManager);
            holder.childRV.setAdapter(holder.modeChildAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return modeDtos.size();
    }

}
