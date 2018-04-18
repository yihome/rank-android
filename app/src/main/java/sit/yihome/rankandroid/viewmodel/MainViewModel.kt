package sit.yihome.rankandroid.viewmodel

import android.arch.lifecycle.ViewModel

/**
 * Created by houyi on 13/04/18.
 */
class MainViewModel : ViewModel(){
    var currentPage = WELFARE_PAGE


    companion object {
        const val WELFARE_PAGE = 0
    }
}