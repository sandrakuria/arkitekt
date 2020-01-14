import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

group = ProjectSettings.group
version = ProjectSettings.version

android {
    compileSdkVersion(ProjectSettings.compileSdk)

    defaultConfig {
        minSdkVersion(ProjectSettings.minSdk)
        targetSdkVersion(ProjectSettings.targetSdk)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(project(":mvvm"))

    implementation(Deps.Test.mockk)
    implementation(Deps.Test.rxSchedulerRule)
    implementation(Deps.Test.androidXCoreTesting)

    implementation(kotlin(Deps.Kotlin.stdlib, KotlinCompilerVersion.VERSION))
    implementation(kotlin(Deps.Kotlin.reflect, KotlinCompilerVersion.VERSION))

    implementation(Deps.AndroidX.lifecycleExtensions)
    implementation(Deps.AndroidX.livedataExtensions)

    // RxJava
    implementation(Deps.Rx.rxKotlin)
    implementation(Deps.Rx.rxAndroid)
    implementation(Deps.Rx.rxJava)

    // Test
    testImplementation(Deps.Test.androidXTestRunnner)
    testImplementation(Deps.Test.androidXTestCore)
    testImplementation(Deps.Test.jUnit)
}

project.apply {
    extensions.add("artifact", ProjectSettings.RxUseCasesTest.artifact)
    extensions.add("libraryName", ProjectSettings.RxUseCasesTest.artifact)
    extensions.add("libraryDescription", ProjectSettings.RxUseCasesTest.libraryDescription)
}

apply("../publish.script.gradle")
