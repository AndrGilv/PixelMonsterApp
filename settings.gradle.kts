pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "PixelMonsterApp3"
include(":app")
include(":feature-monster-list")
include(":shared-ui-core")
include(":shared-navigation-core")
include(":feature-monster-details")
include(":shared-monster-api")
