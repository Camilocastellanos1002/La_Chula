package ui.fragments

import adapter.GalleryAdapter
import adapter.GalleryListener
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lachula.R
import kotlinx.android.synthetic.main.fragment_gallery.*
import model.Gallery
import viewmodel.GalleryViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GalleryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GalleryFragment : Fragment(), GalleryListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var galleryAdapter: GalleryAdapter
    private lateinit var viewModel: GalleryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)
        viewModel.refresh()
        galleryAdapter = GalleryAdapter(this)
        rvGallery.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = galleryAdapter
        }
        observerViewModel()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }
    fun observerViewModel(){
        viewModel.listGallery.observe(viewLifecycleOwner, Observer<List<Gallery>>{ gallerys->
            gallerys.let {
                galleryAdapter.updateData(gallerys)
            }
        })
        viewModel.isLoading.observe(viewLifecycleOwner,Observer<Boolean>{
            if (it != null){
                rlBase_gallerys.visibility = View.INVISIBLE
            }
        })
    }
    override fun onGalleryClicked(gallery: Gallery, position: Int) {
        super.onGalleryClicked(gallery, position)
        var bundle = bundleOf("gallery" to gallery)
        findNavController().navigate(R.id.singersDetailFragmentDialog,bundle)
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GalleryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GalleryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}