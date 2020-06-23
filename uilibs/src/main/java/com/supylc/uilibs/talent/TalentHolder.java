package com.supylc.uilibs.talent;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Roye on 2016/12/10.
 * 泛型 T 必须是准确的数据类型，不能为接口
 */
public abstract class TalentHolder<T> extends RecyclerView.ViewHolder {

    String TAG = TalentHolder.class.getSimpleName();

    public T itemValue;
    public TalentAdapter.OnItemClickListener mItemClickListener;
    public TalentAdapter.OnItemLongClickListener mItemLongClickListener;

    public TalentHolder(View itemView) {
        super(itemView);
        initView();
    }

    public final void bind(T data) {
        itemValue = data;
        toView(data);
    }

    public void initView() {
    }

    protected void setOnItemClick(TalentAdapter.OnItemClickListener listener) {
        mItemClickListener = listener;

        if (listener == null || itemView.hasOnClickListeners()) {//不覆盖item的原来click事件
            return;
        }

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                if (mItemClickListener != null && position != RecyclerView.NO_POSITION) {
                    mItemClickListener.onItemClick(v, itemValue, position);
                }
            }
        });
    }

    protected void setOnItemLongClick(TalentAdapter.OnItemLongClickListener listener) {
        mItemLongClickListener = listener;

        if (listener == null || itemView.hasOnClickListeners()) {//不覆盖item的原来click事件
            return;
        }

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = getAdapterPosition();
                if (mItemLongClickListener != null && position != RecyclerView.NO_POSITION) {
                    mItemLongClickListener.onItemLongClick(itemView, itemValue, position);
                }
                return false;
            }
        });
    }

    //局部刷新
    public void onPayload(Object payload) {

    }

    public Context getContext(){
        return itemView.getContext();
    }

    public <T extends View> T findV(int resId) {
        return (T) itemView.findViewById(resId);
    }

    public abstract void toView(T data);

    public void recycle() {
    }
}
