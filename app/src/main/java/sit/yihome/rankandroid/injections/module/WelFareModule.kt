package sit.yihome.rankandroid.injections.module

import dagger.Module
import dagger.Provides
import sit.yihome.rankandroid.injections.annotation.ApplicationScope
import sit.yihome.rankandroid.network.NetApi
import sit.yihome.rankandroid.model.WelfareModel

/**
 * Created by houyi on 2018/3/15.
 */
@Module
class WelFareModule {
    @ApplicationScope
    @Provides
    fun provideWelfareRepository(netApi: NetApi): WelfareModel {
        return WelfareModel(netApi)
    }
}