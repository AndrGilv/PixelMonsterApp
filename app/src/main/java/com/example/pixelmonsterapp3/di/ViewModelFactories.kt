package com.example.pixelmonsterapp3.di

import com.example.pixelmonsterapp3.presentation.monsterdetails.MonsterDetailsViewModel
import com.example.pixelmonsterapp3.presentation.monsterlist.MonsterListViewModel
import javax.inject.Inject

class ViewModelFactories @Inject constructor(
    val monsterListViewModelAssistedFactory: MonsterListViewModel.MonsterListFactory.MonsterListAssistedFactory,
    val monsterDetailsViewModelAssistedFactory: MonsterDetailsViewModel.MonsterDetailsFactory.MonsterDetailsAssistedFactory,
) {
}