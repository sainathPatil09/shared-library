def call(String frontendDir, String projectKey, String projectName) {
    withSonarQubeEnv("Sonar") {
        sh """
            cd ${frontendDir}
            npx sonar-scanner \
                -Dsonar.projectKey=${projectKey} \
                -Dsonar.projectName="${projectName}" \
                -Dsonar.sources=.
        """
    }
}
