package com.example.hqawasomeapp.hqHome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hqawasomeapp.HQViewModel
import com.example.hqawasomeapp.R
import com.example.hqawasomeapp.databinding.FragmentItemBinding

class HQFragment : Fragment(), HQItemListener {

    private lateinit var adapter: MyhqRecyclerViewAdapter
    private val viewModel by navGraphViewModels<HQViewModel>(R.id.hq_graph) { defaultViewModelProviderFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentItemBinding.inflate(inflater)

        val view = binding.root
        val recyclerView = binding.list

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        adapter = MyhqRecyclerViewAdapter(this)

        recyclerView.apply {
             this.adapter = this@HQFragment.adapter
             this.layoutManager = LinearLayoutManager(context)
        }

        initObservers()

        return view
    }

    private fun initObservers() {
        viewModel.hqListLiveData.observe(viewLifecycleOwner, Observer {

            it?.let{
                adapter.updateData(it)
            }

        })

        viewModel.navigationToDetailLiveData.observe(viewLifecycleOwner, Observer {
            val action = HQFragmentDirections.actionHQFragmentToHQDetailsFragment()
            findNavController().navigate(action)
        })
    }

    override fun onItemSelected(position: Int) {
        viewModel.onHQSelected(position)
    }
}