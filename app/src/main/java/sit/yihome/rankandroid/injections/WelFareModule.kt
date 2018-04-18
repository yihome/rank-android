package sit.yihome.rankandroid.injections

import dagger.Module
import dagger.Provides
import sit.yihome.rankandroid.network.NetApi
import sit.yihome.rankandroid.repository.WelfareRepository

/**
 * Created by houyi on 2018/3/15.
 */
@Module
class WelFareModule {
    @Provides
    fun provideWelfareRepository(netApi: NetApi): WelfareRepository {
        return WelfareRepository(netApi)
    }
}