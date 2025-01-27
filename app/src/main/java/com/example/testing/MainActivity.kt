package com.example.testing

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableDefaults.flingBehavior
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

val userArr: List<Profile> = listOf(
    Profile(
        username = "will_smith",
        profilePhoto = R.drawable.will_smith,
        activeStory = true
    ),
    Profile(
        username = "nicki_minaj",
        profilePhoto = R.drawable.nicki_minaj,
        activeStory = false
    ),
    Profile(
        username = "mohammed_salah",
        profilePhoto = R.drawable.mohammed_salah,
        activeStory = true
    ),
    Profile(
        username = "miles_morales",
        profilePhoto = R.drawable.miles_morales,
        activeStory = true
    ),
    Profile(
        username = "can_yaman",
        profilePhoto = R.drawable.can_yaman,
        activeStory = false
    ),
    Profile(
        username = "boyWithUke",
        profilePhoto = R.drawable.boywithuke,
        activeStory = true
    )
)

val postArr: List<Post> = listOf(
    Post(
        profile = userArr[0],
        postImages = arrayListOf(
            R.drawable.ic_launcher_background,
            R.drawable.will_smith,
            R.drawable.will_smith
        ),
        likes = 1000,
        comments = 100,
        shares = 50,
        caption = "Just posted a new video on my YouTube channel! Check it out!"
    ),
    Post(
        profile = userArr[1],
        postImages = arrayListOf(
            R.drawable.boywithuke,
            R.drawable.nicki_minaj,
            R.drawable.nicki_minaj
        ),
        likes = 10000,
        comments = 1000,
        shares = 500,
        caption = "Just posted a new video on my YouTube channel! Check it out!"
    ),
    Post(
        profile = userArr[2],
        postImages = arrayListOf(
            R.drawable.mohammed_salah,
            R.drawable.mohammed_salah,
            R.drawable.mohammed_salah
        ),
        likes = 100000,
        comments = 10000,
        shares = 5000,
        caption = "Just posted a new video on my YouTube channel! Check it out!"
    ),
    Post(
        profile = userArr[3],
        postImages = arrayListOf(
            R.drawable.can_yaman
        ),
        likes = 1000000,
        comments = 100000,
        shares = 50000,
        caption = "Just posted a new video on my YouTube channel! Check it out!"
    )
)

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(
                    bottomBar = {
                        BottomCard(modifier = Modifier)
                    }
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Top
                    ) {
                        item {
                            UpperCard(modifier = Modifier)
                        }
                        item {
                            StoryList(userArr, modifier = Modifier)
                        }
                        items(postArr.size) { index ->
                            InstagramPostCard(
                                post = postArr[index],
                                modifier = Modifier
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StoryCard(
    profile: Profile,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(5.dp),
            border = BorderStroke(
                width = 2.dp,
                brush = Brush.linearGradient(
                    colors = listOf(Color.Yellow, Color.Red),
                    start = Offset(0f, 0f),
                    end = Offset(70f, 70f)
                )
            ),
            modifier = Modifier.size(61.dp)
        ) {
            Image(
                painter = painterResource(id = profile.profilePhoto),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        }
        Text(
            text = if (profile.username.length > 11) "${
                profile.username.substring(
                    0,
                    8
                )
            }..." else profile.username,
            style = TextStyle(
                fontSize = 13.sp,
                color = Color.Black
            ),
            modifier = Modifier.padding(top = 3.dp)
        )
    }
}

@Composable
fun StoryList(
    userList: List<Profile>,
    modifier: Modifier = Modifier
) {
    val sortedArr = userList.sortedByDescending { it.activeStory }
    LazyRow(
        modifier = modifier.padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(sortedArr.size) { index ->
            if (index == 0) {
                YourStory(
                    profile = sortedArr[index],
                    modifier = Modifier
                )
            } else {
                StoryCard(
                    profile = sortedArr[index],
                    modifier = Modifier
                )
            }
        }
    }
}

@SuppressLint("RememberReturnType", "FrequentlyChangedStateReadInComposition")
@Composable
fun InstagramPostCard(
    post: Post,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                shape = CircleShape,
                elevation = CardDefaults.cardElevation(5.dp),
                modifier = Modifier
                    .size(50.dp)
                    .padding(5.dp)
            ) {
                Image(
                    painter = painterResource(id = post.profile.profilePhoto),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                )
            }
            Text(
                text = post.profile.username,
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.Black
                ),
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.more),
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp)
                )
            }
        }

        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val lazyRowState = rememberLazyListState()
        Column(
            modifier = Modifier
        ) {
            Text(
                text = "Post ${postArr.indexOf(post) + 1}",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.Black
                ),
                modifier = Modifier
                    .padding(start = 5.dp)
                    .
            )
            LazyRow(
                state = lazyRowState,
                flingBehavior = rememberSnapFlingBehavior(lazyRowState)
            ) {
                items(post.postImages.size) { index ->
                    Image(
                        painter = painterResource(id = post.postImages[index]),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(450.dp)
                            .width(screenWidth)
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
//                    .padding(5.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.like),
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp)
                )
            }
            Text(
                text = "${if (post.likes >= 1000) "${post.likes / 1000}k" else post.likes}",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.Black
                ),
//                modifier = Modifier.padding(top = 5.dp, end = 5.dp, start = 1.dp)
            )
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.comment),
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp)
                )
            }
            Text(
                text = "${if (post.comments >= 1000) "${post.comments / 1000}k" else post.comments}",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.Black
                ),
//                modifier = Modifier.padding(top = 5.dp, end = 5.dp, start = 1.dp)
            )
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.share),
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp)
                )
            }
            Text(
                text = "${if (post.shares >= 1000) "${post.shares / 1000}k" else post.shares}",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.Black
                ),
//                modifier = Modifier.padding(top = 5.dp, end = 5.dp, start = 1.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.save),
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp)
                )
            }
        }
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Black, fontWeight = FontWeight.Bold)) {
                    append(post.profile.username)
                }
                append(" ${post.caption}")
            },
            modifier = Modifier
//                .padding(top = 5.dp)
                .padding(start = 15.dp, end = 5.dp)
        )
    }
}

@Composable
fun BottomCard(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = Modifier
            .padding(bottom = 50.dp)
            .height(50.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.home),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.reels),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(Color.Black)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.miles_morales),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                        )
                    }
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpperCard(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = {
            Text(
                text = "Instagram",
                style = TextStyle(
                    fontSize = 25.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
        },
        actions = {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
//                    .padding(5.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.like),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
//                    .padding(5.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.direct),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    )
}

@Composable
fun YourStory(
    modifier: Modifier = Modifier,
    profile: Profile
) {
    Column(
        modifier = modifier.padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Box(
            modifier = Modifier
        ) {
            Card(
                shape = CircleShape,
                elevation = CardDefaults.cardElevation(5.dp),
                modifier = Modifier.size(61.dp)
            ) {
                Image(
                    painter = painterResource(id = profile.profilePhoto),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                )
            }
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .align(Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = null,
                    tint = Color.Blue,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        Text(
            text = "Your story",
            style = TextStyle(
                fontSize = 13.sp,
                color = Color.Black
            ),
            modifier = Modifier
        )
    }
}


@Preview(showBackground = true)
@Composable
fun Test() {
//    StoryCard(userArr[0], modifier = Modifier)
    Column {
        UpperCard(modifier = Modifier)
        StoryList(userArr, modifier = Modifier)
        InstagramPostCard(postArr[0], modifier = Modifier)
        BottomCard(modifier = Modifier)
    }
//    YourStory(modifier = Modifier, profile = userArr[0])
}