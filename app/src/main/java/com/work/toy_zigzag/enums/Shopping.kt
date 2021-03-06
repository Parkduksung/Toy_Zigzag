package com.work.toy_zigzag.enums

enum class Sort {
    AGE, STYLE
}

enum class ClickState(val value: Int) {
    CHECK(1), UNCHECK(0)
}

enum class CornerState(val value: Int) {
    CORNER_LEFT(0), CORNER_RIGHT(1), CORNER_ROUND(2), CORNER_STRIKE_WIDTH(2)
}

enum class ValueState(val value: Int) {
    EMPTY(0), EXIST(1),
}