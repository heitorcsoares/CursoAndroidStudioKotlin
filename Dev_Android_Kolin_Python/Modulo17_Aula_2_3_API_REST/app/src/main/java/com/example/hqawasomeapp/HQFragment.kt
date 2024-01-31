package com.example.hqawasomeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.navigation.navGraphViewModels
import com.example.hqawasomeapp.placeholder.PlaceholderContent
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class HQFragment : Fragment(), HQItemListener {

    private var columnCount = 1
    private val viewModel by navGraphViewModels<HQViewModel>(R.id.hq_graph){defaultViewModelProviderFactory}

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/movie/latest")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

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

    /** Requisição Assíncrona */
    val comicService = retrofit.create(ComicsService::class.java)

    comicService.getComics("test").enqueue(object : Callback<Comic>{
        override fun onResponse(call: Call<Comic>, response: Response<Comic>) {
            if(response.isSucessful){
                //sucesso!!
            }else{
                //erro
            }
        }
        override fun onFailure(call: Call<Comic>, t: Throwable) {
            //erro
        }
    })
    /** FIM */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        /** Configurando o adapter */
        if (view is RecyclerView) {
            val adapter = MyhqRecyclerViewAdapter(PlaceholderContent.ITEMS, this, requireParentFragment())
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                this.adapter = adapter
            }
        }
        return view
    }


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

    override fun onItemSelected(position: Int) {
        val selectedItem = PlaceholderContent.ITEMS[position] // Obtém o item selecionado
        val message = "Item selecionado: ${selectedItem.content}"
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}