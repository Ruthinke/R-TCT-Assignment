package com.ruthloeser.android.tctassignment.ui.articles

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ruthloeser.android.tctassignment.R
import com.ruthloeser.android.tctassignment.model.Failure
import com.ruthloeser.android.tctassignment.model.Success
import kotlinx.android.synthetic.main.articles_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ArticlesFragment : Fragment() {

    private lateinit var viewModel: ArticlesViewModel
    private val adapter by lazy { ArticlesAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.articles_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArticlesViewModel::class.java)
        initUi()
    }

    private fun initUi() {
        progress.visibility = View.VISIBLE
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        viewModel.allArticles.observe(viewLifecycleOwner, { result ->
            progress.visibility = View.GONE
            if (result is Success) {
                adapter.setData(result.data)
            }
            else {
                activity?.let { context ->
                    val err = (result as Failure).error
                    Toast.makeText(context, err?.message?:"", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onDestroy() {
        GlobalScope.launch(Dispatchers.IO) { viewModel.clear() }
        super.onDestroy()
    }

}