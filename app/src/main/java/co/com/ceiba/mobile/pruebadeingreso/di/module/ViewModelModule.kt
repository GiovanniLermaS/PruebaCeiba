package co.com.ceiba.mobile.pruebadeingreso.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.com.ceiba.mobile.pruebadeingreso.di.util.ViewModelKey
import co.com.ceiba.mobile.pruebadeingreso.utils.ViewModelFactory
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.MainActivityViewModel
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.PostActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(PostActivityViewModel::class)
    abstract fun bindDetailActivityViewModel(postActivityViewModel: PostActivityViewModel?): ViewModel?

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory?): ViewModelProvider.Factory?
}