package com.harish.estarradomachinetest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.harish.estarradomachinetest.R
import com.harish.estarradomachinetest.data.ApiResponse
import kotlinx.android.synthetic.main.list_cell_category.view.*
import kotlinx.android.synthetic.main.list_cell_featured.view.*
import kotlinx.android.synthetic.main.list_cell_home_item.view.*

class HomeAdapter(val itemType : String,val featured :List<ApiResponse.Data.Featured>?,val categories: List<ApiResponse.Data.Category>?) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>(){



    class HomeViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_cell_home_item, parent, false)

        return HomeViewHolder(view)

    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val itemTypes = getItemViewType(position)

        if(itemType == "Featured"){
            val adapter = featured?.let { FeaturedProductsAdapter(it) }
            holder.itemView.home_cell_rv.layoutManager = LinearLayoutManager(holder.itemView.context,RecyclerView.HORIZONTAL,false)

            holder.itemView.home_cell_rv.adapter = adapter

            holder.itemView.item_title_tv.text = "Featured Products"

        }
        else if (itemType == "Category"){
            holder.itemView.home_cell_rv.layoutManager = LinearLayoutManager(holder.itemView.context,RecyclerView.HORIZONTAL,false)

            holder.itemView.home_cell_rv.adapter = categories?.let { CategoriesAdapter(it) }
        }


    }


}

class FeaturedProductsAdapter(val products : List<ApiResponse.Data.Featured>):

    RecyclerView.Adapter<FeaturedProductsAdapter.FeaturedViewHolder>() {
    class FeaturedViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_cell_featured, parent, false)
     return FeaturedViewHolder(view)
    }

    override fun getItemCount(): Int {
    return products.size
    }

    override fun onBindViewHolder(holder: FeaturedViewHolder, position: Int) {

        val item = products[position]
        holder.itemView.apply {
            featured_image.load(item.prdImage)
            featured_item_title.text = item.prdName
            featured_item_stock.text = item.currentStock
            features_item_cost.text = "${item.price} ${item.currency}"
            featured_ratingBar.rating = item.rating.toFloat()
        }
    }


}


class CategoriesAdapter(val categories : List<ApiResponse.Data.Category>) : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>(){

    class CategoryViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_cell_featured, parent, false)

        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {

      return  categories.size

    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        val item = categories[position]
        holder.itemView.apply {
            category_imageView.load(item.categoryImage)
            category_title.text = item.categoryName
        }


    }

}