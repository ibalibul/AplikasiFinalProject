package com.iqbal.aplikasifinalproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import binar.academy.flightgoadmin.model.tiket.TiketResponseItem
import com.iqbal.aplikasifinalproject.adapter.AirPlaneAdapter
import com.iqbal.aplikasifinalproject.databinding.FragmentHomeBinding
import com.iqbal.aplikasifinalproject.viewmodel.ViewModelPlane
import com.iqbal.aplikasifinalproject.viewmodel.ViewModelUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), AirPlaneAdapter.onClickInterface  {


    lateinit var binding : FragmentHomeBinding
    private lateinit var modelUser: ViewModelUser
    private lateinit var modelPlane: ViewModelPlane
    private lateinit var adapter : AirPlaneAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(inflater,container,false)
        modelUser = ViewModelProvider(this)[ViewModelUser::class.java]
        modelPlane = ViewModelProvider(this)[ViewModelPlane::class.java]
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Auth
        modelUser.getName().observe(viewLifecycleOwner){
            if (it != null){
                val name = "Selamat Datang, $it"
                binding.nameuser.text = name
            }
        }

        showData()

    }

    private fun setUpRV(tiketResponse: List<TiketResponseItem>) {
        adapter = AirPlaneAdapter(requireContext(), tiketResponse, this)
        binding.rvPromo.adapter = adapter
        binding.rvPromo.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    fun showData(){
        modelPlane.getApiAllTic()
        modelPlane.getLiveAllTic().observe(viewLifecycleOwner) { list ->
            Log.d("Data Tiket: ", list.toString())
            if (list != null) {
                setUpRV(list)
            }
        }

    }

    override fun onResume() {
        super.onResume()
        showData()
    }

    override fun onItemClick(list: TiketResponseItem) {
        Log.d("Item Clicked", "List Tiket : ${list.id}")
        val bundle = Bundle()
        bundle.putSerializable("dataTiket", list)
        //findNavController().navigate(, bundle)
    }
}