package shehab.task.com.marvelcharacters.ui.fragment.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import shehab.task.com.marvelcharacters.R
import shehab.task.com.marvelcharacters.data.model.characters.Results
import java.util.ArrayList

const val TYPE_ITEM = 1
const val TYPE_LOADING = 2
const val IMAGE_VARIANT = "/portrait_xlarge"
const val IMAGE_EXTENSION = ".jpg"

class CharactersListAdapter(
    private val mContext: Context,
    var charsList: ArrayList<Results>,
    var mListener: OnItemClickListener
) : androidx.recyclerview.widget.RecyclerView.Adapter<CharactersListAdapter.ViewHolder>() {

    var layoutType = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var layout = -1
        when (viewType) {
            TYPE_ITEM -> layout = R.layout.item_list_repos
            TYPE_LOADING -> layout = R.layout.item_loading
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val charPOJO = charsList[position]

        if (charPOJO.type == TYPE_ITEM) {
            holder.setRepoItemData(charPOJO)
        }

        if (charPOJO.type == TYPE_LOADING) {
            holder.setProgressBar(true)
        }


        holder.itemView.setOnClickListener {
            mListener.onItemClick(charsList[position])
        }
    }

    override fun getItemCount(): Int {
        return charsList.size
    }

    override fun getItemViewType(position: Int): Int {
        if (charsList[position].type == TYPE_ITEM)
            layoutType = TYPE_ITEM

        if (charsList[position].type == TYPE_LOADING)
            layoutType = TYPE_LOADING

        return layoutType
    }

    interface OnItemClickListener {
        fun onItemClick(characterData: Results)
    }

    inner class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

        //repos item iew
        private lateinit var tvName: TextView
        private lateinit var ivImage: RoundedImageView

        //loading item view
        private var progressBar: ProgressBar? = null

        init {

            when (layoutType) {
                TYPE_ITEM -> {
                    tvName = itemView.findViewById(R.id.tv_name)
                    ivImage = itemView.findViewById(R.id.iv_image)
                }
                TYPE_LOADING -> {
                    progressBar = itemView.findViewById(R.id.progressBar)
                }
            }

        }


        fun setRepoItemData(characterData: Results) {
            tvName.text = characterData.getName()

            val avatarUrl = characterData.thumbnail.path
            if (avatarUrl != null && avatarUrl.isNotEmpty())
                Picasso.with(mContext).load(avatarUrl.plus(IMAGE_VARIANT).plus(IMAGE_EXTENSION)).into(ivImage)
        }

        fun setProgressBar(isVisible: Boolean) {
            when {
                isVisible -> progressBar?.visibility = View.VISIBLE
                else -> progressBar?.visibility = View.GONE
            }

        }
    }
}
