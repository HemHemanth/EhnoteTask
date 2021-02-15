package com.hemanth.ehnotetask.data.model

data class GetPatient (
    val PatientFiles: ArrayList<PatientFiles>
        )

data class PatientFiles (
    val DocumentID: String,
    val TreatmentPlanId: String,
    val ConsultationId: String,
    val DocURL: String,
    val url: String,
    val thumbUrl: String,
    val DocName: String,
    val FileName: String,
    val FileExt: String,
    val Remarks: String,
    val FileTypeId: String,
    val OrgConsentFormId: String,
    val SignedDate: String,
    val ProcedureId: String,
    val AppointmentId: String,
    val IsPartiallyUploaded: String,
    val SubmitInd: String,
    val LabTestId: String,
    val PatientDisplayId: String,
)