package com.example.mynewsapp.activity.main.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.mynewsapp.R
import com.example.mynewsapp.util.NEWS_CATEGORY_VALUES
import kotlinx.android.synthetic.main.profile_fragment.*

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onPause() {
        super.onPause()

        viewModel.saveUserData(
            editTextUsername.text.toString(),
            editTextEmailAddress.text.toString(),
            spinnerCategory.selectedItem.toString()
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        spinnerCategory.adapter = ArrayAdapter(
            activity!!,
            android.R.layout.simple_dropdown_item_1line,
            NEWS_CATEGORY_VALUES
        )

        viewModel.userLiveData.observe(viewLifecycleOwner, Observer { userData ->
            if (userData != null) {
                editTextUsername.setText(userData.name)
                editTextEmailAddress.setText(userData.email)
                spinnerCategory.setSelection(NEWS_CATEGORY_VALUES.indexOf(userData.category))
            }
        })
    }

}
