package com.example.pixelmonsterapp3.di

import com.example.feature_monster_details.presentation.MonsterDetailsViewModel
import com.example.monster_list.presentation.MonsterListViewModel
import javax.inject.Inject

class ViewModelFactories @Inject constructor(
    val monsterListViewModelAssistedFactory: MonsterListViewModel.MonsterListFactory.MonsterListAssistedFactory,
    val monsterDetailsViewModelAssistedFactory: MonsterDetailsViewModel.MonsterDetailsFactory.MonsterDetailsAssistedFactory,
)
