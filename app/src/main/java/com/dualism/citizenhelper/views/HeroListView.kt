package com.dualism.dotaheroes.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.dualism.dotaheroes.models.Hero

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface HeroListView: MvpView {
    fun presentHeroes(data: List<Hero>)
    fun presentLoading()
}