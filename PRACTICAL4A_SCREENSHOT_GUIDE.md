# Practical 4a Screenshot Guide: SonarCloud SAST Implementation

## Overview
This guide will help you capture all the required screenshots for Practical 4a submission. Follow this step-by-step approach to document your SonarCloud SAST implementation.

## Prerequisites Checklist
Before capturing screenshots, ensure:
- [ ] SonarCloud account created and configured
- [ ] GitHub repository connected to SonarCloud
- [ ] SONAR_TOKEN added to GitHub Secrets
- [ ] Code pushed to GitHub repository
- [ ] GitHub Actions workflow executed successfully

## Required Screenshots

### 1. SonarCloud Account Setup
**Screenshot Name**: `01_sonarcloud_account.png`
**What to capture**: SonarCloud dashboard showing your account
**Steps**:
1. Go to [https://sonarcloud.io](https://sonarcloud.io)
2. Log in to your account
3. Take a screenshot of the main dashboard showing:
   - Your username/organization
   - Connected repositories
   - Project overview

### 2. SonarCloud Project Configuration
**Screenshot Name**: `02_sonarcloud_project_config.png`
**What to capture**: Project settings in SonarCloud
**Steps**:
1. Navigate to your project in SonarCloud (`practical4a-cicd`)
2. Go to "Project Settings" or "Administration"
3. Capture screenshot showing:
   - Project key: `Namgay282004_practical4a-cicd`
   - Organization: `namgay282004`
   - Project configuration details

### 3. Repository Configuration Files
**Screenshot Name**: `03_sonar_project_properties.png`
**What to capture**: Your `sonar-project.properties` file
**Steps**:
1. Open your local project or GitHub repository
2. Display the `sonar-project.properties` file
3. Take a screenshot showing the file contents with:
   - Project key and organization
   - Configuration parameters

**Screenshot Name**: `04_github_workflow_build.png`
**What to capture**: Your GitHub Actions workflow file
**Steps**:
1. Show the `.github/workflows/build.yml` file
2. Capture the complete workflow configuration including:
   - Trigger conditions (push, pull_request)
   - SonarQube scan job
   - Environment variables

### 4. GitHub Secrets Configuration
**Screenshot Name**: `05_github_secrets.png`
**What to capture**: GitHub repository secrets showing SONAR_TOKEN
**Steps**:
1. Go to your GitHub repository
2. Navigate to "Settings" → "Secrets and variables" → "Actions"
3. Take a screenshot showing:
   - `SONAR_TOKEN` secret configured (value will be hidden)
   - Any other relevant secrets

### 5. GitHub Actions Execution
**Screenshot Name**: `06_github_actions_workflow.png`
**What to capture**: GitHub Actions workflow execution
**Steps**:
1. Go to "Actions" tab in your GitHub repository
2. Select the most recent workflow run
3. Capture screenshot showing:
   - Workflow name and status
   - All jobs (build, test, sonarqube)
   - Execution times and status

**Screenshot Name**: `07_github_actions_sonarqube_job.png`
**What to capture**: Detailed view of SonarQube job execution
**Steps**:
1. Click on the "SonarQube" job in the workflow
2. Expand the "SonarQube Scan" step
3. Take a screenshot showing:
   - Job execution logs
   - SonarQube analysis details
   - Success/failure status

### 6. SonarCloud Analysis Results
**Screenshot Name**: `08_sonarcloud_analysis_overview.png`
**What to capture**: Main analysis dashboard in SonarCloud
**Steps**:
1. Go to your project in SonarCloud
2. View the "Overview" tab
3. Capture screenshot showing:
   - Code quality metrics (Bugs, Vulnerabilities, Code Smells)
   - Security rating
   - Maintainability rating
   - Coverage information

**Screenshot Name**: `09_sonarcloud_issues_detail.png`
**What to capture**: Detailed issues view
**Steps**:
1. Click on "Issues" tab in SonarCloud
2. Show the list of detected issues
3. Capture screenshot showing:
   - Types of issues found
   - Severity levels
   - File locations
   - Issue descriptions

### 7. Security Analysis Results
**Screenshot Name**: `10_sonarcloud_security_hotspots.png`
**What to capture**: Security hotspots and vulnerabilities
**Steps**:
1. Navigate to "Security Hotspots" tab
2. If any security issues are found, capture them
3. Show security-related metrics and recommendations

### 8. Code Quality Metrics
**Screenshot Name**: `11_sonarcloud_measures.png`
**What to capture**: Detailed code quality measures
**Steps**:
1. Go to "Measures" tab in SonarCloud
2. Capture screenshot showing:
   - Lines of code
   - Cyclomatic complexity
   - Duplication metrics
   - Technical debt

### 9. Integration Verification
**Screenshot Name**: `12_github_security_tab.png`
**What to capture**: GitHub Security tab (if SARIF upload is configured)
**Steps**:
1. Go to "Security" tab in your GitHub repository
2. Check "Code scanning alerts" section
3. Capture any security alerts imported from SonarCloud

## Testing Your Setup

### Step 1: Trigger a New Analysis
1. Make a small change to your code (add a comment)
2. Commit and push to GitHub
3. Verify that GitHub Actions triggers automatically
4. Wait for SonarCloud analysis to complete

### Step 2: Create a Test Pull Request
1. Create a new branch with some code changes
2. Create a pull request
3. Verify that SonarCloud analysis runs on the PR
4. Capture screenshot of PR checks

## Additional Screenshots (Optional but Recommended)

### Quality Gate Status
**Screenshot Name**: `13_quality_gate_status.png`
**What to capture**: Quality gate pass/fail status
**Steps**:
1. View quality gate results in SonarCloud
2. Show whether your project passes the quality gate
3. Display any quality gate conditions

### Historical Trends
**Screenshot Name**: `14_sonarcloud_activity.png`
**What to capture**: Project activity and trends
**Steps**:
1. Go to "Activity" tab in SonarCloud
2. Show analysis history
3. Capture trends in code quality over time

## Troubleshooting Common Issues

### Issue: No Analysis Data in SonarCloud
**Possible Causes**:
- SONAR_TOKEN not configured correctly
- Workflow didn't run or failed
- Project not properly connected

**Solution Steps**:
1. Check GitHub Actions logs for errors
2. Verify SONAR_TOKEN is valid
3. Ensure sonar-project.properties is correct

### Issue: Workflow Fails
**Possible Causes**:
- Missing or invalid SONAR_TOKEN
- Network connectivity issues
- Project configuration errors

**Solution Steps**:
1. Review GitHub Actions logs
2. Check secret configuration
3. Validate sonar-project.properties syntax

### Issue: No Security Results
**Possible Causes**:
- No vulnerabilities found (good!)
- SAST analysis not enabled
- Code doesn't contain security issues

**Solution Steps**:
1. Add some intentionally vulnerable code for testing
2. Verify SonarCloud security rules are enabled
3. Check if security hotspots feature is available

## Code to Add for Testing (Optional)

If your project doesn't show many issues, you can temporarily add this code to demonstrate SonarCloud capabilities:

```java
// Add to DataController.java for demonstration
@GetMapping("/vulnerable-example")
public String vulnerableExample(@RequestParam String input) {
    // Security issue: potential XSS vulnerability
    return "<html><body>Hello " + input + "</body></html>";
}

@GetMapping("/code-smell-example")
public String codeSmellExample() {
    // Code smell: unused variable
    String unusedVariable = "This variable is never used";
    
    // Code smell: redundant condition
    if (true) {
        return "Always true condition";
    }
    return "Never reached";
}
```

## Submission Checklist

Before submitting, ensure you have:
- [ ] All 12 main screenshots captured
- [ ] Screenshots are clear and readable
- [ ] File names match the suggested naming convention
- [ ] Screenshots show relevant information clearly
- [ ] GitHub Actions workflow executed successfully
- [ ] SonarCloud analysis completed
- [ ] Issues (if any) are documented

## File Organization

Create a folder structure for your screenshots:
```
practical4a-screenshots/
├── 01_sonarcloud_account.png
├── 02_sonarcloud_project_config.png
├── 03_sonar_project_properties.png
├── 04_github_workflow_build.png
├── 05_github_secrets.png
├── 06_github_actions_workflow.png
├── 07_github_actions_sonarqube_job.png
├── 08_sonarcloud_analysis_overview.png
├── 09_sonarcloud_issues_detail.png
├── 10_sonarcloud_security_hotspots.png
├── 11_sonarcloud_measures.png
├── 12_github_security_tab.png
├── 13_quality_gate_status.png (optional)
└── 14_sonarcloud_activity.png (optional)
```

## Next Steps

After capturing all screenshots:
1. Review each screenshot for clarity and completeness
2. Create a summary document explaining your implementation
3. Prepare your submission with all required materials
4. Test your implementation one final time

## Important Notes

- **Privacy**: Ensure screenshots don't contain sensitive information
- **Quality**: Use high resolution screenshots that are easy to read
- **Context**: Include enough context in each screenshot to understand what's being shown
- **Timestamps**: Screenshots should reflect recent analysis results
- **Consistency**: Use consistent browser/interface for professional appearance

---

**Good luck with your Practical 4a submission! 🚀**
