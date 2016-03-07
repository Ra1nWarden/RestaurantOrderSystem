package com.project.wifiordersystem.ui;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.wifiordersystem.R;
import com.project.wifiordersystem.data.DishListAdapter;

import java.util.List;

/**
 * A list fragment displaying all dishes.
 */
public final class DishListFragment extends ListFragment {


    private static final String DIALOG_TAG = "reviewDialog";
    private DishListAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new DishListAdapter(getActivity());
        getListView().setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.loadData();
    }

    public void submit() {
        List<Integer> selectedDishes = adapter.getAllSelectedId();
        float totalPrice = adapter.getTotalPrice();
        if (!selectedDishes.isEmpty()) {
            SpecialInstructionDialogFragment f = SpecialInstructionDialogFragment.getInstance
                    (selectedDishes, totalPrice);
            f.show(getActivity().getSupportFragmentManager(), DIALOG_TAG);
        }
    }

}
