
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.parcelize)
}
android {
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        multiDexEnabled =  true
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        buildConfig = true
    }
    dataBinding {
        enable = true
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

    namespace  = "me.hy.jetpackmvvm" //去除gradle对library module默认只编译release buildType的限制
}


dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar","*.aar"))))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    api(libs.net.urlManager)
    api(libs.androidx.activity.ktx)
    api(libs.androidx.fragment.ktx)
    api(libs.androidx.lifecycle.viewmodel.ktx)
    api(libs.androidx.lifecycle.runtime.ktx)
    api(libs.androidx.lifecycle.extension)
    api(libs.androidx.lifecycle.commonJava8)
    api(libs.androidx.lifecycle.livedata.ktx)
    api(libs.androidx.annotation)
    api(libs.third.unPeekLiveData)
    api(libs.retrofit2.converter.gson)
    api(libs.retrofit2)
    api(libs.okhttp)
    api(libs.third.utilcode)
    api(libs.third.unPeekLiveData)
    api(libs.androidx.recyclerview)
    api(libs.androidx.viewpager2)
}

