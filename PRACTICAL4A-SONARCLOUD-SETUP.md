# SonarCloud Setup Guide for Practical 4a

## Overview

This guide documents the implementation of **Practical 4a: Setting up SAST with SonarCloud** for comprehensive static security analysis.

## 🎯 Implementation Summary

### What We've Implemented

1. **SonarCloud Project Configuration** (`sonar-project.properties`)
2. **Maven Integration** (updated `pom.xml` with SonarCloud + JaCoCo plugins)
3. **GitHub Actions Workflow** (`.github/workflows/sonarcloud.yml`)
4. **Vulnerable Code Samples** (`VulnerableController.java`) for testing

## 📋 Setup Instructions

### Step 1: SonarCloud Account Setup

1. **Visit SonarCloud**: Go to https://sonarcloud.io
2. **Sign in with GitHub**: Use your GitHub account (@Namgay282004)
3. **Import Organization**: Select your GitHub organization
4. **Create Project**: Choose the `practical4a-cicd` repository

### Step 2: Generate SonarCloud Token

1. **Profile Settings**: Click your profile → "My Account"
2. **Security Tab**: Navigate to "Security" 
3. **Generate Token**: 
   - Name: `practical4a-cicd-token`
   - Type: User Token
   - Expiration: 90 days
4. **Copy Token**: Save it securely (you won't see it again!)

### Step 3: Configure GitHub Secrets

Add these secrets to your GitHub repository:

1. **Go to Repository Settings**: `practical4a-cicd` → Settings → Secrets and Variables → Actions

2. **Add Repository Secrets**:
   ```
   SONAR_TOKEN = [your-sonarcloud-token]
   ```

### Step 4: Verify Configuration

Your project should have these files configured:

#### `sonar-project.properties`
```properties
sonar.projectKey=Namgay282004_practical4a-cicd
sonar.organization=namgay282004
sonar.projectName=CICD Demo - SAST Implementation
```

#### `pom.xml` (updated properties)
```xml
<sonar.organization>namgay282004</sonar.organization>
<sonar.projectKey>Namgay282004_practical4a-cicd</sonar.projectKey>
```

#### `.github/workflows/sonarcloud.yml`
- Automated SonarCloud scanning on push/PR
- Quality gate enforcement
- Coverage reporting with JaCoCo

## 🔍 Security Issues for Testing

### VulnerableController.java

We've created a controller with **10 different security vulnerabilities** for SonarCloud to detect:

1. **Hard-coded Credentials** (Security Hotspot)
   - Hard-coded database password
   - Location: `DB_PASSWORD = "password123"`

2. **Weak Cryptography** (Security Hotspot)
   - MD5 hash algorithm (deprecated)
   - Location: `MessageDigest.getInstance("MD5")`

3. **SQL Injection** (Critical Vulnerability)
   - Direct string concatenation in SQL
   - Location: `"SELECT * FROM users WHERE id = '" + userId + "'"`

4. **Information Disclosure** (Major Vulnerability)
   - Detailed error messages exposed
   - Location: Exception handling in `getUserData()`

5. **Path Traversal** (Critical Vulnerability)
   - No path validation for file access
   - Location: `new java.io.File("/app/data/" + filename)`

6. **Weak Random Number Generator** (Security Hotspot)
   - Non-cryptographically secure Random
   - Location: `new Random()` for token generation

7. **Code Injection** (Critical Vulnerability)
   - Direct script evaluation of user input
   - Location: `engine.eval(expression)`

8. **Missing Authorization** (Major Vulnerability)
   - Admin endpoints without authentication
   - Location: `/admin/delete-user` endpoint

9. **XXE Vulnerability** (Critical Vulnerability)
   - XML parsing without XXE protection
   - Location: `DocumentBuilderFactory` configuration

10. **Insecure Deserialization** (Critical Vulnerability)
    - Unsafe object deserialization
    - Location: `ObjectInputStream.readObject()`

## 🚀 Running the Analysis

### Manual Trigger
```bash
# Run SonarCloud analysis locally (requires SONAR_TOKEN)
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=Namgay282004_practical4a-cicd \
  -Dsonar.organization=namgay282004 \
  -Dsonar.host.url=https://sonarcloud.io \
  -Dsonar.token=$SONAR_TOKEN
```

### Automatic Trigger
- **Push to main/master**: Triggers SonarCloud workflow
- **Pull Request**: Runs analysis and decorates PR
- **Scheduled**: Can be configured for regular scans

## 📊 Expected Results

### SonarCloud Dashboard Should Show:

1. **Security Issues**: ~10 vulnerabilities detected
2. **Security Hotspots**: ~3-5 hotspots requiring review
3. **Security Rating**: Likely D or E (due to critical vulnerabilities)
4. **Coverage**: Code coverage metrics from JaCoCo

### Vulnerability Categories Expected:
- 🔴 **Critical**: SQL Injection, Path Traversal, XXE, Code Injection, Insecure Deserialization
- 🟠 **Major**: Missing Authorization, Information Disclosure
- 🟡 **Security Hotspots**: Hard-coded credentials, Weak crypto, Weak RNG

## 📸 Screenshots to Capture

For your submission, capture:

1. **SonarCloud Dashboard**: Overall project overview
2. **Security Tab**: Detailed vulnerability list
3. **GitHub Actions**: Successful workflow execution
4. **Quality Gate**: Pass/Fail status
5. **Security Hotspots**: Manual review interface

## 🔧 Troubleshooting

### Common Issues:

1. **"Invalid token"**: 
   - Verify SONAR_TOKEN secret is correct
   - Check token hasn't expired

2. **"Project not found"**:
   - Verify project key matches exactly
   - Check organization name is correct

3. **"No coverage"**:
   - Ensure JaCoCo plugin is configured
   - Run `mvn clean verify` before SonarCloud scan

4. **"Quality gate timeout"**:
   - Remove `-Dsonar.qualitygate.wait=true` temporarily
   - Check SonarCloud dashboard manually

## ✅ Verification Checklist

- [ ] SonarCloud project created and configured
- [ ] GitHub secrets added (SONAR_TOKEN)
- [ ] Configuration files updated
- [ ] Vulnerable code added for testing
- [ ] Workflow runs successfully
- [ ] Security issues detected in dashboard
- [ ] Screenshots captured for submission

## 🔗 Useful Links

- **SonarCloud Project**: https://sonarcloud.io/project/overview?id=Namgay282004_practical4a-cicd
- **GitHub Actions**: https://github.com/Namgay282004/practical4a-cicd/actions
- **SonarCloud Documentation**: https://docs.sonarcloud.io/

---

**Next Step**: After completing Practical 4a, proceed to Practical 4b for DAST implementation!
