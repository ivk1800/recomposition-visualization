package ru.ivk1800.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.DefaultFillType
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathBuilder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@PublishedApi
internal const val MaterialIconDimension = 24f

inline fun materialIcon(
    name: String,
    autoMirror: Boolean = false,
    block: ImageVector.Builder.() -> ImageVector.Builder,
): ImageVector = ImageVector.Builder(
    name = name,
    defaultWidth = MaterialIconDimension.dp,
    defaultHeight = MaterialIconDimension.dp,
    viewportWidth = MaterialIconDimension,
    viewportHeight = MaterialIconDimension,
    autoMirror = autoMirror,
).block().build()

inline fun ImageVector.Builder.materialPath(
    fillAlpha: Float = 1f,
    strokeAlpha: Float = 1f,
    pathFillType: PathFillType = DefaultFillType,
    pathBuilder: PathBuilder.() -> Unit,
) =
    path(
        fill = SolidColor(Color.Black),
        fillAlpha = fillAlpha,
        stroke = null,
        strokeAlpha = strokeAlpha,
        strokeLineWidth = 1f,
        strokeLineCap = StrokeCap.Butt,
        strokeLineJoin = StrokeJoin.Bevel,
        strokeLineMiter = 1f,
        pathFillType = pathFillType,
        pathBuilder = pathBuilder,
    )

val ArrowBack: ImageVector
    get() {
        if (_arrowBack != null) {
            return _arrowBack!!
        }
        _arrowBack = materialIcon(name = "AutoMirrored.Filled.ArrowBack", autoMirror = true) {
            materialPath {
                moveTo(20.0f, 11.0f)
                horizontalLineTo(7.83f)
                lineToRelative(5.59f, -5.59f)
                lineTo(12.0f, 4.0f)
                lineToRelative(-8.0f, 8.0f)
                lineToRelative(8.0f, 8.0f)
                lineToRelative(1.41f, -1.41f)
                lineTo(7.83f, 13.0f)
                horizontalLineTo(20.0f)
                verticalLineToRelative(-2.0f)
                close()
            }
        }
        return _arrowBack!!
    }

private var _arrowBack: ImageVector? = null

val Github: ImageVector
    get() {
        if (_github != null) {
            return _github!!
        }
        _github = materialIcon(name = "Github", autoMirror = false) {
            materialPath {
                moveTo(12.0f, 0.297f)
                curveToRelative(-6.63f, 0.0f, -12.0f, 5.373f, -12.0f, 12.0f)
                curveToRelative(0.0f, 5.303f, 3.438f, 9.8f, 8.205f, 11.385f)
                curveToRelative(0.6f, 0.113f, 0.82f, -0.258f, 0.82f, -0.577f)
                curveToRelative(0.0f, -0.285f, -0.01f, -1.04f, -0.015f, -2.04f)
                curveToRelative(-3.338f, 0.724f, -4.042f, -1.61f, -4.042f, -1.61f)
                curveTo(4.422f, 18.07f, 3.633f, 17.7f, 3.633f, 17.7f)
                curveToRelative(-1.087f, -0.744f, 0.084f, -0.729f, 0.084f, -0.729f)
                curveToRelative(1.205f, 0.084f, 1.838f, 1.236f, 1.838f, 1.236f)
                curveToRelative(1.07f, 1.835f, 2.809f, 1.305f, 3.495f, 0.998f)
                curveToRelative(0.108f, -0.776f, 0.417f, -1.305f, 0.76f, -1.605f)
                curveToRelative(-2.665f, -0.3f, -5.466f, -1.332f, -5.466f, -5.93f)
                curveToRelative(0.0f, -1.31f, 0.465f, -2.38f, 1.235f, -3.22f)
                curveToRelative(-0.135f, -0.303f, -0.54f, -1.523f, 0.105f, -3.176f)
                curveToRelative(0.0f, 0.0f, 1.005f, -0.322f, 3.3f, 1.23f)
                curveToRelative(0.96f, -0.267f, 1.98f, -0.399f, 3.0f, -0.405f)
                curveToRelative(1.02f, 0.006f, 2.04f, 0.138f, 3.0f, 0.405f)
                curveToRelative(2.28f, -1.552f, 3.285f, -1.23f, 3.285f, -1.23f)
                curveToRelative(0.645f, 1.653f, 0.24f, 2.873f, 0.12f, 3.176f)
                curveToRelative(0.765f, 0.84f, 1.23f, 1.91f, 1.23f, 3.22f)
                curveToRelative(0.0f, 4.61f, -2.805f, 5.625f, -5.475f, 5.92f)
                curveToRelative(0.42f, 0.36f, 0.81f, 1.096f, 0.81f, 2.22f)
                curveToRelative(0.0f, 1.606f, -0.015f, 2.896f, -0.015f, 3.286f)
                curveToRelative(0.0f, 0.315f, 0.21f, 0.69f, 0.825f, 0.57f)
                curveTo(20.565f, 22.092f, 24.0f, 17.592f, 24.0f, 12.297f)
                curveToRelative(0.0f, -6.627f, -5.373f, -12.0f, -12.0f, -12.0f)
            }
        }
        return _github!!
    }

