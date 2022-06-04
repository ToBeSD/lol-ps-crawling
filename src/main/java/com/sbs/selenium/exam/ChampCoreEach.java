package com.sbs.selenium.exam;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class CoreEachCraw {
	String itemName;
	String itemWinRate;
	String itemPickRate;
	String itemCount;
	
	CoreEachCraw(String champName, String itemName, String itemWinRate, String itemPickRate) {
		this.itemName = champName;
		this.itemWinRate = itemName;
		this.itemPickRate = itemWinRate;
		this.itemCount = itemPickRate;
	}

	
	@Override
	public String toString() {
		return "패치 : " + itemName + "\t스킬 : " + itemWinRate + "\t내용 : " + itemPickRate + " " + itemCount;
	}
}


public class ChampCoreEach {

		
	
	static void runeCombie() throws SQLException {
		// WebDriver 경로 설정
		Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/chromedriver.exe");
		
		System.setProperty("webdriver.chrome.driver", path.toString());
		
		// WebDriver 옵션 설정
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized"); // 전체화면으로 실행
		options.addArguments("--disable-popup-blocking"); // 팝업 무시
		options.addArguments("--disable-default-apps"); // 기본앱 사용안함
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		// WebDriver 객체 생성
		ChromeDriver driver = new ChromeDriver(options);
		
		// 크롤링 해서 값 넣어줄 배열 생성
		ArrayList<CoreEachCraw> myList2 = new ArrayList<CoreEachCraw>();
		
		String url = "https://lol.ps/ko/champ/12/";
		driver.get(url);
		try {
			Thread.sleep(3000); // 3. 페이지 로딩 대기 시간
			List<WebElement> list = null;
			
			list = driver.findElements(By.className("champ-content-box"));
			
			WebElement element = list.get(0);

			list = element.findElements(By.className("t-row"));

			for(WebElement we : list) {
				System.out.print("-");
				
				String champName = we.findElement(By.xpath("/html/body/main/div[1]/section/div[1]/h3")).getText();
				String stat = we.findElement(By.className("label")).getText();
				String statStart = we.findElement(By.className("basic-stat")).getText();
				String statFinal = we.findElement(By.className("final-stat")).getText();
				String statRank = we.findElement(By.className("ranking")).getText();
				String what = we.findElement(By.xpath("/html/body/main/div[1]/div[3]/section/div[1]/div[2]/div[2]/div[1]/div/p")).getText();
				
				
			}
			
			
			for(int i = 0; i < myList2.size(); i++) {
				System.out.println(myList2.get(i));
			}
			
			System.out.println("룬 조합별 통계 가져왔다");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.close(); // 5. 브라우저 종료
		}
	}
	
	
	void history(int a, PreparedStatement pstmt, ChromeDriver driver) throws SQLException {
		// 크롤링 해서 값 넣어줄 배열 생성
		ArrayList<CoreEachCraw> myList2 = new ArrayList<CoreEachCraw>();
		 	
		String url = "https://lol.ps/ko/champ/"+a+"/patch/";
		driver.get(url);
		
		try {
			Thread.sleep(3000); // 3. 페이지 로딩 대기 시간
			List<WebElement> list = null;
			
			list = driver.findElements(By.className("champ-content-box"));
			
			WebElement element = list.get(0);
			
			list = element.findElements(By.className("ver"));
			
			for(WebElement we : list) {
				System.out.print("-");
				
				String champName = we.findElement(By.xpath("/html/body/main/div[1]/section/div[1]/h3")).getText();
				String version = we.findElement(By.tagName("h4")).getText();
				String skill = we.findElement(By.tagName("li")).getText();
				String statStart = we.findElement(By.tagName("ul")).getText();
				
				pstmt.setString(1, champName);				
				pstmt.setString(2, version);				
				pstmt.setString(3, skill);				
				pstmt.setString(4, statStart);				
				
				myList2.add(new CoreEachCraw(champName, version, skill, statStart));
			}
			
			
			for(int i = 0; i < myList2.size(); i++) {
				System.out.println(myList2.get(i));
			}
			
			System.out.println("룬 조합별 통계 가져왔다");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.close(); // 5. 브라우저 종료
		}
	}
	
	
	
	public static void main(String[] args) throws SQLException {
		ChampStatistics cs = new ChampStatistics();
		ChromeDriver driver = cs.getCromeDriver();

		// jdbc connection 객체 생성
		DBmanager db = new DBmanager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		ChampCoreEach c = new ChampCoreEach();
		
		try {
			conn = db.getConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		for(int i = 1; i < 4; i++) {
			try {
				String sql = "insert into c_patch_history values(?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);			
				
				//		runeCombie();
					c.history(i,  pstmt, driver);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				pstmt.close();
			}
		}
		
		conn.close();
	}
}


