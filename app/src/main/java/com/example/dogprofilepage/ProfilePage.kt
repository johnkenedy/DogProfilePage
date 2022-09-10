package com.example.dogprofilepage

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

@Composable
fun ProfilePage() {
    Card(
        elevation = 6.dp,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(top = 100.dp, bottom = 100.dp, start = 16.dp, end = 16.dp)
    ) {
        BoxWithConstraints {
            val constraints =
                if (minWidth < 600.dp) portraitConstraints() else landscapeConstraints()
            ConstraintLayout(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .verticalScroll(state = rememberScrollState()),
                constraintSet = constraints
            ) {
                Image(
                    painter = painterResource(id = R.drawable.americandog),
                    contentDescription = "american dog super cute",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .layoutId("image")
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(
                            width = 2.dp,
                            color = Color(0xFF6200EE),
                            shape = CircleShape
                        )
                )
                Text(
                    text = "American Dog",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .layoutId("nameText")
                        .padding(top = 8.dp)
                )
                Text(
                    text = "USA",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.layoutId("nationality")
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .layoutId("infoRow")
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    ProfileStats("1500", "Followers")
                    ProfileStats("105", "Following")
                    ProfileStats("143", "Posts")
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .layoutId("buttonRow")
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Button("Follow Dog")
                    Button("Direct Message")
                }
            }
        }
    }
}

@Composable
fun Button(text: String) {
    Button(onClick = { /*TODO*/ }) {
        Text(text = text)
    }
}

@Composable
fun ProfileStats(count: String, title: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = count, fontWeight = FontWeight.Bold)
        Text(text = title)
    }
}

@Composable
private fun portraitConstraints(): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val nameText = createRefFor("nameText")
        val nationality = createRefFor("nationality")
        val infoRow = createRefFor("infoRow")
        val buttonRow = createRefFor("buttonRow")
        constrain(image) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(nameText) {
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(nationality) {
            top.linkTo(nameText.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(infoRow) {
            top.linkTo(nationality.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(buttonRow) {
            top.linkTo(infoRow.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }
}

@Composable
private fun landscapeConstraints(): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val nameText = createRefFor("nameText")
        val nationality = createRefFor("nationality")
        val infoRow = createRefFor("infoRow")
        val buttonRow = createRefFor("buttonRow")
        val margin = 16.dp
        constrain(image) {
            top.linkTo(parent.top)
            start.linkTo(parent.start, margin = margin)
        }
        constrain(nameText) {
            top.linkTo(image.bottom)
            start.linkTo(image.start)
            end.linkTo(image.end)
        }
        constrain(nationality) {
            top.linkTo(nameText.bottom)
            start.linkTo(nameText.start)
            end.linkTo(nameText.end)
        }
        constrain(infoRow) {
            top.linkTo(image.top, margin = margin)
            start.linkTo(image.end, margin = margin)
            end.linkTo(parent.end)
        }
        constrain(buttonRow) {
            top.linkTo(infoRow.bottom)
            start.linkTo(infoRow.start)
            end.linkTo(parent.end)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePagePreview() {
    ProfilePage()
}