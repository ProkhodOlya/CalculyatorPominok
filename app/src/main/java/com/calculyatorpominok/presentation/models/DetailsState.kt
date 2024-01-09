package com.calculyatorpominok.presentation.models

import androidx.annotation.StringRes

data class DetailsState (
    @StringRes val dayDateOfDeathCaption: Int,
    @StringRes val detailsDateOfDeath: Int,
    @StringRes val detailsDateOfDeathDescription: Int,
    )