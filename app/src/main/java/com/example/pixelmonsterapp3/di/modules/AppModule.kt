package com.example.pixelmonsterapp3.di.modules

import android.content.Context
import androidx.room.Room
import com.example.feature.monster.details.presentation.MonsterDetailsRouter
import com.example.feature.monster.list.presentation.MonsterListScreenRouter
import com.example.pixelmonsterapp3.di.annotations.IoCoroutineScope
import com.example.pixelmonsterapp3.navigation.NavigatorImpl
import com.example.pixelmonsterapp3.routers.MonsterDetailsRouterImpl
import com.example.pixelmonsterapp3.routers.MonsterListScreenRouterImpl
import com.example.pixelmonsterapp3.ui.MainActivity
import com.example.shared.navigation.core.Navigator
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
        fun provideMonsterDataBase(context: Context): com.example.shared.monster.api.data.database.MonsterDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                com.example.shared.monster.api.data.database.MonsterDatabase::class.java,
                "monster_database"
            )
                .fallbackToDestructiveMigration()
                .build()

        @Provides
        fun provideMonsterDao(db: com.example.shared.monster.api.data.database.MonsterDatabase): com.example.shared.monster.api.data.database.MonsterDao =
            db.monsterDao()
    }

    @Binds
    abstract fun bindLocalMonsterDataSource(localMonsterDataSourceImpl: com.example.shared.monster.api.data.datasource.LocalMonsterDataSourceImpl): com.example.shared.monster.api.data.datasource.LocalMonsterDataSource

    @Binds
    abstract fun bindRemoteMonsterDataSource(remoteMonsterDataSourceImpl: com.example.shared.monster.api.data.datasource.RemoteMonsterDataSourceImpl): com.example.shared.monster.api.data.datasource.RemoteMonsterDataSource

    @Binds
    abstract fun bindMonsterRepository(monsterRepositoryImpl: com.example.shared.monster.api.data.repository.MonsterRepositoryImpl): com.example.shared.monster.api.domain.repository.MonsterRepository

    @Binds
    abstract fun navigator(navigator: NavigatorImpl): Navigator

    @Binds
    abstract fun bindMonsterListScreenRouter(router: MonsterListScreenRouterImpl): MonsterListScreenRouter

    @Binds
    abstract fun bindMonsterDetailsRouter(router: MonsterDetailsRouterImpl): MonsterDetailsRouter

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}
