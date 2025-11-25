## CICD WORKSHOP SAST/DAST

PRACTICAL 4: INTEGRATING SNYK WITH GITHUB ACTIONS

[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=douglasswmcst_cicd-demo)](https://sonarcloud.io/summary/new_code?id=douglasswmcst_cicd-demo)

[![Security Status](https://img.shields.io/badge/security-monitored-green)](https://github.com/douglasswmcst/cicd-demo/security)
[![Snyk Vulnerabilities](https://img.shields.io/badge/snyk-monitored-blue)](https://snyk.io/)
[![SAST](https://img.shields.io/badge/SAST-enabled-green)](https://github.com/douglasswmcst/cicd-demo/actions)

### Security Features

🔒 **Automated Security Scanning**: Every commit and PR is automatically scanned for vulnerabilities

🛡️ **Dependency Monitoring**: Continuous monitoring of dependencies for new vulnerabilities  

📊 **Security Dashboard**: Real-time security posture tracking and reporting

⚡ **Fast Feedback**: Security issues detected early in development cycle

### Security Workflow

- **Static Application Security Testing (SAST)** with Snyk
- **Dependency Vulnerability Scanning** on every build
- **Automated Security Monitoring** with daily scans
- **GitHub Security Integration** with SARIF uploads

### Getting Started

1. Clone the repository
2. Set up your Snyk token in GitHub Secrets as `SNYK_TOKEN`
3. Push changes to trigger security scans
4. View results in GitHub Security tab

For detailed setup instructions, see [Practical 4 Guide](practical4.md).

### Security Policy

See our [Security Policy](SECURITY.md) for vulnerability reporting and security procedures.
