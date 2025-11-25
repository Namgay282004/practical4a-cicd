# OWASP ZAP DAST Setup Guide for Practical 4b

## Overview

This guide documents the implementation of **Practical 4b: Setting up DAST with OWASP ZAP** for comprehensive dynamic security analysis.

## 🎯 Implementation Summary

### What We've Implemented

1. **OWASP ZAP GitHub Actions Workflow** (`.github/workflows/zap-dast.yml`)
2. **ZAP Configuration Rules** (`.zap/rules.tsv`)
3. **Vulnerable Web Endpoints** (`DastVulnerableController.java`) for testing
4. **Multiple Scan Types**: Baseline, Full, and API scans
5. **Comprehensive Security Testing**: 15+ different web vulnerabilities

## 📋 DAST vs SAST Comparison

| Aspect | SAST (SonarCloud) | DAST (OWASP ZAP) |
|--------|-------------------|------------------|
| **Testing Method** | Source code analysis | Running application testing |
| **Access Required** | Source code | Application URL |
| **Testing Phase** | Development | Testing/Staging |
| **Vulnerabilities Found** | Code-level issues | Runtime/Configuration issues |
| **Example Issues** | SQL injection in code, Hard-coded credentials | Missing headers, XSS in responses, SSL issues |

## 🔍 DAST Vulnerabilities We Can Test

### Web Application Security Issues

Our `DastVulnerableController.java` contains **15 different vulnerability types** that DAST can detect:

#### 🔴 **Critical/High Severity**
1. **Reflected XSS** (`/search?query=<script>alert(1)</script>`)
   - User input reflected without sanitization
   - ZAP Rule: 40012 (XSS Reflected)

2. **Open Redirect** (`/redirect?url=http://evil.com`)
   - Unvalidated redirect parameter
   - ZAP Rule: 20019 (External Redirect)

3. **Path Traversal** (`/download?filename=../../../etc/passwd`)
   - Directory traversal vulnerability
   - ZAP Rule: 6 (Path Traversal)

4. **Information Disclosure** (`/error-disclosure?file=../secret`)
   - Detailed error messages exposing system info
   - ZAP Rule: 10023 (Information Disclosure)

#### 🟠 **Medium Severity**
5. **Missing Security Headers**
   - X-Frame-Options (ZAP Rule: 10020)
   - X-Content-Type-Options (ZAP Rule: 10021)
   - Content-Security-Policy (ZAP Rule: 10038)
   - Strict-Transport-Security (ZAP Rule: 10035)

6. **Insecure Cookies** (`/set-cookie`)
   - Missing Secure, HttpOnly, SameSite flags
   - ZAP Rule: 10054 (Cookie Without SameSite)

7. **Overly Permissive CORS**
   - `@CrossOrigin(origins = "*")`
   - Allows requests from any domain

8. **Content Type Sniffing** (`/content-sniff`)
   - Missing X-Content-Type-Options: nosniff
   - ZAP Rule: 10021

#### 🟡 **Lower Severity**
9. **Server Information Disclosure** (`/server-info`)
   - Server headers revealing technology stack
   - ZAP Rule: 10056 (X-Debug-Token Information Leak)

10. **Weak Session Management** (`/session`)
    - Predictable session IDs
    - ZAP Rule: 10112/10113

11. **Missing Cache Control** (`/private-data`)
    - Sensitive content cacheable
    - ZAP Rule: 10049

## 🚀 ZAP Scan Types Implemented

### 1. Baseline Scan (Fast - 2-5 minutes)
```yaml
# Triggers: Every push/PR
# Purpose: Quick security check
# Coverage: Passive vulnerabilities only
```

**What it detects:**
- Missing security headers
- Information disclosure
- Cookie security issues
- Basic configuration problems

### 2. Full Scan (Comprehensive - 10-20 minutes)
```yaml
# Triggers: Manual workflow dispatch
# Purpose: Deep security analysis
# Coverage: Passive + Active vulnerabilities
```

**What it detects:**
- Everything from baseline scan
- SQL injection attempts
- XSS attacks
- Command injection
- Path traversal attacks

### 3. API Scan (Targeted - 5-10 minutes)
```yaml
# Triggers: Manual workflow dispatch
# Purpose: API-specific testing
# Coverage: REST API vulnerabilities
```

**What it detects:**
- API-specific vulnerabilities
- Parameter manipulation
- Authentication bypass
- API rate limiting issues

## 📊 Expected ZAP Results

### Vulnerability Summary Expected:
- **High**: 3-5 vulnerabilities (XSS, Open Redirect, Path Traversal)
- **Medium**: 8-12 vulnerabilities (Missing Headers, Cookie Issues)
- **Low**: 5-10 informational issues
- **Total Issues**: 15-25+ findings

