def call() {
    sh """
        trivy fs --exit-code 0 --severity HIGH,CRITICAL . > trivy-fs-report.txt
    """
}
