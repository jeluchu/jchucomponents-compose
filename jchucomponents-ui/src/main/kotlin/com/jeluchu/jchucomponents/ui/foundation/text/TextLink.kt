@file:Suppress("unused")

package com.jeluchu.jchucomponents.ui.foundation.text

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.LocalTextStyle
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

/**
 *
 * @param text [Int] ID of the [StringRes] to be displayed
 * @param modifier the [Modifier] to be applied to this layout node
 * @param enabled controls the enabled state of this button. When `false`, this component will not
 * respond to user input, and it will appear visually disabled and disabled to accessibility
 * services.
 * @param shape defines the shape of this button's container, border (when [border] is not null),
 * and shadow (when using [elevation])
 * @param colors [ButtonColors] that will be used to resolve the colors for this button in different
 * states. See [ButtonDefaults.textButtonColors].
 * @param elevation [ButtonElevation] used to resolve the elevation for this button in different
 * states. This controls the size of the shadow below the button. Additionally, when the container
 * color is [ColorScheme.surface], this controls the amount of primary color applied as an overlay.
 * A TextButton typically has no elevation, and the default value is `null`. See [ElevatedButton]
 * for a button with elevation.
 * @param border the border to draw around the container of this button
 * @param contentPadding the spacing values to apply internally between the container and the
 * content
 *
 * If [Color.Unspecified], and [style] has no color set,
 * this will be [LocalContentColor]
 *
 * @param fontSize the size of glyphs to use when painting the text. See [TextStyle.fontSize]
 * @param fontStyle the typeface variant to use when drawing the letters (e.g., italic)
 * See [TextStyle.fontStyle]
 * @param fontWeight the typeface thickness to use when painting the text (e.g., [FontWeight.Bold])
 * @param fontFamily the font family to be used when rendering the text. See [TextStyle.fontFamily]
 * @param letterSpacing the amount of space to add between each letter.
 * See [TextStyle.letterSpacing]
 * @param textDecoration the decorations to paint on the text (e.g., an underline).
 * See [TextStyle.textDecoration]
 * @param textAlign the alignment of the text within the lines of the paragraph.
 * See [TextStyle.textAlign]
 * @param lineHeight line height in [TextUnit] unit, e.g. SP or EM.
 * See [TextStyle.lineHeight]
 * @param overflow how visual overflow should be handled.
 * @param softWrap whether the text should break at soft line breaks
 *
 * If false, the glyphs in the
 * text will be positioned as if there was unlimited horizontal space.
 * If [softWrap] is false,
 * [overflow] and TextAlign may have unexpected effects
 *
 * @param maxLines an optional maximum number of lines for the text to span, wrapping if
 * necessary
 *
 * If the text exceeds the given number of lines, it will be truncated according to
 * [overflow] and [softWrap]
 *
 * If it is not null, then it must be greater than zero
 *
 * @param onTextLayout callback that is executed when a new text layout is calculated
 * A [TextLayoutResult] object that callback provides contains paragraph information, size of the
 * text, baselines and other details
 *
 * The callback can be used to add additional decoration or
 * functionality to the text.
 *
 * @param style style configuration for the text such as color, font, line height etc.
 *
 * @param onClick action when user click text
 *
 */
@Composable
@NonRestartableComposable
fun TextLink(
    @StringRes text: Int,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.textShape,
    colors: ButtonColors = ButtonDefaults.textButtonColors(),
    elevation: ButtonElevation? = null,
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.TextButtonContentPadding,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current,
    onClick: () -> Unit
) = TextButton(
    modifier = modifier,
    onClick = onClick,
    enabled = enabled,
    shape = shape,
    colors = colors,
    elevation = elevation,
    border = border,
    contentPadding = contentPadding
) {
    Text(
        text = text,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout,
        style = style
    )
}

/**
 *
 * @param text [String] to be displayed
 * @param modifier the [Modifier] to be applied to this layout node
 * @param enabled controls the enabled state of this button. When `false`, this component will not
 * respond to user input, and it will appear visually disabled and disabled to accessibility
 * services.
 * @param shape defines the shape of this button's container, border (when [border] is not null),
 * and shadow (when using [elevation])
 * @param colors [ButtonColors] that will be used to resolve the colors for this button in different
 * states. See [ButtonDefaults.textButtonColors].
 * @param elevation [ButtonElevation] used to resolve the elevation for this button in different
 * states. This controls the size of the shadow below the button. Additionally, when the container
 * color is [ColorScheme.surface], this controls the amount of primary color applied as an overlay.
 * A TextButton typically has no elevation, and the default value is `null`. See [ElevatedButton]
 * for a button with elevation.
 * @param border the border to draw around the container of this button
 * @param contentPadding the spacing values to apply internally between the container and the
 * content
 *
 * If [Color.Unspecified], and [style] has no color set,
 * this will be [LocalContentColor]
 *
 * @param fontSize the size of glyphs to use when painting the text. See [TextStyle.fontSize]
 * @param fontStyle the typeface variant to use when drawing the letters (e.g., italic)
 * See [TextStyle.fontStyle]
 * @param fontWeight the typeface thickness to use when painting the text (e.g., [FontWeight.Bold])
 * @param fontFamily the font family to be used when rendering the text. See [TextStyle.fontFamily]
 * @param letterSpacing the amount of space to add between each letter.
 * See [TextStyle.letterSpacing]
 * @param textDecoration the decorations to paint on the text (e.g., an underline).
 * See [TextStyle.textDecoration]
 * @param textAlign the alignment of the text within the lines of the paragraph.
 * See [TextStyle.textAlign]
 * @param lineHeight line height in [TextUnit] unit, e.g. SP or EM.
 * See [TextStyle.lineHeight]
 * @param overflow how visual overflow should be handled.
 * @param softWrap whether the text should break at soft line breaks
 *
 * If false, the glyphs in the
 * text will be positioned as if there was unlimited horizontal space.
 * If [softWrap] is false,
 * [overflow] and TextAlign may have unexpected effects
 *
 * @param maxLines an optional maximum number of lines for the text to span, wrapping if
 * necessary
 *
 * If the text exceeds the given number of lines, it will be truncated according to
 * [overflow] and [softWrap]
 *
 * If it is not null, then it must be greater than zero
 *
 * @param onTextLayout callback that is executed when a new text layout is calculated
 * A [TextLayoutResult] object that callback provides contains paragraph information, size of the
 * text, baselines and other details
 *
 * The callback can be used to add additional decoration or
 * functionality to the text.
 *
 * @param style style configuration for the text such as color, font, line height etc.
 *
 * @param onClick action when user click text
 *
 */
