package com.myproject.antinolabs.di


import android.app.Application
import com.myproject.antinolabs.SessionManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        NetworkModule::class,
        AuthenticationModule::class,
        ViewModelsModule::class
    ]
)
class AppModule {

    @Provides
    @Singleton
    fun provideSessionManager(app: Application): SessionManager {
        return SessionManager(app)
    }
}

