package com.harish.estarradomachinetest.data


import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("httpcode")
    val httpcode: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
) {




    data class Data(
        @SerializedName("banner_slider")
        val bannerSlider: List<BannerSlider>,
        @SerializedName("cartcount")
        val cartcount: Int,
        @SerializedName("cartid")
        val cartid: String,
        @SerializedName("categories")
        val categories: List<Category>,
        @SerializedName("featured")
        val featured: List<Featured>,
        @SerializedName("logo")
        val logo: String,
        @SerializedName("profiledata")
        val profiledata: Profiledata,
        @SerializedName("regions")
        val regions: List<Region>
    ) {
        data class BannerSlider(
            @SerializedName("slider_id")
            val sliderId: String,
            @SerializedName("slider_image")
            val sliderImage: String,
            @SerializedName("slider_name")
            val sliderName: String
        )

        data class Category(
            @SerializedName("category_id")
            val categoryId: String,
            @SerializedName("category_image")
            val categoryImage: String,
            @SerializedName("category_name")
            val categoryName: String
        ){

                val itemType : String = "Categories"

        }

        data class Featured(
            @SerializedName("currency")
            val currency: String,
            @SerializedName("current_stock")
            val currentStock: String,
            @SerializedName("prd_id")
            val prdId: String,
            @SerializedName("prd_image")
            val prdImage: String,
            @SerializedName("prd_name")
            val prdName: String,
            @SerializedName("price")
            val price: String,
            @SerializedName("qty")
            val qty: String,
            @SerializedName("rating")
            val rating: String,
            @SerializedName("unit")
            val unit: String

        ){
            val itemType : String = "Featured"
        }

        data class Profiledata(
            @SerializedName("address1")
            val address1: String,
            @SerializedName("address2")
            val address2: String,
            @SerializedName("district")
            val district: String,
            @SerializedName("email")
            val email: String,
            @SerializedName("image")
            val image: String,
            @SerializedName("mobile")
            val mobile: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("post")
            val post: String,
            @SerializedName("state")
            val state: String
        )

        data class Region(
            @SerializedName("country_code")
            val countryCode: String,
            @SerializedName("country_id")
            val countryId: String,
            @SerializedName("country_name")
            val countryName: String
        )


    }
}

class HomeData
    (
    var item:String,
    var data : Any
){
}