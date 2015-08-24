import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Dictionary;
//import java.util.HashMap;
import java.util.List;
//import java.net.URL;
import java.util.Properties;























import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.android.AndroidDriver;
//import io.selendroid.SelendroidCapabilities;
//import io.selendroid.SelendroidDriver;
import io.selendroid.SelendroidKeys;
//import io.selendroid.exceptions.NoSuchElementException;






















import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.ScreenOrientation;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasTouchScreen;
import org.openqa.selenium.interactions.TouchScreen;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteTouchScreen;
import org.openqa.selenium.remote.RemoteWebElement;
//import org.openqa.selenium.remote.CapabilityType;
//import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.xml.internal.bind.CycleRecoverable.Context;

import sun.awt.im.InputMethodManager;
//Not used yet
//import org.openqa.selenium.WebElement;

/** LDSTools class - suite of tests to test LDSTools app
 * 
 *@author Zade Maxfield
 *
 */
public class LDSToolsiOS {
	private Properties prop;
	AppiumSwipeableDriver driver;
	TouchActions touch;

	
	/** Setup Appium driver
	 * Note the difference between the classpathRoot on Windows vs Mac
	 * 
	 * @throws Exception
	 */
    @Before
    public void setUp() throws Exception {
        // set up appium
        File classpathRoot = new File(System.getProperty("user.dir"));
        //File appDir = new File(classpathRoot, "..\\..\\..\\..\\Selenium");
        //MAC Path
        File appDir = new File(classpathRoot, "../../../Selenium");
        File app = new File(appDir, "LDS Tools.app");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "iOS");
        capabilities.setCapability(CapabilityType.VERSION, "8.4");
        capabilities.setCapability(CapabilityType.PLATFORM, "Mac");
        capabilities.setCapability("platformVersion", "8.4");
        //capabilities.setCapability("deviceName","iPhone 5s");
        capabilities.setCapability("deviceName","iPhone 6");

        capabilities.setCapability("automationName","appium");
        capabilities.setCapability("newCommandTimeout","600");

        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "org.lds.ldstools.dev");
        capabilities.setCapability("sendKeysStrategy", "setValue");
        
        
        //capabilities.setCapability("appActivity", "org.lds.ldstools.ui.StartupActivity");
        //driver = new AndroidDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
        driver = new AppiumSwipeableDriver(new URL("http://127.0.0.1:4444/wd/hub"),capabilities);
        touch = new TouchActions(driver);

    }	

	
    /*
	@Test
	public void simpleTest() throws Exception {
		Thread.sleep(8000);
		//justForTesting();	

		//under18HeadofHouse();	
		//bishopricCounselorAndWardClerk();	
		//bishopMemberOfSeparateStake();	
		//editCurrentUser();	
		//editOtherUser();	
		//editOtherUserInvalidPhone();	
		//editOtherUserInvalidEmail();	
		editVisibility();	
		//invalidLoginCheck();	
		//loginCheck();	
		
		//LeaderNonBishopric("LDSTools22");
		
		
		//Header Check
		//ChristieWhiting();
		//CliffHigby();
		//KevinPalmer();
		//PatriarchOtherWards();

	}
	*/
	

	public void justForTesting() throws Exception {
		syncLogIn("LDSTools2", "toolstester", "UAT" );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		//Check to see if the user can view the directory
		Assert.assertTrue(checkElementTextViewRoboReturn("AFPEighteen, Member"));
		Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));
		
		
		//Reports
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerReports");

		//Unit Statistics
		clickButtonByXpathTitleName("Unit Statistics");
		Thread.sleep(2000);
		displayAllTextViewElements();
		Assert.assertTrue(checkElementTextViewReturn("TOTAL MEMBERS"));
		Assert.assertTrue(checkElementTextViewReturn("637  "));
		Assert.assertTrue(checkElementTextViewReturn("287  "));
		Assert.assertTrue(checkElementTextViewReturn("14  "));
		Assert.assertFalse(checkElementTextViewReturn("8675309  "));

	}
		
	
	
	
    @Rule
    public Retry retry = new Retry(3);
	
   
	@Test
	public void under18HeadofHouseTest() throws Exception {
		under18HeadofHouse();	
	}
	
	
	@Test
	public void bishopricCounselorAndWardClerkTest() throws Exception {
		bishopricCounselorAndWardClerk();	
	}
	
	
	@Test
	public void bishopMemberOfSeparateStakeTest() throws Exception {
		bishopMemberOfSeparateStake();	
	}
	
	@Test
	public void editCurrentUserTest() throws Exception {
		editCurrentUser();	
	}
	
	@Test
	public void editOtherUserTest() throws Exception {
		editOtherUser();	
	}
	
	@Test
	public void editOtherUserInvalidPhoneTest() throws Exception {
		editOtherUserInvalidPhone();	
	}
	
	@Test
	public void editOtherUserInvalidEmailTest() throws Exception {
		editOtherUserInvalidEmail();	
	}
	
	@Test
	public void editVisibilityTest() throws Exception {
		editVisibility();	
	}
	
	@Test
	public void invalidLoginCheckTest() throws Exception {
		invalidLoginCheck();	
	}
	
	//@Test
	//public void HeaderTest() throws Exception {
	//	ChristieWhiting();
	//	CliffHigby();
	//	KevinPalmer();
	//	PatriarchOtherWards();	
	//}

	@Test
	public void HighPriestsGroupLeader() throws Exception {
		LeaderNonBishopric("LDSTools16");
	}
	
	@Test
	public void HighPriestsGroupFirstAssistant() throws Exception {
		LeaderNonBishopric("LDSTools17");
	}
	
	@Test
	public void HighPriestsGroupSecondAssistant() throws Exception {
		LeaderNonBishopric("LDSTools18");
	}
	
	@Test
	public void HighPriestsGroupSecretary() throws Exception {
		LeaderNonBishopric("LDSTools19");
	}
	
	@Test
	public void HighPriestsGroupAssistantSecretary() throws Exception {
		LeaderNonBishopric("LDSTools20");
	}
	
	@Test
	public void EldersQuorumPresident() throws Exception {
		LeaderNonBishopric("LDSTools21");
	}
	
	@Test
	public void EldersQuorumFirstCounselor() throws Exception {
		LeaderNonBishopric("LDSTools22");
	}
	
	@Test
	public void EldersQuorumSecondCounselor() throws Exception {
		LeaderNonBishopric("LDSTools23");
	}
	
	@Test
	public void EldersQuorumSecretary() throws Exception {
		LeaderNonBishopric("LDSTools24");
	}
	
	@Test
	public void EldersQuorumAssistantSecretary() throws Exception {
		LeaderNonBishopric("LDSTools25");
	}
	
	@Test
	public void ReliefSocietyPresident() throws Exception {
		LeaderNonBishopric("LDSTools26");
	}
	
	@Test
	public void ReliefSocietyFirstCounselor() throws Exception {
		LeaderNonBishopric("LDSTools27");
	}
	
	@Test
	public void ReliefSocietySecondCounselor() throws Exception {
		LeaderNonBishopric("LDSTools28");
	}
	
		@Test
	public void ReliefSocietySecretary() throws Exception {
		LeaderNonBishopric("LDSTools29");
	}
	
	@Test
	public void ReliefSocietyAssistantSecretary() throws Exception {
		LeaderNonBishopric("LDSTools30");
	}
	
	@Test
	public void YoungMenPresident() throws Exception {
		LeaderNonBishopric("LDSTools31");
	}
	
	@Test
	public void YoungMenFirstCounselor() throws Exception {
		LeaderNonBishopric("LDSTools32");
	}
	
	@Test
	public void YoungMenSecondCounselor() throws Exception {
		LeaderNonBishopric("LDSTools33");
	}
	
	@Test
	public void YoungMenSecretary() throws Exception {
		LeaderNonBishopric("LDSTools34");
	}
	
	@Test
	public void YoungWomenPresident() throws Exception {
		LeaderNonBishopric("LDSTools35");
	}
	
	@Test
	public void YoungWomenFirstCounselor() throws Exception {
		LeaderNonBishopric("LDSTools36");
	}
	
	@Test
	public void YoungWomenSecondCounselor() throws Exception {
		LeaderNonBishopric("LDSTools37");
	}
	
	@Test
	public void YoungWomenSecretary() throws Exception {
		LeaderNonBishopric("LDSTools38");
	}
	
	@Test
	public void SundaySchoolPresident() throws Exception {
		LeaderNonBishopric("LDSTools39");
	}
	
	@Test
	public void SundaySchoolFirstCounselor() throws Exception {
		LeaderNonBishopric("LDSTools40");
	}
	
	@Test
	public void SundaySchoolSecondCounselor() throws Exception {
		LeaderNonBishopric("LDSTools41");
	}
	
	@Test
	public void SundaySchoolSecretary() throws Exception {
		LeaderNonBishopric("LDSTools42");
	}
	
	@Test
	public void PrimaryPresident() throws Exception {
		LeaderNonBishopric("LDSTools43");
	}
	
	@Test
	public void PrimaryFirstCounselor() throws Exception {
		LeaderNonBishopric("LDSTools44");
	}
	
	@Test
	public void PrimarySecondCounselor() throws Exception {
		LeaderNonBishopric("LDSTools45");
	}
	
	@Test
	public void PrimarySecretary() throws Exception {
		LeaderNonBishopric("LDSTools46");
	}

	
	//@Test
	//public void loginCheckTest() throws Exception {
	//	loginCheck();	
	//}
	
	
	
	
	
	
