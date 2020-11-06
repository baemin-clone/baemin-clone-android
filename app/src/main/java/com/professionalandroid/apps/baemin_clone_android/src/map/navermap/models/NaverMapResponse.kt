package com.professionalandroid.apps.baemin_clone_android.src.map.navermap.models


data class NaverMapsResponse (
    val status: Status,
    val results: List<Result>
)

data class Result (
    val name: String,
    val code: Code,
    val region: Region,
    val land: Land
)

data class Code (
    val id: String,
    val type: String,
    val mappingID: String
)

data class Land (
    val type: String,
    val number1: String,
    val number2: String,
    val addition0: Addition,
    val addition1: Addition,
    val addition2: Addition,
    val addition3: Addition,
    val addition4: Addition,
    val coords: Coords,
    val name: String? = null
)

data class Addition (
    val type: String,
    val value: String
)

data class Coords (
    val center: Center
)

data class Center (
    val crs: String,
    val x: Float,
    val y: Float
)

data class Region (
    val area0: Area,
    val area1: Area1,
    val area2: Area,
    val area3: Area,
    val area4: Area
)

data class Area (
    val name: String,
    val coords: Coords
)

data class Area1 (
    val name: String,
    val coords: Coords,
    val alias: String
)

data class Status (
    val code: Long,
    val name: String,
    val message: String
)
