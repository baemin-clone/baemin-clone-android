package com.professionalandroid.apps.baemin_clone_android.src.home.delivery.interfaces

import com.professionalandroid.apps.baemin_clone_android.src.home.delivery.models.DeliveryResponse
import com.professionalandroid.apps.baemin_clone_android.src.home.delivery.models.RecommendationResponse

interface DeliveryFragmentView {
    fun brand(body: DeliveryResponse)
    fun recommendation1(body: RecommendationResponse)
    fun recommendation2(body: RecommendationResponse)
}