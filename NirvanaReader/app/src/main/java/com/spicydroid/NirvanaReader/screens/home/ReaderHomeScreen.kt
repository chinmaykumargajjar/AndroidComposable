package com.spicydroid.NirvanaReader.screens.home

import android.icu.text.CaseMap.Title
import android.util.Log
import android.widget.HorizontalScrollView
import android.widget.ListView
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.spicydroid.NirvanaReader.R
import com.spicydroid.NirvanaReader.components.BookRating
import com.spicydroid.NirvanaReader.components.FABContent
import com.spicydroid.NirvanaReader.components.ListCard
import com.spicydroid.NirvanaReader.components.ReaderAppBar
import com.spicydroid.NirvanaReader.components.RoundedButton
import com.spicydroid.NirvanaReader.components.TitleSection
import com.spicydroid.NirvanaReader.model.MBook
import com.spicydroid.NirvanaReader.navigation.ReaderScreens
import java.time.format.TextStyle

@Composable
fun Home(navController: NavHostController) {
    Scaffold(
        topBar={
            ReaderAppBar(title="N. Reader" , navController=navController)
        } ,
        floatingActionButton = {
            FABContent{}
        }) { paddingValues ->
        Surface (modifier =Modifier
            .padding(paddingValues)
            .fillMaxSize()) {
            //home content
            HomeContent(navController)
        }
    }
}

@Composable
fun HomeContent(navController : NavController = NavController(LocalContext.current)) {
    val listOfBooks = listOf (
        MBook(id = "dadfa", title = "Hello Again", authors = "All Of us", notes = null),
    MBook(id = "dadfa", title = "Hello ", authors = "All Of us", notes = null),
    MBook(id = "dadfa", title = "Hel", authors = "All Of us", notes = null),
    MBook(id = "dadfa", title = " Again", authors = "All Of us", notes = null),
    MBook(id = "dadfa", title = "o Again", authors = "All Of us", notes = null),
    )

    val email=FirebaseAuth.getInstance().currentUser?.email
    val currentUserName = if (!email.isNullOrEmpty())
        FirebaseAuth.getInstance().currentUser?.email?.split("@")?.get(0) else
            "N/A"
    Column(Modifier.padding(2.dp),
        verticalArrangement = Arrangement.Top) {
        Row(modifier = Modifier.align(Alignment.Start)) {
            TitleSection(label = "Your reading \n "+" activity")
            Spacer(modifier= Modifier.fillMaxWidth(0.7f))
            Column {
                Icon(imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Profile",
                    modifier =Modifier
                        .clickable {
                            navController.navigate(ReaderScreens.ReaderStatsScreen.name)
                        }
                        .size(45.dp),
                    tint = Color.LightGray)
                Text(text = currentUserName!! ,
                    modifier = Modifier.padding(2.dp),
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Clip)
                Divider()

            }

        }
        ListCard()

        ReadingRightNowArea(books=listOf() ,
            navController=navController)

        TitleSection(label = "Reading List")

        BookListArea(listOfBooks = listOfBooks,
            navController = navController)
    }
}

@Composable
fun BookListArea(listOfBooks : List<MBook> , navController : NavController) {
    HorizontalScrollableComponent(listOfBooks){
        // Go to Details
        Log.d("TAG" , "BookListArea: $it")
    }
}

@Composable
fun HorizontalScrollableComponent(listOfBooks : List<MBook>,
                                  onCardPressed: (String) -> Unit) {

    LazyRow(
        modifier =Modifier
            .fillMaxWidth()
            .heightIn(280.dp)
    ) {
        items(listOfBooks) { book ->
            ListCard(book) {
                onCardPressed(it)
            }
        }
    }
}

@Preview
@Composable
fun PreviewFun(){
    Column() {
        RoundedButton {

        }

        ListCard()
    }
}


@Composable
fun ReadingRightNowArea(books: List<MBook> , navController : NavController) {

}
