/*
 * ART
 *
 * Copyright 2019-2021 ART
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

dependencies {
    val javaModulesVersion: String by project
    api("io.art.java:rsocket:$javaModulesVersion")
    api("io.art.java:server:$javaModulesVersion")
    api("io.art.java:communicator:$javaModulesVersion")

    api(project("::transport"))
    api(project(":meta"))
    api(project(":core"))
    implementation(project(":logging"))
}
