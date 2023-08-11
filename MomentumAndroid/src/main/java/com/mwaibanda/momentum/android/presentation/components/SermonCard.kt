package com.mwaibanda.momentum.android.presentation.components

import android.icu.text.StringSearch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.mwaibanda.momentum.android.R
import com.mwaibanda.momentum.android.core.utils.C
import com.mwaibanda.momentum.data.db.MomentumSermon
import com.mwaibanda.momentum.data.db.SermonFavourite
import com.mwaibanda.momentum.domain.models.Sermon

@Composable
fun SermonCard(
    isPlaceholder: Boolean = false,
    sermon: Sermon,
    watchedSermons: List<MomentumSermon> = emptyList(),
    favouriteSermons: List<SermonFavourite> = emptyList(),
    searchTerm: String = "",
    filterOldest: Boolean = false,
    filterNewest: Boolean = false,
    modifier: Modifier = Modifier,
    onFavouriteChange: (Boolean) -> Unit = {}
) {
    var isFavourite by remember {
        mutableStateOf(false)
    }
    var thumbnailHasLoaded by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = favouriteSermons, key2 = filterOldest, key3 = filterNewest) {
        favouriteSermons
            .firstOrNull {
                it.id == sermon.id
            }?.let {
                isFavourite = true
            } ?: kotlin.run {
                isFavourite = false
        }
    }

    LaunchedEffect(key1 = searchTerm) {
        favouriteSermons
            .firstOrNull {
                it.id == sermon.id
            }?.let {
                isFavourite = true
            } ?: kotlin.run {
            isFavourite = false
        }
    }
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            verticalArrangement = Arrangement.Top
        ) {
            Box (Modifier.fillMaxWidth()){

                Image(
                    painter = painterResource(id = R.drawable.thumbnail),
                    contentDescription = "Video thumbnail placeholder",
                    modifier = Modifier
                        .placeholder(
                            visible = true,
                            color = Color.Gray,
                            shape = RoundedCornerShape(4.dp),
                            highlight = PlaceholderHighlight.shimmer(
                                highlightColor = Color.White,
                            ),
                        )
                )
                if (isPlaceholder.not()) {
                    Box(
                        contentAlignment = Alignment.TopEnd,
                        modifier = Modifier
                            .placeholder(
                                visible = isPlaceholder,
                                color = Color.Gray,
                                shape = RoundedCornerShape(4.dp),
                                highlight = PlaceholderHighlight.shimmer(
                                    highlightColor = Color.White,
                                ),
                            )
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(sermon.videoThumbnail)
                                .crossfade(true)
                                .build(),
                            contentDescription = "Sermon thumbnail",
                            contentScale = ContentScale.FillWidth,
                            onSuccess = { thumbnailHasLoaded = true},
                        )
                        if (thumbnailHasLoaded) {
                            IconButton(
                                onClick = {
                                    isFavourite = isFavourite.not()
                                    onFavouriteChange(isFavourite)
                                },
                                modifier = Modifier.offset(y = -(7).dp, x = 5.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Box(
                                        modifier = Modifier
                                            .size(26.dp)
                                            .clip(CircleShape)
                                            .background(Color.LightGray.copy(0.75f))
                                    )

                                    Icon(
                                        painter = painterResource(id = R.drawable.cards_heart),
                                        contentDescription = "",
                                        tint = if (isFavourite) Color.Red else Color.DarkGray,
                                        modifier = Modifier.size(15.dp)
                                    )


                                }
                            }
                        }
                    }
                }
            }
            watchedSermons
                .firstOrNull {
                    it.id == sermon.id
                }?.let {
                    LinearProgressIndicator(
                        progress = it.last_played_percentage.toFloat() / 100.0f,
                        color = Color(C.MOMENTUM_ORANGE),
                        modifier = Modifier.height(4.dp)
                    )
                } ?: kotlin.run {
                LinearProgressIndicator(
                    progress = 0f,
                    color = Color.Gray,
                    modifier = Modifier.height(4.dp)
                )
            }
            Column(Modifier.padding(8.dp)) {

                Text(
                    text = sermon.series,
                    fontSize = 10.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(bottom = if (isPlaceholder) 2.dp else 0.5.dp)
                        .placeholder(
                            visible = isPlaceholder,
                            color = Color.Gray,
                            shape = RoundedCornerShape(4.dp),
                            highlight = PlaceholderHighlight.shimmer(
                                highlightColor = Color.White,
                            ),
                        )
                )
                Text(
                    text = sermon.title,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .placeholder(
                            visible = isPlaceholder,
                            color = Color.Gray,
                            shape = RoundedCornerShape(4.dp),
                            highlight = PlaceholderHighlight.shimmer(
                                highlightColor = Color.White,
                            ),
                        )
                )
                Text(
                    text = sermon.preacher,
                    fontSize = 10.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(vertical = if (isPlaceholder) 2.dp else 0.5.dp)
                        .placeholder(
                            visible = isPlaceholder,
                            color = Color.Gray,
                            shape = RoundedCornerShape(4.dp),
                            highlight = PlaceholderHighlight.shimmer(
                                highlightColor = Color.White,
                            ),
                        )
                )
                Text(
                    text = sermon.date,
                    fontSize = 8.sp,
                    modifier = Modifier
                        .placeholder(
                            visible = isPlaceholder,
                            color = Color.Gray,
                            shape = RoundedCornerShape(4.dp),
                            highlight = PlaceholderHighlight.shimmer(
                                highlightColor = Color.White,
                            ),
                        )
                )
            }
        }
    }

}