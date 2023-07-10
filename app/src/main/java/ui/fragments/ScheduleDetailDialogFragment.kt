package ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.lachula.R
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import model.Schedule
import java.text.SimpleDateFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ScheduleDetailDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScheduleDetailDialogFragment : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        //generar mi estilo
        setStyle(STYLE_NORMAL,R.style.pantallaCompletaDialogos)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_detail_dialog, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ScheduleDetailDialogFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ScheduleDetailDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //agregar icono con un contexto (view.context) de la x para salir
        toolbarEvento.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        //color del titulo
        //toolbarEvento.setTitleTextColor(Color.Black)
        //para cerrar el dialogo
        toolbarEvento.setNavigationOnClickListener{
            dismiss()
        }
        //obtener el objetp recibido para tomer los aggumentos de la clase
        //ingresar de forma segunra con ? para obtener los datos del schedule con getSerializable y castearlo como un objeto
        val schedule = arguments?.getSerializable("schedule") as Schedule //ontener los datos
        //el toolbar tendra el nombre del evento
        toolbarEvento.title = schedule.event
        //se agrega el nombre del evento sobre el textview transparente del dialog fragment
        tvNombreEvento.text = schedule.event
        //imagen
        Glide.with(ivEventoFondo.context)
            .load(schedule.image)
            .into(ivEventoFondo)

        //para la descripcion de la hora del evento
        //generar patron del formato de la hora que se desea
        val patron = "dd/MM/yyyy hh:mm a"
        //formato
        val simpleF = SimpleDateFormat(patron)
        //dato del formato
        val date = simpleF.format(schedule.datetime)
        //asignacion del formato como texto
        tvHoraEvento.text = date

        //descripcion del artista
        tvCantanteEvento.text = schedule.singer
        //descripcion del evento
        tvScheduleDescription.text = schedule.description
    }

    //para poner los margenes de la pantalla
    override fun onStart() {
        super.onStart()
        //obtener dialogo, asignar layout, viewgroup padre, parametros del layout, tod o el ancho de la pantalla
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
    }
}