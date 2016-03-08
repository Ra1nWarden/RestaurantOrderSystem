package com.project.wifiordersystem.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.project.wifiordersystem.R;

/**
 * An input dialog for credit card information.
 */
public final class CreditCardInputDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstance) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.submit_payment_dialog,
                null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.submit_order)
                .setView(view)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .remove(CreditCardInputDialogFragment.this)
                                .commit();
                    }
                });
        return builder.create();
    }
}
