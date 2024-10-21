package com.spicydroid.getcurrencydata.viewModel

import androidx.lifecycle.ViewModel
import com.spicydroid.getcurrencydata.model.DataOrException
import com.spicydroid.getcurrencydata.model.Investment
import com.spicydroid.getcurrencydata.repository.InvestmentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InvestmentViewModel  @Inject constructor(private  val repository: InvestmentRepo)
    : ViewModel() {
        suspend fun getInvestments():
                DataOrException<Investment , Boolean , Exception> {
            return repository.getInvestments()
        }
}