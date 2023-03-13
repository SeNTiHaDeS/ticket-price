#!groovy
node {

   //Etapa:Configurar environment

   stage 'Configurar environment'
   echo 'Configurando environment'
   def mvnHome = tool 'microservicios'
   env.PATH = "${mvnHome}/bin:${env.PATH}"
   echo "var mvnHome='${mvnHome}'"
   echo "var env.PATH='${env.PATH}'"


   // Etapa: Compilar aplicación

   stage 'Compilar Aplicación'
   echo 'Descargando código'
   sh 'rm -rf *'
   checkout scm
   echo 'Compilando aplicación'
   sh 'mvn clean compile'


   stage 'SonarQube Analysis'

   withSonarQubeEnv('sonarqube') {sh 'mvn clean package sonar:sonar -Dsonar.projectKey=ticket-order -Dsonar.host.url=http://mysonarqube:9000 -Dsonar.login=6cf4d57aeb115a0023d5a4b3a20c8d27a7f98f2f'
   }



   // Etapa: Test
   stage 'Test'
   echo 'Ejecutando tests'
   try{
      sh 'mvn verify'
      step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
   }catch(err) {
      step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
      if (currentBuild.result == 'UNSTABLE')
         currentBuild.result = 'FAILURE'
      throw err
   }




   //  ETAPA: ejecutar contenedores

   stage 'Ejecutando contenedores'
   echo 'ejecutando contenedores'
   sh 'docker compose down'
   sh 'docker compose up --build -d'
}