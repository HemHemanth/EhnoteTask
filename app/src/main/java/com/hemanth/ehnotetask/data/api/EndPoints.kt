package com.hemanth.gormalonetask.data.api

import com.hemanth.ehnotetask.data.model.GetPatient
import retrofit2.Call
import retrofit2.http.*

interface EndPoints {

    @GET("MasterData/GetPatientNoteFilesbyId")
    fun getAllAvailableBooks(
        @Header("Authorization") token: String,
        @Query("AppointmentId") AppointmentId: String
    ): Call<GetPatient>
}