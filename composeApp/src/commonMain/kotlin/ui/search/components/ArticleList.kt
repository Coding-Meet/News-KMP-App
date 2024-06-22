//package ui.search.components
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.paging.LoadState
//import androidx.paging.compose.LazyPagingItems
//import com.zeph7.newsapp.R
//import ui.common.EmptyContent
//import theme.getXLargePadding
//import theme.getXSmallPadding
//import com.zeph7.newsapp.feature_news.domain.model.Article
//import ui.common.ArticleItem
//import java.net.ConnectException
//import java.net.SocketTimeoutException
//
//@Composable
//fun ArticlesList(
//    modifier: Modifier = Modifier,
//    articles: LazyPagingItems<Article>,
//    onClick: (Article) -> Unit
//) {
//    LazyColumn(
//        modifier = modifier.fillMaxSize(),
//        contentPadding = PaddingValues(xLargePadding),
//        verticalArrangement = Arrangement.spacedBy(xLargePadding)
//    ) {
//        items(articles.itemCount) {
//            articles[it]?.let { article ->
//                ArticleItem(
//                    article = article,
//                    onClick = { onClick(article) }
//                )
//            }
//        }
//        item {
//            HandlePagingResult(
//                items = articles
//            )
//        }
//    }
//}
//
//@Composable
//fun <T: Any>HandlePagingResult(items: LazyPagingItems<T>) {
//    val loadState = items.loadState
//
//    val error = when {
//        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
//        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
//        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
//        else -> null
//    }
//    val message = when (error?.error) {
//        is SocketTimeoutException -> "Server Unavailable!"
//        is ConnectException -> "Internet Unavailable!"
//        else -> "Unknown Error!"
//    }
//
//    when {
//        loadState.refresh is LoadState.Loading -> {
//            Box(
//                modifier = Modifier.fillMaxSize(),
//                contentAlignment = Alignment.Center,
//                content = { CircularProgressIndicator() },
//            )
//        }
//
//        loadState.append is LoadState.Loading -> {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(xSmallPadding),
//                contentAlignment = Alignment.Center,
//                content = { CircularProgressIndicator() },
//            )
//        }
//
//        loadState.refresh is LoadState.Error -> {
//            EmptyContent(
//                message = message,
//                icon = Res.drawable.ic_network_error,
//                onRetryClick = { items.retry() },
//            )
//        }
//
//        loadState.append is LoadState.Error || loadState.prepend is LoadState.Error -> {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(xSmallPadding),
//                contentAlignment = Alignment.Center,
//            ) {
//                IconButton(onClick = { items.retry() }) {
//                    Icon(
//                        modifier = Modifier.size(36.dp),
//                        painter = painterResource(Res.drawable.ic_retry),
//                        contentDescription = null,
//                    )
//                }
//            }
//        }
//    }
//}