package com.example.zvent.ui.rol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.zvent.R
import com.example.zvent.viewmodels.HomeViewModel


class Role : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentRoleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        (activity as AppCompatActivity).supportActionBar?.title = "Rol"


        binding = DataBindingUtil.inflate<FragmentRoleBinding>(inflater,
            R.layout.role_fragment, container, false)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[HomeViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        viewModel.roleslist.observe(viewLifecycleOwner, Observer { newList ->
            binding.texto6.text = newList
        })

        binding.fab_btn.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_roleFragment_to_addroleFragment)
        }

        return binding.root
    }

}