package sit.yihome.rankandroid.network

import dagger.Module
import dagger.Provides
import sit.yihome.rankandroid.injections.annotation.ApplicationScope
import sit.yihome.rankandroid.network.NetApi
import javax.inject.Singleton

/**
 * Created by houyi on 2018/3/19.
 */
@Module
class NetModule {

    @Singleton
    @Provides
    fun provideNetApi():NetApi{
        return NetApi()
    }
}