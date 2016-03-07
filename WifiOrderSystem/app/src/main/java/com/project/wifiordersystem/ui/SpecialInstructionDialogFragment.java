package com.project.wifiordersystem.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.wifiordersystem.R;
import com.project.wifiordersystem.data.NewOrder;
import com.project.wifiordersystem.data.OrderListAdapter;
import com.project.wifiordersystem.network.RESTClient;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Dialog for reviewing all selected items.
 */
public class SpecialInstructionDialogFragment extends DialogFragment {

    public static final String ORDER_LIST_KEY = "orderList";
    public static final String TOTAL_PRICE_KEY = "totalPrice";
    private static final String TAG = "SpecialInstructionDia";

    public static SpecialInstructionDialogFragment getInstance(List<Integer> selectedDishes,
                                                               float totalPrice) {
        Bundle args = new Bundle();
        args.putSerializable(ORDER_LIST_KEY, (Serializable) selectedDishes);
        args.putFloat(TOTAL_PRICE_KEY, totalPrice);
        SpecialInstructionDialogFragment f = new SpecialInstructionDialogFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstance) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final View view = LayoutInflater.from(getActivity()).inflate(R.layout
                .review_order_dialog, null);
        float totalPrice = getArguments().getFloat(TOTAL_PRICE_KEY);
        ((TextView) view.findViewById(R.id.review_price_field)).setText(Float.toString(totalPrice));
        builder.setTitle(R.string.submit_order_title)
                .setView(view)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText textField = (EditText) view.findViewById(R.id
                                .review_special_instruction_field);
                        String specialInstruction = textField.getText().toString();
                        List<Integer> selectedIds = (List<Integer>) getArguments()
                                .getSerializable(ORDER_LIST_KEY);
                        NewOrder newOrder = new NewOrder();
                        if (!specialInstruction.isEmpty()) {
                            newOrder.setSpecialInstruction(specialInstruction);
                        }
                        newOrder.setDishIds(selectedIds);
                        newOrder.setTableId(PreferenceManager.getDefaultSharedPreferences
                                (getActivity()).getInt(OrderListAdapter.TABLE_ID_KEY, 3));
                        Gson gson = new GsonBuilder().create();
                        try {
                            Volley.newRequestQueue(getActivity()).add(new JsonObjectRequest
                                    (Request.Method.POST, RESTClient.getInstance()
                                            .getPostOrderUrl(), new JSONObject(gson.toJson
                                            (newOrder, NewOrder.class)), new Response
                                            .Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Toast.makeText(getActivity(), getResources().getString(R
                                                    .string.submit_success), Toast.LENGTH_LONG)
                                                    .show();
                                            getActivity().finish();
                                        }
                                    }, new Response.ErrorListener() {

                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Toast.makeText(getActivity(), getResources().getString(R
                                                    .string.submit_failure), Toast.LENGTH_LONG)
                                                    .show();
                                        }
                                    }));
                        } catch (Exception e) {
                            if (Log.isLoggable(TAG, Log.ERROR)) {
                                Log.e(TAG, "Error in parsing json.");
                            }
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .remove(SpecialInstructionDialogFragment.this)
                                .commit();
                    }
                });
        return builder.create();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
