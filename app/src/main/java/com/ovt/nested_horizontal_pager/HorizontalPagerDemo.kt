package com.ovt.nested_horizontal_pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScrollableTabRowSimple() {
    val scope = rememberCoroutineScope()
    val titles = remember {
        mutableStateListOf("掘金小册", "字节内部课")
    }
    val pagerState = rememberPagerState(pageCount = { titles.size })
    Column {
        ScrollableTabRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            selectedTabIndex = pagerState.currentPage,
            contentColor = Color.Black,
            divider = {},
            indicator = {
            }
        ) {
            titles.forEachIndexed { index, data ->
                val selected = pagerState.currentPage == index
                Box(
                    Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                        .clickable {
                            scope.launch {
                                //Tab被点击后让Pager中内容动画形式滑动到目标页
                                pagerState.scrollToPage(index, 0f)
                            }
                        }, contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = data,
                        fontSize = if (selected) 18.sp else 16.sp,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                        color = if (selected) MaterialTheme.colorScheme.primary else Color.Black
                    )
                }
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxHeight()
        ) { pagePosition ->
            SecondPager()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SecondPager() {
    val data = remember {
        mutableStateListOf("Android", "IOS", "人工智能", "开发人员", "代码人生", "阅读", "购买")
    }
    val pagerState = rememberPagerState(pageCount = { data.size })
    val scope = rememberCoroutineScope()
    Column {
        ScrollableTabRow(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            selectedTabIndex = pagerState.currentPage,
            contentColor = Color.Black,
            edgePadding = 10.dp,
            divider = {},
            /*minTabWidth = 76.dp,
            indicator = { tabPositions ->
                if (tabPositions.isNotEmpty()) {
                    PagerTabIndicator(
                        tabPositions = tabPositions,
                        pagerState = pagerState,
                        paddingIndicatorWidth = 35.dp
                    )
                }
            }*/
        ) {
            data.forEachIndexed { index, data ->
                val selected = pagerState.currentPage == index
                Box(
                    Modifier
                        .height(40.dp)
                        .wrapContentWidth()
                        .clickable {
                            scope.launch {
                                pagerState.scrollToPage(index, 0f)//Tab被点击后让Pager中内容动画形式滑动到目标页
                            }
                        }, contentAlignment = Alignment.Center

                ) {
                    Text(
                        text = data, fontSize = 13.sp,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                        color = if (selected) MaterialTheme.colorScheme.primary else Color.Black
                    )
                }
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxHeight()
        ) { pagePosition ->
            Box(
                Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "页面：：=${pagePosition}")
            }
        }


    }
}
