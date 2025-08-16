package com.acme.xxlightingcontrol.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.acme.xxlightingcontrol.R;
import com.acme.xxlightingcontrol.common.MessageConstants;
import com.acme.xxlightingcontrol.dto.ModeDto;
import com.acme.xxlightingcontrol.lib.listener.OnRecyclerItemClickListener;
import com.acme.xxlightingcontrol.lib.net.udp.UDPClient;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author jx9@msn.com
 */
public class ModeAdapter extends RecyclerView.Adapter<ModeAdapter.ViewHolder> {

    public int selectedPosition = RecyclerView.NO_POSITION;
    public int lastSelectedPosition = RecyclerView.NO_POSITION;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView modeIV;

        private final TextView modeNameTV;

        private final RadioGroup childModeRG;

        public ViewHolder(View v) {
            super(v);
            modeNameTV = v.findViewById(R.id.mode_name_tv);
            modeIV = v.findViewById(R.id.mode_image_iv);
            childModeRG = v.findViewById(R.id.child_mode_rg);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int adapterPosition = ViewHolder.this.getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        lastSelectedPosition = selectedPosition;
                        selectedPosition = adapterPosition;
                        if (lastSelectedPosition != RecyclerView.NO_POSITION) {
                            notifyItemChanged(lastSelectedPosition);
                        }
                        if (selectedPosition != RecyclerView.NO_POSITION) {
                            notifyItemChanged(selectedPosition);
                        }
                        ModeDto modeDto = modeDtos.get(selectedPosition);
                        if (!modeDto.isHaveChild()) {
                            mRecyclerItemClickListener.onItemClick(v, adapterPosition);
                        } else {
                            RadioButton defaultCheckedButton = (RadioButton) ViewHolder.this.childModeRG.getChildAt(0);
                            defaultCheckedButton.callOnClick();
                        }
                    }
                }
            });
        }

        @Override
        public void onClick(View view) {

        }

        public void bind(Context context, ModeDto modeDto, boolean isSelected) {
            if (isSelected) {
                Picasso.with(context).load(modeDto.getIconClick()).into(this.modeIV);
            } else {
                Picasso.with(context).load(modeDto.getIcon()).into(this.modeIV);
            }
        }
    }


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
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ModeDto modeDto = modeDtos.get(position);
        holder.modeNameTV.setText(modeDto.getName());
        Picasso.with(mContext).load(modeDto.getIconClick()).fetch();
        Picasso.with(mContext).load(modeDto.getIcon()).fetch();
        holder.bind(mContext, modeDto, position == selectedPosition);
        holder.childModeRG.removeAllViews();
        if (modeDto.isHaveChild()) {
            final List<ModeDto> childModeList = modeDto.getChildren();
            if (childModeList != null && !childModeList.isEmpty()) {
                for (int i = 0, n = childModeList.size(); i < n; i++) {
                    ModeDto childMode = childModeList.get(i);
                    final int childIndex = i;
                    RadioButton childRadioButton = new RadioButton(mContext);
                    childRadioButton.setText(childMode.getName());
                    childRadioButton.setId(View.generateViewId());
                    childRadioButton.setGravity(Gravity.CENTER);
                    childRadioButton.setTextColor(Color.WHITE);
                    childRadioButton.setButtonDrawable(null);
                    childRadioButton.setBackgroundResource(R.drawable.selector_child_item_check);
                    childRadioButton.setChecked(selectedPosition == position && modeDto.getSelectedChildIndex() == i);
                    RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(45, 45);
                    childRadioButton.setLayoutParams(params);
                    childRadioButton.setOnClickListener(v -> {
                        UDPClient.getInstance().sendMessage(MessageConstants.LIGHT + childMode.getType());
                        modeDto.setSelectedChildIndex(childIndex);
//                        holder.itemView.callOnClick();
                        int adapterPosition = holder.getAdapterPosition();
                        if (adapterPosition != RecyclerView.NO_POSITION) {
                            lastSelectedPosition = selectedPosition;
                            selectedPosition = adapterPosition;
                            if (lastSelectedPosition != RecyclerView.NO_POSITION) {
                                notifyItemChanged(lastSelectedPosition);
                            }
                            if (selectedPosition != RecyclerView.NO_POSITION) {
                                notifyItemChanged(selectedPosition);
                            }
                        }
//                        mRecyclerItemClickListener.onItemClick(v, holder.getAdapterPosition());
                        holder.bind(mContext, modeDto, position == selectedPosition);
                    });
                    holder.childModeRG.addView(childRadioButton);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return modeDtos.size();
    }

}
