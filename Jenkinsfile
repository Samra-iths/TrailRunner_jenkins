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

      stage('Test') {

            steps {
                dir("TrailRunner"){
                bat "mvn test"
                }
            }
 
            post {
                always {
                    jacoco(
                    execPattern: 'target/*.exec',
                    classPattern: 'target/classes',
                    sourcePattern:'src/main/java',
                    exclusionPattern: 'src/test*')
                    junit '**/TEST*.xml' 

                }
            }
      }
    }
}