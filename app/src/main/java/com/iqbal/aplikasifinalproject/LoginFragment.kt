package com.iqbal.aplikasifinalproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.iqbal.aplikasifinalproject.databinding.FragmentLoginBinding
import com.iqbal.aplikasifinalproject.viewmodel.ViewModelUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var modelUser: ViewModelUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(layoutInflater,container,false)
        modelUser = ViewModelProvider(this)[ViewModelUser::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvRegist.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnSignIn.setOnClickListener {
            authRequest()
        }

    }

    private fun authRequest() {
        val email = binding.inputEmail.text.toString()
        val password = binding.inputPass.text.toString()
        if (email == "" && password ==""){
            binding.edEmail.error = "Please fill out this field."
            binding.edPassword.error = "Please fill out this field."
        }else{
            modelUser.apiLogin(email, password)
            modelUser.LoginLive().observe(viewLifecycleOwner){
                if (it != null){
                    //save token to Data Store
                    Log.d("Login", "authRequest: $it")
                    modelUser.saveData(it.data.accessToken, it.data.email)
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    Log.d("ACCESS TOKEN: ", it.data.accessToken)
                    Toast.makeText(context, "Halo, ${it.data.email}", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, "Failed Login", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}