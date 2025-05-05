package com.example.project

data class Farmer(
    val name: String,
    val age: Int,
    val farmName: String,
    val location: String,
    val crops: List<String>
) {

    fun displayDetails() {
        println("Farmer Name: $name")
        println("Age: $age")
        println("Farm Name: $farmName")
        println("Location: $location")
        println("Crops grown: ${crops.joinToString(", ")}")
    }

    fun addCrop(crop: String) {
        crops.toMutableList().add(crop)
        println("$crop has been added to the farm.")
    }
    fun removeCrop(crop: String) {
        if (crops.contains(crop)) {
            crops.toMutableList().remove(crop)
            println("$crop has been removed from the farm.")
        } else {
            println("$crop not found on the farm.")
        }
    }

    fun estimateYield(): Double {
        val averageYieldPerCrop = 1000 // kg per year
        return crops.size * averageYieldPerCrop.toDouble()
    }
}
