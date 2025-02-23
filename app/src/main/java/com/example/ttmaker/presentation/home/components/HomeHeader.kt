import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ntech.ttmaker.R

@Composable
fun Header(
    @DrawableRes profileImage: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(45.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){

        Image(
            painter = painterResource(id = profileImage),
            contentDescription = null,
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape)
        )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "TT Maker", style = MaterialTheme.typography.headlineSmall,
                color = colorResource(id = R.color.headingLightBluePale),
            )
        }

        Box(
            modifier = Modifier
                .width(45.dp)
                .height(45.dp)
                .clip(CircleShape)
                .background(color = Color.White), contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.notification),
                contentDescription = "notification"
            )
        }

    }
}
