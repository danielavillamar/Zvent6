package com.example.zvent.ui.results


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.zvent.R
import com.example.zvent.databinding.FragmentRegisterBinding
import com.example.zvent.viewmodels.ResultsViewModel
import com.example.zvent.viewmodels.AddGuestViewModel
import com.example.zvent.ui.invited.Guest
import com.example.zvent.viewmodels.RegisterViewModel
import kotlinx.android.synthetic.main.fragment_register.*

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment() {
    private lateinit var viewModelRegister: RegisterViewModel
    private lateinit var viewModelAddGuest: AddGuestViewModel
    private lateinit var viewModelResults: ResultsViewModel

    /*private val numQuestions = Math.min((invitados.size + 1) / 2,3)*/
    private var invitado = Guest()
    private var contador: String=""

    var yes: Int=0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        /*Implementacion binding*/
        val binding = DataBindingUtil.inflate<FragmentRegisterBinding>(
            inflater,
            R.layout.fragment_register, container, false)

        viewModelAddGuest = ViewModelProvider(activity!!).get(AddGuestViewModel::class.java)
        viewModelRegister = ViewModelProvider(activity!!).get(RegisterViewModel::class.java)
        viewModelResults = ViewModelProvider(activity!!).get(ResultsViewModel::class.java)
        viewModelAddGuest.onStart()
        viewModelResults.total = viewModelAddGuest.getNewGuest().size

        binding.guest = invitado
        val invitados = viewModelAddGuest.getNewGuest()

        binding.apply {
            binding.invalidateAll()
            try{
                viewModelRegister.currentGuest = invitados[viewModelRegister.actualGuest]
                questionText1.text = viewModelRegister.currentGuest.name
                questionText2.text = "Phone: " + viewModelRegister.currentGuest.phone
                questionText3.text = "Email: " + viewModelRegister.currentGuest.email
            } catch (e:IndexOutOfBoundsException){
                questionText1.text = ""
                questionText2.text = ""
                questionText3.text = ""
            }


        }

        setHasOptionsMenu(true)
        return binding.root
    }

    /* Especificación de la barra de cheque o cruz*/
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.register_menu,menu)
    }
    /* Comienza la logica en ver que pasa cuando se apacha que boton */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val invitados = viewModelAddGuest.getNewGuest()


        /*questionIndex += 1*/
        if (viewModelRegister.actualGuest == (viewModelAddGuest.guestsList.size)-1) { /* Al llegar a los invitados -1 automaticamente tira a la siguiente actividad*/
            viewModelRegister.yes = yes
            viewModelResults.totalYes = yes

            viewModelRegister.restartGuest()
            view!!.findNavController().navigate(
                RegisterFragmentDirections.actionRegisterFragmentToResultsFragment(
                    contador,
                    yes
                )
            )
            /*questionText1.text = invitados[questionIndex].name
            questionText2.text = "Phone: " + invitados[questionIndex].number
            questionText3.text = "Email: " + invitados[questionIndex].email*/
        } else {
            viewModelRegister.currentGuest = invitados[viewModelRegister.actualGuest]
            (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_android_trivia_question, viewModelRegister.actualGuest + 1, viewModelAddGuest.guestsList.size)

            questionText1.text = viewModelRegister.currentGuest.name
            questionText2.text = "Phone: " + viewModelRegister.currentGuest.phone
            questionText3.text = "Email: " + viewModelRegister.currentGuest.email
        }
        when(item.itemId) {
            R.id.b_si -> {
                yes++
                contador += (invitados[viewModelRegister.actualGuest].name + ": Confirmed") /*Llena el conteo de quienes si y quienes no*/
                viewModelRegister.actualGuest()

            }
            R.id.b_no -> {

                contador += (invitados[viewModelRegister.actualGuest].name + ": Missing") /*Llena el conteo de quienes si y quienes no*/
                viewModelRegister.actualGuest()

                    /*questionText1.text = invitados[questionIndex].name
                    questionText2.text = "Phone: " + invitados[questionIndex].number
                    questionText3.text = "Email: " + invitados[questionIndex].email*/
                }

        }





        return super.onOptionsItemSelected(item)
    }
}







