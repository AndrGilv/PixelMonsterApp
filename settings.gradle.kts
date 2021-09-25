pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
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

val modules = mapOf(
    ":app" to "app",
    ":feature-monster-list" to "feature/monster/list",
    ":shared-ui-core" to "shared/ui/core",
    ":shared-navigation-core" to "shared/navigation/core",
    ":feature-monster-details" to "feature/monster/details",
    ":shared-monster-api" to "shared/monster/api"
)

modules.forEach { name, path ->
    include(name)
    project(name).projectDir = file(path)
}
