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

class StartItemCraw {
	String spellOne;
	String spellTwo;
	String itemWinRate;
	String itemPickRate;
	String itemCount;
	String itemOption;
	
	StartItemCraw(String spellName, String spellTwo, String itemWinRate, String itemPickRate, String itemCount, String itemOption) {
		this.spellOne = spellName;
		this.spellTwo = spellTwo;
		this.itemWinRate = itemWinRate;
		this.itemPickRate = itemPickRate;
		this.itemCount = itemCount;
		this.itemOption = itemOption;
	}
	
	
	@Override
	public String toString() {
		return "선택1 : " + spellOne + "\t선택2" + spellTwo + "\t승률 : " + itemWinRate + "\t픽률 : " + itemPickRate + "\t카운트 수 : " + itemCount + "\t카테고리 " + itemOption;
	}
}


public class ChampStartItem {
	static void spell() throws SQLException {
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
				ArrayList<StartItemCraw> myList2 = new ArrayList<StartItemCraw>();
				
				// jdbc connection 객체 생성
				DBmanager db = new DBmanager();
				Connection conn = null;
				PreparedStatement pstmt = null;
				String url = "https://lol.ps/ko/champ/1/statistics/?lane=2&region=0&tier=2&version=44";
				driver.get(url);
				try {
					conn = db.getConnection();
					
					Thread.sleep(3000); // 3. 페이지 로딩 대기 시간
					List<WebElement> list = null;
					
					//driver.findElements --> 전체 페이지에서 className "champ-content-box" 안에 item-list 찾아와라
					list = driver.findElements(By.className("champ-content-box").className("item-list"));
					//element --> champ-content-box의 세번째꺼 가져와
					// element -> 코어템 통계
					WebElement element = list.get(0);
					
					// list는 코어템 통계에 있는 item class 애들
					list = element.findElements(By.className("item"));
					
					for(WebElement we : list) {
						String sql = "insert into item_start_spell values(?, ?, ?, ?, ?, ?)";
						pstmt = conn.prepareStatement(sql);
						System.out.print("-");
						
						String[] imgName = new String[3];
						int idx = 0;
						for(WebElement itemImg : we.findElements(By.className("item-img"))) {
							imgName[idx] = itemImg.getAttribute("data-original-title");
							idx++;
						}
						
						//같은 class 녀석들 구분하려고 배열생성해서 for문으로 넣기
						String[] digit = new String[3];
						int idx2 = 0;
						for(WebElement dataDigit : we.findElements(By.className("data-digit"))) {
							digit[idx2] = dataDigit.getText();
							idx2++;
						}
						
						String spellOne = imgName[0];
						String spellTwo = imgName[1];
						String itemWinRate = digit[0];
						String itemPickRate = digit[1];
						String itemCount =  digit[2];
						String itemOption = we.findElement(By.xpath("/html/body/main/div[1]/div[2]/section[3]/div/div[1]/div[1]/h5")).getText();
						
						//데이터 베이스에 insert
						pstmt.setString(1, spellOne);
						pstmt.setString(2, spellTwo);
						pstmt.setString(3, itemWinRate);
						pstmt.setString(4, itemPickRate);
						pstmt.setString(5, itemCount);				
						pstmt.setString(6, itemOption);				
						pstmt.executeUpdate();	
						

						myList2.add(new StartItemCraw(spellOne, spellTwo, itemWinRate, itemPickRate, itemCount, itemOption));
					}
					
					
					System.out.println();
					for(int i = 0; i < myList2.size(); i++) {
						System.out.println(myList2.get(i));
					}
					
					System.out.println("스펠 통계 가져왔다");
				} catch (Exception e) {
					e.printStackTrace();
//					System.out.println("에러");
				} finally {
					conn.close();
					pstmt.close();
					driver.close(); // 5. 브라우저 종료
				}
	}
	
