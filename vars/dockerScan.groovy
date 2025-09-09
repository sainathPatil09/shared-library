def call(String registry, String frontendImage, String backendImage, String tag) {
    def backendImageFull = "${registry}/${backendImage}:${tag}"
    def frontendImageFull = "${registry}/${frontendImage}:${tag}"

    echo "Scanning Frontend Image: ${frontendImageFull}"
    echo "Scanning Backend Image: ${backendImageFull}"

    // Trivy scan frontend
    sh """
        trivy image --exit-code 0 --severity HIGH,CRITICAL ${frontendImageFull} > trivy-frontend-report.txt
    """

    // Trivy scan backend
    sh """
        trivy image --exit-code 0 --severity HIGH,CRITICAL ${backendImageFull} > trivy-backend-report.txt
    """
}
