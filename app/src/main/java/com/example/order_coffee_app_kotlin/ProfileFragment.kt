package com.example.order_coffee_app_kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.order_coffee_app_kotlin.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.name.isEnabled = false
        binding.phoneNumber.isEnabled = false
        binding.emailAddress.isEnabled = false
        binding.addressDetail.isEnabled = false

        binding.name.setText(Globals.user.name)
        binding.phoneNumber.setText(Globals.user.phoneNumber)
        binding.emailAddress.setText(Globals.user.email)
        binding.addressDetail.setText(Globals.user.address)

        val backClickListener = View.OnClickListener {v ->
            when (v) {
                binding.backButton -> {
                    Globals.user.name = binding.name.text.toString()
                    Globals.user.phoneNumber = binding.phoneNumber.text.toString()
                    Globals.user.email = binding.emailAddress.text.toString()
                    Globals.user.address = binding.addressDetail.text.toString()
                    findNavController().popBackStack()
                }
            }
        }
        binding.backButton.setOnClickListener(backClickListener)

        setOnClickListenerForEditButtons()
    }

    private fun setOnClickListenerForEditButtons() {
        val editNameClickListener = View.OnClickListener {v ->
            when (v) {
                binding.editName -> {
                    binding.name.isEnabled = true
                }
            }
        }
        binding.editName.setOnClickListener(editNameClickListener)

        val editPhoneClickListener = View.OnClickListener {v ->
            when (v) {
                binding.editPhone -> {
                    binding.phoneNumber.isEnabled = true
                }
            }
        }
        binding.editPhone.setOnClickListener(editPhoneClickListener)

        val editEmailClickListener = View.OnClickListener {v ->
            when (v) {
                binding.editEmail -> {
                    binding.emailAddress.isEnabled = true
                }
            }
        }
        binding.editEmail.setOnClickListener(editEmailClickListener)

        val editAddressClickListener = View.OnClickListener {v ->
            when (v) {
                binding.editAddress -> {
                    binding.addressDetail.isEnabled = true
                }
            }
        }
        binding.emailAddress.setOnClickListener(editAddressClickListener)
    }
}