package com.project.wifiordersystem.data;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
 * Adapter for dish list.
 */
public final class DishListAdapter extends BaseAdapter {

    private static final String TAG = "DishListAdapter";
    private final Context context;
    private ArrayList<Dish> dishes;

    public DishListAdapter(Context context) {
        this.context = context;
        dishes = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return dishes.size();
    }

    @Override
    public Object getItem(int position) {
        return dishes.get(position);
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
            rowView = inflater.inflate(R.layout.dish_list_item, parent, false);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.priceText = (TextView) rowView.findViewById(R.id.dish_price);
            viewHolder.nameText = (TextView) rowView.findViewById(R.id.dish_name);
            viewHolder.checkBox = (CheckBox) rowView.findViewById(R.id.dish_checkbox);
            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton
                    .OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Dish dish = (Dish) viewHolder.checkBox.getTag();
                    dish.setSelected(buttonView.isChecked());
                }
            });
            rowView.setTag(viewHolder);
            viewHolder.checkBox.setTag(getItem(position));
        } else {
            ((ViewHolder) rowView.getTag()).checkBox.setTag(getItem(position));
        }
        ViewHolder holder = (ViewHolder) rowView.getTag();
        Dish dish = (Dish) getItem(position);
        holder.nameText.setText(dish.getName());
        holder.priceText.setText(Float.toString(dish.getPrice()));
        holder.checkBox.setChecked(dish.isSelected());
        return rowView;
    }

    public void loadData() {
        dishes.clear();
        Volley.newRequestQueue(context).add(new JsonArrayRequest(RESTClient.getInstance()
                .getAllDishes(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Gson gson = new GsonBuilder().create();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        dishes.add(gson.fromJson(response.get(i).toString(), Dish.class));
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
        protected TextView nameText;
        protected TextView priceText;
        protected CheckBox checkBox;
    }
}
