package sit.yihome.rankandroid.injections.module

import dagger.Module
import dagger.Provides
import sit.yihome.rankandroid.injections.annotation.ActivityScope
import sit.yihome.rankandroid.view.fragment.MyFragment
import sit.yihome.rankandroid.view.fragment.WelfareFragment

/**
 * Created by houyi on 13/04/18.
 */
@Module
class FragmentModule{

    @ActivityScope
    @Provides
    fun provideWelfareFragment(): WelfareFragment {
        return WelfareFragment()
    }

    @ActivityScope
    @Provides
    fun provideMyFragment(): MyFragment {
        return MyFragment()
    }
}