package com.leon.uitest;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class MenuFragment extends BottomSheetDialogFragment {
    private static final String ARG_ITEM_COUNT = "item_count";
    TextView textViewItemNumber, textViewItemPrice, textViewItemToman;
    int totalPrice, total;
    Button buttonSend;
    RelativeLayout relativeLayout;

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
        textViewItemNumber = view.findViewById(R.id.textViewItemNumber);
        textViewItemPrice = view.findViewById(R.id.textViewItemPrice);
        textViewItemToman = view.findViewById(R.id.textViewItemToman);
        buttonSend = view.findViewById(R.id.buttonSend);
        relativeLayout = view.findViewById(R.id.relativeLayoutTotal);
        setOnRelativeLayoutClickListener();
        final RecyclerView recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ItemAdapter(menuModels, getActivity()));
    }

    void setOnRelativeLayoutClickListener() {
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSend.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in));
                buttonSend.setVisibility(View.VISIBLE);
            }
        });
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewItem, textViewPrice, textViewDecrease, textViewIncrease, textViewNumber;
        ImageView imageView;
        RelativeLayout relativeLayout;
        LinearLayout linearLayout;
        private View view;
        int padding, size;
        boolean zoom = false;

        ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            // TODO: Customize the item layout
            super(inflater.inflate(R.layout.menu_fragment_list_dialog_item, parent, false));
            textViewItem = itemView.findViewById(R.id.textViewItem);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewDecrease = itemView.findViewById(R.id.textViewDecrease);
            textViewIncrease = itemView.findViewById(R.id.textViewIncrease);
            textViewNumber = itemView.findViewById(R.id.textViewNumber);
            imageView = itemView.findViewById(R.id.image);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
            linearLayout = itemView.findViewById(R.id.linearLayoutNumber);

            textViewDecrease.setVisibility(View.GONE);
            textViewIncrease.setVisibility(View.GONE);
            textViewNumber.setVisibility(View.GONE);
            size = imageView.getLayoutParams().height;
            padding = relativeLayout.getPaddingEnd();
            onMotionTransitionListener();
            view = parent;
        }

        public View getView() {
            return view;
        }

        void onMotionTransitionListener() {
            final Animation animation1 = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_in);
            final Animation animation2 = AnimationUtils.loadAnimation(getActivity(), R.anim.open_main);
            final Animation animation3 = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_out);
            final Animation animation4 = AnimationUtils.loadAnimation(getActivity(), R.anim.close_main);
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!zoom) {
                        imageView.getLayoutParams().height = 250;
                        imageView.getLayoutParams().width = 250;
                        textViewDecrease.setVisibility(View.VISIBLE);
                        textViewIncrease.setVisibility(View.VISIBLE);
                        textViewNumber.setVisibility(View.VISIBLE);
                        relativeLayout.setPadding(0, 0, 0, 0);
                        relativeLayout.setPadding(0, 0, 0, 0);
                        relativeLayout.startAnimation(animation1);
                        textViewNumber.startAnimation(animation2);
                        textViewDecrease.startAnimation(animation2);
                        textViewIncrease.startAnimation(animation2);
                        zoom = true;
                    } else {
                        imageView.getLayoutParams().height = size;
                        imageView.getLayoutParams().width = size;
                        textViewDecrease.setVisibility(View.GONE);
                        textViewIncrease.setVisibility(View.GONE);
                        textViewNumber.setVisibility(View.GONE);
                        relativeLayout.setPadding(padding, padding, padding, padding);
                        relativeLayout.startAnimation(animation3);
                        textViewNumber.startAnimation(animation4);
                        textViewDecrease.startAnimation(animation4);
                        textViewIncrease.startAnimation(animation4);
                        zoom = false;
                    }
                }
            };
            imageView.setOnClickListener(onClickListener);
            textViewItem.setOnClickListener(onClickListener);
            textViewPrice.setOnClickListener(onClickListener);
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
            holder.textViewPrice.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View v, DragEvent event) {

                    return false;
                }
            });
            holder.textViewNumber.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    totalPrice = 0;
                    total = 0;
                    for (MenuModel menuModel : menuModels) {
                        totalPrice = totalPrice + (menuModel.number * menuModel.price);
                        total = total + menuModel.number;
                    }
                    textViewItemNumber.setText(String.valueOf(total));
                    textViewItemNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down));
                    textViewItemPrice.setText(String.valueOf(totalPrice));
                    textViewItemPrice.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_out));
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
