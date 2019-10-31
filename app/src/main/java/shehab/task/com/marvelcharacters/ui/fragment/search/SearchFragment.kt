package shehab.task.com.marvelcharacters.ui.fragment.search

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding.widget.RxTextView
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_search.*
import rx.android.schedulers.AndroidSchedulers
import shehab.task.com.marvelcharacters.R
import shehab.task.com.marvelcharacters.data.model.characters.Results
import shehab.task.com.marvelcharacters.utils.CHARACTER_LIST_DATA
import shehab.task.com.marvelcharacters.utils.NetworkingUtils
import java.util.concurrent.TimeUnit


class SearchFragment : DaggerFragment(), SearchListAdapter.SearchItemListener, View.OnClickListener {

    private var mListener: OnSearchFragmentInteractionListener? = null
    lateinit var rootView: View
    private var characterListData: ArrayList<Results>? = null
    private var characterListDataClone = ArrayList<Results>()
    lateinit var layoutManager: LinearLayoutManager
    private var charactersListAdapter: SearchListAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_search, container, false)
        if (arguments?.getSerializable(CHARACTER_LIST_DATA) != null) {
            characterListData = arguments?.getSerializable(CHARACTER_LIST_DATA) as ArrayList<Results>
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterListDataClone.addAll(characterListData!!)
        initCharactersList()
        observerTextChanges()

        tvCancel.setOnClickListener(this)
    }

    private fun initCharactersList() {
        layoutManager = LinearLayoutManager(activity)
        rvSearch.layoutManager = layoutManager
        rvSearch.setHasFixedSize(true)
        val dividerItemDecoration = DividerItemDecoration(
            rvSearch.context,
            layoutManager.orientation
        )
        rvSearch.addItemDecoration(dividerItemDecoration)
        charactersListAdapter = SearchListAdapter(characterListData!!, this,activity!!)
        rvSearch.adapter = charactersListAdapter
    }


    private fun observerTextChanges() {
        RxTextView.textChanges(etSearch!!).debounce(0, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when {
                    etSearch!!.text.toString().isNotEmpty() -> {
                        if (rvSearch.visibility == View.GONE)
                            rvSearch.visibility = View.VISIBLE
                        charactersListAdapter?.getFilter()?.filter(it)
                    }
                    else -> {
                        characterListData?.clear()
                        characterListData?.addAll(characterListDataClone)
                        charactersListAdapter?.notifyDataSetChanged()
                    }
                }
            }
    }



    override fun onClick(v: View?) {
        when(v){
            tvCancel ->{
                etSearch.setText("")
            }
        }
    }

    override fun onSearchItemClick(characterData: Results) {
        mListener!!.onSearchFragmentInteraction(characterData)
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSearchFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    interface OnSearchFragmentInteractionListener {
        fun onSearchFragmentInteraction(characterData: Results)
    }

}
