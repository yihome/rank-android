package sit.yihome.rankandroid

import android.app.Application
import dagger.Module
import dagger.Provides
import sit.yihome.rankandroid.injections.annotation.ApplicationScope


/**
 * Created by houyi on 2018/3/26.
 */
@Module
class AppModule(application: Application) {
    private var application: Application = application;

    @Provides
    @ApplicationScope
    fun provideApplication(): Application {
        return application
    }
}