package com.project.boostcamp.secondminiproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Hong Tae Joon on 2017-07-11.
 */

public class ShopRecyclerAdapter extends RecyclerView.Adapter {
    public final static int SORTING_DISTANCE = 0x100;
    public final static int SORTING_LIKE = 0x101;
    public final static int SORTING_TIME = 0x102;

    private Context context; // 컨텍스트
    private ArrayList<Shop> shops; // 맛집 데이터 리스트

    public ShopRecyclerAdapter(Context context, ArrayList<Shop> shops) {
        this.context = context;
        this.shops = shops;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShopViewHolder(LayoutInflater.from(context).inflate(R.layout.view_shop, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ShopViewHolder)holder).initLayout(shops.get(position));
    }

    public void changeSorting(int type) {
        switch(type) {
            case SORTING_DISTANCE:
                sort(new Comparator<Shop>() {
                    @Override
                    public int compare(Shop s1, Shop s2) {
                        return s1.getDistance() - s2.getDistance();
                    }
                });
                break;
            case SORTING_LIKE:
                sort(new Comparator<Shop>() {
                    @Override
                    public int compare(Shop s1, Shop s2) {
                        return s2.getLike() - s1.getLike();
                    }
                });
                break;
            case SORTING_TIME:
                sort(new Comparator<Shop>() {
                    @Override
                    public int compare(Shop s1, Shop s2) {
                        return (int)(s2.getTime() - s1.getTime());
                    }
                });
                break;
        }
    }

    private void sort(Comparator<Shop> comparator) {
        Collections.sort(shops, comparator);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return shops.size();
    }
}
