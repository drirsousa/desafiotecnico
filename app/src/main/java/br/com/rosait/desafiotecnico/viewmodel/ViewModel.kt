package br.com.rosait.desafiotecnico.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.rosait.desafiotecnico.di.App
import br.com.rosait.desafiotecnico.di.AppModule
import br.com.rosait.desafiotecnico.di.DaggerAppComponent
import br.com.rosait.desafiotecnico.model.Result
import br.com.rosait.desafiotecnico.service.Service
import javax.inject.Inject

class ViewModel : ViewModel() {

   private val component by lazy { DaggerAppComponent.builder().appModule(AppModule(App())).build() }

    @Inject
    lateinit var mService: Service

    init {
        component.inject(this)
    }

    val mResultMutable: MutableLiveData<Result> = MutableLiveData()

    fun getResults() : LiveData<Result> {
        mService.getResult { result ->
            mResultMutable.value = result
        }

        return mResultMutable
    }
}