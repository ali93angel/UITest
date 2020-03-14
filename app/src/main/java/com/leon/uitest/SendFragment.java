package com.leon.uitest;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    void initialize() {
        final RecyclerView recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ItemAdapter(menuModels2, getActivity()));

    }

    @Override
    public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        super.onResume();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewItem, textViewPrice, textViewDecrease, textViewIncrease, textViewNumber;
        ImageView imageView;
        RelativeLayout relativeLayout;
        LinearLayoutCompat linearLayoutItems, linearLayoutQuestion;
        private View view;

        ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            // TODO: Customize the item layout
            super(inflater.inflate(R.layout.send_fragment_list_dialog_item, parent, false));
            textViewItem = itemView.findViewById(R.id.textViewItem);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewDecrease = itemView.findViewById(R.id.textViewDecrease);
            textViewIncrease = itemView.findViewById(R.id.textViewIncrease);
            textViewNumber = itemView.findViewById(R.id.textViewNumber);
            imageView = itemView.findViewById(R.id.image);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
            linearLayoutItems = itemView.findViewById(R.id.linearLayoutItems);
            linearLayoutQuestion = itemView.findViewById(R.id.linearLayoutQuestion);
            linearLayoutQuestion.setVisibility(View.GONE);

            view = parent;
        }

        public View getView() {
            return view;
        }

    }

    private class ItemAdapter extends RecyclerView.Adapter<ViewHolder> {

        List<MenuModel> menuModels;
        private Context mContext;

        ItemAdapter(List<MenuModel> menuModels, Context context) {
            this.menuModels = menuModels;
            mContext = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.textViewItem.setText(menuModels.get(position).getItem());
            holder.textViewNumber.setText(String.valueOf(menuModels.get(position).getNumber()));
            holder.textViewPrice.setText(String.valueOf(menuModels.get(position).getPrice()));
            holder.getView().setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.zoom_in));
        }

        @Override
        public int getItemCount() {
            return menuModels.size();
        }


    }
}
