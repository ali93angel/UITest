package com.leon.uitest;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuFragment extends BottomSheetDialogFragment {
    private static final String ARG_ITEM_COUNT = "item_count";
    @BindView(R.id.textViewItemNumber)
    TextView textViewItemNumber;
    @BindView(R.id.textViewItemPrice)
    TextView textViewItemPrice;
    @BindView(R.id.textViewItemToman)
    TextView textViewItemToman;
    @BindView(R.id.buttonSend)
    Button buttonSend;
    @BindView(R.id.buttonSent)
    Button buttonSent;
    @BindView(R.id.imageViewClose)
    ImageView imageViewClose;
    @BindView(R.id.bottom_sheet)
    LinearLayout bottom_sheet;
    @BindView(R.id.relativeLayoutTotal)
    RelativeLayout relativeLayout;
    @BindView(R.id.list1)
    RecyclerView recyclerView1;
    @BindView(R.id.list2)
    RecyclerView recyclerView2;
    private int totalPrice, total;
    private View findViewById;
    private ArrayList<MenuModel> menuModels1 = new ArrayList<>();
    private ArrayList<MenuModel> menuModels2 = new ArrayList<>();
    private BottomSheetBehavior sheetBehavior;

//    private Animation closeMain, zoomIn, openMain, zoomOut,
//            blink, hideToBottom, animation8, animation7,
//            animation9, animation10, animation12, animation11,
//            animation13, animation14, animation16, animation15,
//            animation17, animation18, animation20, animation19, animation21;

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
        findViewById = inflater.inflate(R.layout.menu_fragment, container, false);
        ButterKnife.bind(this, findViewById);
        return findViewById;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        fillRecyclerView1();
        sheetBehavior = BottomSheetBehavior.from(bottom_sheet);
//        initializeAnimation();
        setOnBottomSheetListener();
        setOnButtonSendClickListener();
    }
//
//    private void initializeAnimation() {
//        zoomIn = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_in);
//        openMain = AnimationUtils.loadAnimation(getActivity(), R.anim.open_main);
//        zoomOut = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_out);
//        closeMain = AnimationUtils.loadAnimation(getActivity(), R.anim.close_main);
//        blink = AnimationUtils.loadAnimation(getActivity(), R.anim.blink);
//        hideToBottom = AnimationUtils.loadAnimation(getActivity(), R.anim.hide_to_bottom);
//        animation7 = AnimationUtils.loadAnimation(getActivity(), R.anim.jump_from_down);
//        animation8 = AnimationUtils.loadAnimation(getActivity(), R.anim.jump_to_down);
//        animation9 = AnimationUtils.loadAnimation(getActivity(), R.anim.no_change);
//        animation10 = AnimationUtils.loadAnimation(getActivity(), R.anim.open_next);
//        animation11 = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
//        animation12 = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_down);
//        animation13 = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_up);
//        animation14 = AnimationUtils.loadAnimation(getActivity(), R.anim.show_from_bottom);
//        animation15 = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down);
//        animation16 = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_up);
//        animation17 = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_up_info);
//        animation18 = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down_info);
//        animation19 = AnimationUtils.loadAnimation(getActivity(), R.anim.close_next);
//        animation20 = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
//        animation20 = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
//    }

    private void setOnButtonSendClickListener() {
        buttonSend.setOnClickListener(v -> {
            buttonSent.setVisibility(View.VISIBLE);
            bottom_sheet.setBackground(getResources().getDrawable(R.drawable.item_background_border1));
            imageViewClose.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.GONE);
            recyclerView2.setVisibility(View.GONE);
        });
    }

    private void fillRecyclerView1() {
        menuModels1.add(new MenuModel("قهوه", 12000, R.drawable.coffee));
        menuModels1.add(new MenuModel("چیزکیک", 23000, R.drawable.coffee));
        menuModels1.add(new MenuModel("لاته", 1600, R.drawable.coffee));
        menuModels1.add(new MenuModel("کاپوچینو", 1500, R.drawable.coffee));
        menuModels1.add(new MenuModel("کرتادو", 17000, R.drawable.coffee));
        menuModels1.add(new MenuModel("کولد برو", 14500, R.drawable.coffee));
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView1.setAdapter(new ItemAdapter1(menuModels1, getActivity()));
    }

    private void setOnBottomSheetListener() {
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int newState) {
                Log.e("status", String.valueOf(newState));
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        imageViewClose.setVisibility(View.VISIBLE);
                        recyclerView2.setVisibility(View.VISIBLE);
                        buttonSend.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in));
                        buttonSend.setVisibility(View.VISIBLE);
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        recyclerView2.setVisibility(View.GONE);
                        buttonSend.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out));
                        buttonSend.setVisibility(View.GONE);
                        imageViewClose.setVisibility(View.GONE);
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                    case BottomSheetBehavior.STATE_HALF_EXPANDED:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
                fillRecyclerView2();
            }

        });
        imageViewClose.setOnClickListener(view -> {
            if (sheetBehavior.getState() != BottomSheetBehavior.STATE_COLLAPSED) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
    }

    private void fillRecyclerView2() {
        menuModels2 = new ArrayList<>();
        for (MenuModel menuModel : menuModels1)
            if (menuModel.number > 0)
                menuModels2.add(menuModel);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView2.setAdapter(new ItemAdapter2(menuModels2, getActivity()));
    }

    private class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView textViewItem, textViewPrice, textViewDecrease, textViewIncrease, textViewNumber;
        ImageView imageView, imageViewNo, imageViewYes;
        RelativeLayout relativeLayout;
        LinearLayoutCompat linearLayoutItems, linearLayoutQuestion;
        private View view;

        ViewHolder2(LayoutInflater inflater, ViewGroup parent) {
            // TODO: Customize the item layout
            super(inflater.inflate(R.layout.send_fragment_list_dialog_item, parent, false));
            textViewItem = itemView.findViewById(R.id.textViewItem);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewDecrease = itemView.findViewById(R.id.textViewDecrease);
            textViewIncrease = itemView.findViewById(R.id.textViewIncrease);
            textViewNumber = itemView.findViewById(R.id.textViewNumber);
            imageView = itemView.findViewById(R.id.image);
            imageViewNo = itemView.findViewById(R.id.imageNo);
            imageViewYes = itemView.findViewById(R.id.imageYes);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
            linearLayoutItems = itemView.findViewById(R.id.linearLayoutItems);
            linearLayoutQuestion = itemView.findViewById(R.id.linearLayoutQuestion);
            linearLayoutQuestion.setVisibility(View.GONE);

            view = parent;
        }

        View getView() {
            return view;
        }

    }

    private class ItemAdapter2 extends RecyclerView.Adapter<ViewHolder2> {

        ArrayList<MenuModel> menuModels;
        private Context mContext;

        ItemAdapter2(ArrayList<MenuModel> menuModels, Context context) {
            this.menuModels = menuModels;
            mContext = context;
        }

        @NonNull
        @Override
        public ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder2(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder2 holder, int position) {
            holder.textViewItem.setText(menuModels.get(position).getItem());
            holder.textViewNumber.setText(String.valueOf(menuModels.get(position).getNumber()));
            holder.textViewPrice.setText(String.valueOf(menuModels.get(position).getPrice()));
            onClickListener(holder, position);
            holder.getView().setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.zoom_in));
        }

        void onClickListener(final ViewHolder2 holder, final int position) {
            counting();
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
                    counting();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    holder.linearLayoutItems.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.zoom_out));
                    holder.linearLayoutItems.setVisibility(View.GONE);
