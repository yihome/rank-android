package sit.yihome.rankandroid.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
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
import sit.yihome.rankandroid.viewmodel.WelfareViewModel
import sit.yihome.rankandroid.viewmodel.bean.WelfareBean

/**
 * Created by houyi on 2018/4/10.
 */
class WelfareFragment: Fragment(){
    private lateinit var viewmodel: WelfareViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_welfare, null)

        return view!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler_view.adapter = WelfareAdater()
        recycler_view.layoutManager = StaggeredGridLayoutManager(2,RecyclerView.VERTICAL )

        viewmodel = ViewModelProviders.of(this).get(WelfareViewModel::class.java)
        viewmodel.init()
        viewmodel.getWelfare().observe(this, Observer<List<WelfareBean>> { welfares ->

            (recycler_view.adapter as WelfareAdater).setWelfareList(welfares)
            recycler_view.post {
                recycler_view.scrollToPosition(0);
            }
        })
    }
}