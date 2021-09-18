package com.example.pixelmonsterapp3.di.modules

import android.content.Context
import androidx.room.Room
import com.example.pixelmonsterapp3.data.database.MonsterDao
import com.example.pixelmonsterapp3.data.database.MonsterDatabase
import com.example.pixelmonsterapp3.data.datasource.LocalMonsterDataSourceImpl
import com.example.pixelmonsterapp3.data.datasource.LocalMonsterDataSource
import com.example.pixelmonsterapp3.data.datasource.RemoteMonsterDataSource
import com.example.pixelmonsterapp3.data.datasource.RemoteMonsterDataSourceImpl
import com.example.pixelmonsterapp3.data.repository.MonsterRepositoryImpl
import com.example.pixelmonsterapp3.di.annotations.IoCoroutineScope
import com.example.pixelmonsterapp3.domain.repository.MonsterRepository
import com.example.pixelmonsterapp3.navigation.Navigator
import com.example.pixelmonsterapp3.navigation.NavigatorImpl
import com.example.pixelmonsterapp3.presentation.monsterdetails.MonsterDetailsRouter
import com.example.pixelmonsterapp3.presentation.monsterdetails.MonsterDetailsRouterImpl
import com.example.pixelmonsterapp3.presentation.monsterlist.MonsterListScreenRouter
import com.example.pixelmonsterapp3.presentation.monsterlist.MonsterListScreenRouterImpl
import com.example.pixelmonsterapp3.ui.MainActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Module()
abstract class AppModule {

    companion object {
        @Provides
        @IoCoroutineScope
        fun provideIoCoroutineScope() = CoroutineScope(Dispatchers.IO)

        @Provides
        fun provideMonsterDataBase(context: Context): MonsterDatabase = Room.databaseBuilder(
            context.applicationContext,
            MonsterDatabase::class.java,
            "monster_database"
        )
            .fallbackToDestructiveMigration()
            .build()

        @Provides
        fun provideMonsterDao(db: MonsterDatabase): MonsterDao = db.monsterDao()
    }

    @Binds
    abstract fun bindLocalMonsterDataSource(localMonsterDataSourceImpl: LocalMonsterDataSourceImpl): LocalMonsterDataSource

    @Binds
    abstract fun bindRemoteMonsterDataSource(remoteMonsterDataSourceImpl: RemoteMonsterDataSourceImpl): RemoteMonsterDataSource

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
