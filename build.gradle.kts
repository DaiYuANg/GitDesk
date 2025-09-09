import io.miret.etienne.gradle.sass.CompileSass

plugins {
  java
  application
  id("org.javamodularity.moduleplugin") version "1.8.15"
  id("org.openjfx.javafxplugin") version "0.1.0"
  id("org.beryx.jlink") version "3.1.3"
  id("com.github.johnrengelman.shadow") version "8.1.1"
  alias(libs.plugins.version.check)
  alias(libs.plugins.dotenv)
  alias(libs.plugins.lombok)
  alias(libs.plugins.spotless)
  alias(libs.plugins.git)
  alias(libs.plugins.graalvm.native)
  id("io.miret.etienne.sass") version ("1.5.2")
}

group = "org.unigit"
version = "1.0-SNAPSHOT"

repositories {
  mavenLocal()
  mavenCentral()
  google()
  maven("https://jitpack.io")
}

val junitVersion = "5.12.1"

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

tasks.withType<JavaCompile> {
  options.encoding = "UTF-8"
}

application {
  mainModule.set("org.git.desk")
  mainClass.set("org.git.desk.Launcher")
}

javafx {
  version = "21.0.6"
  modules = listOf("javafx.controls", "javafx.fxml")
}

val avajeInject = "11.6"
dependencies {
  implementation("dev.dirs:directories:26")
  implementation("com.github.Dansoftowner:jSystemThemeDetector:3.9.1"){
    exclude(group = "net.java.dev.jna", module = "jna")
    exclude(group = "net.java.dev.jna", module = "jna-platform")
  }
  implementation("net.java.dev.jna:jna:5.10.0-jpms")
  implementation("net.java.dev.jna:jna-platform:5.10.0")
  implementation("ch.qos.logback:logback-classic:1.5.18")
  implementation("com.fasterxml.jackson.core:jackson-databind:2.20.0")
  implementation("org.slf4j:slf4j-api:2.0.17")
  implementation("io.avaje:avaje-inject:${avajeInject}")
  annotationProcessor("io.avaje:avaje-inject-generator:${avajeInject}")
  implementation("org.controlsfx:controlsfx:11.2.2")
  implementation("io.github.mkpaz:atlantafx-base:2.1.0")
  implementation("io.github.snoopy137:language-manager:1.1.2")
  implementation("org.reactfx:reactfx:2.0-M6")
  implementation("com.techsenger.stagepro:stagepro-core:1.1.0")
  implementation("com.techsenger.stagepro:stagepro:1.1.0")
  implementation("org.apache.commons:commons-lang3:3.18.0")
  implementation("commons-io:commons-io:2.20.0")
  implementation("com.google.guava:guava:33.4.8-jre")
  compileOnly(libs.jetbrains.annotation)
  implementation("com.dlsc.formsfx:formsfx-core:11.6.0") {
    exclude(group = "org.openjfx")
  }
  implementation("net.synedra:validatorfx:0.6.1") {
    exclude(group = "org.openjfx")
  }
  implementation("org.kordamp.ikonli:ikonli-javafx:12.4.0")
  testImplementation("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")
  implementation(libs.mapstruct)
  annotationProcessor(libs.mapstruct.annotation.processor)
  implementation(libs.record.builder.core)
  annotationProcessor(libs.record.builder.processor)
}

tasks.withType<Test> {
  useJUnitPlatform()
}

jlink {
  imageZip.set(layout.buildDirectory.file("/distributions/app-${javafx.platform.classifier}.zip"))
  options.set(listOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages"))
  launcher {
    name = "app"
  }
}

tasks.processResources{
  dependsOn(tasks.compileSass)
}

tasks.compileSass {
  sourceDir = file("${layout.projectDirectory.asFile.absolutePath}/src/main/sass")
  outputDir = file("${layout.buildDirectory.asFile.orNull?.absolutePath}/resources/main")
  style = CompileSass.Style.expanded
  noErrorCss()
  sourceMap = CompileSass.SourceMap.file
}