package com.example.administrator.company;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 7/24/2018.
 */

public class RecycleView_adapter extends RecyclerView.Adapter<RecycleView_adapter.MyviewHolder> {

    private LayoutInflater mlayout;
    private Context mContext;
    private List<Rocard_Datas> mDatas;
    private MyItemClickListener mItemClickListener;

    public interface OnItemClickListener{

        void onItemClick(View view,int postion);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }


    public RecycleView_adapter(Context context,List<Rocard_Datas>mDatas){
        mContext = context;
        this.mDatas = mDatas;
        mlayout = LayoutInflater.from(context);
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mContext).inflate(R.layout.recycleview,parent,false);
        MyviewHolder holder = new MyviewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void onBindViewHolder(final MyviewHolder holder, final int position) {

        Rocard_Datas datas = mDatas.get(position);

        holder.address.setText(datas.getAddress());
        holder.time.setText(datas.getTime());
        holder.postion.setText(datas.getPostion());

        if(mOnItemClickListener!=null){

            holder.address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private MyItemClickListener mlistenter;
        TextView address;
        TextView time;
        TextView postion;



        public MyviewHolder(View view){
            super(view);

            address = view.findViewById(R.id.announce_address);
            time = view.findViewById(R.id.announce_time);
            postion = view.findViewById(R.id.announce_postion);

            this.mlistenter =mItemClickListener;



        }
        public void onClick(View view) {

            if( mlistenter!=null){
                mlistenter.onItemClick(view,getPosition());
            }

        }

    }
    public interface  MyItemClickListener{
        void onItemClick(View view , int position);
    }
}
