package shehab.task.com.marvelcharacters.ui.fragment.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_details.*
import shehab.task.com.marvelcharacters.R
import shehab.task.com.marvelcharacters.data.model.characters.Results
import shehab.task.com.marvelcharacters.ui.fragment.home.IMAGE_EXTENSION
import shehab.task.com.marvelcharacters.ui.fragment.home.IMAGE_VARIANT
import shehab.task.com.marvelcharacters.utils.CHARACTER_DATA
import javax.inject.Inject


class DetailsFragment : DaggerFragment(), View.OnClickListener {


    private var mListener: OnDetailsFragmentInteractionListener? = null

    @Inject
    lateinit var layoutManager: LinearLayoutManager
    private var moviesListAdapter: AllCategoriesAdapter? = null
    private var categoriesList = ArrayList<String>()
    private var characterData: Results? = null
    lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_details, container, false)

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(this, onBackPressed =
            {
                mListener?.onDetailsFragmentBackPressed()
            })

        if (arguments?.getSerializable(CHARACTER_DATA) != null) {
            characterData = arguments?.getSerializable(CHARACTER_DATA) as Results
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCategoriesList()
        setImage(characterData)
        ivBack.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when(v){
            ivBack -> mListener?.onDetailsFragmentBackPressed()
        }
    }

    private fun setImage(characterData: Results?){
        val avatarUrl = characterData?.thumbnail?.path
        if (avatarUrl != null && avatarUrl.isNotEmpty())
            Picasso.with(activity).load(avatarUrl.plus(IMAGE_VARIANT).plus(IMAGE_EXTENSION)).into(ivMovieImage)
    }

    private fun initCategoriesList() {
        categoriesList.add(getString(R.string.comics))
        categoriesList.add(getString(R.string.series))
        categoriesList.add(getString(R.string.stories))
        categoriesList.add(getString(R.string.events))

        rvCategories.layoutManager = layoutManager
        rvCategories.setHasFixedSize(true)
        moviesListAdapter =
            AllCategoriesAdapter(categoriesList, characterData!!, activity!!)
        rvCategories.adapter = moviesListAdapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnDetailsFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    interface OnDetailsFragmentInteractionListener {
        fun onDetailsFragmentBackPressed()
    }

}
