plugins {
    kotlin("jvm") version "1.4.32"
}

println(rootProject.subprojects.map { it.name })

dependencies {
    implementation(project(":launcher"))
    implementation(project(":core"))
    implementation(project(":configurator"))
    implementation(project(":server"))
    implementation(project(":communicator"))
    implementation(project(":value"))
    implementation(project(":model"))
    implementation(project(":rsocket"))
    implementation(project(":http"))
    implementation(project(":json"))
    implementation(project(":message-pack"))
    implementation(project(":protobuf"))
    implementation(project(":scheduler"))
    implementation(project(":logging"))
    implementation(project(":rocks-db"))
}
