package com.hemanth.ehnotetask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hemanth.ehnotetask.data.model.PatientFiles
import com.hemanth.ehnotetask.databinding.PatientItemBinding
import com.hemanth.ehnotetask.interfaces.IPatientRemoved

class PatientsAdapter(private val context: Context, private val patientsMap: HashMap<String, ArrayList<PatientFiles>>): RecyclerView.Adapter<PatientsAdapter.PatientHolder>(), IPatientRemoved {

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

        if (patientsMap[(position + 1).toString()]?.size == 0) {
            holder.txtType.visibility = View.GONE
            holder.patientItemBinding.recyclerViewPatientType.visibility = View.GONE
        }

        holder.recyclerViewPatientType.layoutManager = LinearLayoutManager(context)
        holder.recyclerViewPatientType.adapter = patientsMap[(position + 1).toString()]?.let { PatientsChildAdapter(context, it, this) }
    }

    override fun getItemCount(): Int = patientsMap.size

    override fun onPatientRemoved() {
        patientsMap.forEach { (key, value) ->
            if (value.size == 0)
                notifyDataSetChanged()
        }
    }
}