package sit.yihome.rankandroid.injections

import dagger.Component
import dagger.Subcomponent
import sit.yihome.rankandroid.network.NetModule
import sit.yihome.rankandroid.receiver.ImageWidgetProvider
import sit.yihome.rankandroid.viewmodel.WelfareViewModel
import javax.inject.Singleton

/**
 * Created by houyi on 2018/3/16.
 */
@Singleton
@Component(modules = [WelFareModule::class,NetModule::class])
interface WelfareComponent {
    fun inject(viewModel: WelfareViewModel)
    fun inject(container:ImageWidgetProvider.WidgetImageContainer)
}