package com.project.wifiordersystem.data;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.wifiordersystem.R;
import com.project.wifiordersystem.network.RESTClient;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * List adapter for ordered dishes.
 */
public final class OrderedDishListAdapter extends BaseAdapter {

    private static final String TAG = "OrderedDishListAdapter";
    private final Context context;
    private final int orderId;
    private final TextView totalPrice;
    private ArrayList<OrderedDish> orderedDishs;


    public OrderedDishListAdapter(Context context, int orderId, TextView totalPrice) {
        this.context = context;
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        orderedDishs = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return orderedDishs.size();
    }

    @Override
    public Object getItem(int position) {
        return orderedDishs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderedDish dish = (OrderedDish) getItem(position);
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.ordered_dish_list_item, parent,
                    false);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.dishName = (TextView) view.findViewById(R.id.ordered_dish_name);
            viewHolder.status = (TextView) view.findViewById(R.id.ordered_dish_status);
            view.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.dishName.setText(dish.getName());
        holder.status.setText(OrderedDish.convertStatusToString(dish.getStatus()));
        if (dish.getStatus() == OrderedDish.Status.NOT_STARTED) {
            holder.status.setTextColor(context.getResources().getColor(R.color.notStarted));
        } else if (dish.getStatus() == OrderedDish.Status.PREPARING) {
            holder.status.setTextColor(context.getResources().getColor(R.color.preparing));
        } else if (dish.getStatus() == OrderedDish.Status.DONE) {
            holder.status.setTextColor(context.getResources().getColor(R.color.done));
        }
        return view;
    }

    public void loadData() {
        orderedDishs.clear();
        Volley.newRequestQueue(context).add(new JsonArrayRequest(RESTClient.getInstance()
                .getDishesForOrderId(orderId), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Gson gson = new GsonBuilder().create();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        orderedDishs.add(gson.fromJson(response.get(i).toString(), OrderedDish
                                .class));
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
                    Log.e(TAG, "Error in volley for ordered dish list.");
                }
            }
        }));
        Volley.newRequestQueue(context).add(new StringRequest(RESTClient.getInstance()
                .getTotalPriceForId(orderId), new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                totalPrice.setText(response + "元");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (Log.isLoggable(TAG, Log.ERROR)) {
                    Log.e(TAG, "Error in volley for total price.");
                }
            }
        }));
    }

    static class ViewHolder {
        TextView dishName;
        TextView status;
    }
}
