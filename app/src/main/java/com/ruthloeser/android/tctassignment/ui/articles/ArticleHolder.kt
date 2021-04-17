/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

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