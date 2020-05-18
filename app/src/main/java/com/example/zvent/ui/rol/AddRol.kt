package com.example.zvent.ui.rol

import android.os.Bundle
import android.view.*
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.zvent.R
import com.example.zvent.viewmodels.HomeViewModel
import com.example.zvent.viewmodels.RegisterViewModel
import kotlinx.android.synthetic.main.add_role_fragment.*

class AddRol : Fragment() {
    private lateinit var binding: FragmentAddRoleBinding
    private lateinit var viewModel: RegisterViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentAddRoleBinding>(inflater, R.layout.add_role_fragment, container, false)
        viewModel = activity?.run {
            ViewModelProviders.of(this)[HomeViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
        binding.seekBar.max = 10
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.textView4.text = "Orden: "+ progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                binding.textView4.text = "Orden: "+ binding.seekBar.progress
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                binding.textView4.text = "Orden: "+ binding.seekBar.progress
            }
        })
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.save_question, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when (item.itemId) {
            R.id.fab_btn -> {
                viewModel.roleslist.value +=
                        "\nRol " +
                        "\nNombre: " + texto3.text.toString()+
                        "\nDescripción: "+ texto4.text.toString()+
                        "\nOrder: " + binding.seekBar.progress.toString()
                view?.findNavController()?.navigate(R.id.action_addRol_to_rol)
            }
        }
        return super.onOptionsItemSelected(item)
    }



}