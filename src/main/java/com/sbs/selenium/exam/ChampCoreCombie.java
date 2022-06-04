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

class CoreCombieCraw {
	String itemOne;
	String itemWinRate;
	String itemPickRate;
	String itemCount;
	
	//4코어 통게 생성자
	CoreCombieCraw(String itemOne, String itemWinRate, String itemPickRate, String itemCount) {
		this.itemOne = itemOne;
		this.itemWinRate = itemWinRate;
		this.itemPickRate = itemPickRate;
		this.itemCount = itemCount;
	}
	
	
	@Override
	public String toString() {
		return "룬 : " + itemOne + "\t승률 : " + itemWinRate + "\t픽률 : " + itemPickRate + "\t카운트 수 : " + itemCount;
	}
}


public class ChampCoreCombie {
	static String url = "https://lol.ps/ko/champ/1/statistics/";
	
	public ChromeDriver getCromeDriver() {
		// WebDriver 경로 설정
		Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", path.toString());
		
		// WebDriver 옵션 설정
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-popup-blocking"); // 팝업 무시
		options.addArguments("--disable-default-apps"); // 기본앱 사용안함
		options.addArguments("headless");
		options.addArguments("lang=ko");
		//해결 UnknownError: session deleted because of page crash from tab crashed
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");  
		
		// WebDriver 객체 생성
		ChromeDriver driver = new ChromeDriver(options);
		
		return driver;
	}
	
	void twoCoreCombie(ChromeDriver driver, DBmanager db, Connection conn, PreparedStatement pstmt) throws SQLException {
		
		// 크롤링 해서 값 넣어줄 배열 생성
		ArrayList<CoreCombieCraw> myList2 = new ArrayList<CoreCombieCraw>();
		
		driver.get(url);
		try {
			Thread.sleep(3000); // 3. 페이지 로딩 대기 시간
			List<WebElement> list = null;
			
			//driver.findElements --> 전체 페이지에서 className "champ-content-box" 안에 item-list 찾아와라
			list = driver.findElements(By.className("each"));
			//element --> champ-content-box의 세번째꺼 가져와
			// element -> 코어템 통계
			WebElement element = list.get(1);
			
			// list는 코어템 통계에 있는 item class 애들
			list = element.findElements(By.className("item"));
			
			WebElement box = list.get(0);
			
			list = box.findElements(By.tagName("div"));
			
			
			for(WebElement we : list) {
				System.out.print("-");
				
//				String[] imgName = new String[300];
//				int idx = 0;
//				for(WebElement itemImg : we.findElements(By.className("rune-img"))) {
//					imgName[idx] = itemImg.getAttribute("data-original-title");
//					System.out.println(imgName[idx] + " ");
//					idx++;
//				}
//				
//				System.out.println();
//				
				String[] winrate = new String[300];
				int idx2 = 0;
				for(WebElement rate : we.findElements(By.tagName("span"))) {
					winrate[idx2] = rate.getText();
//					System.out.println(winrate[idx2]);
					idx2++;
				}
				
				//같은 class 녀석들 구분하려고 배열생성해서 for문으로 넣기
				
				String itemOne = we.findElement(By.className("rune-img")).getAttribute("data-original-title"); 
				String winRate = winrate[0]; 
				String pickRate = winrate[1]; 
				String count = winrate[2];
				
				//데이터 베이스에 insert
//				pstmt.setString(1, itemOne);
//				pstmt.setString(2, winRate);
//				pstmt.setString(3, pickRate);
//				pstmt.setString(4, count);
//				pstmt.executeUpdate();	

				myList2.add(new CoreCombieCraw(itemOne, winRate, pickRate, count));
			}
			
			
			System.out.println();
			for(int i = 0; i < myList2.size(); i++) {
				System.out.println(myList2.get(i));
			}
			
			System.out.println("rune all 가져왔다");
		} catch (Exception e) {
			e.printStackTrace();
//					System.out.println("에러");
		} 
		
	}
		
	public static void main(String[] args) throws SQLException {
		ChampCoreCombie champCoreCombie = new ChampCoreCombie();
		ChromeDriver driver = champCoreCombie.getCromeDriver();

		// jdbc connection 객체 생성
		DBmanager db = new DBmanager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {	// 코어템 조합 통계		
			conn = db.getConnection();
			String sql = "insert into item_core_combie values(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
						
			champCoreCombie.twoCoreCombie(driver, db, conn, pstmt);
		} catch(Exception e)  {
			System.out.println("에러");
		} finally {
			conn.close();
			System.out.println("Connection 종료");
			pstmt.close(); 
			System.out.println("PrepareStatement 종료");
			driver.close(); // 5. 브라우저 종료
			System.out.println("브라우저 종료");
		}
		
		
	}
}


