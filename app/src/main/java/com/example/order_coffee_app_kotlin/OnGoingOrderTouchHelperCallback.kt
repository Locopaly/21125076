package com.example.order_coffee_app_kotlin

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class OnGoingOrderTouchHelperCallback(private val adapter: OnGoingOrderAdapter) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        return makeMovementFlags(0, ItemTouchHelper.LEFT)
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        Globals.historyOrder.add(Globals.onGoingOrder[position])
        Globals.onGoingOrder.removeAt(position)
        adapter.notifyItemRemoved(position)
    }
}
