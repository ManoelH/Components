package com.manoelh.components.mock

class Mock private constructor(){

    companion object{
        fun getListAnimals() = listOf("Cat", "Dog", "Beer", "Bird", "Horse")
    }
}