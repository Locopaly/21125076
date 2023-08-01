package com.example.order_coffee_app_kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.order_coffee_app_kotlin.databinding.FragmentRewardsBinding

class RewardsFragment : Fragment() {

    private lateinit var binding: FragmentRewardsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var rewardAdapter: RewardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRewardsBinding.inflate(inflater, container, false)
        recyclerView = binding.rewardsRecyclerView
        rewardAdapter = RewardAdapter(Globals.rewardHistory)
        recyclerView.adapter = rewardAdapter
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager= layoutManager

        Globals.setLoyaltyCardView(binding.root)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.redeemPoints.text = "${Globals.redeemPoints}"
        binding.loyaltyPoints.text = "${Globals.loyaltyPoints} / 8"

        val redeemClickListener = View.OnClickListener {v ->
            when (v) {
                binding.redeem -> {
                    findNavController().navigate(R.id.action_rewardsFragment_to_redeemFragment)
                }
            }
        }
        binding.redeem.setOnClickListener(redeemClickListener)
    }
}