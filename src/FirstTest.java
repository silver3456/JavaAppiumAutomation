
import lib.ui.MainPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;


public class FirstTest extends CoreTestCase {

    private MainPageObject mainPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        mainPageObject = new MainPageObject(driver);
    }


    @Test
    public void testSearch() {

        mainPageObject
                .waitForElementAndClick(
                        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                        "Cannot find Search Wikipedia input",
                        5
                );

        mainPageObject
                .waitForElementAndSendKeys(
                        By.xpath("//*[contains(@text, 'Search…')]"),
                        "Java",
                        "Cannot find search input",
                        5

                );


        mainPageObject
                .waitForElementPresent(
                        By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                        "Cannot find Object-oriented language, topic searching by 'Java'",
                        5

                );

    }

    @Test
    public void testCancelSearch() {

        mainPageObject
                .waitForElementAndClick(
                        By.id("org.wikipedia:id/search_container"),
                        "Cannot find 'Search Wikipedia' input",
                        5

                );

        mainPageObject
                .waitForElementAndSendKeys(
                        By.xpath("//*[contains(@text, 'Search…')]"),
                        "Java",
                        "Cannot find search input",
                        5

                );

        mainPageObject
                .waitForElementAndClear(
                        By.id("org.wikipedia:id/search_src_text"),
                        "Cannot find search text",
                        5
                );

        mainPageObject
                .waitForElementAndClick(
                        By.id("org.wikipedia:id/search_close_btn"),
                        "Cannot find 'X' to cancel search",
                        5

                );

        mainPageObject.
                waitForElementNotPresent(
                        By.id("org.wikipedia:id/search_close_btn"),
                        "'X' is still present on the page",
                        5
                );
    }

    @Test
    public void testCompareArticleTitle() {

        mainPageObject
                .waitForElementAndClick(
                        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                        "Cannot find Search Wikipedia input",
                        5
                );

        mainPageObject
                .waitForElementAndSendKeys(
                        By.xpath("//*[contains(@text, 'Search…')]"),
                        "Java",
                        "Cannot find search input",
                        5

                );

        mainPageObject
                .waitForElementAndClick(
                        By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                        "Cannot find Search Wikipedia input",
                        5
                );

        WebElement title_element = mainPageObject
                .waitForElementPresent(
                        By.id("org.wikipedia:id/view_page_title_text"),
                        "Cannot find article title",
                        15
                );

        String articlet_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                articlet_title
        );

    }

    @Test
    public void testSwipeArticle() {

        mainPageObject
                .waitForElementAndClick(
                        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                        "Cannot find Search Wikipedia input",
                        5
                );

        mainPageObject
                .waitForElementAndSendKeys(
                        By.xpath("//*[contains(@text, 'Search…')]"),
                        "Appium",
                        "Cannot find search input",
                        5
                );

        mainPageObject
                .waitForElementAndClick(
                        By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                        "Cannot find 'Appium' article in search",
                        5
                );

        mainPageObject
                .swipeUpToFindElemend(
                        By.xpath("//*[@text = 'View page in browser']"),
                        "Cannot find the end of an article",
                        20
                );
    }

    @Test
    public void testSaveFirstArticleToMyList() {
        mainPageObject
                .waitForElementAndClick(
                        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                        "Cannot find Search Wikipedia input",
                        5
                );

        mainPageObject
                .waitForElementAndSendKeys(
                        By.xpath("//*[contains(@text, 'Search…')]"),
                        "Java",
                        "Cannot find search input",
                        5

                );

        mainPageObject
                .waitForElementAndClick(
                        By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                        "Cannot find Search Wikipedia input",
                        5
                );

        mainPageObject
                .waitForElementPresent(
                        By.id("org.wikipedia:id/view_page_title_text"),
                        "Cannot find article title",
                        15
                );

        mainPageObject
                .waitForElementAndClick(
                        By.xpath("//android.widget.ImageView[@content-desc ='More options']"),
                        "Cannot find button to open article options",
                        5
                );

        mainPageObject
                .waitForElementAndClick(
                        By.xpath("//*[@text = 'Add to reading list']"),
                        "Cannot find option to add article to reading list",
                        5
                );

        mainPageObject
                .waitForElementAndClick(
                        By.id("org.wikipedia:id/onboarding_button"),
                        "Cannot find 'Got it' tip overlay",
                        5
                );

        mainPageObject
                .waitForElementAndClear(
                        By.id("org.wikipedia:id/text_input"),
                        "Cannot find input to set name of articles folder",
                        5
                );

        String name_of_folder = "Learning programmin";

        mainPageObject
                .waitForElementAndSendKeys(
                        By.id("org.wikipedia:id/text_input"),
                        name_of_folder,
                        "Cannot put text into articles folder",
                        5
                );

        mainPageObject
                .waitForElementAndClick(
                        By.xpath(("//*[@text = 'OK']")),
                        "Cannot press OK button",
                        5
                );

        mainPageObject
                .waitForElementAndClick(
                        By.xpath("//android.widget.ImageButton[@content-desc ='Navigate up']"),
                        "Cannot close article, cannot find X link",
                        5
                );

        mainPageObject
                .waitForElementAndClick(
                        By.xpath("//android.widget.FrameLayout[@content-desc ='My lists']"),
                        "Cannot find navigation button to 'My lists'",
                        5
                );

        mainPageObject
                .waitForElementAndClick(
                        By.xpath("//*[@text = '" + name_of_folder + "']"),
                        "Cannot find created folder",
                        5
                );

        mainPageObject
                .swipeElementToLeft(
                        By.xpath("//*[@text = 'Java (programming language)']"),
                        "Cannot find saved article"
                );

        mainPageObject
                .waitForElementNotPresent(
                        By.xpath("//*[@text = 'Java (programming language)']"),
                        "Cannot delete saved article",
                        5
                );
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        mainPageObject
                .waitForElementAndClick(
                        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                        "Cannot find Search Wikipedia input",
                        5
                );

        String search_line = "Linkin Park Discography";
        mainPageObject
                .waitForElementAndSendKeys(
                        By.xpath("//*[contains(@text, 'Search…')]"),
                        search_line,
                        "Cannot find search input",
                        5

                );

        String search_result_locator = "//*[@resource-id ='org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']";
        mainPageObject
                .waitForElementPresent(
                        By.xpath(search_result_locator),
                        "Cannot find anything by the request " + search_line,
                        15
                );

        int amount_of_search_results = mainPageObject
                .getAmountOfElements(
                        By.xpath(search_result_locator)
                );

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {
        mainPageObject
                .waitForElementAndClick(
                        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                        "Cannot find Search Wikipedia input",
                        5
                );

        String search_line = "gjgjgghjgkhl";
        mainPageObject
                .waitForElementAndSendKeys(
                        By.xpath("//*[contains(@text, 'Search…')]"),
                        search_line,
                        "Cannot find search input",
                        5

                );

        String search_result_locator = "//*[@resource-id ='org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']";

        String empty_result_label = "//*[@text = 'No results found']";

        mainPageObject
                .waitForElementPresent(
                        By.xpath(empty_result_label),
                        "Cannot find empty result label by the request " + search_line,
                        15
                );
        mainPageObject
                .assertElementNotPresent(
                        By.xpath(search_result_locator),
                        "We've found some results by request" + search_line
                );
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        mainPageObject
                .waitForElementAndClick(
                        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                        "Cannot find Search Wikipedia input",
                        5
                );

        String search_line = "Java";
        mainPageObject
                .waitForElementAndSendKeys(
                        By.xpath("//*[contains(@text, 'Search…')]"),
                        search_line,
                        "Cannot find search input",
                        5
                );

        mainPageObject
                .waitForElementAndClick(
                        By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                        "Cannot find 'Object-oriented programming language' topic by  " + search_line,
                        15
                );

        String title_before_rotation = mainPageObject
                .waitForElelementAndGetAttribute(
                        By.id("org.wikipedia:id/view_page_title_text"),
                        "text",
                        "Cannot find title of article",
                        15
                );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = mainPageObject
                .waitForElelementAndGetAttribute(
                        By.id("org.wikipedia:id/view_page_title_text"),
                        "text",
                        "Cannot find title of article",
                        15
                );

        Assert.assertEquals(
                "Article title has been changed after rotation",
                title_before_rotation,
                title_after_rotation
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_second_rotation = mainPageObject
                .waitForElelementAndGetAttribute(
                        By.id("org.wikipedia:id/view_page_title_text"),
                        "text",
                        "Cannot find title of article",
                        15
                );

        Assert.assertEquals(
                "Article title has been changed after rotation",
                title_before_rotation,
                title_after_second_rotation
        );

    }

    @Test
    public void testCheckSearchArticleInBackground() {
        mainPageObject
                .waitForElementAndClick(
                        By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                        "Cannot find Search Wikipedia input",
                        5
                );

        mainPageObject
                .waitForElementAndSendKeys(
                        By.xpath("//*[contains(@text, 'Search…')]"),
                        "Java",
                        "Cannot find search input",
                        5

                );

        mainPageObject
                .waitForElementPresent(
                        By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                        "Cannot find Search Wikipedia input",
                        5
                );


        //Send an app in background
        driver.runAppInBackground(2);

        mainPageObject
                .waitForElementPresent(
                        By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                        "Cannot find article after returning from background",
                        5
                );

    }


}
