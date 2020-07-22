package com.example.android.demobrowser;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.MessagePattern;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Item> itemList;
    private OnItemClickListener mListener;
    private OnLongClickListener onLongClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

//        void onAddButtonClick(int position);
    }
    public interface OnLongClickListener{
        void onLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;

    }
    public void setOnLongClickListener(OnLongClickListener listener) {
        onLongClickListener = listener;

    }
    @Override
    public int getItemViewType(int position) {
        if (position == itemList.size()-1) {
            return R.layout.add_item_button;
        } else {
            return R.layout.items_recycler;
        }
    }

    //Item constructor will be placed here
    public ItemsAdapter(Context context, ArrayList<Item> itemArrayList) {
        itemList = itemArrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        final RecyclerView.ViewHolder holder;
        switch (viewType) {

            case R.layout.items_recycler:
                view = LayoutInflater.from(context).inflate(R.layout.items_recycler, parent, false);
                holder = new ItemViewHolder(view,mListener,onLongClickListener);
                view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        return false;
                    }
                });
                break;
            case R.layout.add_item_button:
                view = LayoutInflater.from(context).inflate(R.layout.add_item_button, parent, false);
                holder = new AddItemViewHolder(view,mListener);
                break;


            default:
                throw new IllegalStateException("Unexpected value: " + viewType);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ItemViewHolder) {
            final Item item1 = itemList.get(position);
            ((ItemViewHolder) holder).textViewTitle.setText(item1.getmTitle());
            ((ItemViewHolder) holder).mImageView.setImageResource(item1.getmImageResource());

            ((ItemViewHolder) holder).mImageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    ((ItemViewHolder)holder).textViewClose.setVisibility(View.VISIBLE);

                    return true;
                }

            });
            ((ItemViewHolder)holder).textViewClose.setVisibility(View.INVISIBLE);
//            ((ItemViewHolder)holder).textViewClose.getVisibility()==View.VISIBLE);
            ((ItemViewHolder)holder).textViewClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemList.remove(position);
                    notifyDataSetChanged();

                }
            });



        } else if (holder instanceof AddItemViewHolder) {
            ((AddItemViewHolder) holder).buttonAddItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Add Button clicked", Toast.LENGTH_SHORT).show();
                    if(context instanceof MainActivity){
                        ((MainActivity)context).openDialog();
                    }
                }
            });


//            ((AddItemViewHolder) holder).buttonAddItem.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(context, "Add New ShortCut", Toast.LENGTH_SHORT).show();
////                    context.startActivity( new Intent(context, LogInActivity.class));
//                }
//            });
        }

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        TextView textViewTitle,textViewClose;



        public ItemViewHolder(@NonNull View itemView, final OnItemClickListener listener,final OnLongClickListener longClickListener) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.tv_title);
            mImageView = itemView.findViewById(R.id.icon);
            textViewClose = itemView.findViewById(R.id.tv_close);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                if(listener!= null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION ){
                        listener.onItemClick(position);
                    }
                }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (longClickListener!=null){
                        int position = getAdapterPosition();
                        if ((position != RecyclerView.NO_POSITION)){
                            longClickListener.onLongClick(position);
                            textViewClose.setVisibility(View.VISIBLE);
                        }
                    }

                    return true;
                }
            });

        }
    }

    class AddItemViewHolder extends RecyclerView.ViewHolder {

        public Button buttonAddItem;


        public AddItemViewHolder(@NonNull View itemView,final OnItemClickListener listener ) {
            super(itemView);
            buttonAddItem = itemView.findViewById(R.id.button_add_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!= null){
                        int posistion = getAdapterPosition();
                        if(posistion != RecyclerView.NO_POSITION ){
                            listener.onItemClick(posistion);
                        }
                    }
                }
            });


        }
    }


}