	static void startItem() throws SQLException {
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
				ArrayList<StartItemCraw> myList2 = new ArrayList<StartItemCraw>();
				
				// jdbc connection 객체 생성
				DBmanager db = new DBmanager();
				Connection conn = null;
				PreparedStatement pstmt = null;
				String url = "https://lol.ps/ko/champ/1/statistics/?lane=2&region=0&tier=2&version=44";
				driver.get(url);
				try {
					conn = db.getConnection();
					
					Thread.sleep(3000); // 3. 페이지 로딩 대기 시간
					List<WebElement> list = null;
					
					//driver.findElements --> 전체 페이지에서 className "champ-content-box" 안에 item-list 찾아와라
					list = driver.findElements(By.className("champ-content-box").className("item-list"));
					//element --> champ-content-box의 세번째꺼 가져와
					// element -> 코어템 통계
					WebElement element = list.get(1);
					
					// list는 코어템 통계에 있는 item class 애들
					list = element.findElements(By.className("item"));
					
					for(WebElement we : list) {
						String sql = "insert into item_start_spell values(?, ?, ?, ?, ?, ?)";
						pstmt = conn.prepareStatement(sql);
						System.out.print("-");
						
						String[] imgName = new String[3];
						int idx = 0;
						for(WebElement itemImg : we.findElements(By.className("item-img"))) {
							imgName[idx] = itemImg.getAttribute("data-original-title");
							idx++;
						}
						
						//같은 class 녀석들 구분하려고 배열생성해서 for문으로 넣기
						String[] digit = new String[3];
						int idx2 = 0;
						for(WebElement dataDigit : we.findElements(By.className("data-digit"))) {
							digit[idx2] = dataDigit.getText();
							idx2++;
						}
						
						String spellOne = imgName[0];
						String spellTwo = imgName[1];
						String itemWinRate = digit[0];
						String itemPickRate = digit[1];
						String itemCount =  digit[2];
						String itemOption = we.findElement(By.xpath("/html/body/main/div[1]/div[2]/section[3]/div/div[2]/div[1]/h5")).getText();
						
						//데이터 베이스에 insert
						pstmt.setString(1, spellOne);
						pstmt.setString(2, spellTwo);
						pstmt.setString(3, itemWinRate);
						pstmt.setString(4, itemPickRate);
						pstmt.setString(5, itemCount);				
						pstmt.setString(6, itemOption);				
						pstmt.executeUpdate();	
						

						myList2.add(new StartItemCraw(spellOne, spellTwo, itemWinRate, itemPickRate, itemCount, itemOption));
					}
					
					
					System.out.println();
					for(int i = 0; i < myList2.size(); i++) {
						System.out.println(myList2.get(i));
					}
					
					System.out.println("스타트아이템 통계 가져왔다");
				} catch (Exception e) {
					e.printStackTrace();
//					System.out.println("에러");
				} finally {
					conn.close();
					pstmt.close();
					driver.close(); // 5. 브라우저 종료
				}
	}
	
	static void shoes() throws SQLException {
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
				ArrayList<StartItemCraw> myList2 = new ArrayList<StartItemCraw>();
				
				// jdbc connection 객체 생성
				DBmanager db = new DBmanager();
				Connection conn = null;
				PreparedStatement pstmt = null;
				String url = "https://lol.ps/ko/champ/1/statistics/?lane=2&region=0&tier=2&version=44";
				driver.get(url);
				try {
					conn = db.getConnection();
					
					Thread.sleep(3000); // 3. 페이지 로딩 대기 시간
					List<WebElement> list = null;
					
					//driver.findElements --> 전체 페이지에서 className "champ-content-box" 안에 item-list 찾아와라
					list = driver.findElements(By.className("champ-content-box").className("item-list"));
					//element --> champ-content-box의 세번째꺼 가져와
					// element -> 코어템 통계
					WebElement element = list.get(5);
					
					// list는 코어템 통계에 있는 item class 애들
					list = element.findElements(By.className("item"));
					
					for(WebElement we : list) {
						String sql = "insert into item_start_spell values(?, ?, ?, ?, ?, ?)";
						pstmt = conn.prepareStatement(sql);
						System.out.print("-");
						
						String[] imgName = new String[3];
						int idx = 0;
						for(WebElement itemImg : we.findElements(By.className("item-img"))) {
							imgName[idx] = itemImg.getAttribute("data-original-title");
							idx++;
						}
						
						//같은 class 녀석들 구분하려고 배열생성해서 for문으로 넣기
						String[] digit = new String[3];
						int idx2 = 0;
						for(WebElement dataDigit : we.findElements(By.className("data-digit"))) {
							digit[idx2] = dataDigit.getText();
							idx2++;
						}
						
						String spellOne = imgName[0];
						String spellTwo = imgName[1];
						String itemWinRate = digit[0];
						String itemPickRate = digit[1];
						String itemCount =  digit[2];
						String itemOption = we.findElement(By.xpath("/html/body/main/div[1]/div[2]/section[3]/div/div[3]/div[1]/h5")).getText();
						
						//데이터 베이스에 insert
						pstmt.setString(1, spellOne);
						pstmt.setString(2, spellTwo);
						pstmt.setString(3, itemWinRate);
						pstmt.setString(4, itemPickRate);
						pstmt.setString(5, itemCount);				
						pstmt.setString(6, itemOption);				
						pstmt.executeUpdate();	
						

						myList2.add(new StartItemCraw(spellOne, spellTwo, itemWinRate, itemPickRate, itemCount, itemOption));
					}
					
					
					System.out.println();
					for(int i = 0; i < myList2.size(); i++) {
						System.out.println(myList2.get(i));
					}
					
					System.out.println("신발 통계 가져왔다");
				} catch (Exception e) {
					e.printStackTrace();
//					System.out.println("에러");
				} finally {
					conn.close();
					pstmt.close();
					driver.close(); // 5. 브라우저 종료
				}
	}
	
	public static void main(String[] args) throws SQLException {
		spell();
		startItem();
		shoes();
	}
}


