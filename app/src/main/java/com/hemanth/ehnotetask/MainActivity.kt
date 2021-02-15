package com.hemanth.ehnotetask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hemanth.ehnotetask.adapter.PatientsAdapter
import com.hemanth.ehnotetask.data.model.GetPatient
import com.hemanth.ehnotetask.data.model.PatientFiles
import com.hemanth.ehnotetask.databinding.ActivityMainBinding
import com.hemanth.gormalonetask.data.api.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val call = RetrofitBuilder.apiService.getAllAvailableBooks(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IkxhdGhhIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy91c2VyZGF0YSI6IjE4XzQ5RUJFRUIzX1Byb2QiLCJyb2xlIjoiNCIsIm5iZiI6MTYxMzM2ODA0NCwiZXhwIjoxNjEzNDU0NDQ0LCJpYXQiOjE2MTMzNjgwNDQsImlzcyI6Imh0dHBzOi8vZGV2ZWhub3RlYXBpLmF6dXJld2Vic2l0ZXMubmV0IiwiYXVkIjoiaHR0cHM6Ly9kZXZlaG5vdGVhcGkuYXp1cmV3ZWJzaXRlcy5uZXQifQ.d7VOcKUEN9FXrJ4kAIehuU7nYzipEvJqzq2Exwspv5s",
                "1557963"
        )

        call.enqueue(object: Callback<GetPatient> {
            override fun onResponse(call: Call<GetPatient>, response: Response<GetPatient>) {
                if (response.isSuccessful) {
                    response.body()?.let { setAdapter(it) }
                }
            }

            override fun onFailure(call: Call<GetPatient>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun setAdapter(getPatient: GetPatient) {
        val patientsMap = HashMap<String, ArrayList<PatientFiles>>()
        val patientsList = getPatient.PatientFiles

        for (patient in patientsList) {
            if (patientsMap.containsKey(patient.FileTypeId)) {
                patientsMap[patient.FileTypeId]?.add(patient)
            } else {
                patientsMap[patient.FileTypeId] = arrayListOf(patient)
            }
        }
        binding.recyclerViewPatients.apply {
            adapter = PatientsAdapter(this@MainActivity, patientsMap)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}