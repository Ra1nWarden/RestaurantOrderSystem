package project.com.wifiordersystem.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import project.com.wifiordersystem.R;

/**
 * Home activity for the app.
 */
public final class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.home);
        setUpActionBar();
    }

    private void setUpActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            RelativeLayout layout = (RelativeLayout)
                    LayoutInflater.from(this).inflate(R.layout.home_action_bar, null);
            actionBar.setCustomView(layout);
            actionBar.setDisplayShowCustomEnabled(true);
        } else {
            if (Log.isLoggable(TAG, Log.ERROR)) {
                Log.e(TAG, "Action bar is null at setUpActionBar()");
            }
        }
    }

}
