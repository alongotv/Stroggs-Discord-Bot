import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

kapt {
    generateStubs = true
}

plugins {
    kotlin("jvm") version "1.7.0"
    kotlin("kapt") version "1.7.10"
    application
}

group = "com.alongo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://m2.dv8tion.net/releases")

}

dependencies {
    val kordVersion = "0.8.x-SNAPSHOT"
    val zxingVersion = "3.5.1"
    val lavaPlayerVersion = "1.3.77"
    val daggerVersion = "2.43.2"

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("com.google.zxing:core:$zxingVersion")
    implementation("com.google.zxing:javase:$zxingVersion")

    testImplementation(kotlin("test"))
    implementation("dev.kord:kord-core:$kordVersion") {
        capabilities {
            requireCapability("dev.kord:core-voice:$kordVersion")
        }
    }
    implementation("dev.kord:kord-voice:$kordVersion")
    implementation("com.sedmelluq:lavaplayer:$lavaPlayerVersion")


    implementation("org.slf4j:slf4j-simple:1.7.36")

    implementation ("com.google.dagger:dagger:$daggerVersion")
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("com.alongo.discordbot.Main")
}

tasks.withType<Jar> {
    manifest.attributes["Main-Class"] = "com.alongo.discordbot.Main"
    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map(::zipTree) // OR .map { zipTree(it) }
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}