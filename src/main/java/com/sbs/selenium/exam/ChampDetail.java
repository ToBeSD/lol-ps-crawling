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

class ChampVersus {
	String champName;
	String champInfo;
	String champGameCount;
	String champWinRate;
//	ScoreData2(String champName, String champInfo, String champGameCount) {
//		this.champName = champName;
//		this.champInfo = champInfo;
//		this.champGameCount = champGameCount;
//	}
	
	ChampVersus(String champName, String champInfo, String champGameCount, String champWinRate) {
		this.champName = champName;
		this.champInfo = champInfo;
		this.champGameCount = champGameCount;
		this.champWinRate = champWinRate;
	}

	
	@Override
	public String toString() {
		return champName + " vs " + champInfo + champGameCount + champWinRate;
	}
}


public class ChampDetail {
	public static void main(String[] args) throws SQLException {
		// WebDriver 경로 설정
		Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/chromedriver.exe");
		
		System.setProperty("webdriver.chrome.driver", path.toString());
		
		// WebDriver 옵션 설정
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized"); // 전체화면으로 실행
		options.addArguments("--disable-popup-blocking"); // 팝업 무시
		options.addArguments("--disable-default-apps"); // 기본앱 사용안함
		
		// WebDriver 객체 생성
		ChromeDriver driver = new ChromeDriver(options);
		
		// 크롤링 해서 값 넣어줄 배열 생성
		ArrayList<ChampVersus> myList2 = new ArrayList<ChampVersus>();
		
		// jdbc connection 객체 생성
		DBmanager db = new DBmanager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = db.getConnection();
			String sql = "insert into vs_champ values(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			String url = "https://lol.ps/ko/champ/1/statistics/?lane=2&region=0&tier=2&version=44";
			driver.get(url);
			Thread.sleep(3000); // 3. 페이지 로딩 대기 시간
			List<WebElement> list = null;
			list = driver.findElements(By.className("champ"));
			for(WebElement we : list) {
				System.out.print("-");
				String champName = we.findElement(By.xpath("/html/body/main/div[1]/section/div[1]/h3")).getText();				
				String champInfo = we.findElement(By.className("champ-info")).getText();				
				String champGameCount = we.findElement(By.className("champ-gamecount")).getText();
				String champWinRate = we.getText();
				champWinRate = champWinRate.substring(champWinRate.indexOf("게임")+3);
				
				//insert into 
//				pstmt.setString(1, champName);
//				pstmt.setString(2, champInfo);
//				pstmt.setString(3, champGameCount);
//				pstmt.setString(4, champWinRate);
//				pstmt.executeUpdate();
				myList2.add(new ChampVersus(champName, champInfo, champGameCount, champWinRate));
			}
			
			
			System.out.println();
			for(int i = 0; i < myList2.size(); i++) {
				System.out.println(myList2.get(i));
			}
			System.out.println("챔프 상대승률 가져왔다");
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}

