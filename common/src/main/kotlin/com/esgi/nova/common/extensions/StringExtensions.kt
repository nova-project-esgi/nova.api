package com.esgi.nova.common.extensions

import java.util.*

fun String.toNegativeRegex(): Regex = Regex("^(?!${this}).*\$")
fun String.toUUID(): UUID = UUID.fromString(this);
fun String.withoutExtension(): String = this.substring(0, this.lastIndexOf('.'))