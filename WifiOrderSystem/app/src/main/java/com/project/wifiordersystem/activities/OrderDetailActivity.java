package com.project.wifiordersystem.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.wifiordersystem.R;
import com.project.wifiordersystem.data.Order;

/**
 * An activity for showing order details
 */
public final class OrderDetailActivity extends AppCompatActivity {

    public static final String ORDER_KEY = "order";
    private static final String TAG = "OrderDetailActivity";
    private Order order;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.order_details);
        setUpActionBar();
        order = (Order) getIntent().getExtras().getSerializable(ORDER_KEY);
        setUpOrderDetailHeader();
    }

    private void setUpOrderDetailHeader() {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.order_detail_header);
        ((TextView) layout.findViewById(R.id.order_id_label)).setText(getResources().getString(R
                .string.order_id) + ": " + Integer.toString(order.getId()));
        ((TextView) layout.findViewById(R.id.special_instruction)).setText(order
                .getSpecialInstruction());
        DateFormat dateFormat = new DateFormat();
        ((TextView) findViewById(R.id.date_label)).setText(DateFormat.format("yyyy-MM-dd " +
                "hh:mm:ss", order.getTimeCreated() * 1000));
    }

    private void setUpActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            RelativeLayout layout = (RelativeLayout)
                    LayoutInflater.from(this).inflate(R.layout.order_details_action_bar, null);
            actionBar.setCustomView(layout);
            actionBar.setDisplayShowCustomEnabled(true);
        } else {
            if (Log.isLoggable(TAG, Log.ERROR)) {
                Log.e(TAG, "Action bar is null at setUpActionBar()");
            }
        }
    }

    public int getOrderId() {
        return order.getId();
    }

    public void backToHome(View v) {
        finish();
    }
}
