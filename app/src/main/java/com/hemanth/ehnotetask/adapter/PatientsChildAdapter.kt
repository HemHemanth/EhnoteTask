package com.hemanth.ehnotetask.adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hemanth.ehnotetask.data.model.PatientFiles
import com.hemanth.ehnotetask.databinding.PatientsItemChildBinding
import com.hemanth.ehnotetask.interfaces.IPatientRemoved

class PatientsChildAdapter(private  val context: Context, private val patientsList: ArrayList<PatientFiles>, private val iPatientRemoved: IPatientRemoved): RecyclerView.Adapter<PatientsChildAdapter.PatientsChildHolder>() {

    class PatientsChildHolder(patientsItemChildBinding: PatientsItemChildBinding): RecyclerView.ViewHolder(patientsItemChildBinding.root) {
        val imgPatient = patientsItemChildBinding.imgPatient
        val edtFileName = patientsItemChildBinding.edtFileName
        val imgRemove = patientsItemChildBinding.imgRemove
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsChildHolder =
            PatientsChildHolder(PatientsItemChildBinding.inflate(
                    LayoutInflater.from(parent.context)
            ))

    override fun onBindViewHolder(holder: PatientsChildHolder, position: Int) {
        if (patientsList[position].userText.isNullOrEmpty()) {
            holder.edtFileName.setText(patientsList[position].FileName)
        } else {
            holder.edtFileName.setText(patientsList[position].userText)
        }

        holder.edtFileName.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                patientsList[position].userText = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        Glide.with(context)
                .load(patientsList[position].thumbUrl)
                .into(holder.imgPatient)

        holder.imgRemove.setOnClickListener {
            patientsList.removeAt(position)
            iPatientRemoved.onPatientRemoved()
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = patientsList.size
}