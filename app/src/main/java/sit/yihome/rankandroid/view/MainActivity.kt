package sit.yihome.rankandroid.view

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.orhanobut.logger.Logger
import dagger.Lazy
import kotlinx.android.synthetic.main.activity_main.*
import sit.yihome.rankandroid.R
import sit.yihome.rankandroid.view.fragment.DaggerFragmentComponent
import sit.yihome.rankandroid.view.fragment.MyFragment
import sit.yihome.rankandroid.view.fragment.WelfareFragment
import sit.yihome.rankandroid.viewmodel.MY_PAGE
import sit.yihome.rankandroid.viewmodel.MainViewModel
import sit.yihome.rankandroid.viewmodel.WELFARE_PAGE
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var welfareFragment: Lazy<WelfareFragment>
    @Inject
    lateinit var myFragment: Lazy<MyFragment>
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerFragmentComponent.builder().build().inject(this)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        initFragment()
        initTab()
    }

    private fun initFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.layout_container, getFragment(WELFARE_PAGE), getTag(WELFARE_PAGE))
        transaction.add(R.id.layout_container, getFragment(MY_PAGE), getTag(MY_PAGE))
        transaction.commit()
    }

    private fun initTab() {
        tablayout_main_bottom.addTab(getString(R.string.welfare), R.drawable.selector_tabitem_movie)
        tablayout_main_bottom.addTab(getString(R.string.welfare), R.drawable.selector_tabitem_movie)
        tablayout_main_bottom.addTab(getString(R.string.welfare), R.drawable.selector_tabitem_movie)
        tablayout_main_bottom.addTab(getString(R.string.my), R.drawable.selector_tabitem_personal)
        tablayout_main_bottom.setSelectChangeListener({ currentPosition, lastPosition, isChanged ->
            Logger.d("currentPosition: $currentPosition--lastPosition: $lastPosition " +
                    "isChange: $isChanged")
            if(isChanged) {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.show(getFragment(currentPosition))
                transaction.hide(supportFragmentManager.findFragmentByTag(getTag(lastPosition)))
                transaction.commit()
            }
        })
        tablayout_main_bottom.initSelection(WELFARE_PAGE)
    }

    private fun getTag(currentPosition: Int): String? {
        return when (currentPosition) {
            WELFARE_PAGE -> "welfare"
            MY_PAGE -> "my"
            else -> ""
        }
    }

    private fun getFragment(currentPosition: Int): Fragment? {
        return when (currentPosition) {
            WELFARE_PAGE -> welfareFragment.get()
            MY_PAGE -> myFragment.get()
            else -> null
        }
    }

}