private var _github: ImageVector? = null

val DarkMode: ImageVector
    get() {
        if (_darkMode != null) {
            return _darkMode!!
        }
        _darkMode = materialIcon(name = "DarkMode", autoMirror = false) {
            materialPath {
                path(
                    fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(12.0f, 16.0f)
                    curveTo(14.209f, 16.0f, 16.0f, 14.209f, 16.0f, 12.0f)
                    curveTo(16.0f, 9.791f, 14.209f, 8.0f, 12.0f, 8.0f)
                    verticalLineTo(16.0f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd,
                ) {
                    moveTo(12.0f, 2.0f)
                    curveTo(6.477f, 2.0f, 2.0f, 6.477f, 2.0f, 12.0f)
                    curveTo(2.0f, 17.523f, 6.477f, 22.0f, 12.0f, 22.0f)
                    curveTo(17.523f, 22.0f, 22.0f, 17.523f, 22.0f, 12.0f)
                    curveTo(22.0f, 6.477f, 17.523f, 2.0f, 12.0f, 2.0f)
                    close()
                    moveTo(12.0f, 4.0f)
                    verticalLineTo(8.0f)
                    curveTo(9.791f, 8.0f, 8.0f, 9.791f, 8.0f, 12.0f)
                    curveTo(8.0f, 14.209f, 9.791f, 16.0f, 12.0f, 16.0f)
                    verticalLineTo(20.0f)
                    curveTo(16.418f, 20.0f, 20.0f, 16.418f, 20.0f, 12.0f)
                    curveTo(20.0f, 7.582f, 16.418f, 4.0f, 12.0f, 4.0f)
                    close()
                }
            }
        }
        return _darkMode!!
    }

private var _darkMode: ImageVector? = null

val AlertCircle: ImageVector
    get() {
        if (_alertCircle != null) {
            return _alertCircle!!
        }
        _alertCircle = materialIcon(name = "Infosys", autoMirror = false) {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                path(
                    fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 2.0f, strokeLineCap = Round, strokeLineJoin =
                        StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero,
                ) {
                    moveTo(12.0f, 12.0f)
                    moveToRelative(-9.0f, 0.0f)
                    arcToRelative(9.0f, 9.0f, 0.0f, true, true, 18.0f, 0.0f)
                    arcToRelative(9.0f, 9.0f, 0.0f, true, true, -18.0f, 0.0f)
                }
                path(
                    fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 2.0f, strokeLineCap = Round, strokeLineJoin =
                        StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero,
                ) {
                    moveTo(12.0f, 8.0f)
                    lineTo(12.0f, 12.0f)
                }
                path(
                    fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 2.0f, strokeLineCap = Round, strokeLineJoin =
                        StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero,
                ) {
                    moveTo(12.0f, 16.0f)
                    lineTo(12.01f, 16.0f)
                }
            }
        }
        return _alertCircle!!
    }

private var _alertCircle: ImageVector? = null
