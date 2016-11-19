package com.example.seele.recyclerview;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seele.recyclerview.helper.ItemTouchHelperAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by SEELE on 2016/8/5.
 */
public class recycAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperAdapter {
    public  List<String> tabTitle;
    private Context context;
    public recycAdapter(Context context){
        this.context = context;
    }
    public  void bindView(List tabTitle){
        this.tabTitle = tabTitle;
        notifyDataSetChanged();
    }



    /**在这里反射出我们的item的布局*/
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TabsViewHolder(LayoutInflater.from(context).inflate(R.layout.item,parent,false));
    }
    /**在这里为布局中的控件设置数据*/
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TabsViewHolder tabViewHolder = (TabsViewHolder) holder;
        tabViewHolder.tabs.setText(tabTitle.get(position));
    }

    @Override
    public int getItemCount() {
        return tabTitle.size();
    }



    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //交换mItems数据的位置
        Collections.swap(tabTitle,fromPosition,toPosition);
        //交换RecyclerView列表中item的位置
        notifyItemMoved(fromPosition,toPosition);


        return true;

    }




    @Override
        public void onItemDismiss(final int position) {

            Toast.makeText(context,"----"+position,Toast.LENGTH_LONG).show();



            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("确认删除吗?");
            builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Notify the adapter of the dismissal
                //删除mItems数据
                tabTitle.remove(position);
                //删除RecyclerView列表对应item
                notifyItemRemoved(position);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                tabTitle.add(tabTitle.get(position));
               tabTitle.remove(tabTitle.size()-1);
                notifyDataSetChanged();
//                notifyItemInserted(position);

            }
        });
        builder.create().show();


    }

    public class TabsViewHolder extends RecyclerView.ViewHolder  {

        private TextView tabs;
        private ImageView imgs;
        private RelativeLayout rl;

        public TabsViewHolder(View itemView) {
            super(itemView);
            tabs = (TextView) itemView.findViewById(R.id.recycle_txt);
            imgs = (ImageView) itemView.findViewById(R.id.recycle_img);
            rl = (RelativeLayout) itemView.findViewById(R.id.recycle_rl);
        }

    }
}

