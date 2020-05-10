package com.example.zvent.viewmodels

import androidx.lifecycle.ViewModel
import com.example.zvent.ui.invited.Guest

class RegisterViewModel:ViewModel(){
    lateinit var currentGuest: Guest
    var actualGuest: Int=0
    var yes: Int=0

    fun actualGuest():Int{
        return actualGuest++
    }
    fun restartGuest(){
        actualGuest = 0
    }

}