import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ntech.ttmaker.R
//import com.example.ttmaker.R

import com.example.ttmaker.classes.types.Slide
import com.example.ttmaker.ui.theme.FontSizes
import com.google.accompanist.pager.*
//import kotlinx.coroutines.DefaultExecutor.delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun SlideView(slide: Slide) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White) // Set the background color here
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
                .background(colorResource(id = R.color.bgLightElevated))
        ) {
            Text(
                text = slide.title,
                fontSize = FontSizes.body,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.textLightGrayPale)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = slide.description,
                fontSize = FontSizes.subtitle
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalSlider(){
    val slides = listOf(
        Slide(
            title = "Easy to use",
            description = "Classroom Scheduling in Minutes",
            img = "intro_image_url"
        ),
        Slide(
            title = "Collision Management",
            description = "Eliminate scheduling conflicts effortlessly",
            img = "intro_image_url"
        ),
        Slide(
            title = "Multiple Timetables",
            description = "Manage schedules for different classes",
            img = "ga_image_url"
        ),
        Slide(
            title = "Multiple Institutes",
            description = "Make timetables for different institutes",
            img = "ga_work_image_url"
        )
    )

    // Pager state
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    LaunchedEffect(Unit) {
        while (true) {
            delay(3500) // Change slide every 3 seconds5
            coroutineScope.launch {
                pagerState.animateScrollToPage((pagerState.currentPage + 1) % slides.size)
            }
        }
    }

    Column (
        modifier = Modifier
//        .height(screenHeight * 0.4f)
    ){
        HorizontalPager(
            count = slides.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
        ) { page ->
            SlideView(
                slides[page]
            )
//        }

        Spacer(modifier = Modifier.height(16.dp))
//
//        // Pager indicator
//        HorizontalPagerIndicator(
//            pagerState = pagerState,
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally)
//                .padding(16.dp),
//            activeColor = Color.Blue,
//            inactiveColor = Color.Gray
//        )
        }
    }
}
