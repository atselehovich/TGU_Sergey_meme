package org.gutmann.memeview.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import org.gutmann.memeview.model.MemeInfo
import org.gutmann.memeview.viewmodel.MemesListViewModel

private enum class LoadingState {
    Loading,
    Feed
}

@ExperimentalMaterialApi
@Composable
fun MemesListScreen(
    navigateToMeme: (memeId: Int) -> Unit = {},
    viewModel: MemesListViewModel = hiltViewModel()
) {
    val memesList by viewModel.memesList.observeAsState(initial = emptyList())
    val loadingState = if (memesList.isEmpty()) LoadingState.Loading else LoadingState.Feed
    
    Crossfade(
        targetState = loadingState,
        animationSpec = tween(durationMillis = 800)
    ) { state ->
        when(state) {
            LoadingState.Loading -> LoadingComponent()
            LoadingState.Feed -> MemesList(memesList, navigateToMeme)
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun MemesList(
    memesList: List<MemeInfo>,
    navigateToMeme: (memeId: Int) -> Unit = {}
) {
    LazyColumn {
        items(
            items = memesList,
            itemContent = { memeInfo ->
                MemeInfoListItem(
                    memeInfo = memeInfo,
                    modifier = Modifier.fillParentMaxWidth(),
                    onClick = { navigateToMeme(memeInfo.id) }
                )
            })
    }
}

@ExperimentalMaterialApi
@Composable
private fun MemeInfoListItem(
    memeInfo: MemeInfo,
    modifier: Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.then(Modifier.padding(8.dp)),
    ) {
        Row {
            MemeIcon(memeInfo)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)) {
                Text(text = memeInfo.name, style = typography.h4)
                RatingBar(
                    value = memeInfo.rating,
                    ratingBarStyle = RatingBarStyle.HighLighted,
                    onValueChange = {},
                    onRatingChanged = {}
                )
            }
        }
    }
}

@Composable
private fun MemeIcon(memeInfo: MemeInfo) {
    Image(
        painter = rememberImagePainter(
            data = memeInfo.imageUrl,
            builder = {
                crossfade(true)
                //placeholder(R.drawable.placeholder)
                transformations(RoundedCornersTransformation(16.dp.value))
            }
        ),
        contentDescription = null,
        //contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
    )
}

@Composable
private fun LoadingComponent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally)
        )
    }
}