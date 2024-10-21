package com.spicydroid.getcurrencydata.repository

import android.util.Log
import com.spicydroid.getcurrencydata.api.InvestmentAPI
import com.spicydroid.getcurrencydata.model.DataOrException
import com.spicydroid.getcurrencydata.model.Investment
import javax.inject.Inject

class InvestmentRepo @Inject constructor(private  val api: InvestmentAPI) {
    suspend fun getInvestments() : DataOrException<Investment , Boolean , Exception> {
        val response = try {
            api.getInvestments()
        }catch (e: Exception) {
            Log.d("INSIDE", "getInvestments : $e")
            return DataOrException(e = e)
        }

        return DataOrException(data = response, loading = false)
    }
}