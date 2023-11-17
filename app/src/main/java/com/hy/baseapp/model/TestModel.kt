package com.hy.baseapp.model

import com.google.errorprone.annotations.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class TestModel(val name:String)
