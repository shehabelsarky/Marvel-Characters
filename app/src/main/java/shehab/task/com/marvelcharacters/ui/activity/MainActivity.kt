package shehab.task.com.marvelcharacters.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import dagger.android.support.DaggerAppCompatActivity
import shehab.task.com.marvelcharacters.R
import shehab.task.com.marvelcharacters.data.model.characters.Results
import shehab.task.com.marvelcharacters.ui.fragment.details.DetailsFragment
import shehab.task.com.marvelcharacters.ui.fragment.home.HomeFragment
import shehab.task.com.marvelcharacters.ui.fragment.search.SearchFragment
import shehab.task.com.marvelcharacters.utils.CHARACTER_DATA
import shehab.task.com.marvelcharacters.utils.CHARACTER_LIST_DATA


class MainActivity : DaggerAppCompatActivity()
    , DetailsFragment.OnDetailsFragmentInteractionListener
    , HomeFragment.OnHomeFragmentInteractionListener
    , SearchFragment.OnSearchFragmentInteractionListener {

    /*
    *  Centralize fragments navigation in its parent activity
    */
    override fun onHomeFragmentInteraction(characterData: Results) {
        val bundle = Bundle()
        bundle.putSerializable(CHARACTER_DATA, characterData)
        setFragmentDestination(R.id.detailsFragment, bundle)
    }

    override fun onHomeFragmentInteraction(charactersData: ArrayList<Results>) {
        when {
            charactersData.size > 0 -> {
                val bundle = Bundle()
                bundle.putSerializable(CHARACTER_LIST_DATA, charactersData)
                setFragmentDestination(R.id.searchFragment, bundle)
            }
            else -> //not the optimal way for handling this scenario
                Toast.makeText(this, "Data is not loaded", Toast.LENGTH_SHORT).show()
        }

    }


    override fun onDetailsFragmentBackPressed() {
        popUpFragment()
    }

    override fun onSearchFragmentInteraction(characterData: Results) {
        val bundle = Bundle()
        bundle.putSerializable(CHARACTER_DATA, characterData)
        setFragmentDestination(R.id.detailsFragment, bundle)
    }

    var navController: NavController? = null
    lateinit var navOptions: NavOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavOptions()
        setUpNavComponent()
    }

    private fun setFragmentDestination(@IdRes resId: Int, bundle: Bundle?) {
        runOnUiThread {
            navController!!.navigate(resId, bundle, navOptions)
        }

    }

    private fun setUpNavComponent() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    private fun initNavOptions() {
        navOptions = NavOptions.Builder()
            .setEnterAnim(R.anim.anim_slide_in_from_right)
            .setExitAnim(R.anim.anim_slide_out_from_right)
            .setPopEnterAnim(R.anim.anim_slide_in_from_left)
            .setPopExitAnim(R.anim.anim_slide_out_from_left)
            .build()
    }

    private fun popUpFragment() {
        navController?.popBackStack()
    }

}
