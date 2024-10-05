package com.spicydroid.NirvanaReader.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.spicydroid.NirvanaReader.model.MBook
import com.spicydroid.NirvanaReader.navigation.ReaderScreens


@Composable
fun EmailInput(modifier: Modifier = Modifier, emailState: MutableState<String>,
               labelId: String = "Email",
               enabled: Boolean = true,
               imeAction: ImeAction = androidx.compose.ui.text.input.ImeAction.Next,
               onAction: KeyboardActions = KeyboardActions.Default
) {

    InputField(modifier = modifier,
        valueState = emailState,
        labelId = labelId,
        enabled = enabled,
        keyboardType = KeyboardType.Email,
        imeAction = imeAction,
        onAction = onAction)

}

@Composable
fun InputField(modifier: Modifier = Modifier,
               valueState: MutableState<String>,
               labelId: String,
               enabled: Boolean,
               isSingleLine: Boolean = true,
               keyboardType: KeyboardType = KeyboardType.Text,
               imeAction: ImeAction = androidx.compose.ui.text.input.ImeAction.Next,
               onAction: KeyboardActions = KeyboardActions.Default) {

    OutlinedTextField(value = valueState.value,
        onValueChange = {
            valueState.value = it
        },
        label = { Text (text = labelId) },
        singleLine = isSingleLine,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colorScheme.onBackground),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}


@Composable
fun PasswordInput(
    modifier : Modifier ,
    passwordState : MutableState<String> ,
    labelId : String ,
    enabled : Boolean ,
    passwordVisibility : MutableState<Boolean> ,
    onAction : KeyboardActions = KeyboardActions.Default,
    imeAction : ImeAction = ImeAction.Done
) {
    val visualTransformation = if(passwordVisibility.value) VisualTransformation.None else
        PasswordVisualTransformation()

    OutlinedTextField(
        value=passwordState.value ,
        onValueChange={
            passwordState.value = it
        } ,
        label ={ Text(text=labelId) } ,
        singleLine=true ,
        textStyle=TextStyle(fontSize=18.sp , color=MaterialTheme.colorScheme.onBackground),
        modifier = modifier.padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        visualTransformation = visualTransformation,
        trailingIcon = {PasswordVisibility(passwordVisibility)},
        keyboardActions = onAction
    )
}

@Composable
fun PasswordVisibility(passwordVisibility : MutableState<Boolean>) {
    val visible = passwordVisibility.value
    IconButton(onClick = { passwordVisibility.value = !visible }){
        Icons.Default.Close
    }
}

@Composable
fun TitleSection(modifier : Modifier = Modifier,
                 label: String) {
    Surface(modifier = modifier.padding(start = 5.dp, top = 1.dp)) {
        Column {
            Text(text = label,
                fontSize = 19.sp,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Left)


        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReaderAppBar(
    title: String,
    showProfile: Boolean = true,
    navController : NavController
) {
    TopAppBar(title = {
        Row(verticalAlignment = Alignment.CenterVertically){
            if(showProfile){
                Image(imageVector = Icons.Default.Favorite ,
                    contentDescription = "icon",
                    modifier =Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .scale(0.6f))
                Text(text = title,
                    color = Color.Red.copy(alpha = 0.7f),
                    style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold,
                        fontSize = 20.sp))
                Spacer(modifier = Modifier.width(15.dp))


            }
        }
    } ,
        actions = {
            androidx.compose.material.IconButton(onClick = { FirebaseAuth.getInstance().signOut().run {
                navController.navigate(ReaderScreens.LoginScreen.name)
            }}) {
                Icon(imageVector = Icons.AutoMirrored.Filled.Logout ,
                    contentDescription = "null",
                    tint = Color.Green.copy(alpha = 0.4f))
            }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}

@Composable
fun FABContent(onTap : (String) -> Unit) {
    FloatingActionButton(onClick = {onTap("")},
        shape = RoundedCornerShape(50.dp),
        containerColor = Color(0xFF92CBDF))
    {
        Icon(imageVector = Icons.Default.Add ,
            contentDescription = "Add a Book",
            tint = Color.White)
    }
}


@Composable
fun BookRating(score : Double = 4.5) {
    Surface(
        modifier =Modifier
            .height(70.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(50.dp),
        elevation = 6.dp,
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(4.dp)) {
            Icon(imageVector = Icons.Filled.StarBorder,
                contentDescription = "",
                modifier = Modifier.padding(3.dp))
            Text(text = score.toString(),
                style = MaterialTheme.typography.titleSmall)
        }
    }
}


@Composable
fun ListCard(book: MBook= MBook("asdf", "Running", "Me and you", "hello world") ,
             onPressDetails: (String) -> Unit = {}) {
    val context = LocalContext.current
    val resources = context.resources
    val displayMetrics = resources.displayMetrics
    val spacing = 10.dp
    val screenWidth = displayMetrics.widthPixels / displayMetrics.density

    Card(
        shape = RoundedCornerShape(29.dp),
        backgroundColor = Color.White,
        elevation = 6.dp,
        modifier =Modifier
            .padding(16.dp)
            .height(242.dp)
            .width(202.dp)
            .clickable { onPressDetails.invoke(book.title.toString()) }
    ) {
        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start){
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Image (painter = rememberImagePainter(data = "https://books.google.ca/googlebooks/images/ebook_promo.png") ,
                    contentDescription = "Book Reading now",
                    modifier = Modifier
                        .height(140.dp)
                        .width(100.dp)
                        .padding(4.dp))
                Column(modifier = Modifier.padding(top = 25.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector = Icons.Rounded.FavoriteBorder
                        , contentDescription = "Fav Icon",
                        modifier = Modifier.padding(bottom = 1.dp))

                    BookRating(score = 3.5)
                }
            }
            book.title?.let {
                Text(text =it , modifier = Modifier.padding(4.dp),
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis)
            }

            book.authors?.let {
                Text(text =it ,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.titleSmall)
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.Right,
            verticalAlignment = Alignment.Bottom) {
            RoundedButton(label = "Reading", radius = 70) {

            }
        }
    }

}


@Composable
fun RoundedButton(
    label: String  = "Reading",
    radius: Int = 29,
    onPress : () -> Unit
) {
    Surface (modifier = Modifier.clip(
        RoundedCornerShape(
            bottomEndPercent = radius,
            topStartPercent = radius)),
        color = Color(0xFF92CBDF)
    ) {
        Column(modifier =Modifier
            .width(90.dp)
            .heightIn(40.dp)
            .clickable { onPress.invoke() },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = label, style =androidx.compose.ui.text.TextStyle(
                color=Color.White ,
                fontSize=15.sp
            )
            )
        }
    }
}
