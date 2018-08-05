package sit.yihome.rankandroid.view.fragment

import dagger.Module
import dagger.Provides
import sit.yihome.rankandroid.injections.annotation.ActivityScope

/**
 * Created by houyi on 13/04/18.
 */
@Module
class FragmentModule{

    @Provides
    fun provideWelfareFragment():WelfareFragment{
        return WelfareFragment()
    }

    @Provides
    fun provideMyFragment():MyFragment{
        return MyFragment()
    }
}