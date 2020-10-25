import androidx.lifecycle.ViewModel

/**
 * ViewModel的基类 使用ViewModel类，放弃AndroidViewModel，原因：用处不大 完全有其他方式获取Application上下文
 */
abstract class BaseViewModel : ViewModel() {

//    /**
//     * 数据清除的时候回调次函数.
//     */
//    override fun onCleared() {
//        super.onCleared()
//    }


//    val loadingChange: UiLoadingChange by lazy { UiLoadingChange() }

//    inner class UiLoadingChange {
//        //显示加载框
//        val showDialog by lazy { EventLiveData<String>() }
//        //隐藏
//        val dismissDialog by lazy { EventLiveData<Boolean>() }
//    }

}