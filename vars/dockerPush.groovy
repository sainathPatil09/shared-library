def call(String registry, String frontendImage, String backendImage, String tag, String credentialsId) {
    withCredentials([usernamePassword(credentialsId: credentialsId, usernameVariable: 'USER', passwordVariable: 'PASS')]) {
        // Login to Docker
        sh 'echo $PASS | docker login -u $USER --password-stdin'

        // Push frontend and backend images
        sh "docker push ${registry}/${frontendImage}:${tag}"
        sh "docker push ${registry}/${backendImage}:${tag}"
    }
}
