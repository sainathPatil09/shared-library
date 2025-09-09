def call(String registry, String frontendImage, String backendImage, String tag, String apiUrl, String frontendDir, String backendDir) {
    sh """
        docker build \
            --build-arg REACT_APP_API_URL=${apiUrl} \
            -t ${registry}/${frontendImage}:${tag} ${frontendDir}
    """
    sh """
        docker build \
            -t ${registry}/${backendImage}:${tag} ${backendDir}
    """
}
