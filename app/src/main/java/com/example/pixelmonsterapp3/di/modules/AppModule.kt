package com.example.pixelmonsterapp3.di.modules

import android.content.Context
import androidx.room.Room
import com.example.monster_list.presentation.MonsterListScreenRouter
import com.example.pixelmonsterapp3.di.annotations.IoCoroutineScope
import com.example.pixelmonsterapp3.navigation.NavigatorImpl
import com.example.pixelmonsterapp3.routers.MonsterDetailsRouterImpl
import com.example.pixelmonsterapp3.routers.MonsterListScreenRouterImpl
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
        fun provideMonsterDataBase(context: Context): com.example.shared_monster_api.data.database.MonsterDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                com.example.shared_monster_api.data.database.MonsterDatabase::class.java,
                "monster_database"
            )
                .fallbackToDestructiveMigration()
                .build()

        @Provides
        fun provideMonsterDao(db: com.example.shared_monster_api.data.database.MonsterDatabase): com.example.shared_monster_api.data.database.MonsterDao =
            db.monsterDao()
    }

    @Binds
    abstract fun bindLocalMonsterDataSource(localMonsterDataSourceImpl: com.example.shared_monster_api.data.datasource.LocalMonsterDataSourceImpl): com.example.shared_monster_api.data.datasource.LocalMonsterDataSource

    @Binds
    abstract fun bindRemoteMonsterDataSource(remoteMonsterDataSourceImpl: com.example.shared_monster_api.data.datasource.RemoteMonsterDataSourceImpl): com.example.shared_monster_api.data.datasource.RemoteMonsterDataSource

    @Binds
    abstract fun bindMonsterRepository(monsterRepositoryImpl: com.example.shared_monster_api.data.repository.MonsterRepositoryImpl): com.example.shared_monster_api.domain.repository.MonsterRepository

    @Binds
    abstract fun navigator(navigator: NavigatorImpl): com.example.shared_navigation_core.Navigator

    @Binds
    abstract fun bindMonsterListScreenRouter(router: MonsterListScreenRouterImpl): MonsterListScreenRouter

    @Binds
    abstract fun bindMonsterDetailsRouter(router: MonsterDetailsRouterImpl): com.example.feature_monster_details.presentation.MonsterDetailsRouter

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}
