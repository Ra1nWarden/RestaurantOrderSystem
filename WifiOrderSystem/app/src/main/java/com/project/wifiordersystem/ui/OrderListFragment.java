package com.project.wifiordersystem.ui;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.wifiordersystem.R;
import com.project.wifiordersystem.data.Order;
import com.project.wifiordersystem.data.OrderListAdapter;

import java.util.ArrayList;

/**
 * Order list shown on the home view.
 */
public class OrderListFragment extends ListFragment {

    private OrderListAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.order_list_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new OrderListAdapter(getActivity(), R.layout.order_list_item, new
                ArrayList<Order>());
        getListView().setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.loadData();
    }
}
