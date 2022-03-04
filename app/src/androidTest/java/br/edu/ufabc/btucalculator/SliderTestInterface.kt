package br.edu.ufabc.btucalculator

interface SliderTestInterface {
    fun setUp()
    fun isDisplayed()
    fun hasCorrectBoundaries()
    fun hasCorrectInitialValue()
    fun labelHasCorrectInitialValue()
    fun properlyUpdatesLabel()
    fun properlyUpdatesBTU()
}
