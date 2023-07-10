package viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import model.Gallery
import network.Callback
import network.FirestoreService
import java.lang.Exception

class GalleryViewModel : ViewModel() {
    val firestoreservice = FirestoreService()    //instancia de tipo FirestoreService
    var listGallery: MutableLiveData<List<Gallery>> =
        MutableLiveData()   //mutablelivedata de tipo lista gallery

    //MutableLiveData es una clase de contenedor de datos dentro de un ciclo de vida determinado
    var isLoading = MutableLiveData<Boolean>()   //permite actualizar la view.ui de carga

    fun refresh() {      //funcion que actualizara
        getGalleryFromFirebase()
    }

    fun getGalleryFromFirebase() {
        firestoreservice.getGallery(object :
            Callback<List<Gallery>> {  //llama la funcion getgallery y recibira un callback que contentra una lista de tipo schedule
            //metodos de esta funcion
            //si es exitosa
            override fun onSuccess(result: List<Gallery>?) {
                listGallery.postValue(result)
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
    fun processFinished() {
        isLoading.value = true
    }
}
