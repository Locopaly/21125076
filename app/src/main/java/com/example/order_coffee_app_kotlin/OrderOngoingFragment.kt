package com.example.order_coffee_app_kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.order_coffee_app_kotlin.databinding.FragmentOrderOngoingBinding

class OrderOngoingFragment : Fragment() {

    private lateinit var binding: FragmentOrderOngoingBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var onGoingOrderAdapter: OnGoingOrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOrderOngoingBinding.inflate(inflater, container, false)
        recyclerView = binding.orderRecyclerView
        onGoingOrderAdapter = OnGoingOrderAdapter(Globals.onGoingOrder)
        recyclerView.adapter = onGoingOrderAdapter
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager= layoutManager
        val onGoingOrderTouchHelperCallback = OnGoingOrderTouchHelperCallback(onGoingOrderAdapter)
        val itemTouchHelper = ItemTouchHelper(onGoingOrderTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val historyClickListener = View.OnClickListener {v ->
            when (v) {
                binding.history -> {
                    findNavController().navigate(R.id.action_orderOngoingFragment_to_orderHistoryFragment)
                }
            }
        }
        binding.history.setOnClickListener(historyClickListener)
    }
}