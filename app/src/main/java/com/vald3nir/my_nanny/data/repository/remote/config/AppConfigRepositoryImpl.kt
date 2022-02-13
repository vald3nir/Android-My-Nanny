package com.vald3nir.my_nanny.data.repository.remote.config

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.vald3nir.my_nanny.data.dto.AppConfigDTO

class AppConfigRepositoryImpl : AppConfigRepository {

    override fun loadConfiguration(
        userId: String,
        onSuccess: (AppConfigDTO?) -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        val reference = Firebase.database.reference
        reference.child("users").child(userId).get().addOnSuccessListener {
            val snap = (it as DataSnapshot)
            val appConfigDTO = snap.getValue(AppConfigDTO::class.java)
            onSuccess.invoke(appConfigDTO)
        }.addOnFailureListener {
            onError.invoke(it)
        }
    }

    override fun updateConfiguration(
        userId: String,
        appConfigDTO: AppConfigDTO,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        val reference = Firebase.database.reference
        reference.child("users").child(userId).setValue(appConfigDTO)
            .addOnSuccessListener { onSuccess.invoke() }
            .addOnFailureListener { onError.invoke(it) }
    }
}