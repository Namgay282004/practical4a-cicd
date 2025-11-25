## CICD WORKSHOP SAST/DAST

PRACTICAL 4A & 4B: INTEGRATING SAST & DAST WITH GITHUB ACTIONS

[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=Namgay282004_practical4a-cicd)](https://sonarcloud.io/summary/new_code?id=Namgay282004_practical4a-cicd)

[![Security Status](https://img.shields.io/badge/security-monitored-green)](https://github.com/Namgay282004/practical4a-cicd/security)
[![SAST](https://img.shields.io/badge/SAST-SonarCloud-green)](https://github.com/Namgay282004/practical4a-cicd/actions)
[![DAST](https://img.shields.io/badge/DAST-OWASP_ZAP-blue)](https://github.com/Namgay282004/practical4a-cicd/actions)
[![Snyk Vulnerabilities](https://img.shields.io/badge/snyk-monitored-blue)](https://snyk.io/)

### Security Features

**🔒 Static Application Security Testing (SAST)**: Source code analysis with SonarCloud  
**🛡️ Dynamic Application Security Testing (DAST)**: Runtime vulnerability scanning with OWASP ZAP  
**� Dependency Monitoring**: Continuous monitoring of dependencies for new vulnerabilities  
**⚡ Fast Feedback**: Security issues detected early in development cycle  
**🎯 Automated Scanning**: Every commit and PR is automatically scanned for vulnerabilities

### 🔒 Security Status

-  **Spring Boot 3.2.12** - Latest stable with security patches
-  **56 Snyk vulnerabilities resolved** (3 Critical, 27 High, 18 Medium, 8 Low)
-  **Dependency management** configured for transitive vulnerabilities
-  **JavaFaker exclusions** to prevent vulnerable SnakeYAML versions

### Security Workflow

- **Static Application Security Testing (SAST)** with SonarCloud
- **Dynamic Application Security Testing (DAST)** with OWASP ZAP  
- **Dependency Vulnerability Scanning** with Snyk
- **Automated Security Monitoring** with daily scans
- **GitHub Security Integration** with SARIF uploads

### 🎯 DAST Testing Features

- **Baseline Scan**: Quick passive security check on every PR
- **Full Scan**: Comprehensive active security testing (manual/scheduled)
- **API Scan**: Specialized REST API vulnerability testing
- **15+ Vulnerability Types**: XSS, SQL Injection, CSRF, Security Headers, etc.
- **Real-time Reports**: HTML/JSON/Markdown security reports

### Getting Started

1. **Clone the repository**
   ```bash
   git clone https://github.com/Namgay282004/practical4a-cicd.git
   cd practical4a-cicd
   ```

2. **Set up security tokens in GitHub Secrets**
   - `SONAR_TOKEN`: SonarCloud authentication token
   - `SNYK_TOKEN`: Snyk authentication token

3. **Test the application locally**
   ```bash
   mvn clean package -DskipTests
   mvn spring-boot:run
   # Application runs on http://localhost:3000
   ```

4. **Trigger security scans**
   - **Automatic**: Push changes or create PR
   - **Manual**: Use GitHub Actions "Run workflow" for full DAST scan

5. **View security results**
   - **SAST**: SonarCloud dashboard + GitHub Security tab
   - **DAST**: GitHub Actions artifacts + reports
   - **Dependencies**: Snyk dashboard + GitHub Security tab

For detailed setup instructions, see [Practical 4A Guide](PRACTICAL4A-IMPLEMENTATION-REPORT-CONCISE.md) and [Practical 4B Guide](practical4b.md).

### Security Policy

See our [Security Policy](SECURITY.md) for vulnerability reporting and security procedures.
