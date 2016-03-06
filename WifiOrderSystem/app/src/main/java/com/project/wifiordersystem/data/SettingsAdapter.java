package com.project.wifiordersystem.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.project.wifiordersystem.R;

/**
 * Adapter for settings list.
 */
public class SettingsAdapter extends BaseAdapter {

    private final Context context;

    public SettingsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.settings_list_item, parent, false);
        }
        TextView settingLabel = (TextView) view.findViewById(R.id.setting_title);
        TextView settingValue = (TextView) view.findViewById(R.id.setting_value);
        if (position == 0) {
            settingLabel.setText(context.getResources().getString(R.string.version));
            settingValue.setText("v1.0");
        } else if (position == 1) {
            settingLabel.setText(context.getResources().getString(R.string.table_id));
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            int tableId = preferences.getInt(OrderListAdapter.TABLE_ID_KEY, 3);
            settingValue.setText(Integer.toString(tableId));
        }
        return view;
    }
}
