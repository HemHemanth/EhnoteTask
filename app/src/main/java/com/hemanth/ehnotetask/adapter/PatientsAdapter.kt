package com.hemanth.ehnotetask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hemanth.ehnotetask.data.model.PatientFiles
import com.hemanth.ehnotetask.databinding.PatientItemBinding

class PatientsAdapter(private val context: Context, private val patientsMap: HashMap<String, ArrayList<PatientFiles>>): RecyclerView.Adapter<PatientsAdapter.PatientHolder>() {

    private val types = arrayListOf<String>("Post Treatment", "General", "ICU", "Out Patient")
    class PatientHolder(val patientItemBinding: PatientItemBinding): RecyclerView.ViewHolder(patientItemBinding.root) {
        val txtType: TextView = patientItemBinding.txtType
        val recyclerViewPatientType = patientItemBinding.recyclerViewPatientType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientHolder =
            PatientHolder(PatientItemBinding.inflate(
                    LayoutInflater.from(parent.context)
            ))

    override fun onBindViewHolder(holder: PatientHolder, position: Int) {
        holder.txtType.text = types[position]

            holder.recyclerViewPatientType.layoutManager = LinearLayoutManager(context)
            holder.recyclerViewPatientType.adapter = patientsMap[(position + 1).toString()]?.let { PatientsChildAdapter(context, it) }
    }

    override fun getItemCount(): Int = patientsMap.size
}