package adapter

import model.Schedule
//esta interface sera el evento para la lista de los eventos
interface ScheduleListener {
    //al dar clic hacer una accion que tiene como argumento un objeto tipo schedule y posicion que se le dio clic
    fun onScheduleClicked(schedule: Schedule,position: Int) {
    }
}

//esta interfaz sera el tipo de objeto que ingresara al adaptador de schedule