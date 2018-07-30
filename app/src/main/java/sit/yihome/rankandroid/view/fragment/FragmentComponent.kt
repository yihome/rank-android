package sit.yihome.rankandroid.view.fragment

import android.app.Activity
import dagger.Component
import sit.yihome.rankandroid.view.MainActivity

/**
 * Created by houyi on 13/04/18.
 */
@Component(modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(activity: MainActivity)
}