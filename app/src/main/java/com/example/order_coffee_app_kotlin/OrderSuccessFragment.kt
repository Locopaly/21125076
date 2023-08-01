package com.example.order_coffee_app_kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.order_coffee_app_kotlin.databinding.FragmentOrderSuccessBinding

class OrderSuccessFragment : Fragment() {

    private lateinit var binding: FragmentOrderSuccessBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOrderSuccessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val trackOrderClickListener = View.OnClickListener {v ->
            when (v) {
                binding.trackOrder -> {
                    findNavController().navigate(R.id.orderOngoingFragment)
                }
            }
        }
        binding.trackOrder.setOnClickListener(trackOrderClickListener)
    }

}