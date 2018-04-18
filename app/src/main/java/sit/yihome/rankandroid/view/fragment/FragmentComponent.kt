package sit.yihome.rankandroid.view.fragment

import android.app.Activity
import dagger.Component

/**
 * Created by houyi on 13/04/18.
 */
@Component(modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(activity: FragmentFactory)
}