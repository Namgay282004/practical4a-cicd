# 📸 Practical 4B DAST Screenshot Guide: OWASP ZAP

## 🎯 Required Screenshots for Practical 4B Submission

### Core Screenshots (Required):

#### 1. **GitHub Actions ZAP Workflow Success** ⭐ CRITICAL
**What to capture**: Successful ZAP DAST workflow execution
**Where to find**:
1. Go to: https://github.com/Namgay282004/practical4a-cicd/actions  
2. Click on "OWASP ZAP DAST Security Scan" workflow
3. Show the successful completion with green checkmarks
4. **Screenshot must include**:
   - Workflow name: "OWASP ZAP DAST Security Scan"
   - All jobs completed successfully ✅
   - Execution time and completion status
   - Build summary showing scan completion

#### 2. **ZAP Baseline Scan Results** ⭐ CRITICAL  
**What to capture**: ZAP scan report showing detected vulnerabilities
**Where to find**:
1. In the successful workflow, scroll to "Artifacts" section
2. Download "zap-baseline-report" artifact
3. Open the `report_html.html` file in browser
4. **Screenshot must include**:
   - ZAP report header with scan summary
   - Risk level breakdown (High, Medium, Low, Informational)
   - At least 2-3 detected security vulnerabilities
   - Alert details showing vulnerability descriptions

#### 3. **ZAP Security Issues Detail View** ⭐ CRITICAL
**What to capture**: Detailed view of specific DAST vulnerabilities  
**Where to find**:
1. In the ZAP HTML report, click on specific alerts
2. Show detailed vulnerability information
3. **Screenshot must include**:
   - Vulnerability name and risk level
   - Affected URL/endpoint 
   - Technical description
   - Evidence of the vulnerability
   - Remediation advice

#### 4. **GitHub Actions ZAP Job Execution** ⭐ CRITICAL
**What to capture**: ZAP scan job execution logs
**Where to find**:
1. In GitHub Actions workflow, click "ZAP Baseline Scan" job
2. Expand the "Run ZAP Baseline Scan" step
3. **Screenshot must include**:
   - ZAP scan execution logs
   - Application startup confirmation (port 3000)
   - Scan completion message
   - No critical errors in execution

### Supporting Screenshots (Optional but Recommended):

#### 5. **ZAP Full Scan Results** (Optional)
**What to capture**: Comprehensive ZAP full scan report
**How to trigger**:
1. Go to GitHub Actions → "OWASP ZAP DAST Security Scan"
2. Click "Run workflow" → Select "full" scan type → Run workflow
3. Download "zap-full-scan-report" artifact
4. **Screenshot should include**:
   - More comprehensive vulnerability analysis
   - Higher number of detected issues
   - Active scanning results

#### 6. **Vulnerable Code Examples** (Optional)
**What to capture**: Source code showing intentional DAST vulnerabilities
**Where to find**:
1. Open `src/main/java/.../DastVulnerableController.java`
2. Show examples of vulnerable code
3. **Screenshot should include**:
   - XSS vulnerability code (`/search` endpoint)
   - Missing security headers (`/insecure-page`)
   - Insecure cookie settings (`/set-cookie`)

#### 7. **ZAP Rules Configuration** (Optional)  
**What to capture**: Custom ZAP scanning rules
**Where to find**:
1. Open `.zap/rules.tsv` file
2. Show configured rules and thresholds
3. **Screenshot should include**:
   - Rule IDs and actions (FAIL, WARN, IGNORE)
   - Security rule categories
   - Custom configuration settings

## 📋 Screenshot Checklist

### Essential Screenshots (Must Have):
- [ ] **GitHub Actions ZAP Workflow Success** 
- [ ] **ZAP Baseline Scan Results** (HTML Report)
- [ ] **ZAP Security Issues Detail View**
- [ ] **GitHub Actions ZAP Job Execution Logs**

### Additional Screenshots (Recommended):
- [ ] **ZAP Full Scan Results** (if full scan completed)
- [ ] **Vulnerable Code Examples** 
- [ ] **ZAP Rules Configuration**

## 🔗 Quick Navigation Links

