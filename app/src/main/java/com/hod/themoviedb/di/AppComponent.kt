package com.hod.themoviedb.di

import com.hod.themoviedb.list.ListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, PresenterModule::class])
interface AppComponent {
    fun inject(target: ListFragment)
}