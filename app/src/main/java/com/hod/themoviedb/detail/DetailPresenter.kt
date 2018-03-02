package com.hod.themoviedb.detail

import com.hod.themoviedb.Presenter
import io.reactivex.Observable

class DetailPresenter: Presenter<DetailPresenter.View>() {

    override fun onViewAttached(view: View) {
        super.onViewAttached(view)

        view.next().subscribeUntilDetached { productCode ->  }
    }

    interface View: Presenter.View {
        fun next(): Observable<String>
    }
}