### Sample ZAP Report Structure:
```
OWASP ZAP Report Summary
═══════════════════════
High Priority Alerts: 4
Medium Priority Alerts: 9
Low Priority Alerts: 7
Informational Alerts: 3

High Priority Issues:
├─ Cross Site Scripting (Reflected) - /search
├─ External Redirect - /redirect
├─ Path Traversal - /download
└─ Information Disclosure - /error-disclosure

Medium Priority Issues:
├─ Missing Anti-clickjacking Header - /frame-me
├─ Content Security Policy Missing - /*
├─ X-Content-Type-Options Missing - /*
└─ Cookie Without SameSite Attribute - /set-cookie
```

## 🔧 Running DAST Scans

### Method 1: Automatic (GitHub Actions)
```bash
# Baseline scan runs automatically on every push to main
git push origin main

# Full scan requires manual trigger:
# Go to Actions tab → ZAP DAST → Run workflow → Select "full"
```

### Method 2: Local ZAP Testing
```bash
# Start the application
mvn spring-boot:run

# Run ZAP baseline scan locally
docker run -v $(pwd):/zap/wrk/:rw \
  -t ghcr.io/zaproxy/zaproxy:stable zap-baseline.py \
  -t http://host.docker.internal:8080 \
  -r zap-report.html
```

### Method 3: Manual ZAP GUI Testing
```bash
# Download and run ZAP desktop application
# Configure proxy at localhost:8080
# Browse application manually while ZAP records requests
# Run active scan on discovered URLs
```

## 📸 Screenshots to Capture

For your submission, capture:

1. **GitHub Actions Workflow**: ZAP DAST pipeline execution
2. **ZAP HTML Report**: Vulnerability summary page
3. **High-Severity Alerts**: Detailed view of critical issues
4. **Security Headers Analysis**: Missing headers report
5. **Scan Configuration**: ZAP rules and settings used

### Key ZAP Report Sections:
- **Executive Summary**: Overall risk assessment
- **Alert Details**: Individual vulnerability descriptions
- **Site Structure**: Discovered URLs and parameters
- **Statistics**: Scan coverage and timing information

## 🛡️ Security Testing Workflow

### Complete Security Pipeline:
```
Development → SAST (SonarCloud) → Build → DAST (ZAP) → Deploy
     ↓              ↓                ↓         ↓          ↓
Code Issues   Dependency Scan   Container   Runtime    Production
              Security Issues    Security   Vulns      Monitoring
```

### Integration with Existing Security:
1. **SAST** finds code-level vulnerabilities
2. **Dependency Scanning** finds library vulnerabilities  
3. **DAST** finds runtime/configuration vulnerabilities
4. **Combined Coverage** = Comprehensive security

## 🔧 Troubleshooting

### Common Issues:

1. **"Application not accessible"**:
   ```bash
   # Check application startup logs
   docker logs cicd-demo-app
   # Verify port mapping
   curl http://localhost:8080/
   ```

2. **"ZAP scan timeout"**:
   - Reduce scan scope in rules.tsv
   - Increase timeout in workflow
   - Use baseline scan instead of full scan

3. **"Too many false positives"**:
   - Update `.zap/rules.tsv` to IGNORE specific rules
   - Adjust severity thresholds
   - Add custom exclusions

4. **"No vulnerabilities found"**:
   - Verify vulnerable endpoints are accessible
   - Check ZAP can reach application URL
   - Review scan configuration

## ✅ Verification Checklist

- [ ] ZAP workflow runs successfully in GitHub Actions
- [ ] Application starts and is accessible during scan
- [ ] ZAP finds multiple security vulnerabilities
- [ ] HTML report generated and downloadable
- [ ] High-severity alerts documented
- [ ] Screenshots captured for submission
- [ ] Security recommendations documented

## 📚 Learning Outcomes

After completing this practical, you will understand:

1. **DAST Methodology**: How dynamic testing differs from static analysis
2. **OWASP ZAP**: Industry-standard web application security testing
3. **Web Vulnerabilities**: Common security issues in web applications
4. **CI/CD Security**: Integrating security testing in deployment pipelines
5. **Security Headers**: Importance of proper HTTP security configuration
6. **Vulnerability Assessment**: How to prioritize and remediate security findings

## 🔗 Useful Resources

- **OWASP ZAP Documentation**: https://www.zaproxy.org/docs/
- **OWASP Top 10**: https://owasp.org/Top10/
- **ZAP GitHub Actions**: https://github.com/zaproxy/action-baseline
- **Web Security Headers**: https://securityheaders.com/
- **DAST Best Practices**: https://owasp.org/www-project-devsecops-guideline/

---

**Next Step**: Compare SAST (Practical 4a) and DAST (Practical 4b) results to understand comprehensive security testing!
