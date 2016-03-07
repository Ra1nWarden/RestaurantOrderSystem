package com.project.wifiordersystem.ui;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.project.wifiordersystem.R;
import com.project.wifiordersystem.activities.OrderDetailActivity;
import com.project.wifiordersystem.data.OrderedDishListAdapter;

/**
 * An activity that shows order details.
 */
public final class OrderedDishesFragment extends ListFragment {

    private OrderedDishListAdapter adapter;
    private TextView priceText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        ListView list = (ListView) view.findViewById(android.R.id.list);
        View footerView = inflater.inflate(R.layout.total_price, container, false);
        priceText = (TextView) footerView.findViewById(R.id.total_price_field);
        list.addFooterView(footerView);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new OrderedDishListAdapter(getActivity(), ((OrderDetailActivity) getActivity())
                .getOrderId(), priceText);
        getListView().setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.loadData();
    }
}
