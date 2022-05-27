package com.lmy.wanandroid_compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lmy.wanandroid_compose.ui.main.Main

/**
 * @description：
 *  navigation的使用：
 *  1.通过rememberNavController()方法创建navController对象
 *  2.创建NavHost对象，传入navController并指定首页
 *  3.通过composable()方法来往NavHost中添加页面，构造方法中的字符串就代表该页面的路径，后面的第二个参数就是具体的页面
 * @author：Mengyue.Liu
 * @time： 2022/5/27 10:41
 */

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main"){
            Main()
        }
    }
}