@Composable
@NonRestartableComposable
fun TextLink(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.textShape,
    colors: ButtonColors = ButtonDefaults.textButtonColors(),
    elevation: ButtonElevation? = null,
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.TextButtonContentPadding,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current,
    onClick: () -> Unit
) = TextButton(
    modifier = modifier,
    onClick = onClick,
    enabled = enabled,
    shape = shape,
    colors = colors,
    elevation = elevation,
    border = border,
    contentPadding = contentPadding
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
        style = style,
    )
}

/**
 *
 * @param text [AnnotatedString] to be displayed
 * @param modifier the [Modifier] to be applied to this layout node
 * @param enabled controls the enabled state of this button. When `false`, this component will not
 * respond to user input, and it will appear visually disabled and disabled to accessibility
 * services.
 * @param shape defines the shape of this button's container, border (when [border] is not null),
 * and shadow (when using [elevation])
 * @param colors [ButtonColors] that will be used to resolve the colors for this button in different
 * states. See [ButtonDefaults.textButtonColors].
 * @param elevation [ButtonElevation] used to resolve the elevation for this button in different
 * states. This controls the size of the shadow below the button. Additionally, when the container
 * color is [ColorScheme.surface], this controls the amount of primary color applied as an overlay.
 * A TextButton typically has no elevation, and the default value is `null`. See [ElevatedButton]
 * for a button with elevation.
 * @param border the border to draw around the container of this button
 * @param contentPadding the spacing values to apply internally between the container and the
 * content
 *
 * If [Color.Unspecified], and [style] has no color set,
 * this will be [LocalContentColor]
 *
 * @param fontSize the size of glyphs to use when painting the text. See [TextStyle.fontSize]
 * @param fontStyle the typeface variant to use when drawing the letters (e.g., italic)
 * See [TextStyle.fontStyle]
 * @param fontWeight the typeface thickness to use when painting the text (e.g., [FontWeight.Bold])
 * @param fontFamily the font family to be used when rendering the text. See [TextStyle.fontFamily]
 * @param letterSpacing the amount of space to add between each letter.
 * See [TextStyle.letterSpacing]
 * @param textDecoration the decorations to paint on the text (e.g., an underline).
 * See [TextStyle.textDecoration]
 * @param textAlign the alignment of the text within the lines of the paragraph.
 * See [TextStyle.textAlign]
 * @param lineHeight line height in [TextUnit] unit, e.g. SP or EM.
 * See [TextStyle.lineHeight]
 * @param overflow how visual overflow should be handled.
 * @param softWrap whether the text should break at soft line breaks
 *
 * If false, the glyphs in the
 * text will be positioned as if there was unlimited horizontal space.
 * If [softWrap] is false,
 * [overflow] and TextAlign may have unexpected effects
 *
 * @param maxLines an optional maximum number of lines for the text to span, wrapping if
 * necessary
 *
 * If the text exceeds the given number of lines, it will be truncated according to
 * [overflow] and [softWrap]
 *
 * If it is not null, then it must be greater than zero
 *
 * @param onTextLayout callback that is executed when a new text layout is calculated
 * A [TextLayoutResult] object that callback provides contains paragraph information, size of the
 * text, baselines and other details
 *
 * The callback can be used to add additional decoration or
 * functionality to the text.
 *
 * @param style style configuration for the text such as color, font, line height etc.
 *
 * @param onClick action when user click text
 *
 */
@Composable
@NonRestartableComposable
fun TextLink(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.textShape,
    colors: ButtonColors = ButtonDefaults.textButtonColors(),
    elevation: ButtonElevation? = null,
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.TextButtonContentPadding,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current,
    onClick: () -> Unit
) = TextButton(
    modifier = modifier,
    onClick = onClick,
    enabled = enabled,
    shape = shape,
    colors = colors,
    elevation = elevation,
    border = border,
    contentPadding = contentPadding
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
        style = style,
    )
}