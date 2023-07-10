package adapter

import model.Gallery

interface GalleryListener {
    fun onGalleryClicked(gallery: Gallery, position: Int){
    }
}