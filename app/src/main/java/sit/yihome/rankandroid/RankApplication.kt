package sit.yihome.rankandroid

import android.app.Application


/**
 * Created by houyi on 2018/3/23.
 */
class RankApplication: Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        app = this
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder().build()
    }


    fun getAppComponent(): AppComponent {
        return appComponent
    }

    companion object {
        private lateinit var app: RankApplication
        fun get(): RankApplication {
            return app
        }

        fun getAppCompent(): AppComponent {
            return app.getAppComponent()
        }
    }
}