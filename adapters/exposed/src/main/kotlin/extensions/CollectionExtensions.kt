package extensions

fun Iterable<*>.getInsideType(): Class<*>? {
    this.iterator().next()?.let {
        return it.javaClass
    }
    return null
}