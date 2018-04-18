package sit.yihome.rankandroid.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import sit.yihome.rankandroid.RankApplication
import sit.yihome.rankandroid.injections.WelFareModule
import sit.yihome.rankandroid.repository.WelfareRepository
import sit.yihome.rankandroid.viewmodel.bean.WelfareBean
import javax.inject.Inject

/**
 * Created by houyi on 2018/3/15.
 */
class WelfareViewModel : ViewModel() {
    @Inject
    lateinit var welfareRepository:WelfareRepository

    lateinit var welfares:LiveData<List<WelfareBean>>
    fun init(){
        RankApplication.getAppCompent().get(WelFareModule()).inject(this)
        welfares = welfareRepository.getWelfare()
    }

    fun getWelfare(): LiveData<List<WelfareBean>>{
        return welfares
    }
}