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
    
    stage('Compile') {
            steps {
             script {
                // run lint checks
                //sh './gradlew checkStyle'

                // run unit test cases
                sh './gradlew test'

                // compile
                sh'./gradlew compile${BUILD_TYPE}Sources'
              }
           }
     }
    

//     stage('UI Testing') {
//       steps {
//         script {                                                
//           if (currentBuild.result == null || currentBuild.result == 'SUCCESS') {  
//           // Start your emulator, testing tools
//           sh 'emulator @Nexus_Emulator_API_24'
//           sh 'appium &'  
//           // You're set to go, now execute your UI test
//           sh 'rspec spec -fd'
//           }
//         }
//       }
//     }

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
