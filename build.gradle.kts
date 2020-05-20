import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val springBootVersion = "2.2.7.RELEASE"
//val swaggerVersion = "3.0.0-SNAPSHOT"
val jacksonVersion = "2.10.4"
val h2Version = "0.8.3.RELEASE"

plugins {
	id("org.springframework.boot") version "2.2.7.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm") version "1.3.72"
	kotlin("plugin.spring") version "1.3.72"
}

group = "com.ara"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	maven(url = "http://oss.jfrog.org/artifactory/oss-snapshot-local/")
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux:${springBootVersion}")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive:${springBootVersion}")
//	implementation("org.springframework.boot.experimental:spring-boot-starter-data-r2dbc:${springBootVersion}")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${jacksonVersion}")

	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")

//	implementation("io.springfox:springfox-swagger2:${swaggerVersion}")
//	implementation("io.springfox:springfox-swagger-ui:${swaggerVersion}")
//	implementation("io.springfox:springfox-spring-webflux:${swaggerVersion}")

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

	testImplementation("org.springframework.boot:spring-boot-starter-test:${springBootVersion}") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("io.projectreactor:reactor-test")
//	runtimeOnly("io.r2dbc:r2dbc-h2:${h2Version}")
}
tasks.withType<Test> {
	useJUnitPlatform()
}
tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