//**************************************************************
//**************** Start of tests ******************************
//**************************************************************

	/** under18HeadofHouse()
	 * Test the settings for a Head of House under 18 years of age
	 * @throws Exception
	 */
	public void under18HeadofHouse() throws Exception {
		syncLogIn("LDSTools6", "toolstester", "UAT" );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(5000);
		//Check to see if the user can view the directory
		Assert.assertTrue(checkElementTextViewRoboReturn("Aaron, Jane"));
		Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));
		
		//Search for logged in user
		//clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS6");
		
		//Select the user
		//clickItemByXpathRoboText("Tools, LDS6");
		clickLastTextViewRoboReturn("Tools, LDS6");
		Thread.sleep(1000);
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS6"));
		//clickButtonByXpathTitleName("Show Record Number");
		Assert.assertTrue(checkElementTextCustom("Membership Information", "*"));
		Assert.assertTrue(checkElementTextViewReturn("888-0028-7066"));
		
		pressBackKey();

		
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		//Check Directory user - should be able to view everything
		checkDirectoryUser(false, false, false, false, false);
		
		Thread.sleep(1000);
		
		//Check Drawer Items - If leader there should be a Reports item
		checkDrawerItems(false);
		
		Thread.sleep(1000);
		
		//Check various callings - all users should be able to access this information
		checkCallings();
		
		Thread.sleep(1000);
		
		//Check Missionary drawer items - all user access
		checkMissionary();
	
		//Thread.sleep(1000);
		
		//Check the reports - leadership only
		//checkReports(false);

	}
	
	/** bishopricCounselorAndWardClerk()
	 * This will test a user that is a member of the Bishopric and a Ward Clerk
	 * 
	 * @throws Exception
	 */
	public void bishopricCounselorAndWardClerk() throws Exception {
		//int myCheck;
		//LDSTools3 is Bishopric Counselor and Ward Clerk
		syncLogIn("ngiBPC1", "password1", "UAT" );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		//Check to see if the user can view the directory
		Assert.assertTrue(checkElementTextViewRoboReturn("Aaron, Jane"));
		Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));
		
		//Search for logged in user
		//clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Venasio, Fainu'u");
		Thread.sleep(2000);
		//displayAllTextViewElements();
		//Select the user
		//clickItemByXpathRoboTextContains("Venasio, Fainu");
		//clickItemByXpathRoboText("Venasio, Fainu'u & Moliga");
		clickLastTextViewRoboReturnContains("Venasio, Fainu");
		Thread.sleep(2000);
		clickLastTextViewRoboReturnContains("Fainu");
		//clickLastTextViewRoboReturn("Venasio, Fainu'u");
		Thread.sleep(1000);
		
		//Check the users name, address membership number etc...
		
		//Appium had a real problem with apostrophes
		//Assert.assertTrue(checkElementTextViewReturn("Venasio, Fainu'u"));
		//clickButtonByXpathTitleName("Show Record Number");
		Assert.assertTrue(checkElementTextCustom("Membership Information", "*"));
		//Assert.assertTrue(checkElementTextViewReturn("052-0013-5651"));
		Assert.assertTrue(checkElementTextViewReturn("052-0054-9936"));
		Assert.assertTrue(checkElementTextCustom("Record Number", "*"));
		//Assert.assertTrue(checkElementTextViewReturn("January 1, 1980 (35)"));
		//Assert.assertTrue(checkElementTextCustom("BIRTH DATE", "TextView"));
		//clickButtonByXpathTitleName("Ordinances");
		//Need to test the Ordinances
		pressBackKey();
		Thread.sleep(2000);
		pressBackKey();
		
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		//Check Directory user - should be able to view everything
		checkDirectoryUser(true, true, true, true, true);
		
		Thread.sleep(1000);
		
		//Check Drawer Items - If leader there should be a Reports item
		checkDrawerItems(true);
		
		Thread.sleep(1000);
		
		//Check various callings - all users should be able to access this information
		checkCallings();
		
		Thread.sleep(1000);
		
		//Check Missionary drawer items - all user access
		checkMissionary();
	
		Thread.sleep(1000);
		
		//Check the reports - leadership only
		checkReports(false, false);
		

	}
	
	/** bishopMemberOfSeparateStake()
	 * Bishop that is a member of a separate stake
	 * 
	 * @throws Exception
	 */
	public void bishopMemberOfSeparateStake() throws Exception {
		//int myCheck;
		//LDSTools2 Bishop that is a member of a separate stake
		syncLogIn("LDSTools2", "toolstester", "UAT" );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		//Check to see if the user can view the directory
		Assert.assertTrue(checkElementTextViewRoboReturn("AFPEighteen, Member"));
		Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));
		
		//Search for logged in user
		//clickButtonByXpath("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS2");
		//pressEnterKey();
		
		//Select the user
		//clickItemByXpathRoboText("Tools, LDS2");
		//clickLastTextViewRoboReturn("Tools, LDS2");
		clickLastTextViewRoboReturn("Tools, LDS2");
		Thread.sleep(1000);
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS2"));
		//clickButtonByXpathTitleName("Show Record Number");
		Assert.assertTrue(checkElementTextCustom("Membership Information", "*"));
		Assert.assertTrue(checkElementTextViewReturn("888-0028-7023"));
		Assert.assertTrue(checkElementTextCustom("Record Number", "*"));
		//Assert.assertTrue(checkElementTextViewReturn("January 1, 1980 (35)"));
		//Assert.assertTrue(checkElementTextCustom("BIRTH DATE", "*"));
		
		//Check Ordinances
		clickButtonByXpathTitleName("Ordinances");
		Assert.assertTrue(checkElementTextViewReturn("Baptism"));
		Assert.assertTrue(checkElementTextViewReturn("May 5, 2005"));
		Assert.assertTrue(checkElementTextViewReturn("Confirmation"));
		Assert.assertTrue(checkElementTextViewReturn("May 5, 2005"));
		//pressBackKey();
		clickButtonByXpath("TopIndividual");
		Thread.sleep(1000);
		

		/* Not sure why this isn't working. 
		//Check Other Information
		clickButtonByXpathTitleName("Other Information");
		Thread.sleep(3000);
		displayAllTextViewElements();
		
		Assert.assertTrue(checkElementTextViewReturn("Gender"));
		Assert.assertTrue(checkElementTextViewReturn("Male"));
		Assert.assertTrue(checkElementTextViewReturn("Birth Country"));
		Assert.assertTrue(checkElementTextViewReturn("United States"));
		pressBackKey();
		*/
		
		//pressBackKey();
		//Collapse the search 
		//clickButtonByXpath("Back");
		clickButtonByXpath("TopDirectory");
		Thread.sleep(1000);

		clickButtonByXpath("SearchCollapse");
		
		//Check Directory user - should be able to view everything
		checkDirectoryUser(true, true, true, true, true);
		
		Thread.sleep(1000);
		
		//Check Drawer Items - If leader there should be a Reports item
		checkDrawerItems(true);
		
		Thread.sleep(1000);
		
		//Check various callings - all users should be able to access this information
		checkCallings();
		
		Thread.sleep(1000);
		
		//Check Missionary drawer items - all user access
		checkMissionary();
	
		Thread.sleep(1000);
		
		//Check the reports - leadership only
		checkReports(true, true);
		
		
	}
	
	
	
	public void LeaderNonBishopric(String leaderLogin) throws Exception {
		syncLogIn(leaderLogin, "password1", "UAT" );
		Thread.sleep(2000);
		pinPage("1", "1", "3", "3", true);


		//Check to see if the user can view the directory
		Assert.assertTrue(checkElementTextViewRoboReturn("AFPEighteen, Member"));
		Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));

		checkDirectoryUser(true, true, true, false, false);

		Thread.sleep(1000);
		//Check Drawer Items - If leader there should be a Reports item
		checkDrawerItems(true);

		Thread.sleep(1000);	
		//Check various callings - all users should be able to access this information
		checkCallings();

		Thread.sleep(1000);
		//Check Missionary drawer items - all user access
		checkMissionary();
	
		Thread.sleep(1000);
		//Check the reports - leadership only - true for bishopric rights, false for leaders and remove
		//checkReports for non-leaders
		checkReports(false, false);

	}
	
	
	/** editCurrentUser()
	 * Login as LDSTools100 and edit own information
	 * 
	 * @throws Exception
	 */
	public void editCurrentUser() throws Exception {
		//Edit own information
		syncLogIn("LDSTools44", "password1", "UAT" );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		//Search for logged in user
		//clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS44");
		
		//Select the user
		//clickItemByXpathRoboText("Tools, LDS100");
		clickLastTextViewRoboReturn("Tools, LDS44");
		Thread.sleep(1000);
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS44"));
		
		clickButtonByXpath("MenuEdit");
		Thread.sleep(3000);
		clearTextFieldXpath("EditPersonalPhone");
		clearTextFieldXpath("EditHomePhone");
		clearTextFieldXpath("EditPersonalEmail");
		clearTextFieldXpath("EditHomeEmail");

		clickButtonByXpath("MenuSave");
		
		Thread.sleep(2000);
		
		clickButtonByXpath("MenuEdit");
		Thread.sleep(2000);
		sendTextbyXpath("EditPersonalPhone", "1(801)240-0104");
		sendTextbyXpath("EditHomePhone", "(801) 867-5309");
		sendTextbyXpath("EditPersonalEmail", "personal@nospam.com");
		sendTextbyXpath("EditHomeEmail", "home@nospam.com");
		clickButtonByXpath("MenuSave");
		
		Thread.sleep(3000);
		
		Assert.assertTrue(checkElementTextViewReturn("1(801)240-0104"));
		Assert.assertTrue(checkElementTextViewReturn("(801) 867-5309"));	
		Assert.assertTrue(checkElementTextViewReturn("personal@nospam.com"));
		Assert.assertTrue(checkElementTextViewReturn("home@nospam.com"));
		
		pressBackKey();
		
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerSYNC");
		clickButtonByXpath("AlertOK");
		
		
		
		Thread.sleep(4000);
		waitForTextToDisappear("SyncText", 500 );
		Thread.sleep(2000);
		
		
		//checkForAlertWarning();
		
		//Search for logged in user
		//clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS44");
		
		//Select the user
		//clickItemByXpathRoboText("Tools, LDS44");
		clickLastTextViewRoboReturn("Tools, LDS44");
		Thread.sleep(1000);
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS44"));
		Assert.assertTrue(checkElementTextViewReturn("1(801)240-0104"));
		Assert.assertTrue(checkElementTextViewReturn("(801) 867-5309"));	
		Assert.assertTrue(checkElementTextViewReturn("personal@nospam.com"));
		Assert.assertTrue(checkElementTextViewReturn("home@nospam.com"));
		
		clickButtonByXpath("MenuEdit");
		Thread.sleep(2000);
		clearTextFieldXpath("EditPersonalPhone");
		clearTextFieldXpath("EditHomePhone");
		clearTextFieldXpath("EditPersonalEmail");
		clearTextFieldXpath("EditHomeEmail");

		clickButtonByXpath("MenuSave");
		
		Thread.sleep(3000);
		
		Assert.assertFalse(checkElementTextViewReturn("1(801)240-0104"));
		Assert.assertFalse(checkElementTextViewReturn("(801) 867-5309"));	
		Assert.assertFalse(checkElementTextViewReturn("personal@nospam.com"));
		Assert.assertFalse(checkElementTextViewReturn("home@nospam.com"));
	}
	
	/** editOtherUser()
	 * Edit a user that you are not logged in as. 
	 * 
	 * @throws Exception
	 */
	public void editOtherUser() throws Exception {
		//Edit other user
		syncLogIn("LDSTools2", "toolstester", "UAT" );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		//clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS41");
		
		//Select the user
		//clickItemByXpathRoboText("Tools, LDS41");
		clickLastTextViewRoboReturn("Tools, LDS41");
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS41"));
		
		clickButtonByXpath("MenuEdit");
		Thread.sleep(2000);
		clearTextFieldXpath("EditPersonalPhone");
		clearTextFieldXpath("EditHomePhone");
		clearTextFieldXpath("EditPersonalEmail");
		clearTextFieldXpath("EditHomeEmail");

		clickButtonByXpath("MenuSave");
		Thread.sleep(2000);
		
		clickButtonByXpath("MenuEdit");
		Thread.sleep(3000);
		sendTextbyXpath("EditPersonalPhone", "1(801)240-0104");
		sendTextbyXpath("EditHomePhone", "(801) 867-5309");
		sendTextbyXpath("EditPersonalEmail", "personal@nospam.com");
		sendTextbyXpath("EditHomeEmail", "home@nospam.com");
		clickButtonByXpath("MenuSave");
		
		Thread.sleep(3000);
		
		Assert.assertTrue(checkElementTextViewReturn("1(801)240-0104"));
		Assert.assertTrue(checkElementTextViewReturn("(801) 867-5309"));	
		Assert.assertTrue(checkElementTextViewReturn("personal@nospam.com"));
		Assert.assertTrue(checkElementTextViewReturn("home@nospam.com"));
		
		pressBackKey();
		
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerSYNC");
		clickButtonByXpath("AlertOK");
		
		Thread.sleep(4000);
		waitForTextToDisappear("SyncText", 500 );
		Thread.sleep(2000);
		//checkForAlertWarning();
		//Thread.sleep(2000);
		//Search for logged in user
		//clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS41");
		
		//Select the user
		//clickItemByXpathRoboText("Tools, LDS41");
		clickLastTextViewRoboReturn("Tools, LDS41");
		Thread.sleep(1000);
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS41"));
		Assert.assertTrue(checkElementTextViewReturn("1(801)240-0104"));
		Assert.assertTrue(checkElementTextViewReturn("(801) 867-5309"));	
		Assert.assertTrue(checkElementTextViewReturn("personal@nospam.com"));
		Assert.assertTrue(checkElementTextViewReturn("home@nospam.com"));
		
		clickButtonByXpath("MenuEdit");
		Thread.sleep(2000);
		clearTextFieldXpath("EditPersonalPhone");
		clearTextFieldXpath("EditHomePhone");
		clearTextFieldXpath("EditPersonalEmail");
		clearTextFieldXpath("EditHomeEmail");

		clickButtonByXpath("MenuSave");
		
		Thread.sleep(3000);
		
		Assert.assertFalse(checkElementTextViewReturn("1(801)240-0104"));
		Assert.assertFalse(checkElementTextViewReturn("(801) 867-5309"));	
		Assert.assertFalse(checkElementTextViewReturn("personal@nospam.com"));
		Assert.assertFalse(checkElementTextViewReturn("home@nospam.com"));
				
	}
	
	
	public void editOtherUserInvalidPhone() throws Exception {
		//Edit other user with invalid data - phone
		syncLogIn("LDSTools2", "toolstester", "UAT" );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		//clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS41");
		
		//Select the user
		//clickItemByXpathRoboText("Tools, LDS41");
		clickLastTextViewRoboReturn("Tools, LDS41");
		Thread.sleep(1000);
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS41"));
		
		clickButtonByXpath("MenuEdit");
		Thread.sleep(2000);
		clearTextFieldXpath("EditPersonalPhone");
		clearTextFieldXpath("EditHomePhone");
		clearTextFieldXpath("EditPersonalEmail");
		clearTextFieldXpath("EditHomeEmail");

		clickButtonByXpath("MenuSave");
		
		Thread.sleep(5000);
		
		clickButtonByXpath("MenuEdit");
		Thread.sleep(2000);
		sendTextbyXpath("EditPersonalPhone", "######00000000000*****");
		sendTextbyXpath("EditHomePhone", "878974131648413216421321165484789798461321314644444244624424524245244545644644856465784967465456464144134424342446244323644524452344623446542326342542");
		clickButtonByXpath("MenuSave");
		
		Thread.sleep(3000);
		
		Assert.assertTrue(checkElementTextViewReturn("######00000000000*****"));
		Assert.assertTrue(checkElementTextViewReturn("878974131648413216421321165484789798461321314644444244624424524245244545644644856465784967465456464144134424342446244323644524452344623446542326342542"));	

		
		pressBackKey();
		
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerSYNC");
		clickButtonByXpath("AlertOK");
		
		Thread.sleep(4000);
		waitForTextToDisappear("SyncText", 500 );

		Thread.sleep(2000);
		checkForAlertWarning();
		Thread.sleep(2000);
		
		//Search for logged in user
		//clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS41");
		
		//Select the user
		//clickItemByXpathRoboText("Tools, LDS41");
		clickLastTextViewRoboReturn("Tools, LDS41");
		Thread.sleep(3000);
		
		//Not sure why this isn't showing up. 
		//Check the users name, address membership number etc...
		/*
		Assert.assertTrue(checkElementTextViewReturn("######00000000000*****"));
		Assert.assertTrue(checkElementTextViewReturn("878974131648413216421321165484789798461321314644444244624424524245244545644644856465784967465456464144134424342446244323644524452344623446542326342542"));	

		
		clickButtonByXpath("MenuEdit");
		
		clearTextFieldXpath("EditPersonalPhone");
		clearTextFieldXpath("EditHomePhone");


		clickButtonByXpath("MenuSave");
		
		Thread.sleep(3000);
		*/
		
		Assert.assertFalse(checkElementTextViewReturn("######00000000000*****"));
		Assert.assertFalse(checkElementTextViewReturn("878974131648413216421321165484789798461321314644444244624424524245244545644644856465784967465456464144134424342446244323644524452344623446542326342542"));	

	}
	
	public void editOtherUserInvalidEmail() throws Exception {
		//Edit other user with invalid data - phone
		syncLogIn("LDSTools2", "toolstester", "UAT" );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		//clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS41");
		
		//Select the user
		//clickItemByXpathRoboText("Tools, LDS41");
		clickLastTextViewRoboReturn("Tools, LDS41");
		Thread.sleep(1000);
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS41"));
		
		clickButtonByXpath("MenuEdit");
		Thread.sleep(2000);
		clearTextFieldXpath("EditPersonalPhone");
		clearTextFieldXpath("EditHomePhone");
		clearTextFieldXpath("EditPersonalEmail");
		clearTextFieldXpath("EditHomeEmail");

		clickButtonByXpath("MenuSave");
		
		Thread.sleep(5000);
		
		clickButtonByXpath("MenuEdit");
		Thread.sleep(2000);
		sendTextbyXpath("EditPersonalEmail", "thisisaninvalidemailaddress");
		clickButtonByXpath("MenuSave");
		Assert.assertTrue(checkElementTextViewReturnContains("valid email"));
		clickButtonByXpath("AlertOK");
		clearTextFieldXpath("EditPersonalEmail");
		
		
		sendTextbyXpath("EditHomeEmail", "thisisaninvalidemailaddress");
		clickButtonByXpath("MenuSave");
		Assert.assertTrue(checkElementTextViewReturnContains("valid email"));
		clickButtonByXpath("AlertOK");
		clearTextFieldXpath("EditHomeEmail");
		
		sendTextbyXpath("EditPersonalEmail", "!@#$%^&*()_+-=[]{}|");
		clickButtonByXpath("MenuSave");
		Assert.assertTrue(checkElementTextViewReturnContains("valid email"));
		clickButtonByXpath("AlertOK");
		clearTextFieldXpath("EditPersonalEmail");
		
		sendTextbyXpath("EditHomeEmail", "!@#$%^&*()_+-=[]{}|");
		clickButtonByXpath("MenuSave");
		Assert.assertTrue(checkElementTextViewReturnContains("valid email"));
		clickButtonByXpath("AlertOK");
		clearTextFieldXpath("EditHomeEmail");
		
		
		clickButtonByXpath("MenuSave");
		
		Thread.sleep(3000);
		
		Assert.assertFalse(checkElementTextViewReturn("thisisaninvalidemailaddress"));
		Assert.assertFalse(checkElementTextViewReturn("!@#$%^&*()_+-=[]{}|"));	

		
		pressBackKey();
		
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerSYNC");
		clickButtonByXpath("AlertOK");
		
		Thread.sleep(4000);
		waitForTextToDisappear("SyncText", 500 );
		
		Thread.sleep(2000);
		checkForAlertWarning();
		Thread.sleep(2000);
		//Search for logged in user
		//clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS41");
		
		//Select the user
		//clickItemByXpathRoboText("Tools, LDS41");
		clickLastTextViewRoboReturn("Tools, LDS41");
		Thread.sleep(2000);

		Assert.assertFalse(checkElementTextViewReturn("thisisaninvalidemailaddress"));
		Assert.assertFalse(checkElementTextViewReturn("!@#$%^&*()_+-=[]{}|"));	
	}
	
	
	public void editVisibility() throws Exception {
		//Edit other user with invalid data - phone
		syncLogIn("LDSTools5", "toolstester", "UAT" );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		sendTextbyXpath("SearchArea", "Tools, LDS5");
		
		//Select the user
		clickLastTextViewRoboReturn("Tools, LDS5");
		Thread.sleep(1000);
		
		//Set the visibility to Private-Leadership
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS5"));
		clickButtonByXpath("MenuEdit");
		Thread.sleep(2000);
		clickButtonByXpath("HouseholdVisibilityLimit");
		Thread.sleep(2000);
		clickItemByXpathRoboTextContains("Private");
		Thread.sleep(1000);
		clickButtonByXpath("MenuSave");
		Thread.sleep(3000);
		pressBackKey();
		
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		logoutUser();
		
		
		
		//Login with a user that belongs to the ward. They should NOT be able to see Tools, LDS5 user
		syncLogInNoEnv("LDSTools6", "toolstester", "UAT" );
		Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		sendTextbyXpath("SearchArea", "Tools, lds5");
		Assert.assertFalse(checkElementTextViewRoboReturn("Tools, LDS5"));

		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		logoutUser();
		
		/*
		//Login as a bishopric memeber - should be able to see Tools. LDS5
		syncLogInNoEnv("LDSTools2", "toolstester", "UAT" );
		Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		sendTextbyXpath("SearchArea", "Tools, lds5");
		Assert.assertTrue(checkElementTextViewRoboReturn("Tools, LDS5"));

		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		logoutUser();
		*/
		
		//Login as LDSTools5 change back to Stake Visibility
		syncLogInNoEnv("LDSTools5", "toolstester", "UAT" );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		sendTextbyXpath("SearchArea", "Tools, lds5");
		
		//Select the user
		clickLastTextViewRoboReturn("Tools, LDS5");
		Thread.sleep(1000);
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS5"));
		clickButtonByXpath("MenuEdit");
		Thread.sleep(3000);

		//TODO: Need to check for the alert text for settings set to private
		clickButtonByXpath("SignOutOK");
		
		Thread.sleep(1000);
		clickButtonByXpath("HouseholdVisibilityLimit");
		clickItemByXpathRoboTextContains("Stake");
		Thread.sleep(1000);
		clickButtonByXpath("MenuSave");
		Thread.sleep(3000);
		pressBackKey();
		
		clickButtonByXpath("SearchCollapse");
		
		logoutUser();
		
		//Make sure that LDSTools6 can view LDSTools5
		syncLogInNoEnv("LDSTools6", "toolstester", "UAT" );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		sendTextbyXpath("SearchArea", "Tools, lds5");
		
		Assert.assertTrue(checkElementTextViewRoboReturn("Tools, LDS5"));
	}
	
	/** invalidLoginCheck()
	 * Test invalid logins to LDS Tools
	 * 
	 * @throws Exception
	 */
	public void invalidLoginCheck() throws Exception {
		//Invalid login test
		syncLogIn("LDSTools2", "<login>", "UAT" );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewReturn("Sign-In Failed"));
		clickButtonByXpath("AlertOK");	
	
		//Clear the login and password fields
		clearTextSLOWxpath("LoginUsername");
		clearTextSLOWxpath("LoginPassword");

		syncLogInNoEnv("LDSTools2", "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&", "UAT" );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewReturn("Sign-In Failed"));
		clickButtonByXpath("AlertOK");
		
		//Clear the login and password fields
		clearTextSLOWxpath("LoginUsername");
		clearTextSLOWxpath("LoginPassword");
		
		syncLogInNoEnv("0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789", "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789", "UAT" );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewReturn("Sign-In Failed"));
		clickButtonByXpath("AlertOK");
		
		//Clear the login and password fields
		clearTextSLOWxpath("LoginUsername");
		clearTextSLOWxpath("LoginPassword");
		
		syncLogInNoEnv("LDSTools2", "testtesttest", "UAT" );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewReturn("Sign-In Failed"));
		clickButtonByXpath("AlertOK");
		
		//Clear the login and password fields
		clearTextSLOWxpath("LoginUsername");
		clearTextSLOWxpath("LoginPassword");
		
		syncLogInNoEnv("zmaxfield", "select*fromhousehold", "Production" );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewReturn("Sign-In Failed"));
		clickButtonByXpath("AlertOK");
		
		//Clear the login and password fields
		clearTextSLOWxpath("LoginUsername");
		clearTextSLOWxpath("LoginPassword");
		
		/*
		syncLogInNoEnv("", "", "UAT" );
		Thread.sleep(2000);
		//Assert.assertTrue(checkElementTextViewReturn("Sign in to your LDS Account"));
		
		//Clear the login and password fields
		//clearTextSLOWxpath("LoginUsername");
		//clearTextSLOWxpath("LoginPassword");
		
		syncLogInNoEnv("LDSTools2", "", "UAT" );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewReturn("Sign in to your LDS Account"));
		
		//Clear the login and password fields
		clearTextSLOWxpath("LoginUsername");
		//clearTextSLOWxpath("LoginPassword");
		
		syncLogInNoEnv("", "toolstester", "UAT" );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewReturn("Sign in to your LDS Account"));
		
		//Clear the login and password fields
		//clearTextSLOWxpath("LoginUsername");
		clearTextSLOWxpath("LoginPassword");
		*/
	}
	
	
	public void ChristieWhiting() throws Exception {
		loginProxyData("3446450099",
				"/7u189715/5u511293/",
				"p1175/1151u1000047/:p143/7u189715/5u511293/",
				"Proxy - Test", "ChristieWhiting");
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerHELP");
		Thread.sleep(2000);
		clickButtonByXpath("About");
		Assert.assertTrue(checkElementTextViewReturnContains("ChristieWhiting"));
		
		
		Thread.sleep(2000);
		pressBackKey();
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerSETTINGS");
		
		clickButtonByXpathTitleName("Sign Out");
		clickButtonByXpath("SignOutOK");
	
	}
	
	public void CliffHigby() throws Exception {
		List<String> StakeWard = new ArrayList<String>();
		loginProxyData("295740465",
				"/7u191/5u504505/",
				"p428/467u376892/28u381772/:p1711/59u1004603/22u388300/:p1788/467u376892/28u381772/:p1680/32u1326376/:p789/8u1006967/1u563013/:p1887/14u1004816/467u376892/",
				"P-TEST", "proxyt");
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		checkAllWardDirectories();
		
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerHELP");
		Thread.sleep(2000);
		clickButtonByXpath("About");
		Assert.assertTrue(checkElementTextViewReturnContains("CliffHigby"));

		Thread.sleep(2000);

		pressBackKey();
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerSETTINGS");
		
		clickButtonByXpathTitleName("Sign Out");
		clickButtonByXpath("SignOutOK");
	}
	
	public void KevinPalmer() throws Exception {
		loginProxyData("3182767230",
				"/7u50482/5u511846/",
				"p222/7u50482/5u511846/:p39/3u2019809/1u790206/:p2/5u511846/1u790206/",
				"Proxy - Test", "KevinPalmer");
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerHELP");
		Thread.sleep(2000);
		clickButtonByXpath("About");
		Assert.assertTrue(checkElementTextViewReturnContains("KevinPalmer"));

		Thread.sleep(2000);

		pressBackKey();
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerSETTINGS");
		
		clickButtonByXpathTitleName("Sign Out");
		clickButtonByXpath("SignOutOK");
	}
	
	public void PatriarchOtherWards() throws Exception {
		loginProxyData("3182767230",
				"/7u56030/5u524735/",
				"p13/5u524735/",
				"Proxy - Test", "TestPatriarch");
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		//Check to see if the user can view the directory
		Assert.assertTrue(checkElementTextViewRoboReturn("Aaron, Jane"));
		Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));
		
		//Search for a user that has children
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Sefulu, Isaako");
		
		//Select the user
		//Check that the children are visible
		clickItemByXpathRoboText("Sefulu, Isaako & Telesia");
		clickLastTextViewRoboReturn("Sefulu, Isaako");
		Thread.sleep(1000);
		Assert.assertTrue(checkElementTextViewReturn("Isaako Sefulu"));
		Assert.assertTrue(checkElementTextViewReturn("Telesia Sefulu"));
		Assert.assertTrue(checkElementTextViewReturn("Vaileta Sefulu"));
		Assert.assertTrue(checkElementTextViewReturn("Satalaka Isaako"));
		Assert.assertTrue(checkElementTextViewReturn("Logoipule Sefulu"));
		Assert.assertTrue(checkElementTextViewReturn("Asiasiga Isaako"));
		Assert.assertTrue(checkElementTextViewReturn("Eseta Isaako"));
		clickButtonByXpath("Back");
		clickButtonByXpath("SearchCollapse");
		//pressBackKey();
		
		//Change to another Ward
		//Check to see that the children are visible
		clickButtonByXpath("SpinnerNav");
		Thread.sleep(2000);
		clickButtonByXpathTitleName("Fagamalo 2nd Ward");
		
		
		//Search for a user that has children
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Alofa, Pasi");
		
		//Select the user
		//Check that the children are visible
		clickItemByXpathRoboText("Alofa, Pasi & Rowena");
		clickLastTextViewRoboReturn("Alofa, Pasi");
		Thread.sleep(1000);
		Assert.assertTrue(checkElementTextViewReturn("Pasi Alofa"));
		Assert.assertTrue(checkElementTextViewReturn("Rowena Alofa"));
		Assert.assertTrue(checkElementTextViewReturn("Rozarnah Alofa"));
		Assert.assertTrue(checkElementTextViewReturn("Leativaosalafai Shaleen Alofa"));
		//Assert.assertTrue(checkElementTextViewReturn("Pioneer Aumoto"));
		clickButtonByXpath("Back");
		clickButtonByXpath("SearchCollapse");
		//pressBackKey();
		
		
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerHELP");
		Thread.sleep(2000);
		clickButtonByXpath("About");
		Assert.assertTrue(checkElementTextViewReturnContains("TestPatriarch"));

		Thread.sleep(2000);

		pressBackKey();
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerSETTINGS");
		
		clickButtonByXpathTitleName("Sign Out");
		clickButtonByXpath("SignOutOK");
	}
	
	/**loginCheck()
	 * Go through All LDSTools users to make sure they can login
	 * 
	 * @throws Exception
	 */
	public void loginCheck() throws Exception {
		String password1 = "toolstester";
		String password2 = "password1";
		boolean pinCheck = true;
		
		
		for (int myCounter = 2 ; myCounter <= 47; myCounter++ ){
			System.out.println("USER: LDSTools" + myCounter);
			if (myCounter <= 15){
				syncLogIn("LDSTools" + myCounter, password1, "UAT" );
			} else {
				syncLogIn("LDSTools" + myCounter, password2, "UAT" );
			}
			
			Thread.sleep(2000);
			//Assert.assertFalse(checkElementTextViewReturn("LDS Tools Services are unavailable. Please try again later."));
			
			//displayAllTextViewElements();
			pinCheck = checkElementTextViewReturnContains("PIN");
			
			if (pinCheck == false){
				System.out.println("Login failed!");
			} else {
				//true will setup pin for a non-leader
				pinPage("1", "1", "3", "3", true);
				
				
				logoutUser();
				
				//clickButtonByXpath("Drawer");
				//clickButtonByXpath("DrawerSETTINGS");
				
				//clickButtonByXpathTitleName("Sign Out");
				//clickButtonByXpath("SignOutOK");
			}
		}
	}
	
	//**************************************************************
	//**************** Start of Methods ****************************
	//**************************************************************
	
	/** checkTextByXpath(String textElement, String textToCheck )
	 * Find the element by xpath using the uiMap.properties
	 * 
	 * @param textElement - Must map to the uiMap.properties
	 * @param textToCheck - String of text to check
	 */
	private void checkTextByXpath(String textElement, String textToCheck ) {
		Assert.assertEquals(driver.findElement(By.xpath(this.prop.getProperty(textElement))).getText(),(textToCheck));	
	}
	
	
	
	/** checkTextByXpathReturn(String textElement, String textToCheck )
	 * Find the element by xpath using uiMap then return if the text if found
	 * 
	 * @param textElement - Xpath element in uiMap.properties
	 * @param textToCheck - String of text to check
	 * @return return found = 1 or not found = 0
	 */
	private int checkTextByXpathReturn(String textElement, String textToCheck ) {
		String myText;
		int myReturn = 0;
		myText = driver.findElement(By.xpath(this.prop.getProperty(textElement))).getText();
		if (myText.equals(textToCheck) ) {
			myReturn = 1;
		}
		return myReturn;
	}
	
	
	/** checkTextByID(String textElement, String textToCheck )
	 * Find the element by ID using uiMap
	 * 
	 * @param textElement - Must map to the uiMap
	 * @param textToCheck - String of text to check
	 */
	private void checkTextByID(String textElement, String textToCheck ) {
		Assert.assertEquals(driver.findElement(By.id(this.prop.getProperty(textElement))).getText(),(textToCheck));
	}


	
	/** checkElementTextViewReturn(String textElement)
	 * Check to see if the element is found using the xpath of //TextView[@value=" SOME TEXT "]
	 * This is common for text elements in the app
	 * 
	 * @param textElement - text of an element
	 * @return - false if the element is not found true if it is found. 
	 */
	private Boolean checkElementTextViewReturn(String textElement) {
		Boolean myReturnStatus;
		//List<WebElement> options= driver.findElements(By.xpath("//TextView[@value='" + textElement + "']"));
		List<WebElement> options= driver.findElements(By.xpath("//*[@value='" + textElement + "']"));
		if (options.isEmpty()) {
			myReturnStatus = false;	
		} else {
			myReturnStatus = true;
		}
		
		return myReturnStatus;
	}
	
	private Boolean checkElementExistsByXpath(String textElement) {
		Boolean myReturnStatus;
		//List<WebElement> options= driver.findElements(By.xpath("//TextView[@value='" + textElement + "']"));
		List<WebElement> options= driver.findElements(By.xpath(this.prop.getProperty(textElement)));
		if (options.isEmpty()) {
			myReturnStatus = false;	
		} else {
			myReturnStatus = true;
		}
		
		return myReturnStatus;
	}
	
	
	
	/** checkElementTextViewReturnContains(String textElement)
	 * Check the //TextView element for text in the "value"
	 * 
	 * @param textElement - Text to search for
	 * @return - true if element is found, false if not
	 */
	private Boolean checkElementTextViewReturnContains(String textElement) {
		Boolean myReturnStatus;
		//List<WebElement> options= driver.findElements(By.xpath("//TextView[contains(@value,'" + textElement + "')]"));
		List<WebElement> options= driver.findElements(By.xpath("//*[contains(@value,'" + textElement + "')]"));
		if (options.isEmpty()) {
			myReturnStatus = false;	
		} else {
			myReturnStatus = true;
		}
		
		return myReturnStatus;
	}

	
	/** checkElementTextViewRoboReturn(String textElement)
	 * Check to see if the element is found using the xpath of //RobotoTextView[@value="SOME NAME HERE"]
	 * This is common for text elements in the app
	 * 
	 * @param textElement - text of an element
	 * @return - false if the element is not found true if it is found. 
	 */
	private Boolean checkElementTextViewRoboReturn(String textElement) {
		Boolean myReturnStatus;
		//List<WebElement> options= driver.findElements(By.xpath("//RobotoTextView[@value='" + textElement + "']"));
		List<WebElement> options= driver.findElements(By.xpath("//*[@value='" + textElement + "']"));
		if (options.isEmpty()) {
			myReturnStatus = false;
		} else {
			myReturnStatus = true;
		}
		
		return myReturnStatus;
	}
	
	
	/** checkElementTextCustom(String textElement, String customText)
	 * Check to see if the element is found using the xpath of // CUSTOM TEXT [@value="SOME NAME HERE"]
	 * You could use this method instead of checkElementTextViewRoboReturn or checkElementTextViewReturn
	 * 
	 * @param textElement - text of an element
	 * @param customText - Part of the xpath of the element - valid settings so far: RobotoTextView, TextView, CapitalizedTextView
	 * @return - false if the element is not found true if it is found. 
	 */
	private Boolean checkElementTextCustom(String textElement, String customText) {
		Boolean myReturnStatus;
		List<WebElement> options= driver.findElements(By.xpath("//" + customText + "[@value='" + textElement + "']"));
		if (options.isEmpty()) {
			myReturnStatus = false;
		} else {
			myReturnStatus = true;
		}
		
		return myReturnStatus;
	}
	
	/** displayAllTextViewElements()
	 * For debugging - will display the text of the element. 
	 * 
	 */
	private void displayAllTextViewElements() {
		List<WebElement> options= driver.findElements(By.xpath("//*"));
		for (int i = 0 ; i < options.size(); i++ ) {
			System.out.println(options.get(i).getText());
		}
	}

	
	/** clickLastTextViewRoboReturn(String textElement)
	 * This will match the last element found 
	 * 
	 * @param textElement - text of an element
	 */
	private void clickLastTextViewRoboReturn(String textElement) {
		int myCounter;
		//displayAllTextViewElements(textElement);
		//List<WebElement> options= driver.findElements(By.xpath("//RobotoTextView[@id='text1'][@value='" + textElement + "']"));
		List<WebElement> options= driver.findElements(By.xpath("//UIAStaticText[@value='" + textElement + "']"));
		//List<WebElement> options= driver.findElements(By.xpath("//*[@value='" + textElement + "']"));
		myCounter = options.size() - 1;
		options.get(myCounter).click();
	
	}
	
	private void clickLastTextViewRoboReturnContains(String textElement) {
		int myCounter;
		//List<WebElement> options= driver.findElements(By.xpath("//RobotoTextView[contains(@value, '" + textElement + "')]"));
		List<WebElement> options= driver.findElements(By.xpath("//*[contains(@value, '" + textElement + "')]"));
		//driver.findElement(By.xpath("//RobotoTextView[contains(@value='" + textElement + "')]")).click();
		myCounter = options.size() - 1;
		options.get(myCounter).click();
	
	}
	
	
	

	/** clickButtonByID(String textElement )
	 * Click an element that by ID
	 * 
	 * @param textElement - ID of element must be found if uiMap
	 */
	private void clickButtonByID(String textElement ) {
		driver.findElement(By.id(this.prop.getProperty(textElement))).click();
	}
	

	/** clickButtonByXpath(String textElement)
	 * Click an element that by Xpath
	 * 
	 * @param textElement - Xpath of element must be found if uiMap
	 */
	private void clickButtonByXpath(String textElement) {
		driver.findElement(By.xpath(this.prop.getProperty(textElement))).click();
	}
	
	
	
	
	/** clickButtonByXpathTitleName(String textElement )
	 * Click the button that has the xpath of //TextView[@value" TEXT OF ELEMENT "]
	 * 
	 * @param textElement - text of element
	 */
	private void clickButtonByXpathTitleName(String textElement ) {
		//WebElement element;
		
		driver.findElement(By.xpath("//*[@name='" + textElement + "']")).click();
		
		//I don't really like this sleep but it seems to be needed 
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/** clickItemByXpathRoboText(String textElement )
	 * Click the button that has the xpath of //RobotoTextView[@value" TEXT OF ELEMENT "]
	 * 
	 * @param textElement - text of element
	 */
	private void clickItemByXpathRoboText(String textElement ) {
		//WebElement element;
		//System.out.println("TEXT ELEMENT: " + textElement);
		//driver.findElement(By.xpath("//RobotoTextView[@value='" + textElement + "']")).click();
		driver.findElement(By.xpath("//*[@value='" + textElement + "']")).click();
		
		//I don't really like this sleep but it seems to be needed 
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void clickItemByXpathRoboTextContains(String textElement ) {
		//WebElement element;
		//System.out.println("TEXT ELEMENT: " + textElement);
		//driver.findElement(By.xpath("//RobotoTextView[contains(@value, '" + textElement + "')]")).click();
		driver.findElement(By.xpath("//*[contains(@name, '" + textElement + "')]")).click();

		//I don't really like this sleep but it seems to be needed 
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	

	
	/**clickButtonByXpathPopoutMenu(String textElement )
	 * Click elements by xpath on the popout menu
	 * 
	 * @param textElement - text of the element
	 */
	private void clickButtonByXpathPopoutMenu(String textElement ) {
		//WebElement element;
		
		//System.out.println("Element: " + textElement );
		//driver.findElement(By.xpath("//TintCheckedTextView[@value='" + textElement + "']")).click();
		//driver.findElement(By.xpath("//CheckedTextView[@value='" + textElement + "']")).click();
		driver.findElement(By.xpath("//*[@value='" + textElement + "']")).click();
		
		//I don't really like this sleep but it seems to be needed 
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	/** sendTextbyID(String textElement, String textToSend )
	 * 
	 * @param textElement - Element by ID must be in the uiMap
	 * @param textToSend - Text to send to the element
	 */
	private void sendTextbyID(String textElement, String textToSend ) {
		driver.findElement(By.id(this.prop.getProperty(textElement))).sendKeys(textToSend);
	}
	
	/** sendTextbyXpath(String textElement, String textToSend )
	 * 
	 * @param textElement - Element by Xpath must be in the uiMap
	 * @param textToSend - Text to send to the element
	 */
	private void sendTextbyXpath(String textElement, String textToSend ) {
		driver.findElement(By.xpath(this.prop.getProperty(textElement))).sendKeys(textToSend);
	}
	
	
	private void sendTextbyXpath2(String textElement, String textToSend) {
		clickButtonByXpath(textElement);
		driver.executeScript("target.frontMostApp().keyboard().typeString('" + textToSend + "')");
	}


	
	private void clearTextFieldXpath(String textElement) {
		WebElement myElement = driver.findElement(By.xpath(this.prop.getProperty(textElement)));
		myElement.clear();
		
		//int stringLength = myElement.getText().length();
		//System.out.println("Text" + myElement.getText());
		//System.out.println("Length" + stringLength);

	    //for (int i = 0; i < stringLength; i++) {
	    //    driver.sendKeyEvent(22); // "KEYCODE_DPAD_RIGHT"
	    //}
	
	    //for (int i = 0; i < 50; i++) {
	        //driver.sendKeyEvent(67); // "KEYCODE_DEL"
	    //    clickButtonByXpath("PinKeyDel");
	    //}	
		
	}
	
	
	private void clearTextSLOWxpath(String textElement) throws Exception {
		WebElement myElement = driver.findElement(By.xpath(this.prop.getProperty(textElement)));

		/*
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, Double> tapObject = new HashMap<String, Double>();
		tapObject.put("x", (double) myElement.getLocation().getX());
		tapObject.put("y", (double) myElement.getLocation().getY());
		tapObject.put("duration", 2.0);		
		js.executeScript("mobile: tap", tapObject);
    	*/
		myElement.click();
		//Thread.sleep(1000);
		myElement.click();
		Thread.sleep(1000);
    	driver.findElement(By.xpath("//*[@name=\"Select All\"]")).click();
    	//driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[4]/UIAEditingMenu[1]/UIAElement[4]")).click();
    	Thread.sleep(1000);
        clickButtonByXpath("PinKeyDel");
		
	}
	

	/** waitForTextToDisappear(String textElement, int myTimeOut)
	 * This will wait for an element to disappear 
	 * Used to wait for the sync to finish
	 * 
	 * @param textElement - element to check by xpath in uiMap
	 * @param myTimeOut - number of seconds to wait before giving up
	 */
	private void waitForTextToDisappear(String textElement, int myTimeOut){
		WebDriverWait wait = new WebDriverWait(driver, myTimeOut);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(this.prop.getProperty(textElement))));
	}
	
	/** flickRightToLeft()
	 * Flick or swipe from right to left
	 * 
	 */
	private void flickRightToLeft(){
		WebElement pages = driver.findElement(By.id("pager"));
		//Smaller Devices
		//TouchActions flick = new TouchActions(driver).flick(pages, -1500, 0, 0);
		//Larger devices - seems to work for larger and smaller devices
		TouchActions flick = new TouchActions(driver).flick(pages, -2500, 0, 0);
		flick.perform();
	}
	
	/** flickLeftToRight()
	 * Flick or swipe from left to right
	 */
	private void flickLeftToRight(){
		WebElement pages = driver.findElement(By.id("pager"));
		TouchActions flick = new TouchActions(driver).flick(pages, 5500, 0, 0);
		flick.perform();
	}
	
	/** flickUpOrDown(int yNumber)
	 * Flick up is a -500 down is 500
	 * 
	 * @param yNumber Number of pixel to swipe up or down
	 */
	private void flickUpOrDown(int yNumber){
		WebElement pages = driver.findElement(By.id("pager"));
		TouchActions flick = new TouchActions(driver).flick(pages, 0, yNumber, 0);
		flick.perform();
	}
	
	/** moveSlider(String textElement, int xCords, int yCords, int moveSpeed )
	 * Move a slider 
	 * 
	 * @param textElement - xpath element in uiMap
	 * @param xCords - x cords can be positive or negative
	 * @param yCords - y cords can be positive or negative
	 * @param moveSpeed - speed of movement
	 */
	private void moveSlider(String textElement, int xCords, int yCords, int moveSpeed ) {
        WebElement objSlider = driver.findElement(By.xpath(this.prop.getProperty(textElement)));
        TouchActions drag = new TouchActions(driver).flick(objSlider, xCords, yCords, moveSpeed);
        drag.perform();
	}

	
	
	/** scrollDown(String textElement, int distanceMove)
	 * This will select an element by //TextView then scroll down
	 * 
	 * @param textElement - Text of an element
	 * @param distanceMove - Distance to move 
	 */
	/*
	private void scrollDown(String textElement, int distanceMove){
		WebElement myElement = driver.findElement(By.xpath("//TextView[@value='" + textElement + "']"));
		TouchActions actions = new TouchActions(driver);
		Point p=myElement.getLocation();
		actions.down(p.x, p.y);
		actions.move(p.x, p.y - distanceMove);
		actions.up(p.x, p.y);
		actions.perform();
	}
	*/
	
	/** scrollDown(String textElement, int distanceMove)
	 * This will select an element by //TextView then scroll down
	 * 
	 * @param textElement - Text of an element
	 * @param distanceMove - Distance to move 
	 */
	private void scrollDown(String textElement, int distanceMove){
		int myCheck;
		int myCounter = 0;
		textElement = "//*[@value='" + textElement + "']";
		//WebDriverWait wait = new WebDriverWait(driver, 20);

		//Scroll down if element is not found.
		myCheck = checkElementXpathReturn(textElement);
		//System.out.println("Before While loop - Check: " + myCheck);
		while ((myCheck == 0) && (myCounter < 8 )) {
			scrollDownDistance(distanceMove);
			myCheck = checkElementXpathReturn(textElement);
			myCounter++;
			//System.out.println(textElement);
			//System.out.println("Counter " + myCounter);
			//System.out.println("My Check: " + myCheck);
		}
		
		driver.findElement(By.xpath(textElement)).click();
		
		//I don't really like this sleep but it seems to be needed 
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}


	
	

	/** checkElementXpathReturn(String textElement)
	 * 
	 * @param textElement
	 * @return - found = 1 or not found = 0
	 */
	private int checkElementXpathReturn(String textElement) {
		int myReturnStatus = 0;
		List<WebElement> options= driver.findElements(By.xpath(textElement));
		if (options.isEmpty()) {
			myReturnStatus = 0;
			return myReturnStatus;
		}
		myReturnStatus = 1;
		return myReturnStatus;
	}
	
	
	/** scrollDownDistance(int scrollDistance )
	 * Select the first TextView then scroll down
	 * 
	 * @param scrollDistance - Distance to scroll
	 */
	private void scrollDownDistance(int scrollDistance ){
		TouchActions actions = new TouchActions(driver);
		/*
		Dimension dimensions = driver.manage().window().getSize();
		int screenWidth = dimensions.getWidth();
		int screenHeight = dimensions.getHeight();
		
		System.out.println("Trying to move!");
		System.out.println("Width: " + screenWidth);
		System.out.println("Height: " + screenHeight);
		
		screenWidth = screenWidth - 10;
		screenHeight = screenHeight - 10;
		
		actions.down(screenWidth, screenHeight);
		actions.pause(3000);
		actions.move(screenWidth, screenHeight - scrollDistance);
		actions.pause(2000);
		actions.up(screenWidth, screenHeight - scrollDistance);
		*/
		actions.flick(driver.findElement(By.id("title")), 0, scrollDistance, 100);
		
		//actions.flick(0, -1000);
		//actions.scroll(0, scrollDistance);
		

		
		actions.perform();
		
	}
	
	
	
	
	/** longPressByXpath(String textElement)
	 * This will perform a long press on element
	 * 
	 * @param textElement - xpath element must be in uiMap
	 */
	private void longPressByXpath(String textElement) {
		WebElement myElement = driver.findElement(By.xpath(this.prop.getProperty(textElement)));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, Double> tapObject = new HashMap<String, Double>();
		tapObject.put("x", (double) myElement.getLocation().getX() + 10);
		tapObject.put("y", (double) myElement.getLocation().getY() + 10);
		tapObject.put("duration", 3.0);		
		js.executeScript("mobile: tap", tapObject);

	}
	
	/** longPressByTextView(String textElement)
	 * This will perform a long press on //TextView text
	 * 
	 * @param textElement - text of an element
	 */
	private void longPressByTextView(String textElement) {
		WebElement myElement = driver.findElement(By.xpath("//*[@value='" + textElement + "']"));
		//This was doing a longpress on the wrong element
		//TouchActions longPress = new TouchActions(driver).longPress(myElement);
		//longPress.perform();
		TouchActions actions=new TouchActions(driver);
		Point p=myElement.getLocation();
		//System.out.println("X: " + p.x + "Y: " + p.y);
		actions.down(p.x, p.y);
		actions.pause(2000);
		actions.up(p.x, p.y);
		actions.perform();
	}

	/** pressMenuKey()
	 * Press the menu key
	 * 
	 */
	private void pressMenuKey() {
		new Actions(driver).sendKeys(SelendroidKeys.MENU).perform();
	}
	
	/** pressBackKey()
	 * Press the back key
	 * 
	 */
	private void pressBackKey() {
		//new Actions(driver).sendKeys(SelendroidKeys.BACK).perform();
		clickButtonByXpath("TopBack");
	}
	
	private void pressEnterKey() {
		new Actions(driver).sendKeys(SelendroidKeys.ENTER).perform();
	}
	
	private void pressSearchKey() {
		new Actions(driver).sendKeys(SelendroidKeys.SEARCH).perform();
	}
	
	/** pressHomeKey()
	 * Press the home key
	 * 
	 */
	private void pressHomeKey() {
		new Actions(driver).sendKeys(SelendroidKeys.ANDROID_HOME).perform();
	}
	
	
	//************************************************************
	//*************** Start of command sequences *****************
	//************************************************************
	
	
	
	/** syncLogIn(String loginName, String loginPassword, String chooseNetwork )
	 * Log into LDS tools
	 * 
	 * @param loginName - login name
	 * @param loginPassword - login password
	 * @param chooseNetwork - Network to use "Production", "UAT", "Proxy - UAT", "Proxy - Test"
	 * @throws Exception
	 */
	private void syncLogIn(String loginName, String loginPassword, String chooseNetwork )  throws Exception {
		//If the login is using any of the test networks we need to chagne it. 
		//valid enteries "Production", "UAT", "Proxy - UAT", "Proxy - Test"
		if (!chooseNetwork.equals("Production")) {
			Thread.sleep(1000);
			clickButtonByXpath("TopHelp");
			clickButtonByXpath("About");
			longPressByXpath("Logo");

			clickButtonByXpath("OK");

			clickButtonByXpath("TopHelp");

			clickButtonByXpath("DeveloperSettings");
			clickButtonByXpath("Environment");
			
			
			clickButtonByXpath(chooseNetwork);
			
			clickButtonByXpath("TopDeveloper");
			Thread.sleep(2000);
			clickButtonByXpath("TopHelp");
			Thread.sleep(4000);
			clickButtonByXpath("TopSignIn");
		}
		//sendTextbyXpath("LoginUsername", "LDSTools14");
		//sendTextbyXpath("LoginPassword", "toolstester");
		sendTextbyXpath2("LoginUsername", loginName);
		sendTextbyXpath2("LoginPassword", loginPassword);
		
		//Thread.sleep(1000);
		clickButtonByXpath("DoneButton");
		//Thread.sleep(1000);
		//clickButtonByXpath("SignInButton");
		Thread.sleep(4000);
		
		
		unitsToSync();
		
		waitForTextToDisappear("DownloadingSync", 500 );
		Thread.sleep(8000);
	}
	
	
	private void syncLogInNoEnv(String loginName, String loginPassword, String chooseNetwork )  throws Exception {
		sendTextbyXpath2("LoginUsername", loginName);
		sendTextbyXpath2("LoginPassword", loginPassword);
		
		//Thread.sleep(1000);
		clickButtonByXpath("DoneButton");
		//Thread.sleep(1000);
		//clickButtonByXpath("SignInButton");
		Thread.sleep(4000);
		unitsToSync();
		Thread.sleep(2000);
		waitForTextToDisappear("DownloadingSync", 500 );
		Thread.sleep(8000);
	}
	
	
	
	private void loginProxyData(String IndividualId, String units, String positions, String chooseNetwork, String userName )  throws Exception {
		//If the login is using any of the test networks we need to chagne it. 
		//valid enteries "Production", "UAT", "Proxy - UAT", "Proxy - Test"
		if (!chooseNetwork.equals("Production")) {
			Thread.sleep(1000);
			clickButtonByXpath("TopHelp");
			clickButtonByXpath("About");
			longPressByXpath("Logo");

			clickButtonByXpath("OK");

			clickButtonByXpath("TopHelp");

			clickButtonByXpath("DeveloperSettings");
			clickButtonByXpath("Environment");
			
			
			clickButtonByXpath(chooseNetwork);
			
			clickButtonByXpath("TopDeveloper");
			Thread.sleep(2000);
			
			
			//Set the ID
			clickButtonByXpath("Id");
			sendTextbyXpath("HeaderAlertTextId", IndividualId );
			clickButtonByXpath("HeaderOK");
			
			//Set the Positions
			clickButtonByXpath("Units");
			sendTextbyXpath("HeaderAlertTextUnits", units );
			clickButtonByXpath("HeaderOK");
			
			//Set the Positions
			clickButtonByXpath("Positions");
			sendTextbyXpath("HeaderAlertTextPositions", positions );
			clickButtonByXpath("HeaderOK");
			
			clickButtonByXpath("TopHelp");
			Thread.sleep(4000);
			clickButtonByXpath("TopSignIn");
		}
		//sendTextbyXpath("LoginUsername", "LDSTools14");
		//sendTextbyXpath("LoginPassword", "toolstester");
		sendTextbyXpath2("LoginUsername", userName );
		sendTextbyXpath2("LoginPassword", "toolstester");
		Thread.sleep(1000);
		clickButtonByXpath("DoneButton");
		Thread.sleep(4000);
		
		unitsToSync();
		Thread.sleep(2000);
		waitForTextToDisappear("DownloadingSync", 900 );
		Thread.sleep(2000);
		
		//Calendar doesn't work with proxy data so we will just clear the alert. 
		//clickButtonByXpath("AlertOK");
		
	}

	
	/** pinPage(String digit1, String digit2, String digit3, String digit4, Boolean nonLeaderPin )
	 * Enter a pin 
	 * 
	 * @param digit1 - pin 0-9
	 * @param digit2
	 * @param digit3
	 * @param digit4 
	 * @param nonLeaderPin - false - only for non-leader will skip the pin entry , true - enter pin
	 * @throws Exception
	 */
	private void pinPage(String digit1, String digit2, String digit3, String digit4, Boolean nonLeaderPin ) throws Exception {
		int myCheck;
		boolean elementCheck;
		
		//Check to see if we are getting a warning
		myCheck = checkTextByXpathReturn("AlertMessage", "Warning");
		if (myCheck == 1) {
			clickButtonByXpath("OK");
			
		}
		
		//If this is a non-leader account the PIN message will be different
		myCheck = checkTextByXpathReturn("AlertMessage", "Passcode Required");
		if ((myCheck == 1) || (nonLeaderPin)){
			if (myCheck == 1) {
				clickButtonByXpath("OK");
			} else {
				clickButtonByXpath("Yes");
				Thread.sleep(2000);
				clickButtonByXpath("OK");
			}
			
			Thread.sleep(2000);
			
			//Check for a warning
			elementCheck = checkElementExistsByXpath("AlertMessage");
			//myCheck = checkTextByXpathReturn("AlertMessage", "Warning");
			if (elementCheck == true) {
				clickButtonByXpath("OK");
				
				//driver.rotate(ScreenOrientation.LANDSCAPE);
				//Thread.sleep(2000);
				//driver.rotate(ScreenOrientation.PORTRAIT);
				//Thread.sleep(2000);
				//driver.getKeyboard();
				
				//driver.closeApp();
				//driver.launchApp();
			}
			
			
			checkTextByXpath("PinTitle", "Create New Passcode");
			clickButtonByXpath("PinKey" + digit1);
			clickButtonByXpath("PinKey" + digit2);
			clickButtonByXpath("PinKey" + digit3);
			clickButtonByXpath("PinKey" + digit4);
			
			checkTextByXpath("PinTitle", "Enter New Passcode Again");
			clickButtonByXpath("PinKey" + digit1);
			clickButtonByXpath("PinKey" + digit2);
			clickButtonByXpath("PinKey" + digit3);
			clickButtonByXpath("PinKey" + digit4);
		} else {
			clickButtonByXpath("AlertNotNow");
		}

	}
	
	/**  checkDirectoryUser(boolean memberShipInfo, boolean fullName, boolean birthDate, boolean recordNumber, boolean ordinances )
	 * Check the directory user "Aaron, Jane"
	 * 
	 * All params are boolean - true item is displayed 
	 * @param memberShipInfo
	 * @param fullName
	 * @param birthDate
	 * @param recordNumber
	 * @param ordinances
	 * @throws Exception
	 */
	private void checkDirectoryUser(boolean memberShipInfo, boolean fullName, boolean birthDate, boolean recordNumber, boolean ordinances ) throws Exception {
		//Search for logged in user
		//clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Aaron, Jane");
		
		
		//Directory items that should not be visible
		//clickItemByXpathRoboText("Aaron, Jane");
		//clickLastTextViewRoboReturn("Aaron, Jane");
		clickLastTextViewRoboReturn("Aaron, Jane");
		
		//All Members should be able to view the following information
		Assert.assertTrue(checkElementTextViewReturn("Jane Aaron"));
		Assert.assertTrue(checkElementTextViewReturn("Fagamalo 1st Ward"));

		Assert.assertTrue(checkElementTextCustom("Contact Information", "*"));
		Assert.assertTrue(checkElementTextViewReturn("555-555-5555"));
		Assert.assertTrue(checkElementTextCustom("Personal Cell Phone", "*"));
		Assert.assertTrue(checkElementTextViewReturn("555-555-1234"));
		Assert.assertTrue(checkElementTextCustom("Household Phone", "*"));
		Assert.assertTrue(checkElementTextViewReturn("no-reply@ldschurch.org"));
		Assert.assertTrue(checkElementTextCustom("Personal Email", "*"));
		//Assert.assertTrue(checkElementTextViewReturn("2778 E Saddle Rock Rd Eagle Mountain, Utah 84005"));
		Assert.assertTrue(checkElementTextCustom("Household members", "*"));
		Assert.assertTrue(checkElementTextViewReturn("Jane Aaron"));
		
		
		
		
		
		//Leadership Should be able to see this information
		//Membership Information
		if (memberShipInfo == true ) {
			Assert.assertTrue(checkElementTextCustom("Membership Information", "*"));
		} else {
			Assert.assertFalse(checkElementTextCustom("Membership Information", "*"));
		}
		
		//Full Name
		if (fullName == true){
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member2"));
			Assert.assertTrue(checkElementTextCustom("Full Name", "*"));
		} else {
			Assert.assertFalse(checkElementTextViewReturn("AFPMisc, Member2"));
			Assert.assertFalse(checkElementTextCustom("Full Name", "*"));
		}

		/*
		//Birth Date
		if (birthDate == true){
			Assert.assertTrue(checkElementTextViewReturn("November 11, 1960 (54)"));
			Assert.assertTrue(checkElementTextCustom("Birth Date", "*"));
		} else {
			Assert.assertFalse(checkElementTextViewReturn("November 11, 1960 (54)"));
			Assert.assertFalse(checkElementTextCustom("Birth Date", "*"));
		}
		*/
		
		//Record Number
		if (recordNumber == true ){
			Assert.assertTrue(checkElementTextViewReturn("888-0028-4326"));
			Assert.assertTrue(checkElementTextCustom("Record Number", "*"));
		} else {
			Assert.assertFalse(checkElementTextViewReturn("888-0028-4326"));
			Assert.assertFalse(checkElementTextCustom("Record Number", "*"));
		}

		//Check Ordinances
		if (ordinances == true ){
			clickButtonByXpathTitleName("Ordinances");
			Assert.assertTrue(checkElementTextViewReturn("Baptism"));
			Assert.assertTrue(checkElementTextViewReturn("Nov 11, 1970"));
			Assert.assertTrue(checkElementTextViewReturn("Confirmation"));
			Assert.assertTrue(checkElementTextViewReturn("Nov 11, 1970"));
			//pressBackKey();
			clickButtonByXpath("TopIndividual");
			
		} else {
			Assert.assertFalse(checkElementTextViewReturn("Ordinances"));
		}


		
		/*
		//Check Other Information
		clickButtonByXpathTitleName("Other Information");
		Assert.assertTrue(checkElementTextViewReturn("Gender"));
		Assert.assertTrue(checkElementTextViewReturn("Female"));
		Assert.assertTrue(checkElementTextViewReturn("Birth Country"));
		Assert.assertTrue(checkElementTextViewReturn("United States"));
		pressBackKey();
		*/

		Thread.sleep(2000);

		//pressBackKey();
		clickButtonByXpath("TopDirectory");
		Thread.sleep(1000);
		
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
	}
	
	/** checkDrawerItems (boolean leader)
	 * Check the drawer items - non leaders should not have the reports item
	 * 
	 * @param leader
	 * @throws Exception
	 */
	private void checkDrawerItems (boolean leader) throws Exception {
		//Check the Drawer items
		clickButtonByXpath("Drawer");
		Assert.assertTrue(checkElementTextViewReturn("Directory"));
		Assert.assertTrue(checkElementTextViewReturn("Organizations"));
		Assert.assertTrue(checkElementTextViewReturn("Missionary"));
		Assert.assertTrue(checkElementTextViewReturn("Lists"));
		Assert.assertTrue(checkElementTextViewReturn("Calendar"));
		Assert.assertTrue(checkElementTextViewReturn("Meetinghouses"));
		if (leader == true) {
			Assert.assertTrue(checkElementTextViewReturn("Reports"));
		} else {
			Assert.assertFalse(checkElementTextViewReturn("Reports"));
		}
		
	}
	
	/** checkCallings()
	 * Check the callings all users should have access to this
	 * 
	 * @throws Exception
	 */
	private void checkCallings() throws Exception {
		//Callings
		//clickButtonByXpath("Drawer");
		//clickButtonByXpath("DrawerCallings");
		clickButtonByXpath("DrawerOrganizations");
		
		Assert.assertTrue(checkElementTextViewRoboReturn("Bishopric"));
		Assert.assertTrue(checkElementTextViewRoboReturn("High Priests Group"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Elders Quorum"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Relief Society"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Young Men"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Young Women"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Sunday School"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Primary"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Ward Missionaries"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Other Callings"));
		
		//Bishopric
		clickItemByXpathRoboText("Bishopric");
		Assert.assertTrue(checkElementTextViewRoboReturn("Bishop"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Ami, Samu"));
		//Assert.assertTrue(checkElementTextViewRoboReturn("Bishopric First Counselor"));
		//Assert.assertTrue(checkElementTextViewRoboReturn("AFPMisc, Member15"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Bishopric Second Counselor"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Faapili, Muipu"));
		//Assert.assertTrue(checkElementTextViewRoboReturn("Ward Executive Secretary"));
		//Assert.assertTrue(checkElementTextViewRoboReturn("Sitivi, Sitivi"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Ward Clerk"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Tutunoa, Ualesi Junior, Jr"));
		//Assert.assertTrue(checkElementTextViewRoboReturn("Ward Assistant Clerk"));
		//Assert.assertTrue(checkElementTextViewRoboReturn("Sitivi, Tama Kiliona"));
		//Assert.assertTrue(checkElementTextViewRoboReturn("Ward Assistant Clerk--Membership"));
		//Assert.assertTrue(checkElementTextViewRoboReturn("Whitesel, Jason Raymond"));
		//pressBackKey();
		clickButtonByXpath("TopOrganizations");
		
		
		//High Priests Group
		clickItemByXpathRoboText("High Priests Group");
		clickItemByXpathRoboText("High Priests Group Leadership");
		Assert.assertTrue(checkElementTextViewRoboReturn("High Priests Group Leader"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Faamoe, Panapa Filifili"));
		clickButtonByXpath("TopBack");
		//pressBackKey();
		Thread.sleep(2000);
		clickButtonByXpath("TopOrganizations");
		//pressBackKey();
		
		//Elders Quorum
		clickItemByXpathRoboText("Elders Quorum");
		clickItemByXpathRoboText("Elders Quorum Presidency");
		Assert.assertTrue(checkElementTextViewRoboReturn("Elders Quorum President"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Tutunoa, Joe Liuafi"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Elders Quorum First Counselor"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Tautali, Tamafaiga"));
		clickButtonByXpath("TopBack");
		//pressBackKey();
		Thread.sleep(2000);
		//pressBackKey();
		clickButtonByXpath("TopOrganizations");
		
		
		//Relief Society
		clickItemByXpathRoboText("Relief Society");
		clickItemByXpathRoboText("Relief Society Presidency");
		//displayAllTextViewElements();
		Assert.assertTrue(checkElementTextViewRoboReturn("Relief Society President"));
		//Assert.assertTrue(checkElementTextViewRoboReturn("Frost, Sato'a"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Relief Society First Counselor"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Faamoetauloa, Fiasili"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Relief Society Second Counselor"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Faapili, Baby"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Relief Society Secretary"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Patiole, Luafa"));
		clickButtonByXpath("TopBack");
		//clickButtonByXpath("TopReliefSociety");
		//pressBackKey();
		Thread.sleep(2000);
		//pressBackKey();
		clickButtonByXpath("TopOrganizations");
		
		//Young Men
		clickItemByXpathRoboText("Young Men");
		clickItemByXpathRoboText("Young Men Presidency");
		Assert.assertTrue(checkElementTextViewRoboReturn("Young Men President"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Kitara, Lafaele"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Young Men First Counselor"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Poai, Mikaele"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Young Men Second Counselor"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Faamoetauloa Panapa Jr, Panapa Jnr"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Young Men Secretary"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Venasio Fainuu, Fogavai"));
		clickButtonByXpath("TopYoungMen");
		Thread.sleep(2000);
		//pressBackKey();
		clickItemByXpathRoboText("Priests Quorum");
		Thread.sleep(2000);
		clickItemByXpathRoboText("Priests Quorum Presidency");
		Assert.assertTrue(checkElementTextViewRoboReturn("Priests Quorum First Assistant"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Tulia, Tiueni"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Priests Quorum Second Assistant"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Kitara, Tumua"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Priests Quorum Secretary"));
		//Assert.assertTrue(checkElementTextViewRoboReturn("Tulia, Tiueni"));
		clickButtonByXpath("TopBack");
		Thread.sleep(2000);
		clickButtonByXpath("TopYoungMen");
		//pressBackKey();
		Thread.sleep(2000);
		clickButtonByXpath("TopOrganizations");
		//pressBackKey();
		//Thread.sleep(2000);
		//pressBackKey();
		
		
		//Young Women
		clickItemByXpathRoboText("Young Women");
		clickItemByXpathRoboText("Young Women Presidency");
		Assert.assertTrue(checkElementTextViewRoboReturn("Young Women President"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Tutunoa, Lusi"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Young Women First Counselor"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Kitara, Etevise"));
		clickButtonByXpath("TopBack");
		//clickButtonByXpath("TopYoungWomen");
		//pressBackKey();
		Thread.sleep(2000);
		//pressBackKey();
		clickButtonByXpath("TopOrganizations");
		
		
		//Sunday School
		clickItemByXpathRoboText("Sunday School");
		clickItemByXpathRoboText("Sunday School Presidency");
		Assert.assertTrue(checkElementTextViewRoboReturn("Sunday School President"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Lealaiauloto, Uana Iosefa Sao"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Sunday School First Counselor"));
		//Assert.assertTrue(checkElementTextViewRoboReturn("Mene, Sitivi"));
		//Assert.assertTrue(checkElementTextViewRoboReturn("Sunday School Second Counselor"));
		//Assert.assertTrue(checkElementTextViewRoboReturn("Apofasa, Sasa'a"));
		clickButtonByXpath("TopBack");
		//clickButtonByXpath("TopSundaySchool");
		//pressBackKey();
		Thread.sleep(2000);
		//pressBackKey();
		clickButtonByXpath("TopOrganizations");
		
		
		//Primary
		clickItemByXpathRoboText("Primary");
		clickItemByXpathRoboText("Primary Presidency");
		Assert.assertTrue(checkElementTextViewRoboReturn("Primary President"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Faamoe, Talalelagi"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Primary First Counselor"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Fepuleai, Malele Seuamuli"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Primary Second Counselor"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Tulia, Faagalo"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Primary Secretary"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Samu, Luisa"));
		clickButtonByXpath("TopBack");
		//clickButtonByXpath("TopPrimary");
		//pressBackKey();
		Thread.sleep(2000);
		//pressBackKey();
		clickButtonByXpath("TopOrganizations");

		//Ward Missionaries
		clickItemByXpathRoboText("Ward Missionaries");
		Assert.assertTrue(checkElementTextViewRoboReturn("Mission Leader"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Kitara, Lafaele"));
		//pressBackKey();
		clickButtonByXpath("TopOrganizations");
		
		
		//Other Callings
		clickItemByXpathRoboText("Other Callings");
		clickItemByXpathRoboText("Young Single Adult");
		Assert.assertTrue(checkElementTextViewRoboReturn("Young Single Adult Leader"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Solomona, Solomona"));
		clickButtonByXpath("TopBack");
		//pressBackKey();
		clickItemByXpathRoboText("Music");
		Assert.assertTrue(checkElementTextViewRoboReturn("Music Adviser"));
		//Assert.assertTrue(checkElementTextViewRoboReturn("Frost,Sato'a"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Organist or Pianist"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Betham, Maria"));
		clickButtonByXpath("TopOtherCallings");
		
		//pressBackKey();
		Thread.sleep(2000);
		//pressBackKey();
		clickButtonByXpath("TopOrganizations");
	}
	
	/** checkMissionary()
	 * Check the missionary drawer items
	 * 
	 * @throws Exception
	 */
	private void checkMissionary() throws Exception {
		//Missionary
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerMissionary");
		Assert.assertTrue(checkElementTextViewRoboReturn("Elder Kawika Tupuola"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Samoa Apia Mission"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Elder Dallin Fawcett"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Samoa Apia Mission"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Kitara, Lafaele"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Mission Leader"));
		//pressBackKey();
	}
	
	/** checkReports()
	 * Check reports for leaders
	 * 
	 * @throws Exception
	 */
	private void checkReports(boolean newUnit, boolean bishop) throws Exception {
		//Reports
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerReports");
		//Assert.assertTrue(checkElementTextViewReturn("Action and Interview List"));
		Assert.assertTrue(checkElementTextViewReturn("Birthday List"));
		Assert.assertTrue(checkElementTextViewReturn("Members Moved In"));
		Assert.assertTrue(checkElementTextViewReturn("Members Moved Out"));
		Assert.assertTrue(checkElementTextViewReturn("Members with Callings"));
		Assert.assertTrue(checkElementTextViewReturn("Members without Callings"));
		Assert.assertTrue(checkElementTextViewReturn("New Members"));
		//Assert.assertTrue(checkElementTextViewReturn("Temple Recommend Status"));
		Assert.assertTrue(checkElementTextViewReturn("Unit Statistics"));
		Assert.assertFalse(checkElementTextViewReturn("Death Star Reports"));
		
		
		//Check the members moved out report
		//Should have a ( ) with the age by the birth date
		clickButtonByXpathTitleName("Members Moved Out");
		Assert.assertTrue(checkElementTextViewReturn("Brimley, Steve"));
		//Birth Date
		//TODO need to have the age calculated
		Assert.assertTrue(checkElementTextViewReturn("Jul 2, 1963 (52)"));
		Assert.assertTrue(checkElementTextViewReturn("Jul 6, 2015"));
		
		//The new unit is only available for bishop
		if (bishop == true){
			Assert.assertTrue(checkElementTextViewReturn("Cedar Hills  8th Ward"));
		} else {
			Assert.assertFalse(checkElementTextViewReturn("Cedar Hills  8th Ward"));
		}
		Assert.assertFalse(checkElementTextViewReturn("Solo, Han"));
		
		clickButtonByXpath("TopBack");
		Thread.sleep(2000);
		//pressBackKey();
		//clickButtonByXpath("Drawer");
		//clickButtonByXpath("DrawerReports");
		
		//Members Moved In
		clickButtonByXpathTitleName("Members Moved In");
		Assert.assertTrue(checkElementTextViewReturn("Sa"));
		Assert.assertTrue(checkElementTextViewReturn("Seti (55)"));
		Assert.assertTrue(checkElementTextViewReturn("Head of household"));
		Assert.assertFalse(checkElementTextViewReturn("Skywalker, Luke"));

		
		pressBackKey();
		Thread.sleep(2000);
		//clickButtonByXpath("Drawer");
		//clickButtonByXpath("DrawerReports");
		
		//Members with Callings
		clickButtonByXpathTitleName("Members with Callings");
		Assert.assertTrue(checkElementTextViewReturn("Ami, Christian"));
		Assert.assertTrue(checkElementTextViewReturn("Beehive President (3 months)"));
		Assert.assertFalse(checkElementTextViewReturn("Skywalker, Anakin"));
		
		clickButtonByXpath("TopSort");
		clickButtonByXpathTitleName("Organization");
		Assert.assertTrue(checkElementTextViewReturn("Bishopric Second Counselor"));
		Assert.assertTrue(checkElementTextViewReturn("Faapili, Muipu (1 year, 10 months)"));
		Assert.assertFalse(checkElementTextViewReturn("Kenobi, Obi-Wan"));
		
		clickButtonByXpath("TopSort");
		clickButtonByXpathTitleName("Duration");
		Assert.assertTrue(checkElementTextViewReturn("Young Women President"));
		Assert.assertTrue(checkElementTextViewReturn("Tutunoa, Lusi"));
		Assert.assertFalse(checkElementTextViewReturn("Amidala, Padme"));
		
		clickButtonByXpath("TopSort");
		clickButtonByXpathTitleName("Not Set Apart");
		Assert.assertTrue(checkElementTextViewReturn("Elders Quorum First Counselor (4 months)"));
		Assert.assertTrue(checkElementTextViewReturn("Tautali, Tamafaiga"));
		Assert.assertFalse(checkElementTextViewReturn("P0, C3"));
		pressBackKey();
		Thread.sleep(2000);
		
		//Members without Callings
		clickButtonByXpathTitleName("Members without Callings");
		//displayAllTextViewElements();
		Assert.assertTrue(checkElementTextViewRoboReturn("AFPEighteen, Member"));
		Assert.assertTrue(checkElementTextViewRoboReturn("AFPEleven, Member"));
		Assert.assertFalse(checkElementTextViewRoboReturn("D2, R2"));
		
		clickButtonByXpath("TopSort");
		clickButtonByXpathTitleName("Male");
		Assert.assertTrue(checkElementTextViewRoboReturn("AFPEleven, Member"));
		Assert.assertTrue(checkElementTextViewRoboReturn("AFPFifteen, Member"));
		Assert.assertFalse(checkElementTextViewRoboReturn("Binks, Jarjar"));
		
		clickButtonByXpath("TopSort");
		clickButtonByXpathTitleName("Female");
		Assert.assertTrue(checkElementTextViewRoboReturn("AFPEighteen, Member"));
		Assert.assertTrue(checkElementTextViewRoboReturn("AFPFive, Wife"));
		Assert.assertFalse(checkElementTextViewRoboReturn("Organa, Leia"));
		
		pressBackKey();
		Thread.sleep(2000);
		
		//New Members
		clickButtonByXpathTitleName("New Members");
		Assert.assertTrue(checkElementTextViewReturn("Joezmal, Loana"));
		Assert.assertTrue(checkElementTextViewReturn("13"));
		Assert.assertTrue(checkElementTextViewReturn("F"));
		//Assert.assertTrue(checkElementTextViewReturn("Mar 15, 2015"));
		Assert.assertTrue(checkElementTextViewReturn("Member"));
		Assert.assertFalse(checkElementTextViewReturn("Hutt, Jabba"));
		pressBackKey();
		Thread.sleep(2000);
		
		if (newUnit == true ){
			//Temple Recommend Status
			clickButtonByXpathTitleName("Temple Recommend Status");
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member15"));
			Assert.assertFalse(checkElementTextViewReturn("Ahsoka, Tano"));
			//Assert.assertTrue(checkElementTextViewReturn("Expired"));
			
			clickButtonByXpath("TopSort");
			clickButtonByXpathTitleName("Active");
			Assert.assertTrue(checkElementTextViewReturn("Betham, Maria"));
			Assert.assertTrue(checkElementTextViewReturn("Jul 2016 (Active)"));
			Assert.assertFalse(checkElementTextViewReturn("Maul, Darth"));
			
			clickButtonByXpath("TopSort");
			clickButtonByXpathTitleName("Expiring");
			Assert.assertTrue(checkElementTextViewReturn("Tutunoa, Lusi"));
			Assert.assertFalse(checkElementTextViewReturn("Windu, Mace"));
			
			clickButtonByXpath("TopSort");
			clickButtonByXpathTitleName("Expired");
			Assert.assertTrue(checkElementTextViewReturn("Ami, Lealofi"));
			Assert.assertFalse(checkElementTextViewReturn("Jinn, Qui-Gon"));
			
			clickButtonByXpath("TopSort");
			clickButtonByXpathTitleName("Other");
			Assert.assertTrue(checkElementTextViewReturn("Mene, Matagalu"));
			Assert.assertFalse(checkElementTextViewReturn("Calrissian, Lando"));
			pressBackKey();
			Thread.sleep(2000);
		}

		
		//Unit Statistics
		clickButtonByXpathTitleName("Unit Statistics");
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewReturn("TOTAL MEMBERS"));
		Assert.assertTrue(checkElementTextViewReturn("603  "));
		Assert.assertTrue(checkElementTextViewReturn("270  "));
		Assert.assertTrue(checkElementTextViewReturn("15  "));
		Assert.assertFalse(checkElementTextViewReturn("8675309  "));
	}
	
	private void checkAllWardDirectories() throws Exception {
		List<String> StakeWard = new ArrayList<String>();
		clickButtonByXpath("SpinnerNav");
		Thread.sleep(2000);
		
		//Get Stake and all Wards
		List<WebElement> options= driver.findElements(By.xpath("//*[@id='title']"));
		for (int i = 0 ; i < options.size(); i++ ) {
			//System.out.println(options.get(i).getText());
			StakeWard.add(options.get(i).getText());
		}
		pressBackKey();
		
		//Go through each Stake and Ward to make sure it isn't blank
		for(String StakeWardItem : StakeWard){
			clickButtonByXpath("SpinnerNav");
			Thread.sleep(2000);
			clickButtonByXpathTitleName(StakeWardItem);
			//displayAllTextViewElements();
			
			//Should be a better way to do this. 
			Assert.assertTrue(checkElementTextViewReturnContains("e"));
			Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));
		}
	}
	
	private void checkForAlertWarning() throws Exception {
		int myCheck;
		//Check to see if we are getting a warning
		myCheck = checkTextByXpathReturn("AlertMessage", "Warning");
		if (myCheck == 1) {
			clickButtonByXpath("OK");
			
		}
	}
	
	//TODO: Need to be able to select 1 to 12 units
	/** unitsToSync()
	 * If there are more than 12 units this will select the Savaii Stake
	 * @throws Exception
	 */
	private void unitsToSync() throws Exception {
		if (checkElementTextViewReturn("Select up to 12 units to sync.")) {
			clickButtonByXpathTitleName("Savaii Samoa Fagamalo Stake");
			clickButtonByXpath("SyncButton");
			Thread.sleep(2000);
		}
	}
	
	private void logoutUser() throws Exception {
		Thread.sleep(1000);
		//Open the Drawer
		clickButtonByXpath("Drawer");
		//Press Settings
		clickButtonByXpath("DrawerSETTINGS");
		
		//Select Sign Out
		clickButtonByXpathTitleName("Sign Out");
		//Hit OK on the alert
		clickButtonByXpath("SignOutOK");
		
		Thread.sleep(1000);
		//Hit OK on the alert that the info is reset
		clickButtonByXpath("SignOutOK");
		Thread.sleep(3000);
	}
	
	
	

	@After
	public void teardown() {
		driver.quit();
	}
	
	
	@Before
	public void openGuiMap() {
		
		File file = new File("ConfigFiles/uiMap.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.prop = new Properties();
		
		try {
			prop.load(fileInput);
		} catch(IOException e) {
			e.printStackTrace();
		}

	}
	
	//Need this class to get the touch stuff to work with Appium - Android
	public class AppiumSwipeableDriver extends AppiumDriver implements HasTouchScreen{
		 public RemoteTouchScreen touch;
		 public AppiumSwipeableDriver(URL URL, Capabilities Cap) {
			 super(URL, Cap);
			 touch = new RemoteTouchScreen(getExecuteMethod());
		}

		 @Override
		 public TouchScreen getTouch() {
			 return touch;
		 }

		@Override
			public MobileElement scrollTo(String arg0) {
			return null;
		}

		@Override
			public MobileElement scrollToExact(String arg0) {
			return null;
		}
	}
	
	
	//Retry Test needed so the system will retry a failed test
    public class Retry implements TestRule {
	        private int retryCount;
	
	    public Retry(int retryCount) {
	        this.retryCount = retryCount;
	    }
	
	    public Statement apply(Statement base, Description description) {
	        return statement(base, description);
	    }
	
	    private Statement statement(final Statement base, final Description description) {
	        return new Statement() {
	            @Override
	            public void evaluate() throws Throwable {
	                Throwable caughtThrowable = null;
	
	                // implement retry logic here
	                for (int i = 0; i < retryCount; i++) {
	                    try {
	                        base.evaluate();
	                        return;
	                    } catch (Throwable t) {
	                        caughtThrowable = t;
	                        System.err.println(description.getDisplayName() + ": run " + (i+1) + " failed");
	                    }
	                }
	                System.err.println(description.getDisplayName() + ": giving up after " + retryCount + " failures");
	                throw caughtThrowable;
	            }
	        };
	    }
	    
    }
}
