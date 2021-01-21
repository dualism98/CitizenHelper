package com.dualism.dotaheroes.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.dualism.dotaheroes.models.Hero
import com.dualism.dotaheroes.views.HeroListView
import kotlin.concurrent.thread
import android.os.Handler

@InjectViewState
class HeroListPresenter: MvpPresenter<HeroListView>() {
    fun fetchHeroes(){
        viewState.presentLoading()

        val handler = Handler()
        thread {
            Thread.sleep(3000)

            val mockData = ArrayList<Hero>()
            mockData.add(Hero(id = 0, title = "Anti-mage", icon = "", attackType = 0))
            mockData.add(Hero(id = 1, title = "Dark Willow", icon = "", attackType = 1))
            mockData.add(Hero(id = 2, title = "Lion", icon = "", attackType = 1))

            handler.post{
                viewState.presentHeroes(data = mockData)
            }
        }


    }
}