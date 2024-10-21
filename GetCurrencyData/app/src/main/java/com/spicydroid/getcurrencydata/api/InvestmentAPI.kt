package com.spicydroid.getcurrencydata.api

import com.spicydroid.getcurrencydata.model.Investment
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface InvestmentAPI {
    @GET(value="/dns-mcdaid/b248c852b743ad960616bac50409f0f0/raw/6921812bfb76c1bea7868385adf62b7f447048ce")
    suspend fun getInvestments():Investment
}