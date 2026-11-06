plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

dependencies {
    implementation(project(":shared"))

    // --- CMPDevicePreview Dependency ---
    // Option 1 (Default / Portable): Pre-compiled standalone JVM JAR with custom dialog layer & web frame.
    // Built from: https://github.com/Coding-Meet/CMPDevicePreview
    // Can also be copied and reused in any other Compose project without needing CMPDevicePreview submodule.
    implementation(files("libs/cmpdevicepreview-jvm.jar"))

    // Option 2 (Source / Debugging): Link local source module for active development or troubleshooting.
    // If using this, also uncomment `:cmpdevicepreview` lines in settings.gradle.kts.
    // implementation(project(":cmpdevicepreview"))

    // Official upstream library (reference only; currently has dialog and web frame limitations)
    // implementation("com.apadmi:cmpdevicepreview:0.1.1")

    implementation(compose.desktop.currentOs)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material3)
    implementation(libs.material.icons.extended)
    implementation(libs.compose.components.resources)
    implementation(libs.kotlinx.coroutinesSwing)

    // Coil Image Loading
    implementation(libs.coil.compose)
    implementation(libs.coil.mp)

    // Koin
    implementation(libs.koin.core)
    implementation(libs.koin.compose)

    implementation(libs.compose.uiToolingPreview)

    testImplementation(kotlin("test"))
    @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
    testImplementation(compose.uiTest)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register<JavaExec>("exportDeviceScreenshots") {
    group = "documentation"
    description = "Exports realistic framed device screenshots of News KMP App."
    mainClass.set("com.coding.meet.newsapp.screenshot.ExportScreenshotsKt")
    classpath = sourceSets["main"].runtimeClasspath
    workingDir = rootProject.projectDir
    val outDir = project.findProperty("outputDir")?.toString() ?: "${rootProject.projectDir}/image"
    args = listOf(outDir)
}
