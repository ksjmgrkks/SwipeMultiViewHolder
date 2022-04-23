package com.kks.multiviewholder

import android.graphics.ColorSpace
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TabListAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    ItemTouchHelperListener {

    private var items: ArrayList<TabItem> = ArrayList()

    /** 참고 링크: https://wooooooak.github.io/android/2019/04/13/RecyclerView_mutiType/
        getItemViewType 의 리턴값 Int 가 viewType 으로 넘어온다.
        viewType 으로 넘어오는 값에 따라 viewHolder 를 알맞게 처리해주면 된다. */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View?
        return when (viewType) {
            TabItem.GROUP_TYPE -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.group_item, parent, false)
                GroupViewHolder(view)
            }
            TabItem.CONTENT_TYPE -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.tab_item, parent, false)
                ItemViewHolder(view)
            }
            else -> throw RuntimeException("error")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val obj = items[position]
        when (obj.type) {
            TabItem.GROUP_TYPE -> (holder as GroupViewHolder).onBind(obj, position)
            TabItem.CONTENT_TYPE -> (holder as ItemViewHolder).onBind(obj, position)
        }
    }

    // 여기서 받는 position은 데이터의 index다.
    override fun getItemViewType(position: Int): Int {
        return items[position].type
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(itemList: ArrayList<TabItem>) {
        items = itemList
        notifyDataSetChanged()
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        val item = items[fromPosition]
        items.removeAt(fromPosition)
        items.add(toPosition, item)
        item.number = toPosition
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemSwipe(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tabName: TextView = itemView.findViewById(R.id.name)
        fun onBind(item: TabItem, position: Int) {
            tabName.text = item.name
            item.number = position
        }
    }
    inner class GroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var groupName: TextView = itemView.findViewById(R.id.tv_group_name)
        fun onBind(item: TabItem, position: Int) {
            groupName.text = item.name
            item.number = position
        }
    }
}
