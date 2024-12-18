plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
}

android {
    namespace = "com.psfilter.githubactionstest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.psfilter.githubactionstest"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    tasks.register<Copy>("copyPrePushHook") {
        description = "Copy pre-push hook from scripts to git directory"
        group = "git hooks"
        outputs.upToDateWhen { false }
        from("$rootDir/scripts/pre-push")
        into("$rootDir/.git/hooks/")
    }

    tasks.build {
        dependsOn("copyPrePushHook")
    }

    tasks.register("installGitHooks", Exec::class.java) {
        description = "Installs the pre-push git hooks from /git-hooks."
        group = "git hooks"
        workingDir = rootDir
        commandLine = listOf("chmod")
        args("-R", "+x", ".git/hooks/")
        dependsOn("copyPrePushHook")
        doLast {
            logger.info("Git hook installed successfully.")
        }
    }

    dependencies {

        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.appcompat)
        implementation(libs.material)
        implementation(libs.androidx.activity)
        implementation(libs.androidx.constraintlayout)
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
    }
}
