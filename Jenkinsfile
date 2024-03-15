pipeline {
    agent any 
    
    stages {
           
      stage('Build') {
            steps {
                dir("TrailRunner"){
                bat "mvn compile"
                }
            }
      }
    
}