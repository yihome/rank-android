package sit.yihome.rankandroid.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import sit.yihome.rankandroid.model.WelfareModel
import sit.yihome.rankandroid.viewmodel.bean.LiveUpdateWrapper
import sit.yihome.rankandroid.viewmodel.bean.WelfareBean
import javax.inject.Inject

/**
 * Created by houyi on 2018/3/15.
 */
class WelfareViewModel : ViewModel() {
    @Inject
    lateinit var welfareModel:WelfareModel

    lateinit var welfares:LiveData<LiveUpdateWrapper<WelfareBean>>
    fun init(){
        welfares = welfareModel.getWelfare()
    }

    fun getWelfare(): LiveData<LiveUpdateWrapper<WelfareBean>>{
        return welfares
    }

    fun loadMoreWelfare() {
        welfareModel.loadMoreWelfare(welfares as MutableLiveData<LiveUpdateWrapper<WelfareBean>>)
    }
}