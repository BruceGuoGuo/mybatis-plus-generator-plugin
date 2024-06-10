plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.21"
    id("org.jetbrains.intellij") version "1.16.1"
}

group = "com.guodaxia"
version = "1.0"

repositories {
    // 本地
    mavenLocal()
    // 阿里云
    maven("https://maven.aliyun.com/nexus/content/repositories/central/")
    // maven 中央仓库
    mavenCentral()
//    // 私服
//    maven {
//        // 需要认证时，配置用户名密码
//        credentials {
//            username 'username'
//            password 'password'
//        }
//        name 'My Maven Repository'
//        url 'http://10.168.1.150:81/repository/releases/'
//    }
}

dependencies {
    implementation("com.baomidou:mybatis-plus-boot-starter:3.5.3.1")
    implementation("com.baomidou:mybatis-plus-generator:3.5.1")
    implementation("org.freemarker:freemarker:2.3.32")
    implementation("mysql:mysql-connector-java:8.0.30")
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2023.1.5")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf(/* Plugin Dependencies */))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    patchPluginXml {
        sinceBuild.set("231")
        untilBuild.set("241.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
