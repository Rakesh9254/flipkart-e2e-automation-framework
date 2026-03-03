package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.*;
import utils.ExcelUtility;
import utils.LoggerConfig;
import utils.SeleniumUtils;

public class FlipkartSteps {

    SearchPage searchPage;
    FilterPage filterPage;
    FashionPage fashionPage;
    AddToCartPage addToCartPage;
    TitlePricePage titlePricePage;

    private void initPages() {
        if (searchPage == null) {
            searchPage = new SearchPage(BaseTest.getDriver());
            filterPage = new FilterPage(BaseTest.getDriver());
            fashionPage = new FashionPage(BaseTest.getDriver());
            addToCartPage = new AddToCartPage(BaseTest.getDriver());
            titlePricePage = new TitlePricePage(BaseTest.getDriver());
        }
    }

    @Given("User is on the Flipkart homepage")
    public void user_is_on_the_flipkart_homepage() {
        initPages();
        LoggerConfig.info("On Homepage: " + SeleniumUtils.getTitle());
    }

    @When("User searches for {string}")
    public void user_searches_for(String product) {
        searchPage.searchProduct(product);
    }

    @Then("User validates the search results")
    public void user_validates_the_search_results() {
        addToCartPage.printAllResults();
    }

    @And("User extracts all result titles and prices")
    public void user_extracts_all_result_titles_and_prices() {
        titlePricePage.extractTitlesAndPrices();
    }

    // --- Filter Steps ---

    @And("User applies price filter between {string} and {string}")
    public void user_applies_price_filter(String min, String max) {
        filterPage.selectMinPrice(min);
        SeleniumUtils.pause(2);
        filterPage.selectMaxPrice(max);
        SeleniumUtils.pause(2);
    }

    @And("User applies brand filter")
    public void user_applies_brand_filter() {
        filterPage.selectBrand();
        SeleniumUtils.pause(2);
    }

    @And("User applies RAM filter")
    public void user_applies_ram_filter() {
        filterPage.selectRam();
        SeleniumUtils.pause(2);
    }

    @Then("User validates the search results limit")
    public void user_validates_the_search_results_limit() {
        titlePricePage.extractTitlesAndPrices();
    }

    // --- Fashion Steps ---

    @When("User hovers over Fashion menu")
    public void user_hovers_over_fashion_menu() {
        fashionPage.hoverOverFashion();
        SeleniumUtils.pause(1);
    }

    @And("User hovers over Kids menu")
    public void user_hovers_over_kids_menu() {
        fashionPage.hoverOverKids();
        SeleniumUtils.pause(1);
    }

    @And("User clicks on Girls Dresses")
    public void user_clicks_on_girls_dresses() {
        fashionPage.clickGirlsDresses();
    }

    @Then("User validates the redirected Fashion page title")
    public void user_validates_the_redirected_fashion_page_title() {
        LoggerConfig.info("Fashion Page: " + SeleniumUtils.getTitle());
    }

    // --- Add to Cart Steps ---

    @And("User clicks on the 3rd result in the list")
    public void user_clicks_on_the_3rd_result() {
        addToCartPage.clickOnProduct(2);
    }

    @And("User enters pincode {string} and verifies")
    public void user_enters_pincode(String pincode) {
        addToCartPage.enterPincode(pincode);
    }

    @Then("User verifies the product title on the new tab")
    public void user_verifies_product_title() {
        addToCartPage.verifyProductTitle();
    }

    @And("User clicks on Add to Cart")
    public void user_clicks_add_to_cart() {
        addToCartPage.clickAddToCart();
    }

    // --- Data Driven Steps ---

    @When("User iterates through Excel sheet {string} and performs search")
    public void user_iterates_excel(String sheetName) {
        ExcelUtility.loadSheet(sheetName);
        int rows = ExcelUtility.getRowCount();

        for (int i = 1; i <= rows; i++) {
            String product = ExcelUtility.getCellData(i, 0);
            if (!product.isEmpty()) {
                LoggerConfig.info("Excel search: " + product);
                searchPage.searchProduct(product);
                SeleniumUtils.pause(2);
                ExcelUtility.setCellData(i, 1, "Pass");
                SeleniumUtils.navigateBack();
                SeleniumUtils.pause(2);
            }
        }
    }

    @Then("Search results for all Excel products are logged")
    public void search_results_logged() {
        ExcelUtility.close();
        LoggerConfig.info("Excel data driven test completed.");
    }
}
