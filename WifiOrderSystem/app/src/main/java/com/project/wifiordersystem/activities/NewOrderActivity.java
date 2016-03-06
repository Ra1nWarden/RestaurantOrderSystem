package com.project.wifiordersystem.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.project.wifiordersystem.R;

/**
 * An activity that allows the user to create a new order.
 */
public final class NewOrderActivity extends AppCompatActivity {

    private static final String TAG = "NewOrderActivity";

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.new_order);
        setUpActionBar();
    }

    private void setUpActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            RelativeLayout layout = (RelativeLayout)
                    LayoutInflater.from(this).inflate(R.layout.new_order_action_bar, null);
            actionBar.setCustomView(layout);
            actionBar.setDisplayShowCustomEnabled(true);
        } else {
            if (Log.isLoggable(TAG, Log.ERROR)) {
                Log.e(TAG, "Action bar is null at setUpActionBar()");
            }
        }
    }


    public void cancelOrder(View v) {
        finish();
    }
}
