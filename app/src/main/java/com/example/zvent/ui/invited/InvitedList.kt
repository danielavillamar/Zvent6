package com.example.zvent.ui.invited

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.example.zvent.R
import com.example.zvent.databinding.InvitedListFragmentBinding
import com.example.zvent.viewmodels.InvitedListViewModel

class InvitedList : Fragment() {
    private lateinit var binding: InvitedListFragmentBinding
    private lateinit var viewModel: InvitedListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.invited_list_fragment, container, false)
        //ViewModel
        viewModel = ViewModelProvider(activity!!).get(InvitedListViewModel::class.java)

        var invitedText = ""
        val list = viewModel.getInvited()
        for (item in list){

            invitedText+="\n\nInvitado:"+"\n"+item.name + "\n"+item.phone +"\n"+ item.email + "\n"+ item.rol + "\n"
        }

        binding.invitedList = invitedText

        binding.fab.setOnClickListener {
            view!!.findNavController().navigate(R.id.action_nav_invitado_to_addGuest)
        }

        return binding.root
    }
}
