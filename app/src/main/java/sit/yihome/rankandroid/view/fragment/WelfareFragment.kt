package sit.yihome.rankandroid.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_welfare.*
import sit.yihome.rankandroid.R
import sit.yihome.rankandroid.view.adapter.WelfareAdater
import sit.yihome.rankandroid.view.helper.WelFareItemDecoration
import sit.yihome.rankandroid.viewmodel.WelfareViewModel
import sit.yihome.rankandroid.viewmodel.bean.LiveUpdateWrapper
import sit.yihome.rankandroid.viewmodel.bean.WelfareBean
import javax.inject.Inject

/**
 * Created by houyi on 2018/4/10.
 */
class WelfareFragment : Fragment() {
    @Inject
    lateinit var viewmodel: WelfareViewModel

    private var isLoading: Boolean = false

    companion object {
        const val SPAN_COUNT = 2
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_welfare, null)
        return view!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        initViewModel()
    }

    private fun initViewModel() {
        viewmodel.init()
        viewmodel.getWelfare().observe(this, Observer<LiveUpdateWrapper<WelfareBean>> {

            (recycler_view.adapter as WelfareAdater).setWelfareList(it)
            isLoading = false
        })
    }

    private fun initView() {
        recycler_view.adapter = WelfareAdater()
        recycler_view.layoutManager = StaggeredGridLayoutManager(SPAN_COUNT, RecyclerView.VERTICAL)
        recycler_view.addItemDecoration(WelFareItemDecoration(2))
        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                val position = IntArray(SPAN_COUNT * 2)
                (recyclerView!!.layoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(position)
                if (recyclerView.adapter.itemCount - 4 < position[0] && !isLoading) {
                    isLoading = true
                    viewmodel.loadMoreWelfare()
                }
            }
        })
    }
}