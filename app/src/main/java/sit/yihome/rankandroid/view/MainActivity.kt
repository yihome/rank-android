package sit.yihome.rankandroid.view

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import sit.yihome.rankandroid.R
import sit.yihome.rankandroid.view.fragment.DaggerFragmentComponent
import sit.yihome.rankandroid.view.fragment.FragmentModule
import sit.yihome.rankandroid.view.fragment.WelfareFragment
import sit.yihome.rankandroid.view.widget.TabLayout
import sit.yihome.rankandroid.viewmodel.MainViewModel
import sit.yihome.rankandroid.viewmodel.WelfareViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
//    @Inject
//    private lateinit var welfareFragment:Lazy<WelfareViewModel>
    @Inject
    lateinit var fragment:WelfareFragment
    private lateinit var viewModel:MainViewModel
//    @Inject lateinit var fragment: WelfareFragment
    private val welfareClickListener = View.OnClickListener {
//        switchFragment(viewModel.currentPage,MainViewModel.WELFARE_PAGE)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.layout_container,fragment)
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerFragmentComponent.builder().build().inject(this)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        initTab()
    }

    private fun initTab() {
        tablayout_main_bottom.addTab(
                TabLayout.TabItem(getString(R.string.welfare),R.drawable.selector_tabitem_movie, welfareClickListener))
        tablayout_main_bottom.addTab(TabLayout.TabItem(getString(R.string.welfare),R.drawable.selector_tabitem_movie))
        tablayout_main_bottom.addTab(TabLayout.TabItem(getString(R.string.welfare),R.drawable.selector_tabitem_movie))
        tablayout_main_bottom.addTab(TabLayout.TabItem(getString(R.string.welfare),R.drawable.selector_tabitem_movie))
        tablayout_main_bottom.initSelection(viewModel.currentPage)
    }

    private fun switchFragment(currentPage: Int, nextPage: Int) {

    }

}
