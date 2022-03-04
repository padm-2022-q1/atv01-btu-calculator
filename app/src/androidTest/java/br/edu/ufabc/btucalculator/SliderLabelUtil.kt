package br.edu.ufabc.btucalculator

class SliderLabelUtil(
    format: String,
    initial: Int
) {
    val initialFormatted = String.format(format, initial)
    val incorrectDefaultText = "Could not find TextView with default text '$initialFormatted'"
}
