package com.example.pixelmonsterapp3.di.modules

import android.content.Context
import androidx.room.Room
import com.example.feature.monster.details.MonsterDetailsDestination
import com.example.feature.monster.details.presentation.MonsterDetailsRouter
import com.example.feature.monster.list.MonsterListDestination
import com.example.feature.monster.list.presentation.MonsterListScreenRouter
import com.example.pixelmonsterapp3.di.annotations.Destination
import com.example.pixelmonsterapp3.di.annotations.IoCoroutineScope
import com.example.pixelmonsterapp3.navigation.NavigatorImpl
import com.example.pixelmonsterapp3.routers.MonsterDetailsRouterImpl
import com.example.pixelmonsterapp3.routers.MonsterListScreenRouterImpl
import com.example.pixelmonsterapp3.ui.MainActivity
import com.example.shared.monster.api.data.database.MonsterDao
import com.example.shared.monster.api.data.database.MonsterDatabase
import com.example.shared.monster.api.data.datasource.LocalMonsterDataSource
import com.example.shared.monster.api.data.datasource.LocalMonsterDataSourceImpl
import com.example.shared.monster.api.data.datasource.RemoteMonsterDataSource
import com.example.shared.monster.api.data.datasource.RemoteMonsterDataSourceImpl
import com.example.shared.monster.api.data.repository.MonsterRepositoryImpl
import com.example.shared.monster.api.domain.repository.MonsterRepository
import com.example.shared.navigation.core.NavigationDestination
import com.example.shared.navigation.core.Navigator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoSet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module()
abstract class AppModule {

    companion object {
        @Provides
        @IoCoroutineScope
        fun provideIoCoroutineScope() = CoroutineScope(Dispatchers.IO)

        @Provides
        @Singleton
        fun provideMonsterDataBase(context: Context): MonsterDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                MonsterDatabase::class.java,
                "monster_database"
            )
                .fallbackToDestructiveMigration()
                .build()

        @Provides
        @Singleton
        fun provideMonsterDao(db: MonsterDatabase): MonsterDao =
            db.monsterDao()
    }

    @Binds
    @Singleton
    abstract fun bindLocalMonsterDataSource(localMonsterDataSourceImpl: LocalMonsterDataSourceImpl): LocalMonsterDataSource

    @Binds
    @Singleton
    abstract fun bindRemoteMonsterDataSource(remoteMonsterDataSourceImpl: RemoteMonsterDataSourceImpl): RemoteMonsterDataSource

    @Binds
    @Singleton
    abstract fun bindMonsterRepository(monsterRepositoryImpl: MonsterRepositoryImpl): MonsterRepository

    @Binds
    @Singleton
    abstract fun navigator(navigator: NavigatorImpl): Navigator

    @Binds
    @Singleton
    abstract fun bindMonsterListScreenRouter(router: MonsterListScreenRouterImpl): MonsterListScreenRouter

    @Binds
    @Singleton
    abstract fun bindMonsterDetailsRouter(router: MonsterDetailsRouterImpl): MonsterDetailsRouter

    @Binds
    @IntoSet
    @Singleton
    @Destination(MonsterListDestination::class)
    abstract fun bindMonsterListDestination(monsterListDestination: MonsterListDestination): NavigationDestination

    @Binds
    @IntoSet
    @Singleton
    @Destination(MonsterDetailsDestination::class)
    abstract fun bindMonsterDetailsDestination(monsterDetailsDestination: MonsterDetailsDestination): NavigationDestination

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}
