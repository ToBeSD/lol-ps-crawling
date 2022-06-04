package com.sbs.selenium.exam;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class ChampTierCraw {
	String champName;
	String line;
	String psScore;
	String honeyScore;
	String winRate;
	String pickRate;
	String banRate;
	String count;
	
	ChampTierCraw(String champName, String line, String psScore, String honeyScore, String winRate, 
				  String pickRate, String banRate, String count) {
		
		this.champName = champName;
		this.line = line;
		this.psScore = psScore;
		this.honeyScore = honeyScore;
		this.winRate = winRate;
		this.pickRate = pickRate;
		this.banRate = banRate;
		this.count = count;
	}
	
	@Override
	public String toString() {
		return  champName +" "+ line+ " " + psScore + " " + honeyScore + " " + winRate + " " + pickRate + " " + banRate + " " + count;
	}
}


public class ChampTier {
	
	public ChromeDriver getCromeDriver() {
		// WebDriver 경로 설정
		Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", path.toString());
		
		// WebDriver 옵션 설정
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized"); // 전체화면으로 실행
		options.addArguments("--disable-popup-blocking"); // 팝업 무시
		options.addArguments("--disable-default-apps"); // 기본앱 사용안함
		options.addArguments("lang=ko");
		
		//해결 UnknownError: session deleted because of page crash from tab crashed
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");        

		
		// WebDriver 객체 생성
		ChromeDriver driver = new ChromeDriver(options);
		
		return driver;
	}

	
	void allchamp(ChromeDriver driver, DBmanager db, Connection conn, PreparedStatement pstmt) throws SQLException, InterruptedException {
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		
//		WebElement button = driver.findElement(By.id("tierlist-all-tab"));
//		
//		js.executeScript("arguments[0].click();", button);
		
		// 크롤링 해서 값 넣어줄 배열 생성
		ArrayList<ChampTierCraw> myList2 = new ArrayList<ChampTierCraw>();

		String url = "https://lol.ps/ko/statistics/";
		driver.get(url);
		Thread.sleep(10000);
		try {
			List<WebElement> list = null;
			
			list = driver.findElements(By.className("list"));

			WebElement element = list.get(0);
			
			list = element.findElements(By.className("list-item"));
			
			for(WebElement we : list) {
				System.out.print("-");
				//같은 class 녀석들 구분하려고 배열생성해서 for문으로 넣기
				String[] ban = new String[3];
				int idx = 0;
				for(WebElement banlist : we.findElements(By.className("ban"))) {
					ban[idx] = banlist.getText();
					idx++;
				}
				
				String[] op = new String[3];
				int idx2 = 0;
				for(WebElement oplist : we.findElements(By.className("op"))) {
					op[idx2] = oplist.getText();
					idx2++;
				}
				
				String[] honey = new String[3];
				int idx3 = 0;
				for(WebElement honeylist : we.findElements(By.className("honey"))) {
					honey[idx3] = honeylist.getText();
					idx3++;
				}

				String champName = we.findElement(By.tagName("p")).getText();
				String line = we.findElement(By.className("position")).getText();
				
				String psScore = we.findElement(By.className("op")).getText();
				String honeyScore = we.findElement(By.className("honey")).getText();
				
				if(op[0].equals("")) {
					psScore =  op[1];
				}else {
					psScore =  op[0];
				}
				
				if(honey[0].equals("")) {
					honeyScore =  honey[1];
				}else {
					honeyScore =  honey[0];
				}
				
				
				String winRate = we.findElement(By.className("win")).getText();
				String pickRate = we.findElement(By.className("pick")).getText();
				String banRate = ban[0]; 
				String count = ban[1];
				
				
				//데이터 베이스에 insert
				pstmt.setString(1, champName);
				pstmt.setString(2, line);
				pstmt.setDouble(3, Double.parseDouble(psScore));
				pstmt.setDouble(4, Double.parseDouble(honeyScore));				
				pstmt.setDouble(5, Double.parseDouble(winRate.replace("%", "")));				
				pstmt.setDouble(6, Double.parseDouble(pickRate.replace("%", "")));				
				pstmt.setDouble(7, Double.parseDouble(banRate.replace("%", "")));				
				pstmt.setDouble(8, Double.parseDouble(count.replace(",", "")));				
				pstmt.setString(9, count);				
				pstmt.executeUpdate();	

				myList2.add(new ChampTierCraw(champName, line, psScore, honeyScore, winRate, pickRate, banRate, count));
			}
			
			System.out.println();
			for(int i = 0; i < myList2.size(); i++) {
				System.out.println(myList2.get(i));
			}
			System.out.println("챔피언티어 통계 가져왔다");
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void tierCraw(Connection conn, PreparedStatement pstmt, DBmanager db, ChampTier champtier, ChromeDriver driver) throws SQLException {
		try {
			conn = db.getConnection();
			String sql = "insert into c_champ_tier_before values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			champtier.allchamp(driver, db, conn, pstmt);
		} catch(Exception e) {
		
		} finally {
			conn.close();
			System.out.println("conn종료");
			pstmt.close();
			System.out.println("pstmt종료");
			driver.quit();
			System.out.println("driver종료");
		}
	}
		
	public static void main(String[] args) throws SQLException {
		ChampTier champtier = new ChampTier();
		ChromeDriver driver = champtier.getCromeDriver();
		
		DBmanager db = new DBmanager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		champtier.tierCraw(conn, pstmt, db, champtier, driver);
	
	}
}


