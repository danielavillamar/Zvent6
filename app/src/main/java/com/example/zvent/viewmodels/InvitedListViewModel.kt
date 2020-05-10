package com.example.zvent.viewmodels
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zvent.ui.invited.Guest

class InvitedListViewModel: ViewModel(){
    var invited = MutableLiveData<ArrayList<Guest>>()
    var invitedList = ArrayList<Guest>()


    //Getters
    fun getInvitedList(): MutableLiveData<ArrayList<Guest>>{
        invited.value = invitedList
        return invited
    }
    fun getInvited(): ArrayList<Guest>{
        return invitedList
    }

}
