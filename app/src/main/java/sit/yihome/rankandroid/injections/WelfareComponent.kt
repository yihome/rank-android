package sit.yihome.rankandroid.injections

import dagger.Component
import dagger.Subcomponent
import sit.yihome.rankandroid.viewmodel.WelfareViewModel
import javax.inject.Singleton

/**
 * Created by houyi on 2018/3/16.
 */
@Subcomponent(modules = [WelFareModule::class])
interface WelfareComponent {
    fun inject(viewModel: WelfareViewModel)

}