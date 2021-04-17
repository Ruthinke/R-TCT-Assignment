package com.ruthloeser.android.tctassignment.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ruthloeser.android.tctassignment.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button.setOnClickListener {
            openArticlesFragment()
        }
    }

    private fun openArticlesFragment() {
        view?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_mainFragment_to_articlesFragment)

        }
    }

}