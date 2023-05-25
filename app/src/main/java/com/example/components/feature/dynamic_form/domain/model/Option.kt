package com.example.components.feature.dynamic_form.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Option(
    val optionCode: Int = 0,
    var optionChecked: Boolean = false,
    val optionDescription: String = ""
): Parcelable
