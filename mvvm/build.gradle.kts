import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.github.dcendents.android-maven")
    id("com.jfrog.bintray")
    id("org.jetbrains.dokka-android")
}

group = ProjectSettings.group
version = ProjectSettings.version

android {
    compileSdkVersion(ProjectSettings.compileSdk)

    defaultConfig {
        minSdkVersion(ProjectSettings.minSdk)
        targetSdkVersion(ProjectSettings.targetSdk)
    }

    dataBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(kotlin(Deps.Kotlin.stdlib, KotlinCompilerVersion.VERSION))
    implementation(kotlin(Deps.Kotlin.reflect, KotlinCompilerVersion.VERSION))
    implementation(Deps.javaX)

    implementation(Deps.AndroidX.appcompat)
    implementation(Deps.AndroidX.material)
    implementation(Deps.AndroidX.annnotation)
    implementation(Deps.AndroidX.lifecycleExtensions)
    implementation(Deps.AndroidX.livedataExtensions)
    kapt(Deps.AndroidX.lifecycleCompiler)

    testImplementation(Deps.Test.jUnit)
    testImplementation(Deps.Test.assertJ)
    testImplementation(Deps.Test.mockitoKotlin)
    testImplementation(Deps.AndroidX.archTesting)
}

tasks {
    val sourcesJar by creating(type = Jar::class) {
        from(android.sourceSets.getByName("main").java.srcDirs)
        classifier = "sources"
    }

    val dokka by getting(DokkaTask::class) {
        outputFormat = "javadoc"
        outputDirectory = "$buildDir/dokka"
    }

    val kotlinDocJar by creating(type = Jar::class) {
        dependsOn(dokka)
        classifier = "javadoc"
        from("$buildDir/dokka")
    }

    artifacts {
        add("archives", sourcesJar)
        add("archives", kotlinDocJar)
    }
}

project.apply {
    extensions.add("artifact", ProjectSettings.Mvvm.artifact)
    extensions.add("libraryName", ProjectSettings.Mvvm.artifact)
    extensions.add("libraryDescription", ProjectSettings.Mvvm.libraryDescription)
}

apply("../publish.script.gradle")
