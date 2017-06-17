package com.scode.douzidemo.adapter.base;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 知らないのセカイ on 2017/6/17.
 */

public abstract class BasePageAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public List<T> mData;//数据源
    public  final static int TYPE_NORMAl = 1;
    public final static int TYPE_FOOT = 2;
    public View mFoot;

    public BasePageAdapter(List<T> mData) {
        this.mData = mData;
    }

    //添加数据
    public void addDatas(List<T> datas) {
        if (datas != null) {
            mData.addAll(datas);
            notifyDataSetChanged();
        }
    }

    //更新数据
    public void upDatas(List<T> datas) {
        mData = datas;
        notifyDataSetChanged();
    }

    //设置footview并更新adapter
    public void setFootView(View footView) {
        mFoot = footView;
        notifyItemInserted(getItemCount() - 1);

    }

    //获取footview
    public View getFootView() {
        return mFoot;
    }

    //获取数据源长度
    public int getStart() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mFoot == null) return TYPE_NORMAl;
        if (position == getItemCount() - 1) return TYPE_FOOT;
        return TYPE_NORMAl;
    }

    /**
     * 解决StaggeredGridLayoutManager布局添加脚布局的问题
     *
     * @param holder
     */
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams && holder.getLayoutPosition() == getItemCount() - 1) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }

    /**
     * 解决GridLayoutManager添加脚布局的问题
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                //这个方法的返回值决定了我们每个position上的item占据的单元格个数
                @Override
                public int getSpanSize(int position) {
                    if (getItemViewType(position) == TYPE_FOOT) {
                        return gridManager.getSpanCount();
                    } else {
                        return 1;
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void ItemClickListener(View view, String id, String img);

        void ItemLongClickListener(View view, int position);
    }

    public OnItemClickListener mListener;

    public void setOnClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    @Override
    public int getItemCount() {
        if (mFoot != null) {
            return mData.size() + 1;//不为空时返回加一
        } else {
            return mData.size();
        }
    }
}
