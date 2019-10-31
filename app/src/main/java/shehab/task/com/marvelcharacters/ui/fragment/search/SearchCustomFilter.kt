package shehab.task.com.marvelcharacters.ui.fragment.search

import android.widget.Filter
import shehab.task.com.marvelcharacters.data.model.characters.Results

import java.util.ArrayList

/*
 * Created by Shehab on 12/03/2018.
 */


//This is the class that will search our data.
class SearchCustomFilter(internal var filterList: ArrayList<Results>, internal var adapter: SearchListAdapter) : Filter() {

    //FILTERING OCURS
    override fun performFiltering(constraint: CharSequence?): FilterResults {
        var constraint = constraint
        val results = FilterResults()

        //CHECK CONSTRAINT VALIDITY
        if (constraint != null && constraint.length > 0) {
            //CHANGE TO UPPER
            constraint = constraint.toString().toUpperCase()
            //STORE OUR FILTERED PLAYERS
            val filteredCountries = ArrayList<Results>()

            for (i in filterList.indices) {
                //CHECK
                if (filterList[i].name.toUpperCase().contains(constraint)) {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredCountries.add(filterList[i])
                }
            }

            results.count = filteredCountries.size
            results.values = filteredCountries
        } else {
            results.count = filterList.size
            results.values = filterList

        }


        return results
    }

    override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {

        adapter.charactersList = filterResults.values as ArrayList<Results>
        //REFRESH
        adapter.notifyDataSetChanged()
    }
}
