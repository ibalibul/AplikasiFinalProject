package com.iqbal.aplikasifinalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.iqbal.aplikasifinalproject.databinding.FragmentRegisterBinding
import com.iqbal.aplikasifinalproject.viewmodel.ViewModelUser

class RegisterFragment : Fragment() {

    lateinit var binding : FragmentRegisterBinding
    lateinit var viewmodel : ViewModelUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnSignUp.setOnClickListener{

        var fullname =  binding.editFullname.text.toString()
        var password = binding.editPassword.text.toString()

         addFilm(fullname,password)

        }




        binding.tvLogin.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment)
        }



    }

    fun addFilm(name : String, password : String){

        viewmodel = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewmodel.callPostApiDataUser(name,password)
        viewmodel.postDataUser().observe(viewLifecycleOwner, Observer {
            if ( it != null)
                Toast.makeText(context, "Selamat Datang", Toast.LENGTH_SHORT).show()
        })
    }


}