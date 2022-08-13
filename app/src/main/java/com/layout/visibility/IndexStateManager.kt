package com.layout.visibility

import android.view.View
import androidx.core.view.isVisible
import kotlin.random.Random

/**
 * @ClassName: IndexStateManager
 * @Description: 首页状态管理类
 * @Author: Nicholas.hzf
 * @Date: 2022/8/13 18:01 Created
 */
object IndexStateManager {

    private val mViewMap by lazy {
        HashMap<Int, View>()
    }

    private const val VIEW_SIZE = 11
    private const val INDEX = 1
    const val INDEX_VIEW_AUTHOR_NAME :Int = INDEX shl 0
    const val INDEX_VIEW_AUTHOR_INTRODUCTION :Int = INDEX shl 1
    const val INDEX_VIEW_TOOL_BOX :Int = INDEX shl 2
    const val INDEX_VIEW_FOLDER :Int = INDEX shl 3
    const val INDEX_VIEW_ZOOM_IN :Int = INDEX shl 4
    const val INDEX_VIEW_ZOOM_OUT :Int = INDEX shl 5
    const val INDEX_VIEW_CLOSE :Int = INDEX shl 6
    const val INDEX_VIEW_ANDROID :Int = INDEX shl 7
    const val INDEX_VIEW_TAB_FIRST :Int = INDEX shl 8
    const val INDEX_VIEW_TAB_SECOND :Int = INDEX shl 9
    const val INDEX_VIEW_TAB_THIRD :Int = INDEX shl 10

    const val PRIMARY_STATE =
        INDEX_VIEW_AUTHOR_NAME or
        INDEX_VIEW_AUTHOR_INTRODUCTION or
        INDEX_VIEW_TOOL_BOX or
        INDEX_VIEW_FOLDER or
        INDEX_VIEW_ZOOM_IN or
        INDEX_VIEW_ZOOM_OUT or
        INDEX_VIEW_ANDROID or
        INDEX_VIEW_TAB_FIRST or
        INDEX_VIEW_TAB_SECOND or
        INDEX_VIEW_TAB_THIRD

    const val INDEX_FULL_SCREEN =
        INDEX_VIEW_AUTHOR_NAME or INDEX_VIEW_AUTHOR_INTRODUCTION

    const val INDEX_CONCISE_MODE =
        INDEX_VIEW_AUTHOR_NAME or
                INDEX_VIEW_AUTHOR_INTRODUCTION or
                INDEX_VIEW_TAB_FIRST or
                INDEX_VIEW_TAB_SECOND or
                INDEX_VIEW_TAB_THIRD

    private var CURRENT_STATE = PRIMARY_STATE

    fun initViewMap(viewList: List<View>){
        if (viewList.size != VIEW_SIZE){
            throw Exception("View 数量错误")
        }
        mViewMap.clear()
        CURRENT_STATE = PRIMARY_STATE

        mViewMap[INDEX_VIEW_AUTHOR_NAME] = viewList[0]
        mViewMap[INDEX_VIEW_AUTHOR_INTRODUCTION] = viewList[1]
        mViewMap[INDEX_VIEW_TOOL_BOX] = viewList[2]
        mViewMap[INDEX_VIEW_FOLDER] = viewList[3]
        mViewMap[INDEX_VIEW_ZOOM_IN] = viewList[4]
        mViewMap[INDEX_VIEW_ZOOM_OUT] = viewList[5]
        mViewMap[INDEX_VIEW_CLOSE] = viewList[6]
        mViewMap[INDEX_VIEW_ANDROID] = viewList[7]
        mViewMap[INDEX_VIEW_TAB_FIRST] = viewList[8]
        mViewMap[INDEX_VIEW_TAB_SECOND] = viewList[9]
        mViewMap[INDEX_VIEW_TAB_THIRD] = viewList[10]

        updateViews()
    }

    fun updateViews(){
        mViewMap.keys.forEach { key ->
            mViewMap[key]?.isVisible = (key and CURRENT_STATE) == key
        }
    }

    fun destroyViews(){
        mViewMap.clear()
        CURRENT_STATE = 0
    }

    fun showView(view: Int){
        CURRENT_STATE = CURRENT_STATE or view
        updateViews()
    }

    fun hideView(view: Int){
        CURRENT_STATE = CURRENT_STATE xor view
        updateViews()
    }

    fun changeState(state: Int){
        CURRENT_STATE = state
        updateViews()
    }

    fun changeStateRandom(){
        val random = Random.nextInt(3)
        CURRENT_STATE = when(random){
            0 -> PRIMARY_STATE
            1 -> INDEX_FULL_SCREEN
            2 -> INDEX_CONCISE_MODE
            else -> PRIMARY_STATE
        }
        updateViews()
    }

    fun getCurrentState() = CURRENT_STATE

}