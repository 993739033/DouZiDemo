package com.scode.douzidemo.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scode.douzidemo.MyApplication;
import com.scode.douzidemo.R;
import com.scode.douzidemo.adapter.base.BasePageAdapter;
import com.scode.douzidemo.model.bean.SubjectBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 知らないのセカイ on 2017/6/17.
 */

public class PageMoiveAdapter extends BasePageAdapter<SubjectBean> {
    public PageMoiveAdapter(List<SubjectBean> mData) {
        super(mData);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mFoot!=null&&viewType==TYPE_FOOT) return new MyViewHolder(mFoot);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_basepager, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==TYPE_FOOT) return;//为foot时直接return防止出错 应为借用的时myViewHolder
        ((MyViewHolder)holder).item_base_tv_title.setText(mData.get(position).getTitle());
        ((MyViewHolder)holder).item_base_tv_number.setText(mData.get(position).getRating().getAverage() + "");
        ((MyViewHolder)holder).item_movie_ratingbar.setRating((float) mData.get(position).getRating().getAverage() / 2);

        Glide.with(MyApplication.getContext())
                .load(mData.get(position).getImages().getLarge())
                .into(((MyViewHolder)holder).item_base_iv);
        //设置点击事件
        if (mListener != null) {
            ((MyViewHolder)holder).item_movie_cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition();
                    mListener.ItemClickListener(holder.itemView, mData.get(position).getId(),mData.get(position).getImages().getLarge());
                }
            });

            ((MyViewHolder)holder).item_movie_cardview.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = holder.getLayoutPosition();
                    mListener.ItemLongClickListener(holder.itemView, position);
                    return true;
                }
            });
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_base_iv)
        ImageView item_base_iv;
        @BindView(R.id.item_base_tv_title)
        TextView item_base_tv_title;
        @BindView(R.id.item_base_tv_number)
        TextView item_base_tv_number;
        @BindView(R.id.item_base_ratingbar)
        RatingBar item_movie_ratingbar;
        @BindView(R.id.item_movie_cardview)
        CardView item_movie_cardview;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }


}
