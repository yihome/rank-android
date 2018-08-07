package sit.yihome.rankandroid.injections.component

import dagger.Component
import sit.yihome.rankandroid.injections.annotation.ApplicationScope
import sit.yihome.rankandroid.injections.module.NetModule
import sit.yihome.rankandroid.injections.module.WelFareModule
import sit.yihome.rankandroid.injections.module.WelfareVMModule
import sit.yihome.rankandroid.receiver.ImageWidgetProvider

/**
 * Created by houyi on 2018/3/26.
 */
@ApplicationScope
@Component(modules = [WelFareModule::class,NetModule::class])
interface AppComponent {
    fun get(welFareModule: WelfareVMModule): WelfareComponent
    fun inject(widgetImageContainer: ImageWidgetProvider.WidgetImageContainer)

}