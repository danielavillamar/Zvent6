package com.example.zvent.ui.results
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.zvent.R
import com.example.zvent.databinding.FragmentResultsBinding

import com.example.zvent.viewmodels.ResultsViewModel

/* BASADO EN EL CODELAB DE ANDROID TRIVIA */
class ResultsFragment : Fragment() {
    private lateinit var viewModelResults: ResultsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        /*Especificacion de que fragment inflar*/
        val binding: FragmentResultsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_results, container, false)
        viewModelResults = ViewModelProvider(activity!!).get(ResultsViewModel::class.java)
        /*Referencia de que pasa cuando apachan el boton de restart*/
        binding.restart.setOnClickListener { view: View ->
            view.findNavController().navigate(ResultsFragmentDirections.actionResultsFragmentToNavHome())
        }
        /*Tomando la info utilizando safe args*/
        val args = ResultsFragmentArgs.fromBundle(arguments!!)
        binding.registrados.text="Invitados: "+viewModelResults.total
        binding.confirmados.text="Confirmados: ${args.yes}"
        viewModelResults.invitedInfo = args.contador

        /* Al presionar el boton se despliega el TOAST con los invitados*/
        binding.restart2.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        {
            Toast.makeText(context, viewModelResults.invitedInfo, Toast.LENGTH_SHORT).show()
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    /* Basado en el lab de Android Trivia esto se utiliza para el boton de share*/
    private fun getShareIntent() : Intent {
        val args = ResultsFragmentArgs.fromBundle(arguments!!)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, args.contador)
        return shareIntent
    }
    private fun shareSuccess() {
        startActivity(getShareIntent())
    }
    /*Levanta el menu de share que se encuentra aparte en la carpeta de menu*/
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.results_menu, menu)
    }

    /*Opcion de share*/
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}
