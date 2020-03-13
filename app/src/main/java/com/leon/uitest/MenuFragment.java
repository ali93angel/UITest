package com.leon.uitest;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     Menu2Fragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class MenuFragment extends BottomSheetDialogFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_ITEM_COUNT = "item_count";

    // TODO: Customize parameters
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
        final RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ItemAdapter(getArguments().getInt(ARG_ITEM_COUNT), getActivity()));
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        final TextView text;
        private View view;
        RelativeLayout relativeLayout;
        MotionLayout motionLayout;
        ImageView imageView;

        ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            // TODO: Customize the item layout
            super(inflater.inflate(R.layout.menu_fragment_list_dialog_item, parent, false));
            text = itemView.findViewById(R.id.text1);
            imageView = itemView.findViewById(R.id.image);
            motionLayout = itemView.findViewById(R.id.motionLayout1);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
            onMotionTransitionListener();
            view = parent;
        }

        public View getView() {
            return view;
        }

        public void onMotionTransitionListener() {
            final Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_in);
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
                    relativeLayout.startAnimation(animation);
                }

                @Override
                public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {

                }
            });
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final int mItemCount;
        private Context mContext;

        ItemAdapter(int itemCount, Context context) {
            mItemCount = itemCount;
            mContext = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.text.setText(String.valueOf(position));
            holder.getView().setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.zoom_in));
        }

        @Override
        public int getItemCount() {
            return mItemCount;
        }


    }

}
