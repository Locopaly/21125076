package com.example.order_coffee_app_kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.navigation.fragment.findNavController
import com.example.order_coffee_app_kotlin.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var cartItem: CartItem

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val bundle = this.arguments
        val coffee = bundle?.getInt("CoffeeClass")
        when (coffee) {
            1 -> {
                binding.coffeeDisplay.setImageResource(R.drawable.americano)
                binding.coffeeType.text = "Americano"
            }

            2 -> {
                binding.coffeeDisplay.setImageResource(R.drawable.cappuccino)
                binding.coffeeType.text = "Cappuccino"
            }

            3 -> {
                binding.coffeeDisplay.setImageResource(R.drawable.mocha)
                binding.coffeeType.text = "Mocha"
            }

            4 -> {
                binding.coffeeDisplay.setImageResource(R.drawable.flat_white)
                binding.coffeeType.text = "Flat White"
            }
        }
        val coffeeItem = CoffeeClass(binding.coffeeType.text.toString(), 1, false, 3f, Shot.Single, Ice.Big, Size.Big)
        cartItem = CartItem(coffeeItem, coffeeItem.price)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backClickListener = View.OnClickListener {v ->
            when (v) {
                binding.backButton -> {
                    findNavController().popBackStack()
                }
            }
        }
        binding.backButton.setOnClickListener(backClickListener)

        val cartClickListener = View.OnClickListener {v ->
            when (v) {
                binding.cartButton -> {
                    findNavController().navigate(R.id.action_detailsFragment_to_myCartFragment)
                }
            }
        }
        binding.cartButton.setOnClickListener(cartClickListener)

        val addToCartClickListener = View.OnClickListener {v ->
            when (v) {
                binding.addToCartButton -> {
                    if (cartItem.coffee.shot == Shot.Double) cartItem.coffee.price += 0.5f
                    if (cartItem.coffee.isTakeAway) cartItem.coffee.price += 1f
                    if (cartItem.coffee.size == Size.Big) cartItem.coffee.price += 0.5f
                    else if (cartItem.coffee.size == Size.Small) cartItem.coffee.price -= 0.5f

                    cartItem.totalPrice = cartItem.coffee.price * cartItem.coffee.unit

                    Globals.cartItemList.add(cartItem)
                    findNavController().navigate(R.id.action_detailsFragment_to_myCartFragment)
                }
            }
        }
        binding.addToCartButton.setOnClickListener(addToCartClickListener)

        setOnClickListenerForAddAndRemoveButtons()
        setOnClickListenerForShotButtons()
        setOnClickListenerForTakeAwayButtons()
        setOnClickListenerForSizeButtons()
        setOnClickListenerForIceButtons()
    }

    private fun setOnClickListenerForAddAndRemoveButtons() {
        val addCoffeeClickListener = View.OnClickListener {v ->
            when (v) {
                binding.plus -> {
                    ++cartItem.coffee.unit
                    val s = (binding.amount.text.toString().toInt() + 1).toString()
                    binding.amount.text = s
                }
            }
        }
        binding.plus.setOnClickListener(addCoffeeClickListener)

        val removeCoffeeClickListener = View.OnClickListener {v ->
            when (v) {
                binding.minus -> {
                    if (binding.amount.text.toString().toInt() > 1) {
                        --cartItem.coffee.unit
                        val s = (binding.amount.text.toString().toInt() - 1).toString()
                        binding.amount.text = s
                    }
                }
            }
        }
        binding.minus.setOnClickListener(removeCoffeeClickListener)
    }

    private fun setOnClickListenerForShotButtons() {
        val singleShotClickListener = View.OnClickListener {v ->
            when (v) {
                binding.singleButton -> {
                    cartItem.coffee.shot = Shot.Single

                    binding.singleButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.background))
                    binding.singleText.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

                    binding.doubleButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
                    binding.doubleText.setTextColor(ContextCompat.getColor(requireContext(), R.color.darkblue))
                }
            }
        }
        binding.singleButton.setOnClickListener(singleShotClickListener)

        val doubleShotClickListener = View.OnClickListener {v ->
            when (v) {
                binding.doubleButton -> {
                    cartItem.coffee.shot = Shot.Double

                    binding.singleButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
                    binding.singleText.setTextColor(ContextCompat.getColor(requireContext(), R.color.darkblue))

                    binding.doubleButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.background))
                    binding.doubleText.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                }
            }
        }
        binding.doubleButton.setOnClickListener(doubleShotClickListener)
    }

    private fun setOnClickListenerForTakeAwayButtons() {
        val takeawayCoffeeClickListener = View.OnClickListener {v ->
            when (v) {
                binding.takeaway -> {
                    cartItem.coffee.isTakeAway = true

                    var drawable = binding.forHere.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.transparent_grey))
                    binding.forHere.background = drawable

                    drawable = binding.takeaway.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.black))
                    binding.takeaway.background = drawable
                }
            }
        }
        binding.takeaway.setOnClickListener(takeawayCoffeeClickListener)

        val forHereCoffeeClickListener = View.OnClickListener {v ->
            when (v) {
                binding.forHere -> {
                    cartItem.coffee.isTakeAway = false

                    var drawable = binding.forHere.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.black))
                    binding.forHere.background = drawable

                    drawable = binding.takeaway.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.transparent_grey))
                    binding.takeaway.background = drawable
                }
            }
        }
        binding.forHere.setOnClickListener(forHereCoffeeClickListener)
    }

    private fun setOnClickListenerForSizeButtons() {
        val bigSizeClickListener = View.OnClickListener {v ->
            when (v) {
                binding.bigSize -> {
                    cartItem.coffee.size = Size.Big

                    var drawable = binding.bigSize.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.black))
                    binding.bigSize.background = drawable

                    drawable = binding.mediumSize.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.transparent_grey))
                    binding.mediumSize.background = drawable

                    drawable = binding.smallSize.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.transparent_grey))
                    binding.smallSize.background = drawable
                }
            }
        }
        binding.bigSize.setOnClickListener(bigSizeClickListener)

        val mediumSizeClickListener = View.OnClickListener {v ->
            when (v) {
                binding.mediumSize -> {
                    cartItem.coffee.size = Size.Medium

                    var drawable = binding.bigSize.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.transparent_grey))
                    binding.bigSize.background = drawable

                    drawable = binding.mediumSize.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.black))
                    binding.mediumSize.background = drawable

                    drawable = binding.smallSize.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.transparent_grey))
                    binding.smallSize.background = drawable
                }
            }
        }
        binding.mediumSize.setOnClickListener(mediumSizeClickListener)

        val smallSizeClickListener = View.OnClickListener {v ->
            when (v) {
                binding.smallSize -> {
                    cartItem.coffee.size = Size.Small

                    var drawable = binding.bigSize.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.transparent_grey))
                    binding.bigSize.background = drawable

                    drawable = binding.mediumSize.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.transparent_grey))
                    binding.mediumSize.background = drawable

                    drawable = binding.smallSize.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.black))
                    binding.smallSize.background = drawable
                }
            }
        }
        binding.smallSize.setOnClickListener(smallSizeClickListener)
    }

    private fun setOnClickListenerForIceButtons() {
        val bigIceClickListener = View.OnClickListener {v ->
            when (v) {
                binding.bigIce -> {
                    cartItem.coffee.ice = Ice.Big

                    var drawable = binding.bigIce1.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.black))
                    binding.bigIce1.background = drawable

                    drawable = binding.bigIce2.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.black))
                    binding.bigIce2.background = drawable

                    drawable = binding.bigIce3.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.black))
                    binding.bigIce3.background = drawable

                    drawable = binding.mediumIce1.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.transparent_grey))
                    binding.mediumIce1.background = drawable

                    drawable = binding.mediumIce2.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.transparent_grey))
                    binding.mediumIce2.background = drawable

                    drawable = binding.smallIce1.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.transparent_grey))
                    binding.smallIce1.background = drawable
                }
            }
        }
        binding.bigIce.setOnClickListener(bigIceClickListener)

        val mediumIceClickListener = View.OnClickListener {v ->
            when (v) {
                binding.mediumIce -> {
                    cartItem.coffee.ice = Ice.Medium

                    var drawable = binding.bigIce1.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.transparent_grey))
                    binding.bigIce1.background = drawable

                    drawable = binding.bigIce2.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.transparent_grey))
                    binding.bigIce2.background = drawable

                    drawable = binding.bigIce3.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.transparent_grey))
                    binding.bigIce3.background = drawable

                    drawable = binding.mediumIce1.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.black))
                    binding.mediumIce1.background = drawable

                    drawable = binding.mediumIce2.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.black))
                    binding.mediumIce2.background = drawable

                    drawable = binding.smallIce1.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.transparent_grey))
                    binding.smallIce1.background = drawable
                }
            }
        }
        binding.mediumIce.setOnClickListener(mediumIceClickListener)

        val smallIceClickListener = View.OnClickListener {v ->
            when (v) {
                binding.smallIce -> {
                    cartItem.coffee.ice = Ice.Small

                    var drawable = binding.bigIce1.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.transparent_grey))
                    binding.bigIce1.background = drawable

                    drawable = binding.bigIce2.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.transparent_grey))
                    binding.bigIce2.background = drawable

                    drawable = binding.bigIce3.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.transparent_grey))
                    binding.bigIce3.background = drawable

                    drawable = binding.mediumIce1.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.transparent_grey))
                    binding.mediumIce1.background = drawable

                    drawable = binding.mediumIce2.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.transparent_grey))
                    binding.mediumIce2.background = drawable

                    drawable = binding.smallIce1.background
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(requireContext(), R.color.black))
                    binding.smallIce1.background = drawable
                }
            }
        }
        binding.smallIce.setOnClickListener(smallIceClickListener)
    }
}