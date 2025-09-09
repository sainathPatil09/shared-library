def call(String workspacePath, String tag, Map envCredentials) {
    // Inject all environment credentials
    withCredentials(envCredentials.collect { k, v ->
        string(credentialsId: v, variable: k)
    }) {
        // Rebuild .env file on VPS
        sh """
        cat > ${workspacePath}/backend/.env <<EOF
        JWT_SECRET=$JWT_SECRET

        SPRING_DATASOURCE_URL=$SPRING_DATASOURCE_URL
        SPRING_DATASOURCE_USERNAME=$SPRING_DATASOURCE_USERNAME
        SPRING_DATASOURCE_PASSWORD=$SPRING_DATASOURCE_PASSWORD

        CORS_ALLOWED_ORIGINS=$CORS_ALLOWED_ORIGINS
        CORS_ALLOWED_METHODS=$CORS_ALLOWED_METHODS
        CORS_ALLOWED_HEADERS=$CORS_ALLOWED_HEADERS

        SPRING_PROFILES_ACTIVE=$SPRING_PROFILES_ACTIVE
        SERVER_PORT=$SERVER_PORT

        MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD
        MYSQL_DATABASE=$MYSQL_DATABASE
        EOF
        """

        // Deploy Docker images using docker-compose
        dir(workspacePath) {
            sh "export TAG=${tag}"
            sh "docker compose pull"
            sh "docker compose up -d"
        }
    }
}
