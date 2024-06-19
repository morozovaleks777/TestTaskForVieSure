package com.morozov.testtaskforviesure.di

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.morozov.testtaskforviesure.data.ApiService
import com.morozov.testtaskforviesure.data.RepositoryImpl
import com.morozov.testtaskforviesure.data.room.BookDao
import com.morozov.testtaskforviesure.data.room.BookDatabase
import com.morozov.testtaskforviesure.data.room.RoomRepositoryImpl
import com.morozov.testtaskforviesure.domain.Repository
import com.morozov.testtaskforviesure.domain.RoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


private const val BASE_URL = "https://c27b2d72-8d9c-4aa0-b549-7ae7e5666815.mock.pstmn.io/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideCache(@ApplicationContext context: Context): Cache {
        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
        return Cache(context.cacheDir, cacheSize)
    }


    @Singleton
    @Provides
    fun provideOkhttpBuilder(cache: Cache): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .followSslRedirects(false)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .cache(cache)

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideDailyWireRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        val gson = GsonBuilder().create()

        return Retrofit.Builder()
            //.client(OkHttpClient())
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(booksService: ApiService): Repository {
        return RepositoryImpl(booksService)
    }


//@Singleton
//@Provides
//fun provideDatabase(app: Context): BookDatabase {
//    return Room.databaseBuilder(
//        app,
//        BookDatabase::class.java,
//        "book_database"
//    ).build()
//}


    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext
        context: Context
    ): BookDatabase =
        Room.databaseBuilder(
            context, BookDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration()
            .build()


    @Singleton
    @Provides
    fun provideBookDao(appDatabase: BookDatabase): BookDao =
        appDatabase.bookDao()

    @Provides
    fun provideRoomRepository(bookDao: BookDao): RoomRepository = RoomRepositoryImpl(bookDao)

}