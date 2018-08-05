package sit.yihome.rankandroid.injections

import dagger.Module
import dagger.Provides
import sit.yihome.rankandroid.injections.annotation.ActivityScope
import sit.yihome.rankandroid.injections.annotation.ApplicationScope
import sit.yihome.rankandroid.network.NetApi
import sit.yihome.rankandroid.repository.WelfareRepository
import javax.inject.Singleton

/**
 * Created by houyi on 2018/3/15.
 */
@Module
class WelFareModule {
    @Singleton
    @Provides
    fun provideWelfareRepository(netApi: NetApi): WelfareRepository {
        return WelfareRepository(netApi)
    }
}