package network
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import model.Gallery
import model.Schedule
import model.Singers

const val SCHEDULE_COLLECTION_NAME ="Schedule"
const val SINGERS_COLLECTION_NAME ="Singers"
const val GALLERY_COLLECTION_NAME ="Gallery"

class FirestoreService {
    val firebaseFirestore =
        FirebaseFirestore.getInstance() //instancia que realiza una conexion directa con la base de datos
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true)
        .build()  //configuracion para tener conexion de descargar de forma online

    init {  //inicializador por defecto de kotlin, es como un constructor
        firebaseFirestore.firestoreSettings = settings
    }

    fun getSingers(callback: network.Callback<List<Singers>>) {
        firebaseFirestore.collection(SINGERS_COLLECTION_NAME)
            .orderBy("category")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result) {
                    val list = result.toObjects(Singers::class.java)
                    callback.onSuccess(list)          //no se por que me genera error
                    break
                }
            }
    }

    fun getSchedule(callback: network.Callback<List<Schedule>>) {
        firebaseFirestore.collection(SCHEDULE_COLLECTION_NAME)
            .get()
            .addOnSuccessListener { result ->
                for (doc in result) {
                    val list = result.toObjects(Schedule::class.java)
                    callback.onSuccess(list)      //error
                    break
                }
            }
    }

    fun getGallery(callback: network.Callback<List<Gallery>>) {
        firebaseFirestore.collection(GALLERY_COLLECTION_NAME)
            .get()
            .addOnSuccessListener { result ->
                for (doc in result) {
                    val list = result.toObjects(Gallery::class.java)
                    callback.onSuccess(list)          //error
                    break
                }
            }
    }
}