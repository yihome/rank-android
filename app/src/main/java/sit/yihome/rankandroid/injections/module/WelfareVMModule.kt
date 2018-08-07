package sit.yihome.rankandroid.injections.module

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides
import sit.yihome.rankandroid.injections.annotation.ActivityScope
import sit.yihome.rankandroid.injections.annotation.FragmentScope
import sit.yihome.rankandroid.viewmodel.WelfareViewModel

@Module
class WelfareVMModule(private val fragment: Fragment){

    @ActivityScope
    @Provides
    fun provideWelfareViewModel(): WelfareViewModel{
        return ViewModelProviders.of(fragment).get(WelfareViewModel::class.java)
    }
}