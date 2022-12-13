package com.iqbal.aplikasifinalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.iqbal.aplikasifinalproject.adapter.AirPlaneAdapter
import com.iqbal.aplikasifinalproject.databinding.FragmentHomeBinding
import com.iqbal.aplikasifinalproject.model.ResponseDataFilmItem
import com.iqbal.aplikasifinalproject.network.RetrofitClient
import com.iqbal.aplikasifinalproject.viewmodel.ViewModelPlane
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {


    lateinit var binding : FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        showDataFilm()

    }


    fun showDataFilm(){

        val viewModel = ViewModelProvider(this).get(ViewModelPlane::class.java)
        viewModel.getLiveDataplane().observe(viewLifecycleOwner, Observer {
            if (it != null)
                binding.rvPromo.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                binding.rvPromo.adapter = AirPlaneAdapter(it!!)

        })
        viewModel.getCallApiplane()

    }

}