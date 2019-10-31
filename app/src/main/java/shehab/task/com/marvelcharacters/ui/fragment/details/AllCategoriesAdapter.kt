package shehab.task.com.marvelcharacters.ui.fragment.details

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import shehab.task.com.marvelcharacters.R
import shehab.task.com.marvelcharacters.data.model.characters.Results

const val FIRST_POSITION = 0
const val SECOND_POSITION = 1
const val THIRD_POSITION = 2
const val FOURTH_POSITION = 3

class AllCategoriesAdapter(
    private val categoriesList: ArrayList<String>,
    private val data: Results,
    private val context: Context
) :
    RecyclerView.Adapter<AllCategoriesAdapter.AllCategoriesViewHolder>() {

    private var horizontalAdapter: MovieAdapter? = null
    private val recycledViewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()


    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): AllCategoriesViewHolder {
        return AllCategoriesViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_layout_all_categories,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(@NonNull holder: AllCategoriesViewHolder, position: Int) {
        when (position) {
            FIRST_POSITION -> {
                holder.textViewCategory.text = context.getString(R.string.comics)
                horizontalAdapter = MovieAdapter(data.getComics().getItems(), context)
            }
            SECOND_POSITION -> {
                holder.textViewCategory.text = context.getString(R.string.series)
                horizontalAdapter = MovieAdapter(data.getSeries().getItems(), context)
            }
            THIRD_POSITION -> {
                holder.textViewCategory.text = context.getString(R.string.stories)
                horizontalAdapter = MovieAdapter(data.getStories().getItems(), context)
            }
            FOURTH_POSITION -> {
                holder.textViewCategory.text = context.getString(R.string.events)
                horizontalAdapter = MovieAdapter(data.getEvents().getItems(), context)
            }
        }

        holder.recyclerViewHorizontal.adapter = horizontalAdapter
        holder.recyclerViewHorizontal.setRecycledViewPool(recycledViewPool)
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    inner class AllCategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val recyclerViewHorizontal: RecyclerView = itemView.findViewById(R.id.home_recycler_view_horizontal)
        val textViewCategory: TextView

        private val horizontalManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        init {
            recyclerViewHorizontal.setHasFixedSize(true)
            recyclerViewHorizontal.isNestedScrollingEnabled = false
            recyclerViewHorizontal.layoutManager = horizontalManager
            recyclerViewHorizontal.itemAnimator = DefaultItemAnimator()
            textViewCategory = itemView.findViewById(R.id.tv_movie_category)
        }

    }

}
