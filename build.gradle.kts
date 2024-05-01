import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.23"
    id("com.google.devtools.ksp") version "1.9.23-1.0.20"
    id("io.gitlab.arturbosch.detekt").version("1.23.3")
    application
}

group = "com.alongo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://m2.dv8tion.net/releases")
    maven("https://maven.lavalink.dev/snapshots")
    maven("https://jitpack.io")
}

detekt {
    autoCorrect = true
}

dependencies {
    val kordVersion = "0.11.1"
    val zxingVersion = "3.5.3"
    val lavaPlayerVersion = "0eaeee195f0315b2617587aa3537fa202df07ddc-SNAPSHOT"
    val daggerVersion = "2.51.1"

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("com.google.zxing:core:$zxingVersion")
    implementation("com.google.zxing:javase:$zxingVersion")

    testImplementation(kotlin("test"))
    implementation("dev.kord:kord-core:$kordVersion")
    implementation("dev.kord:kord-core-voice:$kordVersion")
    implementation("dev.kord:kord-voice:$kordVersion")
    implementation("dev.arbjerg:lavaplayer:$lavaPlayerVersion")
    implementation("org.slf4j:slf4j-simple:1.7.36")

    implementation("com.google.dagger:dagger:$daggerVersion")
    ksp("com.google.dagger:dagger-compiler:$daggerVersion")

    detekt("io.gitlab.arturbosch.detekt:detekt-cli:1.23.3")
}


tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainClass.set("com.alongo.discordbot.Main")
}

tasks.withType<Jar> {
    manifest.attributes["Main-Class"] = "com.alongo.discordbot.Main"
    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map(::zipTree)
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}