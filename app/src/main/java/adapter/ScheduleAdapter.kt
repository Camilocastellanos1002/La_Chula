package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lachula.R
import model.Schedule
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

//esta clase tendra como argumento un objeto de tipo ScheduleListener para el boton

class ScheduleAdapter (val scheduleListener: ScheduleListener) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    //se crea un arreglo de lista de tipo schedule
    val listSchedule = ArrayList<Schedule>()     //se guardaran los elementos de forma grafica

    //metodo que permite crear el diseño de la lista a utilizar
    //esta funcion muestra que archivo se va a utilizar atravez de la clase viewholder inflando una lista con LayoutInflater llamando el atributo from y como contexto parent
    //el metodo inflate infla una vista que se referencia, que lo haga atravez de su padre  que lo contiene (parent), y si se desea enlazar con otra vista(false)

    //RESUMEN= referenciar ó enlazar que archivo nesesario para diseñar un item en el recyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_schedule,parent,false))
    // cantidad datos que se desean cargar, de tipo listschedule
    override fun getItemCount() = listSchedule.size

    // cuantos elementos se tienen, que informacion se tendra en los elementos
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val schedule = listSchedule [position] as Schedule  //objeto schedule que muestra  la posicion del elemento

        //mostrar en la interfaz de usuario
        holder.tvScheduleName.text = schedule.event
        holder.tvScheduleDisposicion.text = schedule.provision
        holder.tvScheduleDisposicionBackground.text = schedule.color_provision
        holder.tvScheduleSinger.text = schedule.singer
        holder.tvScheduleDescription.text = schedule.description

        //imagen
        Glide.with(holder.itemView.context)
            .load(schedule.image)
            .into(holder.tvScheduleImage)

        //para la fecha
            //formatos que queremos
        val simpleDateFormat = SimpleDateFormat("yyyy:MM:dd")
        val simpleDateFormatHour = SimpleDateFormat("HH a")
            //objeto calendario para manipular atributos
        val cal = Calendar.getInstance()
        cal.time = schedule.datetime
        val hourformat = simpleDateFormat.format(schedule.datetime)
        holder.tvScheduleDate.text = hourformat
        holder.tvScheduleHour.text = simpleDateFormatHour.format(schedule.datetime).toUpperCase()

        //evento clic
        holder.itemView.setOnClickListener{
            scheduleListener.onScheduleClicked(schedule,position)
        }

    }

    // como enlazar cada uno de los elementos visuales del layout que se realizaran cambios de informacion
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //esta variables ingresan sobre el itemview ó elemento; que lo encuentre por el id, de tipo textview y se pone su referencia id
        val tvScheduleName = itemView.findViewById<TextView>(R.id.tvItemScheduleDetail)
        val tvScheduleDisposicion = itemView.findViewById<TextView>(R.id.tvDisposicion)
        val tvScheduleDisposicionBackground = itemView.findViewById<TextView>(R.id.tvDisposicion)
        val tvScheduleDate = itemView.findViewById<TextView>(R.id.tvDisposicion)
        val tvScheduleHour  = itemView.findViewById<TextView>(R.id.tvDisposicion)
        val tvScheduleSinger = itemView.findViewById<TextView>(R.id.tvCantante)
        val tvScheduleDescription = itemView.findViewById<TextView>(R.id.tvScheduleDescription)
        val tvScheduleImage = itemView.findViewById<ImageView>(R.id.ivEventoFondo)
    }

    //funcion que permita ingresar datos al adaptador
    //recibira datos de tipo lista de conferencias
    fun updateData(data: List<Schedule>){
        //limpiar lista
        listSchedule.clear()
        //que se adicionen todos los datos enviados
        listSchedule.addAll(data)
        //que se notifique que hubo una actualizacion de los datos
        notifyDataSetChanged()

    }
}