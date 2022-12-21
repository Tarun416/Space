package com.example.spacex.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.LocalDataSourceImpl
import com.example.data.local.dao.SpaceDao
import com.example.data.local.db.SpaceDatabase
import com.example.data.mapper.ItemMapper
import com.example.data.remote.SpaceService
import com.example.data.remote.SpaceServiceImpl
import com.example.data.repository.LocalRepositoryImpl
import com.example.data.source.DataSourceFactory
import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource
import com.example.domain.repository.LocalRepository
import com.example.spacex.utils.AppConstants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): SpaceDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            SpaceDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(db: SpaceDatabase): SpaceDao {
        return db.dao()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(dao : SpaceDao): LocalDataSource = LocalDataSourceImpl(dao)


    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.nasa.gov/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesSpaceService(retrofit: Retrofit): SpaceService =
        retrofit.create(SpaceService::class.java)

    @Provides
    @Singleton
    fun provideRemoteDataSource(service: SpaceService): RemoteDataSource = SpaceServiceImpl(service)

    @Provides
    @Singleton
    fun provideDataSourceFactory(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): DataSourceFactory =
        DataSourceFactory(localDataSource, remoteDataSource)

    @Provides
    @Singleton
    fun provideMapper(): ItemMapper = ItemMapper()

    @Provides
    @Singleton
    fun provideLocalRepository(
        dataSourceFactory: DataSourceFactory,
        itemMapper: ItemMapper
    ): LocalRepository = LocalRepositoryImpl(dataSourceFactory, itemMapper)


}