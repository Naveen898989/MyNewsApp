package com.example.mynewsapp.activity.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import com.bumptech.glide.Glide
import com.crashlytics.android.Crashlytics
import com.example.mynewsapp.R
import com.example.mynewsapp.model.News
import com.example.mynewsapp.util.convertStringToElapsedTime
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_NEWS_ITEM: String = "extraNewsItem"

        fun startActivity(context: Context, newsItem: News) {
            context.startActivity(Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_NEWS_ITEM, newsItem)
            })
        }
    }

    private val newsItem: News by lazy {
        intent.getParcelableExtra<News>(EXTRA_NEWS_ITEM)!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initContent()
    }

    private fun initContent() {
        Glide
            .with(this)
            .load(newsItem.urlToImage)
            .centerInside()
            .into(imageView)

        textViewSource.text = newsItem.source?.name
        textViewAuthor.text = getString(R.string.author_format, newsItem.author)
        textViewTime.text = convertStringToElapsedTime(this, newsItem.publishedAt)
        textViewTitle.text = newsItem.title
        textViewDescription.text = newsItem.description
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textViewContent.text =
                Html.fromHtml(newsItem.content ?: "", Html.FROM_HTML_MODE_COMPACT)
        } else {
            textViewContent.text = Html.fromHtml(newsItem.content ?: "")
        }
    }

    fun onClick(view: View) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(newsItem.url)))
    }
}
