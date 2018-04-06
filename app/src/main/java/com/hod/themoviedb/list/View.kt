package com.hod.themoviedb.list

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.hod.themoviedb.App
import com.hod.themoviedb.R
import com.hod.themoviedb.detail.DetailFragment
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class ListFragment : Fragment(), ListPresenter.View {

    @Inject lateinit var presenter: ListPresenter
    private val adapter = Adapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_list, container, false).findViews()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity.application as App).component.inject(this)
        presenter.onViewAttached(this)
    }

    override fun onDestroyView() {
        presenter.onViewDetached(this)
        super.onDestroyView()
    }

    override fun displayList(movies: kotlin.collections.List<Movie>) {
        adapter.addItems(movies)
        adapter.notifyDataSetChanged()
    }

    override fun displayDetail(code: Int) {
        val detailFragment = DetailFragment()
        val bundle = Bundle()
        bundle.putInt("code", code)
        detailFragment.arguments = bundle

        fragmentManager.beginTransaction()
                .replace(R.id.container, detailFragment)
                .addToBackStack("detail")
                .commit()
    }

    override fun clicks(): Observable<Int> = adapter.clicks

    private fun View.findViews(): View {
        val recyclerView = this.findViewById<RecyclerView>(R.id.recycler)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        return this
    }
}

class Adapter : RecyclerView.Adapter<Adapter.Vh>() {
    private val data = ArrayList<Movie>()
    internal val clicks: PublishSubject<Int> = PublishSubject.create<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            Vh(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))


    override fun onBindViewHolder(holder: Adapter.Vh, position: Int) {
        holder.title.text = data[position].title
        holder.image.loadImage(data[position].posterPath)
        holder.clicks.map { data[position].id }.subscribe(clicks)
    }

    override fun getItemCount(): Int = data.size

    fun addItems(movies: kotlin.collections.List<Movie>) = data.addAll(movies)

    class Vh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val title: TextView = itemView.findViewById(R.id.title)
        val clicks: Observable<Unit> = itemView.clicks()
    }

    private fun ImageView.loadImage(posterPath: String?) {
        Glide.with(this.context)
                .load("https://image.tmdb.org/t/p/w500$posterPath")
                .into(this)
    }
}
