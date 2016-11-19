package com.example.seele.recyclerview;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.example.seele.recyclerview.helper.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private RecyclerView recyclerView;
    private ItemTouchHelper mItemTouchHelper;
    private recycAdapter adapter;
    private List<String> tabTitle = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        adapter = new recycAdapter(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //固定recyclerview大小
        recyclerView.setHasFixedSize(true);
        //这里用线性显示 类似于listview
        LinearLayoutManager manger = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manger);
        recyclerView.setAdapter(adapter);
        adapter.bindView(tabTitle);

        /**
         * //关联ItemTouchHelper和RecyclerView
         * 拖拽和侧滑删除的功能我们要借助ItemTouchHelper这个类，我们只需要创建出一个ItemTouchHelper对象
         * ，然后调用mItemTouchHelper.attachToRecyclerView(recyclerView);就可以了。
         * ItemTouchHelper的构造是一个callback，callback是ItemTouchHelper的一个内部类，
         * 就需要写一个类继承自callback实现实现内部方法
         */
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter,this);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);

    }
    private void initData() {
        tabTitle.add("最新");
        tabTitle.add("移动开发");
        tabTitle.add("前端");
        tabTitle.add("系统&安全");
        tabTitle.add("数据库");
        tabTitle.add("业界");
        tabTitle.add("语言");
        tabTitle.add("云计算");
        tabTitle.add("游戏&图像");
        tabTitle.add("大数据");
        tabTitle.add("软件工程");
        tabTitle.add("程序人生");

    }
}
