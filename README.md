# Saucedemo Automation

![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Selenium](https://img.shields.io/badge/selenium-%2343B02A.svg?style=for-the-badge&logo=selenium&logoColor=white)
![TestNG](https://img.shields.io/badge/testng-%23F2A400.svg?style=for-the-badge&logo=testng&logoColor=white)
![Maven](https://img.shields.io/badge/maven-%23C71A36.svg?style=for-the-badge&logo=apachemaven&logoColor=white)
![Allure](https://img.shields.io/badge/allure-%235D2B86.svg?style=for-the-badge&logo=allure&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white)


> POM-based UI automation for Saucedemo web with Selenium + TestNG + Maven.  
> Fast, readable tests, grouped by smoke, regression, positive, and negative, with Allure reporting and CI/CD integration.

---

## Highlights

- Clean Page Object Model with a minimal `BasePage`
- Readable locators and explicit waits
- Allure reports: trends, steps, attachments
- GitHub Actions CI/CD pipeline with automated test execution
- Configurable browser mode (visible locally, headless in CI)

---

## Tech Stack

| Tool | Purpose |
|---|---|
| **Java 21** | Core language |
| **Selenium 4.38** | Browser automation |
| **TestNG** | Grouping & assertions |
| **Maven** | Build & dependencies |
| **Allure** | Reporting |
| **GitHub Actions** | CI/CD automation |

---

## Project Structure
```bash
src/
├─ main/java/
│ ├─ common/
│ │ └─ DataProviders.java
│ ├─ core/
│ │ └─ BasePage.java
│ ├─ pages/
│ │ ├─ HomePage.java
│ │ └─ LoginPage.java
│ └─ utils/
│ ├─ CredentialsUtils.java
│ └─ DriverManager.java
└─ test/java/
├─ core/
│ └─ BaseTest.java
├─ negative/
│ └─ LoginNegativeTest.java
├─ positive/
│ └─ LoginPositiveTest.java
├─ regression/
│ ├─ CartFunctionalityTest.java
│ ├─ HomePageAddToCartTest.java
│ ├─ HomePageNameSortTest.java
│ ├─ HomePagePriceSortTest.java
│ └─ RemoveFromCartTest.java
├─ smoke/
│ ├─ HomePageVisibilityTest.java
│ └─ LoginFieldVisibilityTest.java
└─ utils/
  └─ DataProviders.java
```

---

## Test Coverage

| Group | What it checks |
|---|---|
| **Smoke** | Login field visibility, home page elements visibility, product listings display, basic functionality |
| **Regression** | Add to cart, remove from cart, cart badge functionality, name sorting (A-Z, Z-A), price sorting (low-high, high-low), product sorting changes |
| **Negative** | Empty credentials validation, incorrect username/password, locked out user, special characters, very long strings, spaces-only inputs |
| **Positive** | Successful login with all valid usernames, multiple logins, login after page refresh, login with leading/trailing spaces |

**Total Test Cases:** 47 test methods

---

## How to Run

**Prereqs:** JDK 21, Maven 3.9+, Google Chrome

```bash
# All tests
mvn clean test

# By TestNG group
mvn -Dgroups=smoke test
mvn -Dgroups=regression test
mvn -Dgroups=negative test
mvn -Dgroups=positive test
```

### Allure Report

```bash
# Generate and open
mvn allure:serve

# Generate only
mvn allure:report
```

---

## CI/CD

Tests automatically run on GitHub Actions for every push and pull request. The workflow:
- Runs tests automatically on push/PR
- Generates Allure reports
- Uploads test results and reports as artifacts
- Runs tests in headless mode for CI environment

See `.github/workflows/ci.yml` for configuration.



