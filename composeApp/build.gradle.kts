import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
}

kotlin {
    jvm("desktop")

    jvm("backend")

    sourceSets {

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            // Shared dependencies
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.1")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.1")

            // ObjectBox
            implementation("io.objectbox:objectbox-kotlin:3.7.1")

            // Ktor common
            implementation("io.ktor:ktor-client-core:2.3.6")
            implementation("io.ktor:ktor-client-content-negotiation:2.3.6")
            implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.6")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.kotlinx.coroutinesSwing)
            }
        }
        val backendMain by getting {
            dependencies {
                // Ktor server
                implementation("io.ktor:ktor-server-core:2.3.6")
                implementation("io.ktor:ktor-server-netty:2.3.6")
                implementation("io.ktor:ktor-server-content-negotiation:2.3.6")
                implementation("io.ktor:ktor-server-cors:2.3.6")
                implementation("io.ktor:ktor-server-call-logging:2.3.6")
                implementation("io.ktor:ktor-server-status-pages:2.3.6")

                // Backend-specific ObjectBox
                implementation("io.objectbox:objectbox-linux:3.7.1")
                implementation("io.objectbox:objectbox-macos:3.7.1")
                implementation("io.objectbox:objectbox-windows:3.7.1")

                // Logging
                implementation("ch.qos.logback:logback-classic:1.4.11")
            }
        }
    }
}


compose.desktop {
    application {
        mainClass = "com.butterknifebudgeting.app.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.butterknifebudgeting.app"
            packageVersion = "1.0.0"
        }
    }
}
