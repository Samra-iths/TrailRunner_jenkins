pipeline {
    agent any 
    
    stages {
           
      stage('Build') {
            steps {
                dir("Trail_runner"){
                bat "mvn compile"
                }
            }
      }
    
}