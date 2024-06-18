package com.morozov.testtaskforviesure.di


import com.morozov.testtaskforviesure.di.annotations.Dispatcher
import com.morozov.testtaskforviesure.di.annotations.ForApplicationScope
import com.morozov.testtaskforviesure.di.annotations.AppDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

    @Singleton
    @Provides
    @ForApplicationScope
    fun coroutineScope(): CoroutineScope =
        CoroutineScope(SupervisorJob() + Dispatchers.Default)

    @Provides
    @Dispatcher(AppDispatchers.IO)
    fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO


    @Provides
    @Dispatcher(AppDispatchers.MAIN)
    fun mainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Dispatcher(AppDispatchers.DEFAULT)
    fun defaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}