package org.gutmann.memeview.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import org.gutmann.memeview.R
import org.gutmann.memeview.model.MemeInfo
import org.gutmann.memeview.viewmodel.MemeInfoViewModel

@Composable
fun MemeInfoScreen(memeId: Int, viewModel: MemeInfoViewModel = hiltViewModel()) {
    val memeInfo by viewModel.getMemeInfo(memeId).observeAsState()
    memeInfo?.let { MemeInfoScreen(it) }
}

@Composable
private fun MemeInfoScreen(memeInfo: MemeInfo) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                ) {
                    MemeInfoHeader(
                        memeInfo,
                        this@BoxWithConstraints.maxHeight
                    )
                    MemeInfoContent(memeInfo)
                }
            }
        }
    }
}

@Composable
private fun MemeInfoHeader(memeInfo: MemeInfo, containerHeight: Dp) {
    Image(
        modifier = Modifier
            .heightIn(max = containerHeight / 2)
            .fillMaxWidth(),
        painter = rememberImagePainter(memeInfo.imageUrl),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Composable
private fun MemeInfoContent(memeInfo: MemeInfo) {
    Column {
        Title(memeInfo)
        MemeInfoProperty(stringResource(R.string.meme_info_name), memeInfo.name)
        MemeInfoProperty(stringResource(R.string.meme_info_description), memeInfo.description)
    }
}

@Composable
private fun Title(memeInfo: MemeInfo) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp)) {
        Text(
            text = memeInfo.name,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
        RatingBar(
            value = memeInfo.rating,
            ratingBarStyle = RatingBarStyle.HighLighted,
            onValueChange = {},
            onRatingChanged = {}
        )
    }
}

@Composable
private fun MemeInfoProperty(label: String, value: String) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Divider(modifier = Modifier.padding(bottom = 4.dp))
        Text(
            text = label,
            modifier = Modifier.height(24.dp),
            style = MaterialTheme.typography.caption,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Visible
        )
    }
}