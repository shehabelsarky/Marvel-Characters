package shehab.task.com.marvelcharacters.ui.fragment.search

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_search.view.*
import shehab.task.com.marvelcharacters.R
import shehab.task.com.marvelcharacters.data.model.characters.Results
import shehab.task.com.marvelcharacters.ui.fragment.home.IMAGE_EXTENSION
import shehab.task.com.marvelcharacters.ui.fragment.home.IMAGE_VARIANT
import java.util.ArrayList


class SearchListAdapter(
    var charactersList: List<Results>,
    var mListener: SearchItemListener?,
    private val mContext: Context
    ) : RecyclerView.Adapter<SearchListAdapter.ViewHolder>(), Filterable {

    var filterList: List<Results> = ArrayList()
    var filter: SearchCustomFilter? = null

    init {
        filterList = charactersList
    }

    override fun getFilter(): Filter {
        if (filter == null) {
            filter = SearchCustomFilter(filterList as ArrayList<Results>, this)
        }
        return filter!!
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_search, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvSearch.text = charactersList[position].name
        val avatarUrl = charactersList[position].thumbnail.path
        if (avatarUrl != null && avatarUrl.isNotEmpty())
            Picasso.with(mContext).load(avatarUrl.plus(IMAGE_VARIANT).plus(IMAGE_EXTENSION)).into(holder.ivSearch)

        holder.itemView.setOnClickListener {
            mListener?.onSearchItemClick(charactersList[position])
        }
    }

    interface SearchItemListener {
        fun onSearchItemClick(characterData: Results)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSearch: TextView = itemView.tvSearch
        val ivSearch: ImageView = itemView.ivSearch
    }


}
