package com.hemanth.ehnotetask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hemanth.ehnotetask.data.model.PatientFiles
import com.hemanth.ehnotetask.databinding.PatientsItemChildBinding

class PatientsChildAdapter(private  val context: Context, private val patientsList: ArrayList<PatientFiles>): RecyclerView.Adapter<PatientsChildAdapter.PatientsChildHolder>() {

    class PatientsChildHolder(patientsItemChildBinding: PatientsItemChildBinding): RecyclerView.ViewHolder(patientsItemChildBinding.root) {
        val imgPatient = patientsItemChildBinding.imgPatient
        val edtFileName = patientsItemChildBinding.edtFileName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsChildHolder =
            PatientsChildHolder(PatientsItemChildBinding.inflate(
                    LayoutInflater.from(parent.context)
            ))

    override fun onBindViewHolder(holder: PatientsChildHolder, position: Int) {
        holder.edtFileName.setText(patientsList[position].FileName)

        Glide.with(context)
                .load(patientsList[position].thumbUrl)
                .into(holder.imgPatient)
    }

    override fun getItemCount(): Int = patientsList.size
}