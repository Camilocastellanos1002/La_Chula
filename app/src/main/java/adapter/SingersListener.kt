package adapter

import model.Singers

interface SingersListener{
    fun onSingersClicked(singers: Singers,position: Int){
    }
}