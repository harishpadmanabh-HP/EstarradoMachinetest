package com.harish.estarradomachinetest

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.harish.estarradomachinetest.adapters.HomeAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*


class Home : Fragment() {

    private lateinit var root: View
    private lateinit var viewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_home, container, false)
        initViewModel(requireActivity().application)
        viewModel.getDataForHome()
        setupObservers()



        return root
    }

    private fun setupObservers() {

        viewModel.apply {
            apiResponse.observe(requireActivity(), Observer {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()




                root.home_recycler_view.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
                root.home_recycler_view.adapter = HomeAdapter("Featured",it.data.featured.featured,null)
                root.home_recycler_view.adapter = HomeAdapter("Categories",null,it.data.categories)

            })
            events.observe(requireActivity(), Observer {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            })
        }

    }

    private fun initViewModel(application: Application) {
        val factory = HomeViewModelFactory(application)
        viewModel = ViewModelProvider(requireActivity(),factory).get(HomeViewModel::class.java)

    }


}