package br.com.rosait.desafiotecnico.di

import br.com.rosait.desafiotecnico.service.Service
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: App) {

    @Provides @Singleton
    fun provideApplication() = app

    @Provides
    fun provideService() : Service {
        return Service()
    }
}