package sit.yihome.rankandroid.view.helper

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import android.support.v7.widget.StaggeredGridLayoutManager



class WelFareItemDecoration(space: Int) : RecyclerView.ItemDecoration() {
    private val halfSpace: Int = space / 2

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)

    }

}