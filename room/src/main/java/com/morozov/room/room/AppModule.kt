package com.morozov.room.room

import android.content.Context
import androidx.room.Room
import com.morozov.common.utils.UserDatabasePassphrase
import com.morozov.domain.domain.RoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideUserDatabasePassphrase(@ApplicationContext context: Context) =
        UserDatabasePassphrase(context)

    @Provides
    @Singleton
    fun provideSupportFactory(userDatabasePassphrase: UserDatabasePassphrase): SupportFactory {
        return SupportFactory(userDatabasePassphrase.getPassphrase())
    }

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext
        context: Context,
        supportFactory: SupportFactory
    ): BookDatabase {

        return Room.databaseBuilder(
            context, BookDatabase::class.java,
            "book_database"
        ).openHelperFactory(supportFactory)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBookDao(appDatabase: BookDatabase): BookDao =
        appDatabase.bookDao()

    @Provides
    fun provideRoomRepository(roomRepositoryImpl: RoomRepositoryImpl): RoomRepository {
        return roomRepositoryImpl
    }
}