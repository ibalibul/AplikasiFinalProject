package com.iqbal.aplikasifinalproject

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.iqbal.aplikasifinalproject.databinding.FragmentFlashScreenBinding


class FlashScreenFragment : Fragment() {

    lateinit var binding : FragmentFlashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFlashScreenBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
          Navigation.findNavController(view).navigate(R.id.action_flashScreenFragment_to_loginFragment)
        }, 3000)
    }

}