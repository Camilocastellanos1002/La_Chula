package ui.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.request.RequestOptions
import com.example.lachula.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.type.LatLng
import model.Ubication

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UbicationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UbicationFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ubication, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UbicationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) = UbicationFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }

    //para cargar el mapa
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //variable para cargar el mapa en la pantalla
        //se llama al hijo que esta acargo del manejo del fragmento y sera un objeto supportMapFragment
        val mapsFragment = childFragmentManager.findFragmentById(R.id.maps) as SupportMapFragment
        //para sincronizar el contenido
        mapsFragment.getMapAsync(this)

    }

    //para manipular el mapa
    //se cambia el nombre de la variable por defecto
    override fun onMapReady(googleMap: GoogleMap?) {
        //variable tipo ubicacion
        val ubication = Ubication()
        // para el zoom
        val zoom = 16f
        //para centrar el mapa
        val centerMap =
            com.google.android.gms.maps.model.LatLng(ubication.latitude, ubication.longitude)
        //asignar las variables previas al mapa con acceso seguro (?), con el atributo camera update factory, luego crear una nueva
        //longitud y latitud
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap, zoom))
        //marcador personalizado
        //datos del lugar
        val centerMark =
            com.google.android.gms.maps.model.LatLng(ubication.latitude, ubication.longitude)
        //crear un marcador de opciones
        val markerOptions = MarkerOptions()
        //centrar marcador
        markerOptions.position(centerMark)
        //titulo del marcador
        markerOptions.title("La Chula, Puritico Corazón")
        //marcador con una imagen
        val bitmapDraw = context?.applicationContext.let {
            view?.context?.let { it1 ->
                ContextCompat.getDrawable(it1, R.drawable.logo_mapa_la_chula)
            }
        } as BitmapDrawable
        //tamaño
        val smallMarker = Bitmap.createScaledBitmap(bitmapDraw.bitmap, 150, 150, false)

        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
        googleMap?.addMarker(markerOptions)

        //para acceder a la funcion onMarkerClick es realizar el evento clic desde esta funcion
        googleMap?.setOnMarkerClickListener(this)

        googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.custom_map))

    }

    //esta funcion realiza la vinculacion de navegacion
    override fun onMarkerClick(p0: Marker): Boolean {
        //encontrar navegador
        findNavController().navigate(R.id.ubicationDetailFragmentDialog)
        return true
    }
}