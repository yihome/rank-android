package sit.yihome.rankandroid

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import sit.yihome.rankandroid.injections.DaggerWelfareComponent
import sit.yihome.rankandroid.injections.WelfareComponent


/**
 * Created by houyi on 2018/3/23.
 */
class RankApplication: Application() {
    private lateinit var appComponent: AppComponent
    private lateinit var welfareComponent: WelfareComponent

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
        welfareComponent = DaggerWelfareComponent.builder().build()
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }

    fun getWelfareComponent(): WelfareComponent {
        return welfareComponent
    }

    companion object {
        private lateinit var app: RankApplication
        fun get(): RankApplication {
            return app
        }

        fun getAppComponent(): AppComponent {
            return app.getAppComponent()
        }

        fun getWelfareComponent(): WelfareComponent {
            return app.getWelfareComponent()
        }
    }
}