package com.hod.themoviedb.list

import com.hod.themoviedb.Presenter
import com.hod.themoviedb.network.Service
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListPresenter : Presenter<ListPresenter.View>() {

    public override fun onViewAttached(view: View) {
        super.onViewAttached(view)

        Service().service.fetchList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeUntilDetached({ view.display(it.toString()) }, { it.printStackTrace() })
    }

    interface View : Presenter.View {
        fun display(string: String)
    }
}