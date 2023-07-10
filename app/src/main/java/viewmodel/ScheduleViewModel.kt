package viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import model.Schedule
import network.Callback
import network.FirestoreService
import java.lang.Exception

class ScheduleViewModel:ViewModel() {
    val firestoreservice= FirestoreService()    //instancia de tipo FirestoreService
    var listSchedule: MutableLiveData<List<Schedule>> = MutableLiveData()   //mutablelivedata de tipo lista schedule
                                                //MutableLiveData es una clase de contenedor de datos dentro de un ciclo de vida determinado
    var isLoading= MutableLiveData<Boolean>()   //permite actualizar la view.ui de carga

    fun refresh(){      //funcion que actualizara
        getScheduleFromFirebase()
    }
    fun getScheduleFromFirebase(){
        firestoreservice.getSchedule(object: Callback<List<Schedule>>{  //llama la funcion getschedule y recibira un callback que contentra una lista de tipo schedule
            //metodos de esta funcion
            //si es exitosa
            override fun onSuccess(result: List<Schedule>?) {
                listSchedule.postValue(result)
                processFinished()
            }
            //si ocurre algun error
            override fun onFailed(exception: Exception) {
                processFinished()

            }
        })
    }
    // funcion que hace que el mutablelivedata de carga tendra el valor de verdadero
        //permite saber cuando termina el proceso ya sea exitoso o fallido
    fun processFinished(){
        isLoading.value = true
    }
}