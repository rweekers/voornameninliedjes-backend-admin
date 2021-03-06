import com.bmuschko.gradle.docker.tasks.container.DockerCreateContainer
import com.bmuschko.gradle.docker.tasks.container.DockerStartContainer
import com.bmuschko.gradle.docker.tasks.container.DockerStopContainer
import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val buildMyAppImage by tasks.creating(DockerBuildImage::class) {
	inputDir.set(file("."))
	images.add("mongo:latest")
}

val createMyAppContainer by tasks.creating(DockerCreateContainer::class) {
	dependsOn(buildMyAppImage)
	targetImageId(buildMyAppImage.imageId)
	hostConfig.portBindings.set(listOf("27017:27017"))
}

val startMyAppContainer by tasks.creating(DockerStartContainer::class) {
	dependsOn(createMyAppContainer)
	targetContainerId(createMyAppContainer.containerId)
}

val stopMyAppContainer by tasks.creating(DockerStopContainer::class) {
	targetContainerId(createMyAppContainer.containerId)
}

plugins {
	id("org.springframework.boot") version "2.4.1"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	id("com.bmuschko.docker-remote-api") version "6.6.1"
	id("org.sonarqube") version "3.0"
	kotlin("jvm") version "1.4.21"
	kotlin("plugin.spring") version "1.4.21"
	jacoco
}

tasks.jacocoTestReport {
	reports {
		xml.isEnabled = true
		csv.isEnabled = false
		html.isEnabled = false
		html.destination = file("$buildDir/reports/coverage")
	}
}

tasks.jacocoTestCoverageVerification {
	violationRules {
		rule {
			limit {
				minimum = "0.3".toBigDecimal()
			}
		}
	}
}

tasks.test {
	useJUnitPlatform()

	dependsOn(startMyAppContainer)
	finalizedBy(stopMyAppContainer)
}

sonarqube {
	properties {
		property("sonar.projectKey", "nl.orangeflamingo:voornameninliedjes-backend-admin")
	}
}

group = "nl.orangeflamingo"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-log4j2")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("com.ninja-squad:springmockk:3.0.0")
}

configurations {
	"implementation" {
		exclude(group = "org.springframework.boot", module="spring-boot-starter-logging")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}