pipeline{
  
  agent any

  environment{
    APP_NAME='app'
  }
  
  options{
     skipStagesAfterUnstable()
  }
  
  stages{
    
    stage('Detect build type'){
      steps{
        script{
          if(env.BRANCH_NAME=='develop'||env.CHANGE_TARGET=='develop'){
            env.BUILD_TYPE='debug'
          }
          if(env.BRANCH_NAME=='master'||env.CHANGE_TARGET=='master'){
            env.BUILD_TYPE='release'
          }
        }
      }
    }
    
    stage('Compile'){
      steps{
         sh'./gradlew compile${BUILD_TYPE}Sources'
      }
    }
    stage('Build'){
      steps{
         sh'./gradlew assemble${BUILD_TYPE}'
      }
    }
    stage('Publish'){
      steps{
         archiveArtifacts"**/${APP_NAME}-${BUILD_TYPE}-unsigned.apk"
       }
    }
  }
}
