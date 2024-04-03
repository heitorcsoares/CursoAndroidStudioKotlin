package com.example.filmes.filmesHome

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.navGraphViewModels
import com.example.filmes.FilmeViewModel
import com.example.filmes.R
import com.example.filmes.placeholder.PlaceholderContent

class FilmeItemFragment : Fragment(), FilmeItemListener {

    private var columnCount = 1
    private val viewModel by navGraphViewModels<FilmeViewModel>(R.id.filmes_graph){defaultViewModelProviderFactory}

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

        /** configurando adapter */
        if (view is RecyclerView) {
            val adapter = MyfilmeRecyclerViewAdapter(PlaceholderContent.ITEMS, this, requireParentFragment())   /**this -> referencia de instancia da Classe FilmesItemListener */
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

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            FilmeItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }

    /** Função original (MyfilmeRecyclerViewAdapter | interface)  */
    override fun onItemSelected(position: Int) {   }

}