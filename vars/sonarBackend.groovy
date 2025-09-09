def call(String backendDir, String projectKey, String projectName) {
    withSonarQubeEnv("Sonar") {
        sh """
            cd ${backendDir}
            ./mvnw clean install -DskipTests
            ./mvnw sonar:sonar \
                -Dsonar.projectKey=${projectKey} \
                -Dsonar.projectName="${projectName}"
        """
    }
}
