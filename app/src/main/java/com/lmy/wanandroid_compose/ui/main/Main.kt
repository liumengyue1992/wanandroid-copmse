package com.lmy.wanandroid_compose.ui.main

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lmy.wanandroid_compose.R
import com.lmy.wanandroid_compose.ui.home.Home
import com.lmy.wanandroid_compose.ui.mine.Mine
import com.lmy.wanandroid_compose.ui.project.Project
import com.lmy.wanandroid_compose.ui.square.Square

/**
 * @description：首页
 * 通过脚手架Scaffold构建。底部是一个BottomNavigation。
 * @author：Mengyue.Liu
 * @time： 2022/5/27 11:00
 */

@Composable
fun Main() {
    val navController = rememberNavController()
    val bottomItems = listOf(Route.Home, Route.Square, Route.Project, Route.Mine)
    // tab未选图标
    val icons = listOf(R.mipmap.home,R.mipmap.home,R.mipmap.home,R.mipmap.home)
    // tab选中图标
    // val selectIcons = listOf(R.drawable.homeselect,R.drawable.findselect,R.drawable.profileselect)
    var selectIndex by remember { mutableStateOf(0) }
    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigation(backgroundColor = Color.Blue) {
                // 获取当前的 NavBackStackEntry,此条目允许您访问当前的 NavDestination
                // 通过 hierarchy 辅助方法将该项的路由与当前目的地及其父目的地的路由进行比较来确定每个 BottomNavigationItem 的选定状态（以处理使用嵌套导航的情况）。
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination
                bottomItems.forEachIndexed { index, route ->
                    BottomNavigationItem(selected = currentRoute?.hierarchy?.any { it.route == route.routeName } == true,
                        onClick = {
                            selectIndex = index
                            // 根据routeName导航到指定页面
                            navController.navigate(route.routeName) {
                                //当底部导航导航到在非首页的页面时，执行手机的返回键回到首页
                                popUpTo(navController.graph.startDestDisplayName)
                                // 避免重新选择同一个导航
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        },
                        icon = {
                            when (index) {
                                selectIndex -> Icon(painter = painterResource(icons[index]), contentDescription = "")
                                else -> {
                                }
                            }
                            
                        },
                        label = {
                            Text(text = stringResource(id = route.resId))
                        })
                }
            }
        }, content = {
            NavHost(navController, startDestination = Route.Home.routeName) {
                composable(Route.Home.routeName) {
                    Home(navController)
                }
                composable(Route.Square.routeName) {
                    Square(navController)
                }
                composable(Route.Project.routeName) {
                    Project(navController)
                }
                composable(Route.Mine.routeName) {
                    Mine(navController)
                }
            }
        })
}

/**
 * 官方建议使用密封类和导航来进行底部切换
 * @property routeName String
 * @property resId Int
 * @constructor
 */
sealed class Route(val routeName: String, @StringRes val resId: Int) {
    object Home : Route("home", R.string.home)
    object Square : Route("square", R.string.square)
    object Project : Route("project", R.string.project)
    object Mine : Route("mine", R.string.mine)
}