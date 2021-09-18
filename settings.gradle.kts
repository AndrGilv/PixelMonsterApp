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
include(":feature_monster_list")
include(":shared_ui_core")
include(":shared_navigation_core")
include(":feature_monster_details")
include(":shared_monster_api")
