package sit.yihome.rankandroid.injections.component

import dagger.Component
import dagger.Subcomponent
import sit.yihome.rankandroid.injections.module.WelFareModule
import sit.yihome.rankandroid.injections.annotation.ActivityScope
import sit.yihome.rankandroid.injections.module.FragmentModule
import sit.yihome.rankandroid.injections.module.WelfareVMModule
import sit.yihome.rankandroid.receiver.ImageWidgetProvider
import sit.yihome.rankandroid.view.MainActivity
import sit.yihome.rankandroid.viewmodel.WelfareViewModel

/**
 * Created by houyi on 2018/3/16.
 */
@ActivityScope
@Subcomponent(modules = [WelfareVMModule::class,FragmentModule::class])
interface WelfareComponent {
    fun inject(activity: MainActivity)
    fun inject(container:ImageWidgetProvider.WidgetImageContainer)
}