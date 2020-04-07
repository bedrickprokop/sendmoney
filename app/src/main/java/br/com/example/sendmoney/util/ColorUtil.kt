package br.com.example.sendmoney.util

import androidx.core.graphics.ColorUtils

object ColorUtil {

    private const val MEDIUM_LUMINANCE: Double = 0.5

    fun isLight(color: Int): Boolean = ColorUtils.calculateLuminance(color) > MEDIUM_LUMINANCE

}