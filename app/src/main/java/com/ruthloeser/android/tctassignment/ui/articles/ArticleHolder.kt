package com.ruthloeser.android.tctassignment.ui.articles

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.ruthloeser.android.tctassignment.App
import com.ruthloeser.android.tctassignment.R
import com.ruthloeser.android.tctassignment.model.Article
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_article.view.*
import java.text.SimpleDateFormat
import java.util.*


fun getDate(date: String): String {
  val parsedDate = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(date.split("T")[0])
  val formatOut = SimpleDateFormat(App.getContext().getString(R.string.article_date_format), Locale.US)
  return if (parsedDate != null) {
    formatOut.format(parsedDate)
  } else {
    ""
  }
}

class ArticleHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
  LayoutContainer {


  fun bindData(article: Article) {

    containerView.apply {
      bgImageView.load(article.imageUrl) {
        crossfade(true)
        placeholder(R.color.design_default_color_primary_dark)
        scale(Scale.FILL)
        diskCachePolicy(CachePolicy.DISABLED)
      }
      saveBtn.isChecked = article.isSaved
      likeBtn.isChecked = article.isLiked
      numOfLikes.text = article.likesCount.toString()
      categoryText.text = article.category
      title.text = article.title
      authorAvatar.load(article.author.authorAvatar.imageUrl) {
        crossfade(true)
        placeholder(R.drawable.logo50)
        scale(Scale.FIT)
        diskCachePolicy(CachePolicy.DISABLED)
        transformations(CircleCropTransformation())
      }
      authorName.text = article.author.authorName
      lastUpdated.text = getDate(article.metaData.updateTime)
    }

  }
}