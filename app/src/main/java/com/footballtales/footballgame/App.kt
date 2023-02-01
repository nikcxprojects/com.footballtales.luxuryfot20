package com.footballtales.footballgame

import android.app.Application
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig

class App : Application()  {
    private val YANDEX_API_KEY = "b1e3ddc8-504b-4fcb-9373-3797e992b55c"

    companion object{
        private lateinit var myApp: App
    }

    override fun onCreate() {
        super.onCreate()
        myApp = this
        val config = YandexMetricaConfig.newConfigBuilder(YANDEX_API_KEY).build()
        YandexMetrica.activate(applicationContext, config)
        YandexMetrica.enableActivityAutoTracking(this)
    }

}