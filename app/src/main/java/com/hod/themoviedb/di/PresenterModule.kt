package com.hod.themoviedb.di

import com.hod.themoviedb.list.ListPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {
  @Provides
  @Singleton
  fun provideListPresenter(): ListPresenter = ListPresenter ()
}