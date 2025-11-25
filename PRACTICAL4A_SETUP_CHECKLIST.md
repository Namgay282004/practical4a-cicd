# Practical 4a Pre-Screenshot Setup Checklist

## Current Status ✅
- [x] `sonar-project.properties` configured
- [x] `.github/workflows/build.yml` created
- [x] Repository structure ready

## Before Taking Screenshots - Setup Steps

### 1. SonarCloud Account Setup (5 minutes)
**REQUIRED FIRST STEP**

1. **Go to SonarCloud**: Visit [https://sonarcloud.io](https://sonarcloud.io)
2. **Sign Up/Login**: Use your GitHub account for easier integration
3. **Import Repository**: 
   - Click "Import an organization"
   - Select your GitHub account (`Namgay282004`)
   - Import the `practical4a-cicd` repository

### 2. Get Your SonarCloud Token (3 minutes)

1. **Account Settings**: Click your avatar → "My Account"
2. **Security Tab**: Click "Security" tab
3. **Generate Token**: 
   - Name: "GitHub Actions Token"
   - Click "Generate"
   - **COPY THE TOKEN** (you won't see it again!)

### 3. Add GitHub Secret (2 minutes)

1. **Repository Settings**: Go to your GitHub repo → Settings
2. **Secrets**: Navigate to "Secrets and variables" → "Actions"
3. **New Secret**: 
   - Name: `SONAR_TOKEN`
   - Value: Paste your SonarCloud token
   - Click "Add secret"

### 4. Commit and Push Your Changes (2 minutes)

Let's commit all your SonarCloud setup files:

```bash
# Add all the new SonarCloud files
git add .github/workflows/build.yml
git add sonar-project.properties
git add PRACTICAL4A_SCREENSHOT_GUIDE.md

# Commit the changes
git commit -m "Add SonarCloud SAST integration for Practical 4a

- Configure sonar-project.properties with project settings
- Add GitHub Actions workflow for SonarQube scanning  
- Include comprehensive screenshot guide for submission"

# Push to trigger the workflow
git push origin main
```

### 5. Monitor the First Run (5 minutes)

1. **GitHub Actions**: Go to Actions tab in your repository
2. **Watch Workflow**: The build workflow should trigger automatically
3. **Check Results**: Wait for completion (usually 2-3 minutes)

## Quick Setup Script

Run these commands in your project directory:

```bash
# 1. Commit SonarCloud setup
git add .github/workflows/build.yml sonar-project.properties PRACTICAL4A_SCREENSHOT_GUIDE.md
git commit -m "feat: Add SonarCloud SAST integration for Practical 4a"

# 2. Push to trigger workflow
git push origin main

# 3. Open GitHub Actions to monitor
echo "🚀 Changes pushed! Go to GitHub Actions tab to monitor the workflow."
echo "📝 Don't forget to:"
echo "   1. Set up SonarCloud account"
echo "   2. Get SONAR_TOKEN"
echo "   3. Add secret to GitHub"
echo "   4. Wait for workflow completion"
echo "   5. Follow screenshot guide"
```

## Expected Workflow Result

After pushing, you should see:
- ✅ GitHub Actions workflow triggered
- ✅ SonarQube job runs (may fail initially without SONAR_TOKEN)
- ✅ Once SONAR_TOKEN is added, subsequent runs should succeed

## Screenshot Capture Order

Follow this sequence for best results:

1. **Setup Screenshots** (Account, Config) - Take these during setup
2. **Push Changes** - Trigger the workflow  
3. **Workflow Screenshots** - Capture while workflow runs
4. **Results Screenshots** - After successful completion
5. **Analysis Screenshots** - SonarCloud dashboard results

## Troubleshooting Quick Fixes

### Workflow Fails Initially
- **Expected**: First run will fail without SONAR_TOKEN
- **Solution**: Add the secret and re-run the workflow

### No SonarCloud Data
- **Check**: Ensure project is imported in SonarCloud
- **Verify**: Token has correct permissions
- **Retry**: Re-run the GitHub Actions workflow

### Analysis Takes Long
- **Normal**: First analysis can take 2-5 minutes
- **Wait**: Be patient for complete analysis

## Success Indicators

You're ready for screenshots when:
- ✅ GitHub Actions workflow completes successfully
- ✅ SonarCloud shows analysis results
- ✅ Project appears in SonarCloud dashboard
- ✅ No errors in workflow logs

---

**Next Step**: Follow the `PRACTICAL4A_SCREENSHOT_GUIDE.md` to capture all required screenshots! 📸
