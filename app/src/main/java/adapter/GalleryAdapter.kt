package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import model.Gallery
import com.example.lachula.R

class GalleryAdapter(val galleryListener: GalleryListener):RecyclerView.Adapter<GalleryAdapter.ViewHolder>(){
    val listGallery = ArrayList<Gallery>()

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val ivGalleryImage = itemView.findViewById<ImageView>(R.id.fotogaleria)
    }

    fun updateData(data: List<Gallery>){
        listGallery.clear()
        listGallery.addAll(data)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_gallery,parent,false))
    override fun getItemCount() = listGallery.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gallery = listGallery[position]
        Glide.with(holder.itemView.context)
            .load(gallery.pictures)
            .into(holder.ivGalleryImage)

        holder.itemView.setOnClickListener{
            galleryListener.onGalleryClicked(gallery,position)
        }
    }

}