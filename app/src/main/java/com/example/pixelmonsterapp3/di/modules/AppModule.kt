package com.example.pixelmonsterapp3.di.modules

import com.example.loanapp.di.annotations.LocalDataSource
import com.example.loanapp.di.annotations.RemoteDataSource
import com.example.pixelmonsterapp3.data.datasource.*
import com.example.pixelmonsterapp3.data.repository.MonsterRepositoryImpl
import com.example.pixelmonsterapp3.domain.repository.MonsterRepository
import com.example.pixelmonsterapp3.navigation.Navigator
import com.example.pixelmonsterapp3.navigation.NavigatorImpl
import com.example.pixelmonsterapp3.presentation.monsterdetails.MonsterDetailsRouter
import com.example.pixelmonsterapp3.presentation.monsterdetails.MonsterDetailsRouterImpl
import com.example.pixelmonsterapp3.presentation.monsterlist.MonsterListScreenRouter
import com.example.pixelmonsterapp3.presentation.monsterlist.MonsterListScreenRouterImpl
import com.example.pixelmonsterapp3.ui.MainActivity
import dagger.*
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

    @Binds
    abstract fun navigator(navigator: NavigatorImpl): Navigator

    @Binds
    abstract fun bindMonsterListScreenRouter(router: MonsterListScreenRouterImpl): MonsterListScreenRouter

    @Binds
    abstract fun bindMonsterDetailsRouter(router: MonsterDetailsRouterImpl): MonsterDetailsRouter

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}

