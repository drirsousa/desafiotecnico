package br.com.rosait.desafiotecnico.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.rosait.desafiotecnico.R
import br.com.rosait.desafiotecnico.databinding.ItemSpotlightBinding
import br.com.rosait.desafiotecnico.model.Spotlight
import com.squareup.picasso.Picasso

class SpotlightAdapter(var spotlightList: ArrayList<Spotlight>) : RecyclerView.Adapter<SpotlightAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_spotlight, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return spotlightList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(spotlightList[position])
    }

    fun updateList(newList: ArrayList<Spotlight>) {
        this.spotlightList.removeAll(spotlightList)
        this.spotlightList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemSpotlightBinding? = DataBindingUtil.bind(itemView)

        fun bind(spotlight: Spotlight) {
            spotlight.bannerURL?.let {
                Picasso.get()
                    .load(it)
                    //.centerCrop()
                    .into(binding?.imvSpotlight)
            }
        }
    }
}