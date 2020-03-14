package com.leon.uitest;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SendFragment extends DialogFragment {
    private static final String MENU_ARGUMENT = "menuModels";
    View view;
    Context context;
    ArrayList<MenuModel> menuModels1;
    List<MenuModel> menuModels2;

    public static SendFragment newInstance(ArrayList<MenuModel> menuModels) {
        SendFragment sendFragment = new SendFragment();
        Bundle bundle = new Bundle();
        bundle.putString(MENU_ARGUMENT, new Gson().toJson(menuModels));
        sendFragment.setArguments(bundle);
        return sendFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.send_fragment, container, false);
        if (getArguments() != null) {
            String jsonBundle = getArguments().getString(MENU_ARGUMENT);
            menuModels2 = Arrays.asList(new Gson().fromJson(jsonBundle, MenuModel[].class));
        }
        context = getActivity();
        initialize();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    void initialize() {
    }

    @Override
    public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        super.onResume();
    }

}
