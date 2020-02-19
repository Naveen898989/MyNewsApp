package com.example.mynewsapp.activity.main.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.mynewsapp.BuildConfig
import com.example.mynewsapp.R
import com.example.mynewsapp.model.ErrorObject
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.top_news_fragment.*

class TopNewsFragment : Fragment() {

    companion object {
        fun newInstance() = TopNewsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.top_news_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel: TopNewsViewModel by viewModels()
        viewModel.progressBarVisibilityLiveData.observe(this) { show ->
            progressBar.visibility = if (show) View.VISIBLE else View.GONE
        }
        viewModel.topNewsLiveData.observe(this) { news -> }
        viewModel.errorLiveData.observe(this) { errorObject ->
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
