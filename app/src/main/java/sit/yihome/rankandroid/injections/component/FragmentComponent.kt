package sit.yihome.rankandroid.injections.component

import dagger.Component
import sit.yihome.rankandroid.injections.module.FragmentModule
import sit.yihome.rankandroid.injections.annotation.ActivityScope
import sit.yihome.rankandroid.view.MainActivity

/**
 * Created by houyi on 13/04/18.
 */
@ActivityScope
@Component(modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(activity: MainActivity)
}