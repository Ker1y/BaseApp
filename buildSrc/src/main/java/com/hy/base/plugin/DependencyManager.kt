package com.hy.base.plugin

/**
 * <pre>
 *     author: Hy
 *     date  : 2020/7/3
 *     desc  : 如果数量少的话，放在一个类里面就可以，如果数量多的话，可以拆分为多个类
 * </pre>
 */

object Versions {
    const val retrofit = "2.9.0"
    const val okhttp = "4.11.0"
    const val appcompat = "1.6.1"
    const val coreKtx = "1.9.0"
    const val constraintlayout = "2.1.3"
    const val paging = "3.0.0-alpha02"
    const val timber = "4.7.1"
    const val kotlin = "1.9.0"
    const val kotlinCoroutinesCore = "1.7.3"
    const val kotlinCoroutinesAndroid = "1.6.0"
    const val koin = "2.1.5"
    const val work = "2.2.0"
    const val room = "2.5.0"
    const val splash = "1.0.0"
    const val cardview = "1.0.0"
    const val recyclerview = "1.0.0"
    const val fragment = "1.4.1"
    const val anko = "0.10.8"
    const val swiperefreshlayout = "1.1.0"
    const val junit = "4.13.2"
    const val junitExt = "1.1.3"
    const val espressoCore = "3.4.0"
    const val jDatabinding = "1.0.4"
    const val runtime = "1.1.0"
    const val hit = "2.28-alpha"
    const val hitViewModule = "1.0.0-alpha01"
    const val appStartup = "1.0.0"
    const val material = "1.5.0"
    const val binding = "1.1.3"
    const val bugly = "4.0.4"
    const val ktkitSnapshot = "1.0.0-SNAPSHOT"
    const val ktkit = "1.0.0"
    const val lifecycleVersion = "2.5.1"
    const val lifecycleExtensions = "2.2.0"
    const val flexbox = "3.0.0"
    const val immersionBar = "3.2.2"
    const val mmkv = "1.2.10"
    const val autosize = "1.2.1"
    const val backgroundLibrary = "1.7.5"
    const val baseRecyclerViewAdapterHelper = "3.0.6"
    const val brv = "BRV:1.5.5"
    const val net = "Net:3.6.3"
    const val span = "spannablex:1.0.4"
    const val toolTip = "Tooltip:1.2.3"
    const val retrofitAdapter = "3.0.0"
    const val materialDialogs = "3.3.0"
    const val glide = "4.16.0"
    const val basePopup = "3.2.1"
    const val xxPermissions = "18.3"
    const val smartRefreshLayout = "2.0.5"
    const val gifDrawable = "1.2.6"
    const val unPeekLiveData = "7.8.0"
    const val navigation = "2.5.3"
    const val cookieJar = "v1.0.1"
    const val urlManager = "1.4.0"
    const val utilCodex = "1.31.0"
    const val viewPager2 = "1.0.0"
    const val pictureSelector = "v3.11.1"
    const val pictureSelectorCompress = "v3.11.1"
    const val uCrop = "v3.11.1"
    const val camerax = "v3.11.1"
    const val tencentTbs = "tbssdk:44216"
    const val rWidget = "androidx.v0.0.4"
    const val moshi = "moshi-kotlin:1.14.0"
    const val stateLayout = "StateLayout:1.4.2"
    const val googleAuth = "play-services-auth:20.7.0"
    const val emoji = "0.18.0-SNAPSHOT"
    const val indicator = "1.1.6"
    const val progressBar = "2.2.1"
    const val gridLayoutManager = "1.0.6"
    const val spiderMan = "spiderman:v1.1.9"
}

object AndroidX {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val pagingRuntime = "androidx.paging:paging-runtime:${Versions.paging}"

    const val workRuntime = "androidx.work:work-runtime:${Versions.work}"
    const val workTesting = "androidx.work:work-testing:${Versions.work}"
    const val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val swiperefreshlayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swiperefreshlayout}"
    const val appStartup = "androidx.startup:startup-runtime:${Versions.appStartup}"
    const val navigation = "androidx.navigation:navigation-runtime-ktx:${Versions.navigation}"
    const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
    const val splashScreen = "androidx.core:core-splashscreen:${Versions.splash}"
}

object Android {
    const val material = "com.google.android.material:material:${Versions.material}"
    const val libphonenumber = "com.googlecode.libphonenumber:libphonenumber:7.2.2"
    const val flexbox = "com.google.android.flexbox:flexbox:${Versions.flexbox}"
}

object Hilt {
    const val daggerRuntime = "com.google.dagger:hilt-android:${Versions.hit}"
    const val daggerCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hit}"
    const val viewModule = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hitViewModule}"
    const val compiler = "androidx.hilt:hilt-compiler:${Versions.hitViewModule}"
}

object Coil {
    const val runtime = "io.coil-kt:coil:${Versions.runtime}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.room}"
    const val compiler = "androidx.room:room-compiler:${Versions.room}"
    const val ktx = "androidx.room:room-ktx:${Versions.room}"
    const val rxjava2 = "androidx.room:room-rxjava2:${Versions.room}"
    const val testing = "androidx.room:room-testing:${Versions.room}"
}

object Fragment {
    const val runtime = "androidx.fragment:fragment:${Versions.fragment}"
    const val runtimeKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val testing = "androidx.fragment:fragment-testing:${Versions.fragment}"
}

