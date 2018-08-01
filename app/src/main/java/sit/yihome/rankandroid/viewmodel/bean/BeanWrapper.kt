package sit.yihome.rankandroid.viewmodel.bean

/**
 * Created by houyi on 2018/3/16.
 */
class BeanWrapper<T>(){
    val error = false
    lateinit var results:MutableList<T>
}