# Flipkart E2E Automation Framework

A Selenium-based end-to-end test automation framework for Flipkart, built with **Java**, **Cucumber BDD**, **TestNG**, and **Page Object Model (POM)** design pattern.

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 11 | Programming Language |
| Selenium 4.21 | Browser Automation |
| Cucumber 7.18 | BDD Framework |
| TestNG 7.10 | Test Runner |
| Apache POI 5.2 | Excel Data-Driven Testing |
| Log4j2 | Logging |
| Extent Reports | HTML Reporting |
| WebDriverManager | Auto ChromeDriver Setup |
| Maven | Build & Dependency Management |

## Project Structure

```
src/
├── main/java/
│   ├── base/
│   │   └── BaseTest.java           # Browser init, config, popup handling
│   ├── hooks/
│   │   └── Hooks.java              # Cucumber @Before/@After hooks
│   ├── pages/
│   │   ├── SearchPage.java         # Search box & search button
│   │   ├── FilterPage.java         # Price, brand, RAM filters
│   │   ├── FashionPage.java        # Fashion menu hover navigation
│   │   ├── AddToCartPage.java      # Product selection & cart
│   │   └── TitlePricePage.java     # Extract product titles & prices
│   └── utils/
│       ├── SeleniumUtils.java      # Click, type, scroll, waits
│       ├── LoggerConfig.java       # Log4j2 wrapper
│       └── ExcelUtility.java       # Read/write Excel test data
│
├── test/java/
│   ├── runners/
│   │   └── TestRunner.java         # Cucumber-TestNG runner
│   └── stepdefinitions/
│       └── FlipkartSteps.java      # Step definitions
│
└── test/resources/
    ├── features/
    │   └── Flipkart.feature        # BDD scenarios
    ├── config.properties           # URL, browser, timeouts
    ├── testdata.xlsx               # Excel test data
    ├── extent.properties           # Report config
    └── log4j2.properties           # Logger config
```

## Setup & Run

### Prerequisites
- Java 11+
- Maven 3.6+
- Chrome browser installed

### Run Tests
```bash
git clone git@github.com:Rakesh9254/flipkart-e2e-automation-framework.git
cd flipkart-e2e-automation-framework
mvn test -DsuiteXmlFile=testng.xml
```

## Key Features

- **Page Object Model** — Clean separation of locators and test logic
- **Cucumber BDD** — Human-readable `.feature` files with Gherkin syntax
- **Auto Login Popup Dismissal** — Handles Flipkart's login overlay automatically
- **JS Click Fallback** — Falls back to JavaScript click when elements are blocked
- **Excel Data-Driven** — Read test data from `.xlsx` files using Apache POI
- **Screenshot on Failure** — Auto-captures screenshot when a test fails
- **Extent + Cucumber Reports** — HTML reports generated after each run

## Test Scenarios

| Scenario | Tags | Description |
|---|---|---|
| Search (mobile) | `@Regression @Search` | Search for "mobile" and validate results |
| Search (laptop) | `@Regression @Search` | Search for "laptop" and validate results |

## Reports

After test execution, reports are available at:
- **Cucumber HTML** — `test-output/cucumber-reports/cucumber-pretty.html`
- **Extent Report** — `test-output/SparkReport/`
- **Cucumber Cloud** — Auto-published link shown in console output

## CI/CD — GitHub Actions

Tests run automatically on every **push** and **pull request** to `main`.

- ✅ Sets up Java 11 + Chrome on Ubuntu
- ✅ Runs tests in **headless mode** (no display needed)
- ✅ Uploads test reports as downloadable artifacts

📍 **Pipeline config:** [`.github/workflows/ci.yml`](.github/workflows/ci.yml)

![CI](https://github.com/Rakesh9254/flipkart-e2e-automation-framework/actions/workflows/ci.yml/badge.svg)

## Author

**Rakesh** — [GitHub](https://github.com/Rakesh9254)