object Kt {
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutinesCore}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutinesAndroid}"
    const val test = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object Koin {
    const val core = "org.koin:koin-core:${Versions.koin}"
    const val androidCore = "org.koin:koin-android:${Versions.koin}"
    const val viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val androidScope = "org.koin:koin-android-scope:$${Versions.koin}"
}

object Anko {
    const val common = "org.jetbrains.anko:anko-common:${Versions.anko}"
    const val sqlite = "org.jetbrains.anko:anko-sqlite:${Versions.anko}"
    const val coroutines = "org.jetbrains.anko:anko-coroutines:${Versions.anko}"
    const val design = "org.jetbrains.anko:anko-design:${Versions.anko}" // For SnackBars
}

object Net {
    const val runtime = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val mock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val retrofitAdapter = "com.github.akarnokd:rxjava3-retrofit-adapter:${Versions.retrofitAdapter}"
    const val urlManager = "me.jessyan:retrofit-url-manager:${Versions.urlManager}"
    const val moshi = "com.squareup.moshi:${Versions.moshi}"
    const val net = "com.github.liangjingkanji:${Versions.net}"
}

object Depend {
    const val junit = "junit:junit:${Versions.junit}"
    const val androidTestJunit = "androidx.test.ext:junit:${Versions.junitExt}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val brv = "com.github.liangjingkanji:${Versions.brv}"
    const val pictureSelector = "io.github.lucksiege:pictureselector:${Versions.pictureSelector}"
    const val pictureSelectorCompress = "io.github.lucksiege:compress:${Versions.pictureSelectorCompress}"
    const val jDatabinding = "com.hy-base:jdatabinding:${Versions.jDatabinding}"
    const val binding = "com.hy-base:binding:${Versions.binding}"
    const val ktkit = "com.hy-base:ktkit:${Versions.ktkit}"
    const val ktkitSnapshot = "com.hy-base:ktkit:${Versions.ktkitSnapshot}"
    const val immersionBar = "com.geyifeng.immersionbar:immersionbar:${Versions.immersionBar}"
    const val immersionBarKtx = "com.geyifeng.immersionbar:immersionbar-ktx:${Versions.immersionBar}"
    const val mmkv = "com.tencent:mmkv-static:${Versions.mmkv}"
    const val autoSize = "me.jessyan:autosize:${Versions.autosize}"
    const val backgroundLibrary = "com.github.JavaNoober.BackgroundLibrary:libraryx:${Versions.backgroundLibrary}"
    const val baseRecyclerViewAdapterHelper = "com.github.CymChad:BaseRecyclerViewAdapterHelper:${Versions.baseRecyclerViewAdapterHelper}"
    const val materialDialogs = "com.afollestad.material-dialogs:core:${Versions.materialDialogs}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    const val basePop = "io.github.razerdp:BasePopup:${Versions.basePopup}"
    const val xxPermissions = "com.github.getActivity:XXPermissions:${Versions.xxPermissions}"
    const val uCrop = "io.github.lucksiege:ucrop:${Versions.uCrop}"
    const val smartRefreshLayoutKernel = "io.github.scwang90:refresh-layout-kernel:${Versions.smartRefreshLayout}"
    const val smartRefreshLayoutHeaderClassics = "io.github.scwang90:refresh-header-classics:${Versions.smartRefreshLayout}"
    const val smartRefreshLayoutHeaderMaterial = "io.github.scwang90:refresh-header-material:${Versions.smartRefreshLayout}"
    const val gifDrawable = "pl.droidsonroids.gif:android-gif-drawable:${Versions.gifDrawable}"
    const val unPeekLiveData = "com.kunminx.arch:unpeek-livedata:${Versions.unPeekLiveData}"
    const val cookieJar = "com.github.franmontiel:PersistentCookieJar:${Versions.cookieJar}"
    const val utilCodex = "com.blankj:utilcodex:${Versions.utilCodex}"
    const val kotlinExtPersonal = "com.liupei:kotlin-extension:1.2.3'"
    const val stateLayout = "com.github.liangjingkanji:${Versions.stateLayout}"
    const val iosEmoji = "com.vanniktech:emoji-ios:${Versions.emoji}"
    const val toolTip = "com.github.liangjingkanji:${Versions.toolTip}"
    const val span = "com.itxca.spannablex:${Versions.span}"
    const val indicator = "com.github.XuQK:KDTabLayout:${Versions.indicator}"
    const val progressBar = "com.akexorcist:round-corner-progress-bar:${Versions.progressBar}"
    const val gridLayoutManager = "github.hellocsl:GalleryLayoutManager:${Versions.gridLayoutManager}"
    const val spiderMan = "com.github.simplepeng.SpiderMan:${Versions.spiderMan}"
}

object Google {
    const val googleAuth = "com.google.android.gms:${Versions.googleAuth}"
    const val firebaseAuth = "com.google.firebase:firebase-auth-ktx"
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx:21.3.0"
    const val firebaseCrash = "com.google.firebase:firebase-crashlytics-ktx:18.3.7"
    const val firebaseBoom = "com.google.firebase:firebase-bom:32.5.0"
}

object Lifecycle {
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}" // viewModelScope
    const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}" // lifecycleScope
    const val commonJava8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycleVersion}"
    const val extension = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}"
    const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"
}