pipeline {
  agent any
  stages {
    stage('Detect build type') {
      parallel {
        stage('Detect build type') {
          steps {
            sh ''' script {
      if (env.BRANCH_NAME == \'develop\' || env.CHANGE_TARGET == \'develop\') {
        env.BUILD_TYPE = \'debug\'
      } else if (env.BRANCH_NAME == \'master\' || env.CHANGE_TARGET == \'master\') {
        env.BUILD_TYPE = \'release\'
      }
    }'''
            }
          }

          stage('Compile') {
            steps {
              sh '''    sh \'./gradlew compile${BUILD_TYPE}Sources\'
'''
            }
          }

          stage('Build') {
            steps {
              sh ''' sh \'./gradlew assemble${BUILD_TYPE}\'
 //sh \'./gradlew generatePomFileForLibraryPublication\''''
            }
          }

          stage('Publish') {
            steps {
              sh 'archiveArtifacts "**/${APP_NAME}-${BUILD_TYPE}.apk"'
            }
          }

        }
      }

    }
    environment {
      APP_NAME = 'test'
    }
  }