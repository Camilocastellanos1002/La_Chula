package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.lachula.R
import model.Singers

class SingersAdapter(val singersListener: SingersListener):RecyclerView.Adapter<SingersAdapter.ViewHolder>() {
    val listSingers = ArrayList<Singers>()
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val ivSingersName = itemView.findViewById<TextView>(R.id.tvnombrecantante)
        val ivSingersImage = itemView.findViewById<ImageView>(R.id.ivcantante)
        val ivSingerstypemusic =itemView.findViewById<TextView>(R.id.tvtipodemusica)
    }

    override fun getItemCount() = listSingers.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_singers,parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val singers = listSingers[position]
        holder.ivSingersName.text = singers.name
        holder.ivSingerstypemusic.text = singers.typemusic

        //para agregar la imagen debemos adicionar la dependencia glide en el archivo gradle
        Glide.with(holder.itemView.context) //imagen de contexto
            .load(singers.image)    //url de la imagen
            .apply(RequestOptions.circleCropTransform())    //comvertir la vista de la imagen de forma circular
            .into(holder.ivSingersImage)    //ubicacion de donde se pondra

        holder.itemView.setOnClickListener{
            singersListener.onSingersClicked(singers,position)
        }
    }

    fun updateData(data: List<Singers>){
        listSingers.clear()
        listSingers.addAll(data)
        notifyDataSetChanged()
    }
}