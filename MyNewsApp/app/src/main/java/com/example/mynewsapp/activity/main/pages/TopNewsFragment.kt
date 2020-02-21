package com.example.mynewsapp.activity.main.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynewsapp.BuildConfig
import com.example.mynewsapp.R
import com.example.mynewsapp.activity.detail.DetailActivity
import com.example.mynewsapp.model.ErrorObject
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.top_news_fragment.*

class TopNewsFragment : Fragment() {

    companion object {
        fun newInstance() = TopNewsFragment()
    }

    private val newsListAdapter: NewsListAdapter by lazy {
        NewsListAdapter(activity!!, this, R.layout.item_news_list) { position ->
            DetailActivity.startActivity(activity!!, newsListAdapter.getItem(position))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.top_news_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = newsListAdapter
        }

        val viewModel: TopNewsViewModel by viewModels()
        viewModel.progressBarVisibilityLiveData.observe(viewLifecycleOwner) { show ->
            progressBar.visibility = if (show) View.VISIBLE else View.GONE
        }
        viewModel.topNewsLiveData.observe(viewLifecycleOwner) { news ->
            newsListAdapter.setItems(news)
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) { errorObject ->
            if (BuildConfig.DEBUG) {
                errorObject.t?.printStackTrace()
            }
            Snackbar.make(
                top_new_fragment,
                if (errorObject.id == ErrorObject.ERROR_ID_NO_INTERNET)
                    R.string.error_no_internet
                else
                    R.string.error_unknown_error,
                Snackbar.LENGTH_INDEFINITE
            )
                .setAction(R.string.retry) { viewModel.fetchNews() }
                .show()
        }
    }

}
