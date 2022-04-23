package com.kks.multiviewholder

interface ItemTouchHelperListener {
    fun onItemMove(formPosition: Int, toPosition: Int): Boolean
    fun onItemSwipe(position: Int)
}