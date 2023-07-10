package ui.fragments

import adapter.SingersAdapter
import adapter.SingersListener
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
import kotlinx.android.synthetic.main.fragment_singers.*
import model.Singers
import viewmodel.SingersViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SingersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SingersFragment : Fragment(), SingersListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    //nuevas variables
    private lateinit var singersAdapter: SingersAdapter
    private lateinit var viewModel: SingersViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(SingersViewModel::class.java)
        viewModel.refresh()
        singersAdapter = SingersAdapter(this)
        rvSingers.apply{
            layoutManager = GridLayoutManager(context,2) //muestra los cantantes en forma de grilla con 2 columnas
            adapter = singersAdapter
        }
        observerViewModel()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_singers, container, false)
    }
    fun observerViewModel(){
        viewModel.listSingers.observe(viewLifecycleOwner, Observer<List<Singers>>{ singers->
            singers.let {
                singersAdapter.updateData(singers)
            }
        })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean>{
            if (it != null){
                rlBase_singers.visibility = View.INVISIBLE
            }
        })
    }

    override fun onSingersClicked(singers: Singers, position: Int) {
        super.onSingersClicked(singers, position)
        var bundle = bundleOf("singers" to singers)
        findNavController().navigate(R.id.singersDetailFragmentDialog,bundle)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SingersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SingersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}