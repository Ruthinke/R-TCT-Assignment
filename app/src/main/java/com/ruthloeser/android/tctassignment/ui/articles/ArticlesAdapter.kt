package com.ruthloeser.android.tctassignment.ui.articles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ruthloeser.android.tctassignment.R
import com.ruthloeser.android.tctassignment.model.Article

class ArticlesAdapter : RecyclerView.Adapter<ArticleHolder>() {

  private val data: MutableList<Article> = mutableListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
    val view = LayoutInflater
      .from(parent.context)
      .inflate(R.layout.item_article, parent, false)

    return ArticleHolder(view)
  }

  override fun getItemCount() = data.size

  override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
    holder.bindData(data[position])
  }

  fun setData(data: List<Article>) {
    this.data.clear()
    this.data.addAll(data)
    notifyDataSetChanged()
  }
}
