package com.example.hqawasomeapp.hqHome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hqawasomeapp.HQViewModel
import com.example.hqawasomeapp.R
import com.example.hqawasomeapp.databinding.FragmentItemListBinding

class HQFragment : Fragment(), HQItemListener {

    private lateinit var adapter: MyhqRecyclerViewAdapter
    private val viewModel by navGraphViewModels<HQViewModel>(R.id.hq_graph) { defaultViewModelProviderFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentItemListBinding.inflate(inflater)

        val view = binding.root
        val recyclerView = binding.list

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        adapter = MyhqRecyclerViewAdapter(this)

        recyclerView.apply {
            this.adapter = this@HQFragment.adapter
            this.layoutManager = LinearLayoutManager(context)
        }

        Toast.makeText(context, "Bem-vindo!", Toast.LENGTH_SHORT).show()

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
            it.getContentIfNotHandled()?.let{
                val action = HQFragmentDirections.actionHQFragmentToHQDetailsFragment()
                findNavController().navigate(action)
            }
        })
    }

    override fun onItemSelected(position: Int) {
        viewModel.onHQSelected(position)
    }
}