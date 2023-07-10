package ui.fragments

import adapter.ScheduleAdapter
import adapter.ScheduleListener
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lachula.R
import kotlinx.android.synthetic.main.fragment_schedule.*
import model.Schedule
import viewmodel.ScheduleViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ScheduleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScheduleFragment : Fragment(), ScheduleListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    //nuevas variables
    private lateinit var scheduleAdapter: ScheduleAdapter
    private lateinit var viewModel: ScheduleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        //estas lineas hacen el llamado de los datos
        viewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)
        viewModel.refresh()
        //crear instancia del adaptador del RecyclerView donde se pondra la informacion
        scheduleAdapter = ScheduleAdapter(this)
        //dar atributos al RecyclerView
        rvSchedule.apply{
            //el layoutManager es como se van a mostrar las vistas o elementos en este recylcerView, en este caso linealmente y sin modo reversa(false)
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)
            //cual sera su adaptador
            adapter = scheduleAdapter
        }
        //observador
        observerViewModel()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }
    //funcion para obervar los datos constantemente los datos del RecyclerView
    fun  observerViewModel(){
        //mantener observacion sobre la lista si existen cambios o evento de config de la pantalla lo tenga presente
        viewModel.listSchedule.observe(viewLifecycleOwner, Observer<List<Schedule>>{ schedule ->  //schdule es un de los cronogramas que se tienen
            //el adaptador debe actualizar los datos
            scheduleAdapter.updateData(schedule)
        })
        //funcion que controla que los datos terminen de cargar
        viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean>{
            if(it != null){
                rlBase_schedule.visibility = View.INVISIBLE
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ScheduleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ScheduleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    //evento clic
    override fun onScheduleClicked(schedule: Schedule, position: Int) {
        super.onScheduleClicked(schedule, position)
        //enviar objeto atravez de un bundle llamado schedule de valor objeto schedule
        val bundle = bundleOf("schedule" to schedule)
        //llamar navigation y enviar el bundle o detalle
        findNavController().navigate(R.id.scheduleDetailFragmentDialog, bundle)
    }
}