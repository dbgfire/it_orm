node {
    try {
    def mvnHome = tool name: 'M3', type: 'maven'
    def mvnCMD
    //def javaHome = env.JAVA_HOME="${tool 'Jdk'}"
    //def javaHome = tool name: 'Jdk'
    def scannerHome
    
    stage('Preparation') {
        slackSend color: '#01B0F0', message: " ${env.STAGE_NAME} ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)" 
        if (isUnix()) {
            mvnCMD = "${mvnHome}/bin/mvn"
            sh "${mvnCMD} --version"
	    } else {
	   	    mvnCMD = "${mvnHome}\\bin\\mvn"
	   	    bat (/"${mvnCMD}" --version/)
	    }
    }
    
    stage('SCM Checkout') {
        slackSend color: '#01B0F0', message: " ${env.STAGE_NAME} ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)" 
        git url: 'https://github.com/Jagostini/tp-hibernate.git/'
    }
    
    stage('SonarQube analysis') { 
        slackSend color: '#01B0F0', message: " ${env.STAGE_NAME} ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)" 
        scannerHome = tool 'SonarQube Scanner 2.8'
		withSonarQubeEnv('TPORM') {
		    mvnCMD = "${mvnHome}\\bin\\mvn"
		    bat (/"${mvnCMD}" sonar:sonar/)
		}
    }
    
	stage('Build') {
	    slackSend color: '#01B0F0', message: " ${env.STAGE_NAME} ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)" 
		// Run the maven build
		env.JAVA_HOME="${tool 'Jdk'}"
		mvnCMD = "${mvnHome}\\bin\\mvn"
		bat(/"${mvnCMD}" -Dmaven.test.failure.ignore clean package/)
	}
    
	stage('Results') {
	    slackSend color: '#01B0F0', message: " ${env.STAGE_NAME} ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)" 
		junit '**/target/surefire-reports/TEST-*.xml'
		archive 'target/*.jar'
	}
		
	stage ('Distribute binaries to Artifactory') { 
	    slackSend color: '#01B0F0', message: " ${env.STAGE_NAME} ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)" 
		def SERVER_ID = 'art-1' 
		def server = Artifactory.server SERVER_ID
		def rtMaven = Artifactory.newMavenBuild()
		rtMaven.tool = "${mvnCMD}"
		def uploadSpec = 
		"""
		{
			"files": [
			{
				"pattern": "target/(*).jar",
				"target": "generic-local/BobyetBoby/"
			}]
		}
		"""
		def buildInfo = Artifactory.newBuildInfo() 
		buildInfo.env.capture = true 
		buildInfo.env.collect()
		buildInfo=server.upload(uploadSpec) 
		server.publishBuildInfo(buildInfo)
		rtMaven.resolver server: server, releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot'
		rtMaven.deployer server: server, releaseRepo: 'libs-release-local', snapshotRepo: 'libs-snapshot-local'
		
	}
	
	
	
	
	slackSend color: 'good', message: "Pipeline success - ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)" 
    } catch (err) {
        //echo "Caught: ${err}"
        currentBuild.result = 'FAILURE'
        slackSend color: 'warning', message: "${err} - ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)" 
    }
}
