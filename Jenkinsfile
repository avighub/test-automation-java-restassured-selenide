pipeline{
	agent any
	environment {
       PATH='/usr/local/bin:/usr/bin:/usr/local/sbin:/usr/sbin:/opt/maven/bin:/app/bin'
    }
	tools {
        maven 'maven_home'
    }
	stages{
		stage('---Clean---'){
			steps{
			    sh "mvn clean"
				}
		}
		stage('---install---'){
        			steps{
        				sh "mvn install"
        				}
        		}	}
}
