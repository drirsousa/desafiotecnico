package br.com.rosait.desafiotecnico.di

import br.com.rosait.desafiotecnico.viewmodel.ViewModel
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(app: App)

    fun inject(viewModel: ViewModel)
}