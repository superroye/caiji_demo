package com.supylc.uilibs.talent;

import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.collection.ArrayMap;
import androidx.recyclerview.widget.RecyclerView;

import com.supylc.uilibs.talent.support.HolderRes;
import com.supylc.uilibs.talent.support.NullHolder;
import com.supylc.uilibs.talent.support.NullObject;
import com.supylc.uilibs.talent.support.TalentItemType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roye on 2016/12/10.
 */

public class TalentAdapter extends RecyclerView.Adapter {

    String TAG = TalentAdapter.class.getSimpleName();

    private List mItems = new ArrayList<>();

    protected OnItemClickListener mItemClickListener;

    protected OnItemLongClickListener mItemLongClickListener;

    private SparseArray<Class> mHolderClassesMap = new SparseArray<>();
    private ArrayMap<Class, Integer> mHolderLayoutIdMap = new ArrayMap<>();

    //viewType 划分：高24位为数据类型，低8位为数据类型的二级类型
    private final int LOW_TYPE_SIZE = 0x000F;

    private List<Class> dataClasses = new ArrayList<>(); //原则上超过255种数据类型就会出bug
    private LayoutInflater inflater;

    public interface OnItemLongClickListener<T> {
        void onItemLongClick(View view, T t, int position);
    }

    public abstract static class OnItemClickListener<T> {
        public abstract void onItemClick(View view, T t, int position);
    }


    public TalentAdapter() {
        this(null);
    }

    public TalentAdapter(List items) {
        if (items != null) {
            mItems = items;
        }
        registerHolder(NullHolder.class);
    }

    public List getItems() {
        return mItems;
    }

    public void resetItems(List items) {
        resetItems(items, true);
    }

    public void resetItems(List items, boolean notify) {
        if (items != null) {
            mItems = items;
            if (notify) {
                notifyDataSetChanged();
            }
        }
    }

    public void removeItem(int position) {
        if (mItems != null && mItems.size() > position) {
            mItems.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void removeItems(int position, int itemCount) {
        if (mItems != null && mItems.size() > position) {
            for (int i = 0; i < itemCount; i++) {
                mItems.remove(position);
            }
            notifyItemRangeRemoved(position, itemCount);
        }
    }

    public void addItems(List items) {
        insertItems(items, mItems.size());
    }

    public void addItem(Object item) {
        insertItem(item, mItems.size());
    }

    public void insertItem(Object item, int position) {
        if (mItems != null && position > -1 && mItems.size() >= position) {
            mItems.add(position, item);
            notifyItemInserted(position);
        }
    }

    public void insertItems(List items, int position) {
        if (items != null) {
            if (mItems != null && position > -1 && mItems.size() >= position) {
                mItems.addAll(position, items);
                notifyItemRangeInserted(position, items.size());
            }
        }
    }

    public Object getItem(int position) {
        return mItems.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener itemLongClickListener) {
        mItemLongClickListener = itemLongClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }

        Class holderClass = mHolderClassesMap.get(viewType);
        if (holderClass == null) {
            throw new RuntimeException("the viewType not found.");
        }

        Object holder = null;
        try {
            int holderResId;
            Integer layoutId = mHolderLayoutIdMap.get(holderClass);
            if (layoutId == null) {
                HolderRes holderRes = (HolderRes) holderClass.getAnnotation(HolderRes.class);
                holderResId = holderRes.value();
                if (!TextUtils.isEmpty(holderRes.resName())) {
                    holderResId = parent.getResources().getIdentifier(holderRes.resName(), "layout", parent.getContext().getPackageName());
                }
            } else {
                holderResId = layoutId;
            }
            View root = inflater.inflate(holderResId, parent, false);
            holder = holderClass.getConstructor(View.class).newInstance(root);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (holder == null) {
            throw new RuntimeException("holder not found.");
        }
        return (TalentHolder) holder;
    }

    @Override
    public int getItemViewType(int position) {
        Object dataItem;
        dataItem = getItem(position);
        if (dataItem == null) {
            dataItem = new NullObject();
        }
        Class dataClass = dataItem.getClass();
        int index = dataClasses.indexOf(dataItem.getClass());
        int lowType = 0;
        if (index < 0) {
            throw new RuntimeException("this dataItem has no designated itemType.");
        }
        if (TalentItemType.class.isAssignableFrom(dataClass)) {
            lowType = ((TalentItemType) dataItem).getItemType();
            if (lowType < 0 || lowType > LOW_TYPE_SIZE) {
                throw new RuntimeException(String.format("itemType must between %d and %d", 0, LOW_TYPE_SIZE));
            }
        }
        int itemType = getRealItemType(index, lowType);
        return itemType;
    }

    private int getRealItemType(int index, int lowType) {
        return (index + 1) << 4 + lowType;
    }

    private Class getDataTypeClass(Class holderClass) {
        Class cls = null;
        try {
            ParameterizedType pt = ((ParameterizedType) holderClass.getGenericSuperclass());
            Type type = pt.getActualTypeArguments()[0];
            if (type instanceof ParameterizedType) {
                cls = (Class) ((ParameterizedType) type).getRawType();
            } else {
                cls = (Class) (type);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return cls;
    }

    //手动绑定Holder和layoutId，Holder不必添加注解
    public void registerHolderAndLayoutId(Class<? extends TalentHolder> holderClass, @LayoutRes int layoutId) {
        mHolderLayoutIdMap.put(holderClass, layoutId);
        registerHolder(holderClass, 0);
    }

    //绑定Holder，此Holder类需要添加注解
    public void registerHolder(Class<? extends TalentHolder> holderClass) {
        registerHolder(holderClass, 0);
    }

    /**
     * @param holderClass 支持覆盖注册. Holder绑定同样的数据类型，会把旧的Holderclass覆盖
     * @param lowType     此模式指定数据类型值绑定对应的holder，注意：需要数据item实现TalentItemType返回相应的类型值.
     *                    注意，lowType < 16
     */
    public void registerHolder(Class<? extends TalentHolder> holderClass, int lowType) {
        if (lowType < 0 || lowType > LOW_TYPE_SIZE) {
            throw new RuntimeException(String.format("itemType must between %d and %d", 0, LOW_TYPE_SIZE));
        }

        Class dataType = getDataTypeClass(holderClass);
        if (dataType == null) {
            throw new RuntimeException("holder DataType class not found.");
        }

        int itemTypeIndex = dataClasses.indexOf(dataType);
        if (itemTypeIndex < 0) {
            dataClasses.add(dataType);
            itemTypeIndex = dataClasses.size() - 1;
        }
        int viewType = getRealItemType(itemTypeIndex, lowType);
        mHolderClassesMap.put(viewType, holderClass);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TalentHolder mHolder = ((TalentHolder) holder);
        mHolder.bind(getItem(position));

        mHolder.setOnItemClick(mItemClickListener);
        mHolder.setOnItemLongClick(mItemLongClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        TalentHolder mHolder = ((TalentHolder) holder);
        if (payloads.isEmpty()) {
            mHolder.bind(getItem(position));

            mHolder.setOnItemClick(mItemClickListener);
            mHolder.setOnItemLongClick(mItemLongClickListener);
        } else {
            mHolder.onPayload(payloads.get(0));
        }
    }

    @Override
    public int getItemCount() {
        return getRealItemCount();
    }

    public int getRealItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        if (holder instanceof TalentHolder) {
            ((TalentHolder) holder).recycle();
        }
    }
}
