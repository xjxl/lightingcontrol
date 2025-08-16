package com.acme.xxlightingcontrol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.acme.xxlightingcontrol.R;
import com.acme.xxlightingcontrol.common.MessageConstants;
import com.acme.xxlightingcontrol.dto.ProductDto;
import com.acme.xxlightingcontrol.lib.listener.OnRecyclerItemClickListener;
import com.acme.xxlightingcontrol.lib.net.udp.UDPClient;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author jx9@msn.com
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    public int selectedPosition = RecyclerView.NO_POSITION;
    public int lastSelectedPosition = RecyclerView.NO_POSITION;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView productImgIV;

        public TextView productNameTV;

        public ImageView statusIV;

        public ViewHolder(View v) {
            super(v);
            productImgIV = v.findViewById(R.id.productImgIv);
            productNameTV = v.findViewById(R.id.productNameTv);
            statusIV = v.findViewById(R.id.statusIV);
            itemView.setOnClickListener(view -> {
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
                    mRecyclerItemClickListener.onItemClick(v, adapterPosition);
                }
            });
        }

        @Override
        public void onClick(View view) {

        }

        public void bind(ProductDto productDto, boolean isSelected) {
            productDto.setPlay(isSelected);
            if (isSelected) {
                this.itemView.setBackgroundResource(R.drawable.product_item_bg_on);
                this.statusIV.setImageResource(R.drawable.product_pause);
            } else {
                this.itemView.setBackgroundResource(R.drawable.product_item_bg);
                this.statusIV.setImageResource(R.drawable.product_play);
            }
        }

    }

    private Context mContext;

    private List<ProductDto> productDtos;

    private OnRecyclerItemClickListener mRecyclerItemClickListener;

    private OnRecyclerItemClickListener mRecyclerItemLongClickListener;

    public ProductAdapter(Context context, List<ProductDto> productDtos,
                          OnRecyclerItemClickListener recyclerItemClickListener,
                          OnRecyclerItemClickListener recyclerItemLongClickListener) {
        this.mContext = context;
        this.productDtos = productDtos;
        this.mRecyclerItemClickListener = recyclerItemClickListener;
        this.mRecyclerItemLongClickListener = recyclerItemLongClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        final ViewHolder vh = new ViewHolder(v);
        v.setOnLongClickListener(view -> {
            mRecyclerItemLongClickListener.onItemClick(v, vh.getAdapterPosition());
            return false;
        });

        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ProductDto productDto = productDtos.get(position);
        holder.productNameTV.setText(productDto.getName());
        holder.bind(productDto, position == selectedPosition);
        holder.statusIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UDPClient.getInstance().sendMessage(productDto.isPlay()
                        ? MessageConstants.PRODUCT_PAUSE : MessageConstants.PRODUCT_PLAY);
                productDto.setPlay(!productDto.isPlay());
                holder.statusIV.setImageResource(productDto.isPlay() ?
                        R.drawable.product_pause : R.drawable.product_play);
            }
        });
        Picasso.with(mContext).load(productDto.getIcon()).into(holder.productImgIV);
    }

    @Override
    public int getItemCount() {
        return productDtos.size();
    }
}
