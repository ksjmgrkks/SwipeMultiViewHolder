package com.kks.multiviewholder

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: TabListAdapter
    private lateinit var mItemTouchHelper: ItemTouchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRecyclerView = findViewById<View>(R.id.rv_tab_list) as RecyclerView
        val manager = LinearLayoutManager(applicationContext)
        manager.orientation = LinearLayoutManager.VERTICAL
        mRecyclerView.layoutManager = manager
        mAdapter = TabListAdapter()
        mRecyclerView.adapter = mAdapter

        mItemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(mAdapter))
        mItemTouchHelper.attachToRecyclerView(mRecyclerView)

        val items: ArrayList<TabItem> = ArrayList()
        val item1 = TabItem("표시중인 앱", 0, TabItem.GROUP_TYPE)
        val item2 = TabItem("1", 0, TabItem.CONTENT_TYPE)
        val item3 = TabItem("2", 0, TabItem.CONTENT_TYPE)
        val item4 = TabItem("3", 0, TabItem.CONTENT_TYPE)
        val item5 = TabItem("표시하지 않는 앱", 0, TabItem.GROUP_TYPE)
        val item6 = TabItem("4", 0, TabItem.CONTENT_TYPE)
        val item7 = TabItem("5", 0, TabItem.CONTENT_TYPE)
        val item8 = TabItem("6", 0, TabItem.CONTENT_TYPE)
        items.add(item1)
        items.add(item2)
        items.add(item3)
        items.add(item4)
        items.add(item5)
        items.add(item6)
        items.add(item7)
        items.add(item8)

        mAdapter.setItems(items)
    }
}
