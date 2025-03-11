pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = java.net.URI("https://devrepo.kakao.com/nexus/repository/kakaomap-releases/") }
        maven { url = java.net.URI("https://repository.map.naver.com/archive/maven") }
        maven("https://jitpack.io")
    }
    versionCatalogs {
        create("libs") {
            plugin("hilt", "com.google.dagger.hilt.android").version("2.49")
        }
    }
}

rootProject.name = "YunSeoulTransportation"
include(":app")
include(":domain")
include(":data")
