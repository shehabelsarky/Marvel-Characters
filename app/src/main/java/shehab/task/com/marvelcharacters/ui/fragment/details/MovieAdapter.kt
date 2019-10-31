package shehab.task.com.marvelcharacters.ui.fragment.details

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import shehab.task.com.marvelcharacters.R
import shehab.task.com.marvelcharacters.data.model.characters.Items

class MovieAdapter(private val movieList: List<Items>, private val context: Context) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movie = movieList[position]
        holder.textViewTitle.text = movie.getName()

      /*  Picasso
            .with(context)
            .load(context.resources.getString(R.string.image_url) + movie.resourceURI)
            .into(holder.imageViewMovie)*/

    }

    override fun getItemCount(): Int {
        return movieList.size
    }


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textViewTitle: TextView = itemView.findViewById(R.id.tv_title)
        val imageViewMovie: ImageView = itemView.findViewById(R.id.image_view_movie)
    }
}