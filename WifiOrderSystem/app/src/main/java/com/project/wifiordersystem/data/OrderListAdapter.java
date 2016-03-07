package com.project.wifiordersystem.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

import java.util.ArrayList;

/**
 * Adapter for order list in HomeActivity.
 */
public final class OrderListAdapter extends BaseAdapter {

    public static final String TABLE_ID_KEY = "table_id";
    private static final String TAG = "OrderListAdapter";
    private final Context context;
    private ArrayList<Order> orders;

    public OrderListAdapter(Context context) {
        this.context = context;
        orders = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int position) {
        return orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        View rowView = convertView;
        if (rowView == null) {
            rowView = inflater.inflate(R.layout.order_list_item, parent, false);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.orderId = (TextView) rowView.findViewById(R.id.order_id_label);
            viewHolder.specialInstruction = (TextView) rowView.findViewById(R.id
                    .special_instruction);
            viewHolder.timeCreated = (TextView) rowView.findViewById(R.id.last_modification_label);
            rowView.setTag(viewHolder);
        }
        Order order = (Order) getItem(position);
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.orderId.setText(Integer.toString(order.getId()));
        holder.specialInstruction.setText(order.getSpecialInstruction());
        DateFormat dateFormat = new DateFormat();
        holder.timeCreated.setText(DateFormat.format("yyyy-MM-dd hh:mm:ss", order.getTimeCreated
                () * 1000));
        return rowView;
    }

    public void loadData() {
        orders.clear();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int tableId = preferences.getInt(TABLE_ID_KEY, 3);
        Volley.newRequestQueue(context).add(new JsonArrayRequest(RESTClient.getInstance()
                .getOrderUrlForTable(tableId), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Gson gson = new GsonBuilder().create();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        orders.add(gson.fromJson(response.get(i).toString(), Order.class));
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

    static class ViewHolder {
        protected TextView orderId;
        protected TextView specialInstruction;
        protected TextView timeCreated;
    }
}
