package shehab.task.com.marvelcharacters.ui.fragment.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import shehab.task.com.marvelcharacters.utils.NetworkingUtils
import shehab.task.com.marvelcharacters.utils.SnackbarMessage
import shehab.task.com.marvelcharacters.utils.SnackbarUtils
import shehab.task.com.marvelcharacters.R
import shehab.task.com.marvelcharacters.data.model.characters.Results
import shehab.task.com.marvelcharacters.utils.EndlessRecyclerViewScrollListener
import javax.inject.Inject

const val LOADING = 2
const val SKIP_TEN_ITEMS: Int = 10
const val TYPE_DETAILS: Int = 1
const val TYPE_SEARCH: Int = 2

class HomeFragment : DaggerFragment(),
    CharactersListAdapter.OnItemClickListener, View.OnClickListener {


    private val TAG: String = HomeFragment::class.java.simpleName
    private var offset = 0
    private var listener: OnHomeFragmentInteractionListener? = null

    private val dummyPOJO = Results()
    private var isTypeAdded = false
    private var charsList = ArrayList<Results>()
    private var charactersListAdapter: CharactersListAdapter? = null
    private var scrollListener: EndlessRecyclerViewScrollListener? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var layoutManager: LinearLayoutManager

    private val viewModel: HomeFragmentViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(HomeFragmentViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dummyPOJO.type = LOADING
        setupSnackbar()
        if (isAdded)
            initCharactersList()

        ivSearch.setOnClickListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getAllCharacters(offset)
        observeCharactersLiveData()
    }



    override fun onClick(v: View?) {
        when(v){
            ivSearch ->{
                listener!!.onHomeFragmentInteraction(charsList)
            }
        }
    }

    private fun getAllCharacters(offset: Int) {
        viewModel.getCharacters(offset)
    }

    private fun observeCharactersLiveData() {
        when {
            NetworkingUtils(activity!!).isNetworkConnected -> viewModel.getCharacterOnlineLiveData().observe(viewLifecycleOwner, Observer {
                if (it == null) return@Observer
                Log.d(TAG, "Data loaded remotely")
                setListData(it)
            })
            else -> viewModel.getCharactersLiveData().observe(viewLifecycleOwner, Observer {
                if (it == null) return@Observer
                Log.d(TAG, "Data loaded locally")
                setListData(it)
            })
        }

    }


    private fun setListData(it: List<Results>) {
        if (charsList.size > 0) {
            charsList.removeAt(charsList.size - 1)
            charactersListAdapter!!.notifyItemRemoved(charsList.size)
            isTypeAdded = false
        }
        charsList.addAll(it)
        charactersListAdapter!!.notifyDataSetChanged()
    }

    private fun initCharactersList() {
        layoutManager = LinearLayoutManager(activity)
        rvCharactersList.layoutManager = layoutManager
        rvCharactersList.setHasFixedSize(true)
        charactersListAdapter =
            CharactersListAdapter(activity!!, charsList, this)
        rvCharactersList.adapter = charactersListAdapter

        scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                if (NetworkingUtils(activity!!).isNetworkConnected) {
                    Log.d(TAG, "Load More")
                    loadMoreData()
                }
            }
        }
        rvCharactersList.addOnScrollListener(scrollListener!!)
    }


    private fun setupSnackbar() {
        viewModel.getSnackbarMessage().observe(this, object : SnackbarMessage.SnackbarObserver {
            override fun onNewMessage(@StringRes snackbarMessageResourceId: Int) {
                SnackbarUtils.showSnackbar(view, getString(snackbarMessageResourceId))
            }
        })
    }

    override fun onItemClick(characterData: Results) {
        listener!!.onHomeFragmentInteraction(characterData)
    }

    private fun loadMoreData() {
        if (!isTypeAdded) {
            charsList.add(dummyPOJO)
            charactersListAdapter!!.notifyItemInserted(charsList.size - 1)
        }
        isTypeAdded = true
        offset += SKIP_TEN_ITEMS
        getAllCharacters(offset)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnHomeFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnHomeFragmentInteractionListener {
        fun onHomeFragmentInteraction(characterData: Results)
        fun onHomeFragmentInteraction(charactersData: ArrayList<Results>)
    }
}
