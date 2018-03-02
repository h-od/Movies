package com.hod.themoviedb.list

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hod.themoviedb.R

class ListFragment: Fragment(), ListPresenter.View {
    private val presenter = ListPresenter()

    private lateinit var tv: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_list, container, false).findViews()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewAttached(this)
    }

    override fun onDestroyView() {
        presenter.onViewDetached(this)
        super.onDestroyView()
    }

    override fun display(string: String) {
        tv.text = string
    }

    private fun View.findViews(): View {
        tv = this.findViewById(R.id.text)
        return this
    }
}

