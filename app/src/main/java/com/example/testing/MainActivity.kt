package com.example.testing

import android.R.style
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
            R.drawable.will_smith,
            R.drawable.will_smith,
            R.drawable.will_smith
        ),
        likes = 1000,
        comments = 100,
        shares = 50,
        caption = "Just posted a new video on my YouTube channel! Check it out!"
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StoryList(userArr, modifier = Modifier)
            InstagramPostCard(postArr[0], modifier = Modifier)
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
            border = BorderStroke(2.dp, if (profile.activeStory) Color.Green else Color.Red),
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
            text = if (profile.username.length > 11) "${profile.username.substring(0, 8)}..." else profile.username,
            style = TextStyle(
                fontSize = 12.sp,
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
        modifier = modifier.padding(top = 20.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(sortedArr.size) { index ->
            StoryCard(
                profile = sortedArr[index],
                modifier = Modifier
            )
        }
    }
}

@Composable
fun InstagramPostCard(
    post: Post,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(450.dp)
        ) {
            Image(
                painter = painterResource(id = post.postImages[0]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
//                    .horizontalScroll(rememberScrollState())
                    .padding(5.dp)
            )
        }
        Text(
            text = "Likes: ${post.likes} Comments: ${post.comments} Shares: ${post.shares}",
            style = TextStyle(
                fontSize = 12.sp,
                color = Color.Black
            ),
            modifier = Modifier.padding(top = 5.dp)
        )
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Black, fontWeight = FontWeight.Bold)) {
                    append(post.profile.username)
                }
                append(" ${post.caption}")
            },
            modifier = Modifier.padding(top = 5.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Test() {
//    StoryCard(arr[0], modifier = Modifier)
//    StoryList(arr, modifier = Modifier)
    InstagramPostCard(postArr[0], modifier = Modifier)
}