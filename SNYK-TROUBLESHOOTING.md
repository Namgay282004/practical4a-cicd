# Snyk Integration Troubleshooting Guide

## Issue: Snyk CLI Authentication Failed

### Problem Description
The error you encountered shows:
- `xdg-open: no method available for opening` - Browser authentication failing in headless environment
- `authentication failed (timeout)` - Cannot complete interactive authentication

### Root Cause
The workflow is trying to run `snyk auth` interactively, which requires a web browser. GitHub Actions runs in a headless environment without a display, so interactive authentication fails.

## ✅ Solution: Use Snyk GitHub Action with Token

### Step 1: Create Snyk Account and Get API Token

1. **Sign up for Snyk**: Visit https://app.snyk.io/signup
   - Sign up with your GitHub account or email
   - Complete the onboarding process

2. **Get your API Token**:
   - Go to https://app.snyk.io/account
   - Click on "API Token" or "Auth Token" section
   - Copy the token (it starts with `snyk-`)

### Step 2: Add Token to GitHub Repository Secrets

1. Go to your repository: `https://github.com/Namgay282004/practical4a-cicd/settings/secrets/actions`
2. Click "New repository secret"
3. Set:
   - **Name**: `SNYK_TOKEN` (exact case-sensitive)
   - **Value**: Your API token from Step 1
4. Click "Add secret"

### Step 3: Test the Integration

Run the test workflow we created:
```bash
git add .
git commit -m "fix: add Snyk authentication troubleshooting and test workflow"
git push origin main
```

Then go to your repository's Actions tab and run the "Snyk Authentication Test" workflow manually.

## 🔧 Fixed Workflow Implementation

We've updated your workflows to use the Snyk GitHub Action properly:

### Enhanced Security Workflow (`.github/workflows/maven.yml`)
- ✅ Uses `snyk/actions/maven@master` instead of CLI
- ✅ Automatic token-based authentication
- ✅ Proper error handling with `continue-on-error`
- ✅ SARIF upload to GitHub Security tab

### Advanced Security Pipeline (`.github/workflows/security-advanced.yml`)
- ✅ Matrix strategy for different scan types
- ✅ Token verification before running scans
- ✅ Graceful handling of authentication issues

### New Test Workflow (`.github/workflows/snyk-test.yml`)
- ✅ Manual and automatic testing
- ✅ Clear setup instructions
- ✅ Simplified authentication testing

## 📋 Verification Checklist

After setting up the SNYK_TOKEN:

- [ ] Token added to repository secrets
- [ ] Workflows updated with latest fixes
- [ ] Test workflow runs successfully
- [ ] SARIF files uploaded to GitHub Security tab
- [ ] No authentication errors in logs

## 🚨 Common Issues and Solutions

### Issue 1: "SNYK_TOKEN is not set"
**Solution**: Verify the secret name is exactly `SNYK_TOKEN` (case-sensitive)

### Issue 2: "Invalid token" errors
**Solution**: 
- Regenerate token in Snyk dashboard
- Ensure token is copied completely (starts with `snyk-`)
- Update repository secret with new token

### Issue 3: No vulnerabilities found after fixes
**Solution**: This is expected! Your Spring Boot upgrade and dependency fixes resolved most issues.

### Issue 4: SARIF upload fails
**Solution**: Check if SARIF files are generated:
- Look for `continue-on-error: true` in workflow
- Check workflow logs for file generation
- Ensure GitHub Advanced Security is enabled

## 🎯 Expected Results After Setup

Once properly configured, you should see:

1. **GitHub Actions**: All security workflows run successfully
2. **Security Tab**: Vulnerability reports from Snyk
3. **Pull Requests**: Automatic security checks on new code
4. **Dashboard**: Security badges showing clean status

## 🔄 Next Steps

1. **Commit and push** the fixes:
   ```bash
   git add .
   git commit -m "fix: resolve Snyk authentication issues and add troubleshooting"
   git push origin main
   ```

2. **Configure Snyk token** following steps above

3. **Run the test workflow** to verify integration

4. **Check GitHub Security tab** for vulnerability reports

## 📚 Additional Resources

- [Snyk GitHub Action Documentation](https://github.com/snyk/actions)
- [GitHub Secrets Documentation](https://docs.github.com/en/actions/security-guides/using-secrets-in-github-actions)
- [SARIF Upload Documentation](https://docs.github.com/en/code-security/code-scanning/integrating-with-code-scanning/uploading-a-sarif-file-to-github)

The key fix is using the Snyk GitHub Action instead of trying to install and authenticate the CLI manually in the workflow. The GitHub Action handles authentication automatically using your repository secret.
