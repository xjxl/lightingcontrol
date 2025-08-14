package com.acme.xxlightingcontrol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.acme.xxlightingcontrol.R;
import com.acme.xxlightingcontrol.dto.ModeDto;
import com.acme.xxlightingcontrol.lib.listener.OnRecyclerItemClickListener;

import java.util.List;

/**
 * @author jx9@msn.com
 */
public class ModeChildAdapter extends RecyclerView.Adapter<ModeChildAdapter.ViewHolder> {

    private int selectedPosition = -1;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView modeNameTV;

        public ViewHolder(View v) {
            super(v);
            modeNameTV = v.findViewById(R.id.mode_name_tv);
        }

        @Override
        public void onClick(View view) {

        }

    }

    private Context mContext;

    private List<ModeDto> modeDtos;

    private OnRecyclerItemClickListener mRecyclerItemClickListener;

    public ModeChildAdapter() {
    }

    public ModeChildAdapter(Context context, List<ModeDto> modeDtos,
                            OnRecyclerItemClickListener recyclerItemClickListener) {
        this.mContext = context;
        this.modeDtos = modeDtos;
        this.mRecyclerItemClickListener = recyclerItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_child_mode, parent, false);
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
            holder.modeNameTV.setBackgroundResource(R.drawable.child_mode_item_shape);
        } else {
            holder.modeNameTV.setBackground(null);
        }
    }

    @Override
    public int getItemCount() {
        return modeDtos.size();
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public List<ModeDto> getModeDtos() {
        return modeDtos;
    }

    public void setModeDtos(List<ModeDto> modeDtos) {
        this.modeDtos = modeDtos;
    }

    public OnRecyclerItemClickListener getmRecyclerItemClickListener() {
        return mRecyclerItemClickListener;
    }

    public void setmRecyclerItemClickListener(OnRecyclerItemClickListener mRecyclerItemClickListener) {
        this.mRecyclerItemClickListener = mRecyclerItemClickListener;
    }
}
