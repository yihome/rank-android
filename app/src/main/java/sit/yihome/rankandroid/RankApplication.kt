package sit.yihome.rankandroid

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy


/**
 * Created by houyi on 2018/3/23.
 */
class RankApplication: Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        app = this
        initAppComponent()
        initLogger()
    }

    private fun initLogger() {
        var formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(0)
                .methodOffset(7)
                .tag("Rank")
                .build()
        Logger.addLogAdapter(object:AndroidLogAdapter(formatStrategy){
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
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