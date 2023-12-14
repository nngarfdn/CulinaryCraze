package com.arfdn.culinarycraze

import android.app.Application
import com.arfdn.core.di.databaseModule
import com.arfdn.core.di.networkModule
import com.arfdn.core.di.repositoryModule
import com.arfdn.culinarycraze.di.useCaseModule
import com.arfdn.culinarycraze.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CulinaryCrazeApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@CulinaryCrazeApp)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}
