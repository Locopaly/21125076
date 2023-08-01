package com.example.order_coffee_app_kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.order_coffee_app_kotlin.databinding.FragmentOrderHistoryBinding

class OrderHistoryFragment : Fragment() {

    private lateinit var binding: FragmentOrderHistoryBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var historyOrderAdapter: HistoryOrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOrderHistoryBinding.inflate(inflater, container, false)
        recyclerView = binding.orderRecyclerView
        historyOrderAdapter = HistoryOrderAdapter(Globals.historyOrder)
        recyclerView.adapter = historyOrderAdapter
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ongoingClickListener = View.OnClickListener {v ->
            when (v) {
                binding.ongoing -> {
                    findNavController().popBackStack()
                }
            }
        }
        binding.ongoing.setOnClickListener(ongoingClickListener)
    }
}