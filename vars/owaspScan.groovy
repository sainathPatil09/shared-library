def call(String backendDir) {
    sh """
        dependency-check.sh \
          --project "Chattingo" \
          --scan ${backendDir} \
          --out dependency-check-report \
          --format HTML || true
    """
}
