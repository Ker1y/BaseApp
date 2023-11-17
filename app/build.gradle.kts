

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    useLibrary ("org.apache.http.legacy")
    compileSdk = libs.versions.compileSdk.get().toInt()


//    signingConfigs {
//        config {
//            storeFile file('..\\key/fomo')
//            storePassword ("qwerasdf1234")
//            keyAlias ('fomo')
//            keyPassword ('qwerasdf1234')
//        }
//    }

//    android.applicationVariants.all { variant ->
//            val variant = this
//            val dateString = Date().format("yyyy-MM-dd HH-mm")
//            variant.outputs
//                .map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
//                .forEach { output ->
//                    val outputFileName = "base_${variant.baseName}_${variant.versionName}_${dateString}.apk"
//                    println("OutputFileName: $outputFileName")
//                    output.outputFileName = outputFileName
//                }
//        }

    defaultConfig {
        applicationId = libs.versions.applicationId.get()
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "com.google.samples.apps.sunflower.utilities.MainTestRunner"
        ndk {
            abiFilters.addAll(arrayOf("armeabi-v7a", "arm64-v8a", "x86_64"))//, 'x86_64' , 'armeabi','mips','mips64',))
        }
    }

    buildTypes {
        release {
            buildConfigField("String", "HTTP_HOST_URL", libs.versions.hostUrl.get())
            isShrinkResources = true
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            buildConfigField("String", "HTTP_HOST_URL", libs.versions.debugUrl.get())
            isShrinkResources = false
            isMinifyEnabled = false
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    sourceSets["main"].java {
        srcDir("libs")
    }


    buildFeatures {
        buildConfig = true
        dataBinding = true
    }


    dataBinding {
        enable = true
    }
    lintOptions {
        disable ("NullSafeMutableLiveData")
    }
    namespace = "com.hy.baseapp"
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar","*.aar"))))

    implementation(project(":base_mvvm"))

    ksp(libs.androidx.room.compiler)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.splashscreen)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.net.net)
    implementation(libs.net.moshi)

    implementation(libs.third.brv)
    implementation(libs.third.backgroundLibrary)
    implementation(libs.third.mmkv)
    implementation(libs.third.span)
    implementation(libs.third.immersionKtx)
    implementation(libs.third.immersionBar)
    implementation(libs.third.basePop)
    implementation(libs.third.gifDrawable)
    implementation(libs.third.pictureSelector)
    implementation(libs.third.pictureSelectorCompress)
    implementation(libs.third.stateLayout)
    implementation(libs.third.toolTip)
    implementation(libs.third.uCrop)
    implementation(libs.third.xxPermissions)


    debugImplementation(libs.third.spiderMan)


    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation(libs.junit)


}