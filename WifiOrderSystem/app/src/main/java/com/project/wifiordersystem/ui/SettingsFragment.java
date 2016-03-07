package com.project.wifiordersystem.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.project.wifiordersystem.R;
import com.project.wifiordersystem.data.OrderListAdapter;
import com.project.wifiordersystem.data.SettingsAdapter;

/**
 * Settings view.
 */
public final class SettingsFragment extends ListFragment implements SharedPreferences
        .OnSharedPreferenceChangeListener {

    private static final String DIALOG_TAG = "dialog";
    private SettingsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PreferenceManager.getDefaultSharedPreferences(getActivity())
                .registerOnSharedPreferenceChangeListener(this);
        adapter = new SettingsAdapter(getActivity());
        getListView().setAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    TableIdDialogFragment f = new TableIdDialogFragment();
                    f.show(getActivity().getSupportFragmentManager(), DIALOG_TAG);
                }
            }
        });
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(OrderListAdapter.TABLE_ID_KEY)) {
            adapter.notifyDataSetChanged();
        }
    }
}
