package com.example.pixelmonsterapp3.di

import com.example.feature.monster.details.presentation.MonsterDetailsViewModel
import com.example.feature.monster.list.presentation.MonsterListViewModel
import javax.inject.Inject

class ViewModelFactories @Inject constructor(
    val monsterListViewModelAssistedFactory: MonsterListViewModel.MonsterListFactory.MonsterListAssistedFactory,
    val monsterDetailsViewModelAssistedFactory: MonsterDetailsViewModel.MonsterDetailsFactory.MonsterDetailsAssistedFactory,
)
