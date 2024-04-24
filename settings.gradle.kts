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
        //다음 카카오 저장소 주소
        maven ("https://devrepo.kakao.com/nexus/repository/kakaomap-releases/")

    }
}

rootProject.name = "WorkOut"
include(":app")
 