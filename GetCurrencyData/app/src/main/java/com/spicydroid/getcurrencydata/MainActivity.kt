package com.spicydroid.getcurrencydata

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.spicydroid.getcurrencydata.model.DataOrException
import com.spicydroid.getcurrencydata.model.Investment
import com.spicydroid.getcurrencydata.ui.theme.GetCurrencyDataTheme
import com.spicydroid.getcurrencydata.viewModel.InvestmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            GetCurrencyDataTheme {
               //Scaffold(modifier=Modifier.fillMaxSize()) { innerPadding ->
                    showData()
               // }
            }
        }
    }
}

@Composable
fun showData(viewModel: InvestmentViewModel = hiltViewModel()){
    val weatherData = produceState<DataOrException<Investment , Boolean , Exception>> (
        initialValue = DataOrException(loading = true)
    ) {
        value = viewModel.getInvestments()
    }.value

    Log.d("TAG" , "showData: Exception="+weatherData.e?.toString())
    weatherData.data?.get(0)?.let { Text(text= it.name) }
}

@Preview(showBackground=true)
@Composable
fun GreetingPreview() {
    GetCurrencyDataTheme {
        //Greeting("Android")
    }
}