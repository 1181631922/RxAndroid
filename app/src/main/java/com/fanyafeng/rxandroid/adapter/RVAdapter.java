package com.fanyafeng.rxandroid.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fanyafeng.rxandroid.R;
import com.fanyafeng.rxandroid.hong9.bean.ProductBean;
import com.fanyafeng.rxandroid.util.ControllerListenerUtil;
import com.fanyafeng.rxandroid.util.MyUtils;

import java.util.List;

/**
 * Created by fanyafeng on 16/6/23.
 */
public class RVAdapter extends BaseRecyclerAdapter<RVAdapter.ViewHolder> {
    private Context context;
    private List<ProductBean> productBeanList;

    public RVAdapter(Context context, List<ProductBean> productBeanList) {
        this.context = context;
        this.productBeanList = productBeanList;
    }

    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(View view, ProductBean productBean, int position);

        void onItemLongClickListener(View view, ProductBean productBean, int position);
    }

    @Override
    public ViewHolder getViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position, boolean isItem) {
//        ControllerListenerUtil.setControllerListener(holder.sdvRvItem, productBeanList.get(position).img, MyUtils.getScreenWidth(context) >> 1, context);
        holder.sdvRvItem.setImageURI(Uri.parse(productBeanList.get(position).img));
        holder.sdvRvItem.setAspectRatio(1.0f);
        if (onItemClickListener != null) {
            holder.sdvRvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClickListener(v, productBeanList.get(position), position);
                }
            });
            holder.sdvRvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onItemClickListener(v, productBeanList.get(position), position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getAdapterItemCount() {
        return productBeanList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView sdvRvItem;

        public ViewHolder(View itemView) {
            super(itemView);
            sdvRvItem = (SimpleDraweeView) itemView.findViewById(R.id.sdvRvItem);
        }
    }
}

