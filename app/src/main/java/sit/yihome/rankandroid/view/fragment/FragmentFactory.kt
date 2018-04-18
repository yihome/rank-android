package sit.yihome.rankandroid.view.fragment

/**
 * Created by houyi on 13/04/18.
 */
class FragmentFactory{
    init {
        DaggerFragmentComponent.builder().build().inject(this)
    }
}