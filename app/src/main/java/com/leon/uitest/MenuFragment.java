package com.leon.uitest;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class MenuFragment extends BottomSheetDialogFragment {
    private static final String ARG_ITEM_COUNT = "item_count";

    public static MenuFragment newInstance(int itemCount) {
        final MenuFragment fragment = new MenuFragment();
        final Bundle args = new Bundle();
        args.putInt(ARG_ITEM_COUNT, itemCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_fragment_list_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ArrayList<MenuModel> menuModels = new ArrayList<>();
        menuModels.add(new MenuModel("قهوه", 12000, R.drawable.coffee));
        menuModels.add(new MenuModel("چیزکیک", 23000, R.drawable.coffee));
        menuModels.add(new MenuModel("لاته", 1600, R.drawable.coffee));
        menuModels.add(new MenuModel("کاپوچینو", 1500, R.drawable.coffee));
        menuModels.add(new MenuModel("کرتادو", 17000, R.drawable.coffee));
        menuModels.add(new MenuModel("کولد برو", 14500, R.drawable.coffee));
        final RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ItemAdapter(menuModels, getActivity()));
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        final TextView textViewItem;
        TextView textViewPrice, textViewDecrease, textViewIncrease, textViewNumber;
        ImageView imageView;
        RelativeLayout relativeLayout;
        LinearLayout linearLayout;
        MotionLayout motionLayout;
        private View view;

        ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            // TODO: Customize the item layout
            super(inflater.inflate(R.layout.menu_fragment_list_dialog_item, parent, false));
            textViewItem = itemView.findViewById(R.id.textViewItem);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewDecrease = itemView.findViewById(R.id.textViewDecrease);
            textViewIncrease = itemView.findViewById(R.id.textViewIncrease);
            textViewNumber = itemView.findViewById(R.id.textViewNumber);
            imageView = itemView.findViewById(R.id.image);
            motionLayout = itemView.findViewById(R.id.motionLayout1);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
            linearLayout = itemView.findViewById(R.id.linearLayoutNumber);

            textViewDecrease.setVisibility(View.GONE);
            textViewIncrease.setVisibility(View.GONE);
            textViewNumber.setVisibility(View.GONE);

            onMotionTransitionListener();
            view = parent;
        }

        public View getView() {
            return view;
        }

        void onMotionTransitionListener() {
            final Animation animation1 = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_in);
            final Animation animation2 = AnimationUtils.loadAnimation(getActivity(), R.anim.fragment_fade_enter);
            motionLayout.setInteractionEnabled(true);
            motionLayout.setTransitionListener(new MotionLayout.TransitionListener() {
                @Override
                public void onTransitionStarted(MotionLayout motionLayout, int i, int i1) {
                }

                @Override
                public void onTransitionChange(MotionLayout motionLayout, int i, int i1, float v) {

                }

                @Override
                public void onTransitionCompleted(MotionLayout motionLayout, int i) {
                    relativeLayout.setPadding(0, 0, 0, 0);
                    imageView.getLayoutParams().height = 250;
                    imageView.getLayoutParams().width = 250;
                    relativeLayout.startAnimation(animation1);
                    textViewDecrease.setVisibility(View.VISIBLE);
                    textViewIncrease.setVisibility(View.VISIBLE);
                    textViewNumber.setVisibility(View.VISIBLE);
                    textViewDecrease.startAnimation(animation2);
                    textViewIncrease.startAnimation(animation2);
                    textViewNumber.startAnimation(animation2);
                }

                @Override
                public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {

                }
            });
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ViewHolder> {

        ArrayList<MenuModel> menuModels;
        private Context mContext;

        ItemAdapter(ArrayList<MenuModel> menuModels, Context context) {
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
            onTextViewClickListener(holder, position);
            holder.getView().setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.zoom_in));
        }

        void onTextViewClickListener(final ViewHolder holder, final int position) {
            holder.textViewIncrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    menuModels.get(position).number = menuModels.get(position).number + 1;
                    holder.textViewNumber.setText(String.valueOf(menuModels.get(position).number));
                }
            });
            holder.textViewDecrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (menuModels.get(position).number > 0) {
                        menuModels.get(position).number = menuModels.get(position).number - 1;
                        holder.textViewNumber.setText(String.valueOf(menuModels.get(position).number));
                    }
                }
            });
            holder.textViewNumber.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }


        @Override
        public int getItemCount() {
            return menuModels.size();
        }


    }

}
