plugins {
    kotlin("jvm") version "1.9.20"
}

dependencies {
    api("org.assertj:assertj-core:3.23.1")
    implementation("junit:junit:4.13.1")
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}

tasks {
    wrapper {
        gradleVersion = "8.5"
    }
}
