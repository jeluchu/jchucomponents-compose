package com.jeluchu.jchucomponents.ktx.numbers

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

val BigDecimal.digitsCount
    get() = toString().length

fun BigDecimal.isSmallerOrEqual(compareTo: BigDecimal): Boolean = this <= compareTo

fun BigDecimal.isSmallerThan(compareTo: BigDecimal): Boolean = this < compareTo

fun BigDecimal.isSmallerOrEqualThanZero(): Boolean = this.isSmallerOrEqual(BigDecimal.ZERO)

fun BigDecimal.isGreaterOrEqual(compareTo: BigDecimal): Boolean = this >= compareTo

fun BigDecimal.isGreaterThan(compareTo: BigDecimal): Boolean = this > compareTo

fun BigDecimal.isInteger() = this.stripTrailingZeros().scale() <= 0

fun BigDecimal.isNotInteger() = !isInteger()

fun List<BigDecimal>.average() = sum() / size

fun List<BigDecimal>.abs() = map { it.abs() }

fun List<BigDecimal>.sum(): BigDecimal = if (this.isNotEmpty()) reduce { acc, it -> acc + it } else BigDecimal.ZERO

operator fun BigDecimal.div(int: Int) = this / int.toBigDecimal()

fun BigDecimal.isZero() = setScale(0, RoundingMode.UP) == BigDecimal.ZERO

fun BigDecimal.safeDiv(n: BigDecimal, context: MathContext = MathContext.DECIMAL128): BigDecimal =
    this.divide(if (n.isZero()) BigDecimal.ONE else n, context)

fun BigDecimal.isNotZero() = !isZero()

fun BigDecimal?.isNullOrZero() = this == null || isZero()

fun BigDecimal?.isNotNullOrZero() = !isNullOrZero()

fun BigDecimal.isPositive() = this > BigDecimal.ZERO

fun BigDecimal.isNegative() = this < BigDecimal.ZERO

fun BigDecimal.divide(n: Int, context: MathContext = MathContext.DECIMAL128): BigDecimal = divide(n.toBigDecimal(), context)

fun BigDecimal.divide(n: Float, context: MathContext = MathContext.DECIMAL128): BigDecimal = divide(n.toBigDecimal(), context)

fun BigDecimal.divide(n: Double, context: MathContext = MathContext.DECIMAL128): BigDecimal = divide(n.toBigDecimal(), context)

fun BigDecimal.multiply(n: Int, context: MathContext = MathContext.DECIMAL128): BigDecimal = multiply(n.toBigDecimal(), context)

fun BigDecimal.multiply(n: Float, context: MathContext = MathContext.DECIMAL128): BigDecimal = multiply(n.toBigDecimal(), context)

fun BigDecimal.multiply(n: Double, context: MathContext = MathContext.DECIMAL128): BigDecimal = multiply(n.toBigDecimal(), context)

fun BigDecimal.sharesNumberFormattedWithoutDecimal(): CharSequence {
    return try {
        val s = DecimalFormatSymbols()
        val format = DecimalFormat("#,##0", s)
        s.groupingSeparator = '.'
        format.decimalFormatSymbols = s
        format.format(this)
    } catch (e: Exception) {
        "0"
    }
}