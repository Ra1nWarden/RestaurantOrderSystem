package com.project.wifiordersystem.ui;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.wifiordersystem.R;
import com.project.wifiordersystem.activities.OrderDetailActivity;
import com.project.wifiordersystem.data.OrderedDishListAdapter;

/**
 * An activity that shows order details.
 */
public final class OrderedDishesFragment extends ListFragment {

    private OrderedDishListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new OrderedDishListAdapter(getActivity(), ((OrderDetailActivity) getActivity())
                .getOrderId());
        getListView().setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.loadData();
    }
}
