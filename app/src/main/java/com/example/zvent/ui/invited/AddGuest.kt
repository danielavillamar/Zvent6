package com.example.zvent.ui.invited

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

import com.example.zvent.R
import com.example.zvent.databinding.AddGuestFragmentBinding
import com.example.zvent.viewmodels.AddGuestViewModel
import com.example.zvent.viewmodels.InvitedListViewModel
import kotlinx.android.synthetic.main.add_guest_fragment.*

class AddGuest : Fragment() {
    private lateinit var binding: AddGuestFragmentBinding
    private lateinit var viewModel: AddGuestViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.add_guest_fragment, container, false)
        //Se guarda el teclado
        getActivity()?.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        setHasOptionsMenu(true)

        return binding.root
    }
    //Menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.save_question, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        // Obteniendo las vistas
        viewModel = ViewModelProvider(activity!!).get(AddGuestViewModel::class.java)

        //Get guest
        val name = EditTextName.getText().toString()
        val phone = EditTextPhone.getText().toString()
        val email = EditTextEmail.getText().toString()

        //Save guest
        if (item.itemId == R.id.saveQuestion){
            viewModel.addGuest(Guest(name, phone, email))
            Toast.makeText(activity, "Guest saved", Toast.LENGTH_SHORT).show()
            //Clearing for next guest info
            EditTextName.getText().clear()
            EditTextPhone.getText().clear()
            EditTextEmail.getText().clear()
        }

        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController())
    }
}

