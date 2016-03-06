package com.project.wifiordersystem.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.wifiordersystem.R;
import com.project.wifiordersystem.network.RESTClient;

import org.json.JSONArray;

import java.util.List;

/**
 * Adapter for order list in HomeActivity.
 */
public class OrderListAdapter extends ArrayAdapter<Order> {

    private static final String TAG = "OrderListAdapter";
    private static final String TABLE_ID_KEY = "table_id";
    private final Context context;

    public OrderListAdapter(Context context, int resourceId, List<Order> objects) {
        super(context, resourceId, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        View rowView = convertView;
        if (rowView == null) {
            rowView = inflater.inflate(R.layout.order_list_item, parent, false);
        }
        Order order = getItem(position);
        Log.d(TAG, "order is " + order.getId() + " tableId " + order.getTableId());
        ((TextView) rowView.findViewById(R.id.id_label)).setText(Integer.toString(order.getId()));
        ((TextView) rowView.findViewById(R.id.table_label)).setText(Integer.toString(order
                .getTableId()));
        return rowView;
    }

    public void loadData() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int tableId = preferences.getInt(TABLE_ID_KEY, 0);
        Volley.newRequestQueue(context).add(new JsonArrayRequest(RESTClient.getInstance()
                .getOrderUrlForTable(3), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Gson gson = new GsonBuilder().create();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        add(gson.fromJson(response.get(i).toString(), Order.class));
                    } catch (Exception e) {
                        if (Log.isLoggable(TAG, Log.ERROR)) {
                            Log.e(TAG, "Error in parsing json with gson.");
                        }
                    }
                }
                notifyDataSetChanged();
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (Log.isLoggable(TAG, Log.ERROR)) {
                    Log.e(TAG, "Error in volley.");
                }
            }
        }));
    }
}
