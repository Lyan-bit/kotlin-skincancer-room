package com.example.skincancer
	
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.RuntimeException

class ListSkinCancerFragment : Fragment() { 
	private var mColumnCount = 1
	private var mListener: OnListSkinCancerFragmentInteractionListener? = null

	private var root: View? = null
	private lateinit var myContext: Context
	private lateinit var model: ModelFacade
		  		
    companion object {
    	const val ArgColumnCount = "column-count"
        fun newInstance(c: Context): ListSkinCancerFragment {
            val fragment = ListSkinCancerFragment()
            val args = Bundle()
			args.putInt(ArgColumnCount, 1)
			fragment.arguments = args
			fragment.myContext = c
			return fragment
		}
	}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        	mColumnCount = requireArguments().getInt(ArgColumnCount)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		val view = inflater.inflate(R.layout.listskincancer_layout, container, false)
		model = ModelFacade.getInstance(myContext)

		if (view is RecyclerView) {
			val context = view.getContext()
			if (mColumnCount <= 1) {
				view.layoutManager = LinearLayoutManager(context)
			} else {
				view.layoutManager = GridLayoutManager(context, mColumnCount)
			}
		}
		root = view

		return root as View
	}

	override fun onAttach(context: Context) {
		super.onAttach(context)
		if (context is OnListSkinCancerFragmentInteractionListener) {
			mListener = context
		} else {
			throw RuntimeException("$context must implement OnListSkinCancerFragmentInteractionListener")
		}
	}

	override fun onResume() {
		super.onResume()
		model.allSkinCancers.observe( this) { skinCancer ->
		            skinCancer.let {
						(root as RecyclerView).adapter = SkinCancerRecyclerViewAdapter(skinCancer, mListener!!)
				 }
	        }
	}

	override fun onDetach() {
		super.onDetach()
		mListener = null
	}

	interface OnListSkinCancerFragmentInteractionListener {
		fun onListSkinCancerFragmentInteraction(item: SkinCancerEntity)
	}
}
