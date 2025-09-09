def call(String frontendDir) {
    sh """
        cd ${frontendDir}
        npm audit --json > npm-audit-report.json || true
    """
}
