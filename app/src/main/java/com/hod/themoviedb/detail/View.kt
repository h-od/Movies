package com.hod.themoviedb.detail

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.Observable

class DetailFragment: Fragment(), DetailPresenter.View {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun next(): Observable<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}