### GitHub Actions
- **Workflow Runs**: https://github.com/Namgay282004/practical4a-cicd/actions
- **ZAP DAST Workflow**: https://github.com/Namgay282004/practical4a-cicd/actions/workflows/zap-dast.yml

### Repository Files  
- **ZAP Rules**: https://github.com/Namgay282004/practical4a-cicd/blob/main/.zap/rules.tsv
- **Vulnerable Code**: https://github.com/Namgay282004/practical4a-cicd/blob/main/src/main/java/sg/edu/nus/iss/cicddemo/Controller/DastVulnerableController.java
- **ZAP Workflow**: https://github.com/Namgay282004/practical4a-cicd/blob/main/.github/workflows/zap-dast.yml

## 🎯 What Your Screenshots Should Demonstrate

### DAST Functionality Proof:
1. ✅ **ZAP Integration Working** - Successful workflow execution
2. ✅ **Runtime Vulnerability Detection** - Actual security issues found  
3. ✅ **Multiple Vulnerability Types** - XSS, headers, cookies, etc.
4. ✅ **Automated DAST Pipeline** - CI/CD integration functional
5. ✅ **Security Reports Generated** - Professional HTML reports

### Technical Evidence:
- **15+ Security Vulnerabilities** detected by ZAP
- **High/Medium/Low risk** categorization  
- **Specific endpoints** tested and analyzed
- **Runtime behavior** analysis (not just code)
- **Security headers** validation

## 📊 Screenshot Organization

Organize your screenshots with clear names:
```
practical4b-screenshots/
├── 07-github-actions-zap-workflow-success.png ⭐ REQUIRED
├── 08-zap-baseline-scan-results.png ⭐ REQUIRED  
├── 09-zap-security-issues-detail.png ⭐ REQUIRED
├── 10-github-actions-zap-job-execution.png ⭐ REQUIRED
├── 11-zap-full-scan-results.png (optional)
├── 12-vulnerable-code-examples.png (optional)
└── 13-zap-rules-configuration.png (optional)
```

## 🚨 Important Notes

### Before Taking Screenshots:
1. ✅ **Wait for workflow completion** - Don't screenshot failed runs
2. ✅ **Verify ZAP detected vulnerabilities** - Should find 10+ issues
3. ✅ **Check artifact generation** - Reports must be available for download
4. ✅ **Test application endpoints** - Ensure vulnerabilities are accessible

### Common Issues to Avoid:
❌ **Empty ZAP reports** - Indicates application didn't start properly  
❌ **Workflow timeouts** - Application startup issues on port 3000
❌ **Missing artifacts** - Upload action failed (fixed with v4 update)
❌ **No vulnerabilities found** - ZAP couldn't access vulnerable endpoints

### If ZAP Scan Finds No Issues:
1. **Check application startup** - Verify port 3000 is accessible
2. **Test vulnerable endpoints manually**:
   ```bash
   curl http://localhost:3000/search?query=<script>alert(1)</script>
   curl http://localhost:3000/insecure-page  
   curl http://localhost:3000/set-cookie
   ```
3. **Verify ZAP rules** - Ensure rules.tsv is properly configured
4. **Check container logs** - Application may have startup issues

## 🎉 Success Indicators

### Your DAST implementation is working correctly if:
✅ **ZAP workflow completes successfully** without errors  
✅ **10+ vulnerabilities detected** across multiple risk levels
✅ **HTML report generated** with detailed findings
✅ **Vulnerable endpoints accessible** during scan
✅ **Security issues categorized** properly (High/Medium/Low)
✅ **Artifacts uploaded** and downloadable from GitHub

## 🔄 Next Steps After Screenshots

1. **Review ZAP findings** - Understand each vulnerability type
2. **Compare with SAST results** - See differences between static/dynamic
3. **Plan remediation** - Prioritize High/Medium risk issues
4. **Document learning** - Note differences between SAST and DAST
5. **Prepare submission** - Organize screenshots with implementation report

---

**🎯 Focus Priority: Get the 4 core screenshots showing ZAP successfully detecting vulnerabilities in your running application!**

**⚡ Quick Test**: Visit http://localhost:3000/search?query=test during workflow run to verify app is accessible for DAST scanning.
