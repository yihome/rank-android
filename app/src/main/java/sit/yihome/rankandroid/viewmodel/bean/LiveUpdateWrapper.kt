package sit.yihome.rankandroid.viewmodel.bean

class LiveUpdateWrapper<T : Comparable<T>> constructor(totalList: MutableList<T>?) {
    private var totalList = totalList
    private var position = 0
    private var count = 0

    var isUpdate: Boolean = false

    fun addLast(addList: MutableList<T>?) {
        if (addList == null) {
            return
        }
        if (totalList == null) {
            totalList = addList
            return
        }
        isUpdate = true
        position = totalList!!.lastIndex
        count = addList.size
        totalList!!.addAll(addList)
    }

    fun refreshFirst(refreshList: MutableList<T>) {

    }

    fun totalchange(totalList: MutableList<T>) {

    }

    fun getTotalList(): MutableList<T>? {
        return totalList
    }

    fun getInsertPosition(): Int {
        return position
    }

    fun getInsertCount(): Int {
        return count
    }

}