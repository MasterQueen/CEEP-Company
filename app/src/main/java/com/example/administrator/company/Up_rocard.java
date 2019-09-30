package com.example.administrator.company;

import android.arch.lifecycle.Lifecycle;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 7/23/2018.
 */

public class Up_rocard extends Fragment {

    private RecyclerView mRecycleView;
    private List<Rocard_Datas> mDatas = new ArrayList<>();
    private RecycleView_adapter mAdapter;
    private LinearLayoutManager linearLayoutManager;
    private String select_json = MainActivity.select_json;
    private int count = MainActivity.count;

    private String a[] = new String[count];
    private String b[] = new String[count];
    private String c[] = new String[count];
    private String d[] = new String[count];
    private String e[] = new String[count];
    private String f[] = new String[count];
    private String g[] = new String[count];


    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.rocard_layout,null);
        setView(view);
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.c));
        mRecycleView.addItemDecoration(divider);


        return view;
    }


    public  void setView(View view){

        try {
            JSONArray jsonArray = new JSONArray(select_json);
            for (int i = 0; i < count; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                a[i] = jsonObject.getString("address");
                b[i] = jsonObject.getString("sendtime");
                c[i] = jsonObject.getString("mark");
                d[i] = jsonObject.getString("postion");
                e[i] = jsonObject.getString("request");
                f[i] = jsonObject.getString("time");
                g[i] = jsonObject.getString("people");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0;i<count;i++){
            Rocard_Datas A = new Rocard_Datas(a[i],b[i],c[i]);
            mDatas.add(A);
        }

        mRecycleView = view.findViewById(R.id.id_recycleview);

        mRecycleView.setLayoutManager(linearLayoutManager);

        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        mRecycleView.setLayoutManager(linearLayoutManager);

        mAdapter = new RecycleView_adapter(getActivity(),mDatas);

        mRecycleView.setAdapter(mAdapter);

        //监听
        mAdapter.setmOnItemClickListener(new RecycleView_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(),Up_rocard_massage.class);
                intent.putExtra("postion",d[position]);
                intent.putExtra("request",e[position]);
                intent.putExtra("address",a[position]);
                intent.putExtra("time",f[position]);
                intent.putExtra("people",g[position]);
                startActivity(intent);

            }
        });

    }
}
