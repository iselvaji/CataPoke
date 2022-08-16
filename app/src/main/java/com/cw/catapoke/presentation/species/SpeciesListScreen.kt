package com.cw.catapoke.presentation.species

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyGridScope
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.cw.catapoke.R
import com.cw.catapoke.domain.model.SpeciesResult
import com.cw.catapoke.presentation.common.ComposeUtil
import com.cw.catapoke.presentation.common.ComposeUtil.LoadImage
import com.cw.catapoke.presentation.common.ComposeUtil.LoadingProgress
import com.cw.catapoke.presentation.common.ConnectivityObserver
import com.cw.catapoke.presentation.common.NetworkConnectivityObserver
import com.cw.catapoke.utils.AppUtil
import com.cw.catapoke.utils.Constants.TEST_TAG_GRID
import com.cw.catapoke.utils.Constants.TEST_TAG_SPECIES_NAME
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.Flow

@Composable
@Destination(start = true)
fun SpeciesListScreen(
    navigator: DestinationsNavigator,
    viewModel: SpeciesListViewModel = hiltViewModel()){

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) }
            )
        }
    ) {
        val state = viewModel.state
        val connectivityObserver = NetworkConnectivityObserver(LocalContext.current)
        val networkStatus by connectivityObserver.observe().collectAsState(initial = ConnectivityObserver.Status.Unavailable)

        if(state.error == null) {
            if(networkStatus == ConnectivityObserver.Status.Available) {
                DisplaySpeciesList(
                    state.speciesPagingData,
                    onItemClicked = { selectedItem ->
                        viewModel.onUiEvent(SpeciesScreenEvent.OnSpeciesSelect(id = selectedItem.id, navigator = navigator))
                    })
            } else {
                ErrorItem(
                    message = stringResource(R.string.err_connectivity),
                    modifier = Modifier.fillMaxSize(),
                    onClickRetry = {}
                )
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                ComposeUtil.ErrorText(stringResource(state.error))
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun DisplaySpeciesList(data: Flow<PagingData<SpeciesResult>>?,
                       onItemClicked: (SpeciesResult) -> Unit) {

    val lazyItems = data?.collectAsLazyPagingItems()

    lazyItems?.let {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            modifier = Modifier.testTag(TEST_TAG_GRID),
            content = {
                items(lazyItems.itemCount) { i->
                    lazyItems[i]?.let {
                        ListItem(data = it, onItemClicked = { onItemClicked(it) })
                    }
                }
                updateLoadingState(lazyItems.loadState, lazyItems)
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun LazyGridScope.updateLoadingState(
    loadState: CombinedLoadStates,
    lazyItems: LazyPagingItems<SpeciesResult>
) {
    lazyItems.apply {
        when {
            loadState.refresh is LoadState.Loading -> {
                item { LoadingProgress() }
            }
            loadState.append is LoadState.Loading -> {
                item{ LoadingProgress() }
            }
            loadState.refresh is LoadState.Error -> {
                item {
                    ErrorItem(
                        message = stringResource(R.string.err_unable_to_load),
                        modifier = Modifier.fillParentMaxSize(),
                        showRetry = true,
                        onClickRetry = { retry() }
                    )
                }
            }
            loadState.append is LoadState.Error -> {
                item {
                    ErrorItem(
                        message = stringResource(R.string.err_unable_to_load),
                        modifier = Modifier.fillParentMaxSize(),
                        showRetry = true,
                        onClickRetry = { retry() }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListItem(data : SpeciesResult, modifier: Modifier = Modifier,
             onItemClicked: (SpeciesResult) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(3.dp),
        shape = RoundedCornerShape(size = 8.dp),
        elevation = 5.dp,
        onClick = { onItemClicked(data) },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoadImage(AppUtil.getImageUrl(data.id))
            data.name?.let {
                Text(
                    text = it,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .testTag(TEST_TAG_SPECIES_NAME)

                )
            }
        }

    }
}

@Composable
fun ErrorItem(
    message: String,
    modifier: Modifier = Modifier,
    showRetry: Boolean = false,
    onClickRetry: () -> Unit
) {
    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            color = Color.Red
        )
        if(showRetry) {
            OutlinedButton(onClick = onClickRetry) {
                Text(text = stringResource(id = R.string.try_again))
            }
        }
    }
}