//                    holder.linearLayoutQuestion.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.zoom_in));
                    holder.linearLayoutQuestion.setVisibility(View.VISIBLE);
                }
            });
            holder.imageViewNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    holder.linearLayoutQuestion.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.zoom_out));
                    holder.linearLayoutQuestion.setVisibility(View.GONE);
//                    holder.linearLayoutItems.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.zoom_in));
                    holder.linearLayoutItems.setVisibility(View.VISIBLE);
                }
            });
            holder.imageViewYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    menuModels.remove(position);
//                    holder.linearLayoutQuestion.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.zoom_out));
                    holder.linearLayoutQuestion.setVisibility(View.GONE);
//                    holder.linearLayoutItems.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.zoom_in));
                    holder.linearLayoutItems.setVisibility(View.VISIBLE);
                    counting();
                }
            });
        }

        void counting() {
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
        public int getItemCount() {
            return menuModels.size();
        }


    }

    private class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView textViewItem, textViewPrice, textViewDecrease, textViewIncrease, textViewNumber;
        ImageView imageView;
        RelativeLayout relativeLayout;
        LinearLayout linearLayout;
        View view;
        int padding, size;
        boolean zoom = false;

        ViewHolder1(LayoutInflater inflater, ViewGroup parent) {
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

        View getView() {
            return view;
        }

        void onMotionTransitionListener() {
            View.OnClickListener onClickListener = v -> {
                if (!zoom) {
                    imageView.getLayoutParams().height = 180;
                    imageView.getLayoutParams().width = 180;
                    textViewDecrease.setVisibility(View.VISIBLE);
                    textViewIncrease.setVisibility(View.VISIBLE);
                    textViewNumber.setVisibility(View.VISIBLE);
                    relativeLayout.setPadding(0, 0, 0, 0);
                    relativeLayout.setPadding(0, 0, 0, 0);
                    relativeLayout.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_in));
                    textViewNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in));
                    textViewDecrease.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in));
                    textViewIncrease.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in));
                    zoom = true;
                } else {
                    imageView.getLayoutParams().height = size;
                    imageView.getLayoutParams().width = size;
                    textViewDecrease.setVisibility(View.GONE);
                    textViewIncrease.setVisibility(View.GONE);
                    textViewNumber.setVisibility(View.GONE);
                    relativeLayout.setPadding(padding, padding, padding, padding);
                    relativeLayout.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_out));
                    textViewNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.close_main));
                    textViewDecrease.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.close_main));
                    textViewIncrease.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.close_main));
                    zoom = false;
                }
            };
            imageView.setOnClickListener(onClickListener);
            textViewItem.setOnClickListener(onClickListener);
            textViewPrice.setOnClickListener(onClickListener);
        }
    }

    private class ItemAdapter1 extends RecyclerView.Adapter<ViewHolder1> {

        ArrayList<MenuModel> menuModels;
        private Context mContext;

        ItemAdapter1(ArrayList<MenuModel> menuModels, Context context) {
            this.menuModels = menuModels;
            mContext = context;
        }

        @NonNull
        @Override
        public ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder1(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder1 holder, int position) {
            holder.textViewItem.setText(menuModels.get(position).getItem());
            holder.textViewNumber.setText(String.valueOf(menuModels.get(position).getNumber()));
            holder.textViewPrice.setText(String.valueOf(menuModels.get(position).getPrice()));
            onTextViewClickListener(holder, position);
            holder.getView().setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.zoom_in));
        }

        void onTextViewClickListener(final ViewHolder1 holder, final int position) {
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
