package com.zxn.mvvm.ext

import java.lang.reflect.ParameterizedType


/**
 * 获取当前类绑定的泛型ViewModel-clazz
 */
@Suppress("UNCHECKED_CAST")
fun <VM> getVmClazz(obj: Any): VM? {
    try {
        return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

fun <M> getNewInstance(obj: Any?, i: Int): M? {
    if (obj != null) {
        try {
            return ((obj.javaClass
                    .genericSuperclass as ParameterizedType).actualTypeArguments[i] as Class<M>)
                    .newInstance()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return null
}

///**
// * 在Activity中得到Application上下文的ViewModel
// */
//inline fun <reified VM : BaseViewModel> AppCompatActivity.getAppViewModel(): VM {
//    (this.application as? BaseApp).let {
//        if (it == null) {
//            throw NullPointerException("你的Application没有继承框架自带的BaseApp类，暂时无法使用getAppViewModel该方法")
//        } else {
//            return it.getAppViewModelProvider().get(VM::class.java)
//        }
//    }
//}
//
///**
// * 在Fragment中得到Application上下文的ViewModel
// * 提示，在fragment中调用该方法时，请在该Fragment onCreate以后调用或者请用by lazy方式懒加载初始化调用，不然会提示requireActivity没有导致错误
// */
//inline fun <reified VM : BaseViewModel> Fragment.getAppViewModel(): VM {
//    (this.requireActivity().application as? BaseApp).let {
//        if (it == null) {
//            throw NullPointerException("你的Application没有继承框架自带的BaseApp类，暂时无法使用getAppViewModel该方法")
//        } else {
//            return it.getAppViewModelProvider().get(VM::class.java)
//        }
//    }
//}
