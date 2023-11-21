package config

import org.gradle.api.JavaVersion

object Config {
//    val android = AndroidConfig(
//        minSdkVersion = 21,
//        targetSdkVersion = 34,
//        compileSdkVersion = 34,
//        applicationId = "com.fjr619.calorietracker",
//        versionCode = 1,
//        versionName = "1.0",
//        nameSpace = "com.fjr619.calorietracker"
//    )
    val jvm = JvmConfig(
        javaVersion = JavaVersion.VERSION_17,
        kotlinJvm = JavaVersion.VERSION_17.toString(),
        freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
    )
}
//data class AndroidConfig(
//    val minSdkVersion : Int,
//    val targetSdkVersion : Int,
//    val compileSdkVersion : Int,
//    val applicationId : String,
//    val versionCode : Int,
//    val versionName : String,
//    val nameSpace: String
//)
data class JvmConfig(
    val javaVersion : JavaVersion,
    val kotlinJvm : String,
    val freeCompilerArgs : List<String>
)