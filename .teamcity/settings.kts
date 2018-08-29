import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_1.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2018.1"

project {

    vcsRoot(HttpsGithubComShall3790teamcityCourseCardsGit)

    buildType(DockerTest)
    buildType(id01FastTests)

    template(Template_1)
}

object id01FastTests : BuildType({
    templates(Template_1)
    id("01FastTests")
    name = "01. Fast Tests"

    steps {
        script {
            name = "test"
            id = "RUNNER_5"
            enabled = false
            scriptContent = "npm test -- --single-run --browsers PhantomJS --colors false"
        }
    }
})

object DockerTest : BuildType({
    name = "DockerTest"

    steps {
        script {
            name = "one"
            scriptContent = "npm -v"
            dockerImage = "nodejs"
            dockerPull = true
        }
    }
})

object Template_1 : Template({
    id("Template")
    name = "Template"

    vcs {
        root(HttpsGithubComShall3790teamcityCourseCardsGit)
    }

    steps {
        script {
            name = "npm install / Phantom JS"
            id = "RUNNER_4"
            scriptContent = "npm install"
        }
    }
})

object HttpsGithubComShall3790teamcityCourseCardsGit : GitVcsRoot({
    name = "https://github.com/shall3790/teamcity-course-cards.git"
    url = "https://github.com/shall3790/teamcity-course-cards.git"
    authMethod = password {
        userName = "shall3790"
        password = "zxx4d746753c9a8e3df914e1d33e972a338"
    }
})
