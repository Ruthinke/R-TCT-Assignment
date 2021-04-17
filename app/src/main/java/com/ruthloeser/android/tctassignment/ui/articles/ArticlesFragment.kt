package com.ruthloeser.android.tctassignment.ui.articles

import android.content.Context
import android.graphics.Rect
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        recyclerView.addItemDecoration( object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State,
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.bottom = activity?.dpToPx(16)?:16
            }
        }

        )

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

    fun Context.dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

    fun Context.pxToDp(px: Int): Int {
        return (px / resources.displayMetrics.density).toInt()
    }

}