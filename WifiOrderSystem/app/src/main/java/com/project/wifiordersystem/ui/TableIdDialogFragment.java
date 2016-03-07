package com.project.wifiordersystem.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.project.wifiordersystem.R;
import com.project.wifiordersystem.data.OrderListAdapter;

/**
 * A dialog fragment for setting table id.
 */
public final class TableIdDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstance) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final View view = LayoutInflater.from(getActivity()).inflate(R.layout
                .set_table_id_dialog, null);
        builder.setTitle(R.string.set_table_id_title)
                .setView(view)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText textField = (EditText) view.findViewById(R.id.id_value);
                        int newId = Integer.parseInt(textField.getText().toString());
                        SharedPreferences preferences = PreferenceManager
                                .getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt(OrderListAdapter.TABLE_ID_KEY, newId).commit();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .remove(TableIdDialogFragment.this)
                                .commit();
                    }
                });
        return builder.create();

    }
}
