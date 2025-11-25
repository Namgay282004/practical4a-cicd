## CICD WORKSHOP SAST/DAST

PRACTICAL 4: INTEGRATING SNYK WITH GITHUB ACTIONS

[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=Namgay282004_practical4a-cicd)](https://sonarcloud.io/summary/new_code?id=Namgay282004_practical4a-cicd)

[![Security Status](https://img.shields.io/badge/security-monitored-green)](https://github.com/Namgay282004/practical4a-cicd/security)
[![Snyk Vulnerabilities](https://img.shields.io/badge/snyk-monitored-blue)](https://snyk.io/)
[![SAST](https://img.shields.io/badge/SAST-enabled-green)](https://github.com/Namgay282004/practical4a-cicd/actions)

### Security Features

**Automated Security Scanning**: Every commit and PR is automatically scanned for vulnerabilities

🛡️ **Dependency Monitoring**: Continuous monitoring of dependencies for new vulnerabilities  

📊 **Security Dashboard**: Real-time security posture tracking and reporting

⚡ **Fast Feedback**: Security issues detected early in development cycle

### 🔒 Security Status

- ✅ **Spring Boot 3.2.12** - Latest stable with security patches
- ✅ **56 Snyk vulnerabilities resolved** (3 Critical, 27 High, 18 Medium, 8 Low)
- ✅ **Dependency management** configured for transitive vulnerabilities
- ✅ **JavaFaker exclusions** to prevent vulnerable SnakeYAML versions

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
