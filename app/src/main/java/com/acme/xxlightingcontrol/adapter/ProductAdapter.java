package com.acme.xxlightingcontrol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.acme.xxlightingcontrol.R;
import com.acme.xxlightingcontrol.dto.ProductDto;
import com.acme.xxlightingcontrol.lib.listener.OnRecyclerItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author jx9@msn.com
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView productImgIV;

        public TextView productNameTV;

        public ImageView statusIV;

        public ViewHolder(View v) {
            super(v);
            productImgIV = v.findViewById(R.id.productImgIv);
            productNameTV = v.findViewById(R.id.productNameTv);
            statusIV = v.findViewById(R.id.statusIV);
        }

        @Override
        public void onClick(View view) {

        }

    }

    private Context mContext;

    private List<ProductDto> productDtos;

    private OnRecyclerItemClickListener mRecyclerItemClickListener;

    public ProductAdapter(Context context, List<ProductDto> productDtos,
                          OnRecyclerItemClickListener recyclerItemClickListener) {
        this.mContext = context;
        this.productDtos = productDtos;
        this.mRecyclerItemClickListener = recyclerItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        final ViewHolder vh = new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerItemClickListener.onItemClick(v, vh.getAdapterPosition());
            }
        });

        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ProductDto productDto = productDtos.get(position);
        holder.productNameTV.setText(productDto.getName());
        Picasso.with(mContext).load(productDto.getIcon()).into(holder.productImgIV);
    }


    @Override
    public int getItemCount() {
        return productDtos.size();
    }
}
