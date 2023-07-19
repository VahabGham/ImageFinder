package com.example.imagefinder.feature.imagelist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.imagefinder.R
import com.example.imagefinder.common.ui.component.toolbar.ToolBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.imagefinder.common.ui.component.EmptyScreen
import com.example.imagefinder.common.ui.component.ProgressIndicator
import com.example.imagefinder.feature.imagelist.ui.component.InnerContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ImageListScreen(
    viewModel: ImageListViewModel = hiltViewModel(),
    onItemClick: (id: Long) -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val pullToRefreshState = rememberPullRefreshState(state.isLoading, { viewModel.fetchImages() })

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            ToolBar(
                title = stringResource(id = R.string.image_list_title)
            )
        }
    ) {

        Box(
            modifier = Modifier
                .pullRefresh(pullToRefreshState)
                .padding(it)
        ) {

            with(state) {

                if (error != null)
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = context.getString(error.data),
                            actionLabel = context.getString(R.string.general_retry_action_label)
                        ).let { result ->
                            if (result == SnackbarResult.ActionPerformed)
                                viewModel.fetchImages()
                        }
                    }

                if (isLoading)
                    ProgressIndicator()

                if (images?.isNotEmpty() == true) {
                    InnerContent(images) { imageId ->
                        onItemClick(imageId)
                    }
                }

                if (images?.isEmpty() == true) {
                    EmptyScreen(
                        title = stringResource(id = R.string.general_no_data_message)
                    )
                }
            }

            PullRefreshIndicator(
                refreshing = state.isLoading,
                state = pullToRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )

        }
    }
}
