package com.hy.bella.net.exception

open class NoNetException(
    message: String? = null,
    cause: Throwable? = null
):Exception(message, cause)