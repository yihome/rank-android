package sit.yihome.rankandroid

import dagger.Component
import sit.yihome.rankandroid.injections.WelFareModule
import sit.yihome.rankandroid.injections.WelfareComponent
import sit.yihome.rankandroid.injections.annotation.ApplicationScope
import sit.yihome.rankandroid.network.NetModule

/**
 * Created by houyi on 2018/3/26.
 */
@ApplicationScope
@Component(modules = [NetModule::class])
interface AppComponent {
    fun get(welFareModule: WelFareModule): WelfareComponent

}