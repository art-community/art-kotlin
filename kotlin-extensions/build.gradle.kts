plugins {
    kotlin("jvm") version "1.4.32"
}


dependencies {
    implementation("io.art:launcher")
    implementation("io.art:core")
    implementation("io.art:configurator")
    implementation("io.art:server")
    implementation("io.art:communicator")
    implementation("io.art:value")
    implementation("io.art:model")
    implementation("io.art:rsocket")
    implementation("io.art:http")
    implementation("io.art:json")
    implementation("io.art:message-pack")
    implementation("io.art:protobuf")
    implementation("io.art:scheduler")
    implementation("io.art:logging")
    implementation("io.art:rocks-db")
}
