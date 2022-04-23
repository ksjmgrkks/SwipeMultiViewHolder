package com.kks.multiviewholder

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


class ItemTouchHelperCallback(private val listener: ItemTouchHelperListener) :
    ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return if (viewHolder !is TabListAdapter.GroupViewHolder) {
            makeMovementFlags(dragFlags, swipeFlags)
        }else{
            makeMovementFlags(0, 0)
        }
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        targetViewHolder: RecyclerView.ViewHolder
    ): Boolean {
        return if (viewHolder !is TabListAdapter.GroupViewHolder &&
                   targetViewHolder !is TabListAdapter.GroupViewHolder) {
            listener.onItemMove(viewHolder.adapterPosition, targetViewHolder.adapterPosition)
        }else{
            return false
        }

    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        return if (viewHolder !is TabListAdapter.GroupViewHolder) {
            listener.onItemSwipe(viewHolder.adapterPosition)
        }else{
            return
        }

    }

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }
}
