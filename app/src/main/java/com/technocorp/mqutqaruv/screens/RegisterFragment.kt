package com.technocorp.mqutqaruv.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.technocorp.mqutqaruv.R
import com.technocorp.mqutqaruv.databinding.FragmentLoginBinding
import com.technocorp.mqutqaruv.util.BindingFragment

class RegisterFragment : BindingFragment<FragmentLoginBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentLoginBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            navigate(R.id.action_registerFragment_to_mapFragment)
        }
    }
}