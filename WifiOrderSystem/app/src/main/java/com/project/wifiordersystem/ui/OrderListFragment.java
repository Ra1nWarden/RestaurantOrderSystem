package com.project.wifiordersystem.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.project.wifiordersystem.R;
import com.project.wifiordersystem.activities.OrderDetailActivity;
import com.project.wifiordersystem.data.OrderListAdapter;

import java.io.Serializable;

/**
 * Order list shown on the home view.
 */
public final class OrderListFragment extends ListFragment {

    private OrderListAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new OrderListAdapter(getActivity());
        getListView().setAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                Bundle args = new Bundle();
                args.putSerializable(OrderDetailActivity.ORDER_KEY, (Serializable) adapter
                        .getItem(position));
                intent.putExtras(args);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.loadData();
    }
}
