package com.example.pixelmonsterapp3.di.modules

import com.example.loanapp.di.annotations.LocalDataSource
import com.example.loanapp.di.annotations.RemoteDataSource
import com.example.pixelmonsterapp3.data.datasource.*
import com.example.pixelmonsterapp3.data.repository.MonsterRepositoryImpl
import com.example.pixelmonsterapp3.domain.repository.MonsterRepository
import com.example.pixelmonsterapp3.ui.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module()
abstract class AppModule {

    companion object {


    }

    @Binds
    @LocalDataSource
    abstract fun bindLocalMonsterDataSource(localMonsterDataSourceImpl: LocalMonsterDataSourceImpl): MonsterDataSource

    @Binds
    @RemoteDataSource
    abstract fun bindRemoteMonsterDataSource(remoteMonsterDataSourceImpl: RemoteMonsterDataSourceImpl): MonsterDataSource

    @Binds
    abstract fun bindMonsterRepository(monsterRepositoryImpl: MonsterRepositoryImpl): MonsterRepository

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}

