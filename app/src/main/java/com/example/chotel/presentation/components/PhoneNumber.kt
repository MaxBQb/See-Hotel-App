package com.example.chotel.presentation.components

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneVisualTransformation(
    val mask: String = "+7 (***) ***-**-**",
    val maskNumber: Char = '*'
) : VisualTransformation {
    private val maxLength = mask.count { it == maskNumber }

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.length > maxLength) text.take(maxLength) else text

        val annotatedString = buildAnnotatedString {
            var maskIndex = 0
            var textIndex = 0
            while (textIndex < trimmed.length && maskIndex < mask.length) {
                if (mask[maskIndex] != maskNumber) {
                    val nextDigitIndex = mask.indexOf(maskNumber, maskIndex)
                    append(mask.substring(maskIndex, nextDigitIndex))
                    maskIndex = nextDigitIndex
                }
                append(trimmed[textIndex++])
                maskIndex++
            }
            append(mask.substring(maskIndex))
        }

        return TransformedText(annotatedString, PhoneOffsetMapper(mask, maskNumber, trimmed.length))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PhoneVisualTransformation) return false
        if (mask != other.mask) return false
        if (maskNumber != other.maskNumber) return false
        return true
    }

    override fun hashCode(): Int {
        return mask.hashCode()
    }
}

private class PhoneOffsetMapper(
    val mask: String,
    val numberChar: Char,
    val maxLength: Int,
) : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int {
        var noneDigitCount = 0
        var i = 0
        while (i < offset + noneDigitCount && i < mask.length) {
            if (mask[i++] != numberChar) noneDigitCount++
        }
        return (offset + noneDigitCount).coerceIn(mask.indexOf(numberChar), mask.length)
    }

    override fun transformedToOriginal(offset: Int): Int =
        (offset - mask.take(offset).count { it != numberChar }).coerceAtMost(maxLength)
}