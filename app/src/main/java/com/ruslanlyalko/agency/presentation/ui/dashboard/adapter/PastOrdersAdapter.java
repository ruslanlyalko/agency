package com.ruslanlyalko.agency.presentation.ui.dashboard.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruslanlyalko.agency.R;
import com.ruslanlyalko.agency.data.models.Order;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ruslan Lyalko
 * on 08.08.2018.
 */
public class PastOrdersAdapter extends RecyclerView.Adapter<PastOrdersAdapter.ViewHolder> {

    private final OnItemClickListener mOnItemClickListener;
    private List<Order> mData = new ArrayList<>();

    public PastOrdersAdapter(final OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setData(final List<Order> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_past_order, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_logo) ImageView mImageLogo;
        @BindView(R.id.text_title) TextView mTextTitle;
        @BindView(R.id.text_subtitle) TextView mTextSubtitle;
        @BindView(R.id.text_income) TextView mTextIncome;
        @BindView(R.id.text_date) TextView mTextDate;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(final Order order) {
        }
    }
}