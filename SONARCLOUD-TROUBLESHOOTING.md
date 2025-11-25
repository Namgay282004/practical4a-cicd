# 🚨 SonarCloud Fix for Analysis Failure

## Current Problem
The SonarCloud scan is failing with project configuration issues. Let's fix this step by step.

## ✅ IMMEDIATE FIX - Updated Workflow

The issue is with how we're running the SonarCloud analysis. Let's use a more robust approach.

## Step 1: First, ensure you have SONAR_TOKEN set

**CRITICAL**: Make sure you have:
1. Created SonarCloud account at https://sonarcloud.io
2. Generated a token from: Profile → My Account → Security → Generate Token
3. Added token to GitHub Secrets as `SONAR_TOKEN`

## Step 2: Check Current Files

Your current configuration should be:
- `.github/workflows/build.yml` ✅ Updated 
- `sonar-project.properties` ✅ Updated
- `pom.xml` ✅ Has SonarCloud plugin

## Step 3: Alternative Workflow (If Current Still Fails)

If the current approach keeps failing, we can try the SonarQube GitHub Action instead:

```yaml
name: Build
on:
  push:
    branches:
      - main
  pull_request:
    types: [opened, synchronize, reopened]

jobs:
  sonarcloud:
    name: SonarCloud
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
        with:
          fetch-depth: 0  # Shallow clones should be disabled for a better relevancy of analysis
      
      - name: Set up JDK 17
        uses: actions/setup-java@v4
        with:
          java-version: 17
          distribution: 'zulu' # Alternative distribution

      - name: Cache SonarCloud packages
        uses: actions/cache@v3
        with:
          path: ~/.sonar/cache
          key: ${{ runner.os }}-sonar
          restore-keys: ${{ runner.os }}-sonar

      - name: Cache Maven packages
        uses: actions/cache@v3
        with:
          path: ~/.m2
          key: ${{ runner.os }}-m2-${{ hashFiles('**/pom.xml') }}
          restore-keys: ${{ runner.os }}-m2

      - name: Build project
        run: mvn clean compile

      - name: Run tests and SonarCloud analysis
        env:
          GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
          SONAR_TOKEN: ${{ secrets.SONAR_TOKEN }}
        run: |
          mvn clean verify sonar:sonar \
            -Dsonar.projectKey=Namgay282004_practical4a-cicd \
            -Dsonar.organization=namgay282004 \
            -Dsonar.host.url=https://sonarcloud.io
```

## Step 4: Debug Information

Let's check what might be wrong:

### Check if SONAR_TOKEN is set correctly:
1. Go to your GitHub repo: https://github.com/Namgay282004/practical4a-cicd
2. Settings → Secrets and Variables → Actions
3. Verify `SONAR_TOKEN` exists

### Check SonarCloud Project:
1. Go to https://sonarcloud.io
2. Look for project: `Namgay282004_practical4a-cicd`
3. If not found, create/import the project

## Step 5: Manual Test (Local)

Try running locally to see if configuration works:

```bash
# Set your token (replace with actual token)
export SONAR_TOKEN="your-actual-token-here"

# Run the analysis
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=Namgay282004_practical4a-cicd \
  -Dsonar.organization=namgay282004 \
  -Dsonar.host.url=https://sonarcloud.io \
  -Dsonar.token=$SONAR_TOKEN
```

## Step 6: Alternative - Use SonarQube Action

If Maven approach keeps failing, we can use the dedicated SonarCloud GitHub Action:

```yaml
name: Build
on:
  push:
    branches:
      - main
  pull_request:
    types: [opened, synchronize, reopened]

jobs:
  sonarcloud:
    name: SonarCloud
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
        with:
          fetch-depth: 0
      
      - name: SonarCloud Scan
        uses: SonarSource/sonarcloud-github-action@master
        env:
          GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
          SONAR_TOKEN: ${{ secrets.SONAR_TOKEN }}
```

## Common Issues and Solutions:

### 1. "Project key not found"
- **Solution**: Create project in SonarCloud dashboard first
- **URL**: https://sonarcloud.io → Import project from GitHub

### 2. "Invalid token"
- **Solution**: Generate new token from SonarCloud
- **URL**: https://sonarcloud.io → Profile → My Account → Security

### 3. "Organization not found"
- **Solution**: Check organization name in SonarCloud
- **Expected**: `namgay282004`

### 4. "Build failure"
- **Solution**: Ensure Java 17 and Maven are properly set up
- **Check**: `mvn clean compile` works locally

## Next Actions:

1. **Priority 1**: Verify SONAR_TOKEN is correctly set in GitHub Secrets
2. **Priority 2**: Create/verify project exists in SonarCloud
3. **Priority 3**: Try the alternative workflow if needed
4. **Priority 4**: Test locally with your token

## Quick Checklist:

- [ ] SonarCloud account created
- [ ] Project imported/created in SonarCloud 
- [ ] SONAR_TOKEN generated and copied
- [ ] GitHub Secret `SONAR_TOKEN` added
- [ ] Workflow file updated
- [ ] Configuration files correct

---

**Next Step**: Try the fixes above and let me know which specific step fails!
