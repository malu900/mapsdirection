node {
    def mvnHome

   stage('Initialize') {
      mvnHome = tool 'maven-3.6.3'
      registry = "maluvdb/location-api"
      registryCredential = 'dockerhub'
   }
   stage('Node version'){
     sh "which node"
   }
   stage('Docker version') {
     sh "docker version"
   }
   stage('Checkout') {
        git 'https://github.com/malu900/directionapplication.git'
   }

   // stage('Build Docker Image') {
   //    dockerImage = docker.build("locationapp:${env.BUILD_NUMBER}")
   // }
   
   stage('Build') {
      // Run the maven build
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore -f ./maps/pom.xml clean install"
      } else {
         bat(/"%MVN_HOME%\bin\mvn" -Dmaven.test.failure.ignore -f ".\maps\pom.xml" clean package/)
      }
   }
   //
   stage('Test')   {
      //Perform test
      echo '***********-----------------------------------------------------EXECUTE TESTS-----------------------------------------------------***********'
      echo 'Execute the script'
      if (isUnix()) {
        sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore -f ./maps/pom.xml test"
      } else {
        bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore ".\maps\pom.xml" test/)
      }
      echo '***********-----------------------------------------------------END TESTS-----------------------------------------------------***********'
   }
   stage('Results') {
      archiveArtifacts 'directionsapp/target/*.jar'
      junit allowEmptyResults: true, testResults: '**/target/surefire-reports/TEST-*.xml'
   }

   //  stage('Deploy Docker Image'){
   //      echo "Docker Image Tag Name: ${dockerImageTag} ."
   //      sh "docker stop locationapp"
   //      sh "docker rm locationapp"
   //      sh "docker run --name server -d -p 8090:8090 app-server:${env.BUILD_NUMBER}"

   //    //   docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
   //    //   dockerImage.push("${env.BUILD_NUMBER}")
   //    //   dockerImage.push("latest")
   //  }
//    stage('Deploy to docker hub') {
// //         docker.withRegistry('https://hub.docker.com/repository/docker/maluvdb/location-api') {
// //
// //                def customImage = docker.build("maluvdb/location-api:${env.BUILD_ID}")
// //                customImage.push()
// //            }
//    }
}
