package com.example.zvent.viewmodels
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.zvent.ui.invited.Guest

class AddGuestViewModel : ViewModel() {
    private var addedGuest = ArrayList<Guest>()
    lateinit var guestsList: ArrayList<Guest>
    var guest = Guest("", "", "")

    fun onStart(){
        Log.d("Crear", "se crean")
        //Starting on clear arraylist
        guestsList = ArrayList()
        //Adding new guests
        for (question in addedGuest){
            guestsList.add(question)
        }
        //Default guests
        guestsList.add(
            Guest(
                "Carlos Perez",
                "CarlitosP@gmail.com",
                "33678310",
                "Fotografo"
            )
        )
        guestsList.add(
            Guest(
                "Maria Rosales",
                "RosalesMaria@gmail.com",
                "45781123",
                "Fotografo"
            )
        )
        guestsList.add(
            Guest(
                "Pepita Perez",
                "Pepitap@gmail.com",
                "35672312",
                "Participante"
            )
        )
        guestsList.add(
            Guest(
                "Oscar Maldonado",
                "Maldonado123@gmail.com",
                "36785641",
                "Participante"
            )
        )
        guestsList.add(
            Guest(
                "Laura Rodriguez",
                "LauraEmilia@gmail.com",
                "56478930",
                "Participante"
            )
        )
        guestsList.add(
            Guest(
                "Maria Barrios",
                "Mbarrios@gmail.com",
                "27678910",
                "Organizador"
            )
        )
        guestsList.add(
            Guest(
                "Daniela Collia",
                "ColliaDaniela@gmail.com",
                "56783213",
                "Participante"
            )
        )
        guestsList.add(
            Guest(
                "Daniel Lopez",
                "LopraDaniel@gmail.com",
                "23567832",
                "Participante"
            )
        )
        guestsList.add(
            Guest(
                "Pedro Mejicano",
                "Mex@gmail.com",
                "56431188",
                "Organizador"
            )
        )
        guestsList.add(
            Guest(
                "Martin Zuluaga",
                "ZulMartin@gmail.com",
                "78093715",
                "Participante"
            )
        )

        Log.d("Crear", " " + guestsList.size)

    }

    fun nextGuest() {
        if (guestsList.isNotEmpty()) {
            guest = guestsList.removeAt(0)
        }
    }
    //Se agregan
    fun addGuest(guest: Guest) {
        addedGuest.add(guest)
    }
    //Se obtienen
    fun getNewGuest(): ArrayList<Guest>{
        return guestsList
    }

}