package com.example.hqawasomeapp.hqHome

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.navGraphViewModels
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.hqawasomeapp.HQViewModel
import com.example.hqawasomeapp.R
import com.example.hqawasomeapp.databinding.FragmentItemBinding
import com.google.android.material.snackbar.Snackbar

class HQFragment : Fragment(), HQItemListener {

    private lateinit var adapter: MyhqRecyclerViewAdapter
    private val viewModel by navGraphViewModels<HQViewModel>(R.id.hq_graph){defaultViewModelProviderFactory}

    /** Permissões para uso CAMERA */
    private var permissiomResultLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
                when{
                    granted -> {} //Usuário Aceitou
                    !shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                        view?.let {
                            Snackbar.make(
                                it,
                                "Precisamos da permissão de câmera",
                                Snackbar.LENGTH_INDEFINITE
                            ).setAction("Solicitar Permissão") {
                                val intent = Intent()
                                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                                val uri = Uri.fromParts("package", requireActivity().packageName, null)
                                intent.data = uri
                                startActivity(intent)
                            }.show()
                        }
                    }
                    else -> {}
                }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentItemBinding.inflate(inflater)

        val view = binding.root
        val recyclerView = binding.list

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        adapter = MyhqRecyclerViewAdapter( this)

        view.apply {
            this.adapter = this@HQFragment.adapter
            this.layoutManager = LinearLayoutManager(context)
        }

        initObservers()

        return view

    }


    private fun initObservers() {
        viewModel.hqListLiveData.observe(viewLifecycleOwner) {

            it?.let{
                adapter.updateData(it)
            }
        }

        viewModel.navigationToDetailLiveData.observe(viewLifecycleOwner) {
            val action = HQFragmentDirections.actionHqFragmentToHQDetailsFragment()
            findNavController().navigate(action)
        }
    }

    override fun onItemSelected(position: Int) {
        val message = "Item selecionado"
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    /** Solicitação Uso CAMERA  */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                /** Usuário Aceitou */
            }
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                /** Usuário NÃO Aceitou */
                Snackbar.make(
                    view,
                    "Precisamos da permissão de câmera",
                    Snackbar.LENGTH_INDEFINITE
                ).setAction("Solicitar Permissão") {
                    permissiomResultLauncher.launch(Manifest.permission.CAMERA)
                }.show()
            }
            else -> {
                /** Pedir permissão */
                permissiomResultLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }


    companion object {
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            HQFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }

}