package br.com.rosait.desafiotecnico.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.rosait.desafiotecnico.R
import br.com.rosait.desafiotecnico.databinding.ItemProductBinding
import br.com.rosait.desafiotecnico.model.Product
import com.squareup.picasso.Picasso

class ProductAdapter(var productList: ArrayList<Product>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    fun updateList(newList: ArrayList<Product>) {
        this.productList.removeAll(productList)
        this.productList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemProductBinding? = DataBindingUtil.bind(itemView)

        fun bind(product: Product) {
            product.imageURL?.let {
                Picasso.get()
                    .load(it)
                    .into(binding?.imvProduct)
            }
        }
    }
}