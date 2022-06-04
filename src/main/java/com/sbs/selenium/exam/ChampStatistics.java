package com.sbs.selenium.exam;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class WholeCraw {
	String champName;
	String champPosition;
	String champInfo;
	String champGameCount;
	String champWinRate;
	
	String itemOne;
	String itemTwo;
	String itemThree;
	String itemFour;
	String itemWinRate;
	String itemPickRate;
	String itemCount;
	String itemOption;
	
	String runeOne;
	String runeTwo;
	String runeThree;
	String runeFour;
	String runeFive;
	String runeSix;
	String runeWinRate;
	String runePickRate;
	
	String shardOne;
	String shardTwo;
	String shardThree;
	String shardWinRate;
	String shardPickRate;
	
	String skillOne;
	String skillTwo;
	String skillThree;
	String skillFour;
	String skillFive;
	String skillSix;
	String skillSeven;
	String skillEight;
	String skillNine;
	String skillTen;
	String skillEleven;
	String skillWinRate;
	String skillPickRate;
	String skillCount;
	
	String constructorName;
	
	
	//코어템 조합 통계, 룬 조합별 통계 생성자
	WholeCraw(String type, String champName, String champPosition, String itemOne, String itemTwo, String itemThree, String itemFour, 
			String itemWinRate, String itemPickRate, String itemCount, String itemOption) {
		if(type.equals("코어템조합")) {			
			this.constructorName = "코어템조합";
			this.champName = champName;
			this.champPosition = champPosition;
			this.itemOne = itemOne;
			this.itemTwo = itemTwo;
			this.itemThree = itemThree;
			this.itemFour = itemFour;
			this.itemWinRate = itemWinRate;
			this.itemPickRate = itemPickRate;
			this.itemCount = itemCount;
			this.itemOption = itemOption;
		} else if(type.equals("조합별룬")) {
			this.constructorName = "조합별룬";
			this.champName = champName;
			this.champPosition = champPosition;
			this.runeOne = itemOne;
			this.runeTwo = itemTwo;
			this.runeThree = itemThree;
			this.runeFour = itemFour;
			this.runeFive = itemWinRate;
			this.runeSix = itemPickRate;
			this.runeWinRate = itemCount;
			this.runePickRate = itemOption;
		} 
	}
	
	
	// 스펠, 스타트아이템, 신발 생성자
	WholeCraw(String type, String champName, String champPosition, String itemOne, String itemTwo, String itemWinRate, String itemPickRate, 
			String itemCount, String itemOption) {
		if(type.equals("스타트아이템")) {
			this.constructorName = "스타트아이템";
			this.champName = champName;
			this.champPosition = champPosition;
			this.itemOne = itemOne;
			this.itemTwo = itemTwo;
			this.itemWinRate = itemWinRate;
			this.itemPickRate = itemPickRate;
			this.itemCount = itemCount;
			this.itemOption = itemOption;
		}else if(type.equals("스킬마스터순서")) {
			this.constructorName = "스킬마스터순서";
			this.champName = champName;
			this.champPosition = champPosition;
			this.skillOne = itemOne;
			this.skillTwo = itemTwo;
			this.skillThree = itemWinRate;
			this.skillWinRate = itemPickRate;
			this.skillPickRate = itemCount;
			this.skillCount = itemOption;
			
		}
		
	}
	
	// 코어템 통계 생성자, 	// 룬파편 생성자
	WholeCraw(int type, String champName, String champPosition, String itemOne, String itemWinRate, String itemPickRate, String itemCount, 
			String itemOption) {
		if(type == 1) {
			this.constructorName = "코어템";
			this.champName = champName;
			this.champPosition = champPosition;
			this.itemOne = itemOne;
			this.itemWinRate = itemWinRate;
			this.itemPickRate = itemPickRate;
			this.itemCount = itemCount;
			this.itemOption = itemOption;
		}else if(type == 2) {
			this.constructorName = "룬파편조합";
			this.champName = champName;
			this.champPosition = champPosition;
			this.shardOne = itemOne;
			this.shardTwo = itemWinRate;
			this.shardThree = itemPickRate;
			this.shardWinRate = itemCount;
			this.shardPickRate = itemOption;
		}
	}
	
	// 상대 챔피언 생성자
	WholeCraw(String champName, String champPosition, String champInfo, String champGameCount, String champWinRate) {
		this.constructorName = "상대챔피언";
		this.champName = champName;
		this.champPosition = champPosition;
		this.champInfo = champInfo;
		this.champGameCount = champGameCount;
		this.champWinRate = champWinRate;
	}
	
	

	public WholeCraw(String champName, String champPosition, String skillOne, String skillTwo, String skillThree,
			String skillFour, String skillFive, String skillSix, String skillSeven, String skillEight, String skillNine,
			String skillTen, String skillEleven, String skillWinRate, String skillPickRate, String skillCount) {
		this.constructorName = "스킬순서";
		this.champName = champName;
		this.champPosition = champPosition;
		this.skillOne = skillOne;
		this.skillTwo = skillTwo;
		this.skillThree = skillThree;
		this.skillFour = skillFour;
		this.skillFive = skillFive;
		this.skillSix = skillSix;
		this.skillSeven = skillSeven;
		this.skillEight = skillEight;
		this.skillNine = skillNine; 
		this.skillTen = skillTen;
		this.skillEleven = skillEleven;
		this.skillWinRate = skillWinRate;
		this.skillPickRate = skillPickRate;
		this.skillCount = skillCount;
	}


	@Override
	public String toString() {
		switch(constructorName) {
		case "상대챔피언" :
			return 	"챔피언이름 : " + champName + "포지션 : " + champPosition +"\t상대챔피언 : " + champInfo + "\t게임 수 : " + champGameCount +
					"\t승률 : " + champWinRate;
			
		case "스타트아이템" :
			return 	"챔피언이름 : " + champName + "포지션 : " + champPosition + "\t선택1 : " + itemOne + "\t선택2 : " + itemTwo + 
					"\t승률 : " + itemWinRate +"\t픽률 : " + itemPickRate +  "\t카운트수 : " + itemCount +  "\t카테고리 : " + itemOption;   

		case "코어템" : 
			return 	"챔피언이름 : " + champName + "포지션 : " + champPosition + "\t아이템이름 : " + itemOne + "\t승률 : " + itemWinRate +
					"\t픽률 : " + itemPickRate +  "\t카운트수 : " + itemCount +  "\t카테고리 : " + itemOption;
			
		case "코어템조합" : 
			return	"챔피언이름 : " + champName + "포지션 : " + champPosition + "\t1코어 : " + itemOne + "\t2코어 : " + itemTwo + 
					"\t3코어 : " + itemThree + "\t4코어 : " + itemFour + "\t승률 : " + itemWinRate + 
					"\t픽률 : " + itemPickRate +  "\t카운트수 : " + itemCount +  "\t카테고리 : " + itemOption;
		
		case "스킬마스터순서" : 
			return "챔피언이름 : " + champName + "포지션 : " + champPosition + "\t스킬1 : " + skillOne + "\t스킬2 : " + skillTwo + "\t스킬3 : " + skillThree +  
					"\t승률 : " + skillWinRate + "\t픽률 : " + skillPickRate + "\t카운트수" + skillCount;
			
		case "스킬순서" : 
			return "챔피언이름 : " + champName + "포지션 : " + champPosition + "\t스킬1 : " + skillOne + "\t스킬2 : " + skillTwo + "\t스킬3 : " + skillThree +  
					skillFour + skillFive + skillSix + skillSeven + skillEight + skillNine + skillTen + 
					skillEleven + "\t승률 : " + skillWinRate + "\t픽률 : " + skillPickRate + "\t카운트수" + skillCount;
			
		case "조합별룬" :
			return "챔피언이름 : " + champName + "포지션 : " + champPosition + "룬1 : " + runeOne + "\t룬2 : " + runeTwo + "\t룬3 : " + runeThree + "\t룬4 : " + runeFour +
					"\t룬5 : " + runeFive + "\t룬6 : " + runeSix + "\t승률 : " + runeWinRate + "\t픽률 : " + runePickRate;
		
		case "룬파편조합" :
			return "챔피언이름 : " + champName + "포지션 : " + champPosition + "\t파편1 : " + shardOne + "\t파편2 : " + shardTwo + "\t파편3 : " + shardThree +  
					"\t승률 : " + shardWinRate + "\t픽률 : " + shardPickRate;
			
		default : 
			return "생성자 잘못 호출한듯?";
		}
		
	}
}


public class ChampStatistics {
	String url;
	
	public void setUrl(int i, int line) {
		this.url = "https://lol.ps/ko/champ/"+i+"/statistics/?lane=" + line + " ";
	}
	
	public void setUrl(int i, String where) {
		if(where.equals("기본정보")) {
			this.url = "https://lol.ps/ko/champ/"+i+"/";
		}else if(where.equals("패치히스토리")) {
			this.url = "https://lol.ps/ko/champ/"+i+"/patch/";
		}
	}
	
	
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
	
	void twoCoreCombie(ChromeDriver driver, DBmanager db, Connection conn, PreparedStatement pstmt, int line) throws SQLException {
		
		// 크롤링 해서 값 넣어줄 배열 생성
		ArrayList<WholeCraw> myList2 = new ArrayList<WholeCraw>();
		
		driver.get(url);
		try {
			List<WebElement> list = null;
			
			//driver.findElements --> 전체 페이지에서 className "champ-content-box" 안에 item-list 찾아와라
			list = driver.findElements(By.className("champ-content-box").className("item-list"));
			//element --> champ-content-box의 세번째꺼 가져와
			// element -> 코어템 통계
			WebElement element = list.get(6);
			
			// list는 코어템 통계에 있는 item class 애들
			list = element.findElements(By.className("item"));
			
			String[] value = { "탑", "정글", "미드", "원딜", "서폿" };
			
			
			for(WebElement we : list) {
				System.out.print("-");
				
				String[] imgName = new String[4];
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
			
				String type = "코어템조합";
				String champName = we.findElement(By.xpath("/html/body/main/div[1]/section/div[1]/h3")).getText();
				String position = value[line];
				String itemOne = imgName[0];
				String itemTwo = imgName[1];
				String itemThree = imgName[2];
				String itemFour = imgName[3];
				String itemWinRate = digit[0];
				String itemPickRate = digit[1];
				String itemCount =  digit[2];
				String itemOption = we.findElement(By.xpath("/html/body/main/div[1]/div[2]/section[5]/div/div[1]/div[1]/h5")).getText();
				
				//데이터 베이스에 insert
				pstmt.setString(1, champName);
				pstmt.setString(2, position);
				pstmt.setString(3, itemOption);
				pstmt.setString(4, itemOne);
				pstmt.setString(5, itemTwo);
				pstmt.setString(6, itemThree);
				pstmt.setString(7, itemFour);
				pstmt.setDouble(8, Double.parseDouble(itemWinRate));				
				pstmt.setDouble(9, Double.parseDouble(itemPickRate));				
				pstmt.setString(10, itemCount);
				pstmt.executeUpdate();	
				
				myList2.add(new WholeCraw(type, champName, position, itemOne, itemTwo, itemThree, itemFour, itemWinRate, itemPickRate, itemCount, itemOption));
			}
			
			
			System.out.println();
			for(int i = 0; i < myList2.size(); i++) {
				System.out.println(myList2.get(i));
			}
			
			System.out.println("2코어 조합 가져왔다");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
		
	void threeCoreCombie(ChromeDriver driver, DBmanager db, Connection conn, PreparedStatement pstmt, int line) throws SQLException {
		
		// 크롤링 해서 값 넣어줄 배열 생성
		ArrayList<WholeCraw> myList2 = new ArrayList<WholeCraw>();
		
		driver.get(url);
		try {
			List<WebElement> list = null;
			
			//driver.findElements --> 전체 페이지에서 className "champ-content-box" 안에 item-list 찾아와라
			list = driver.findElements(By.className("champ-content-box").className("item-list"));
			//element --> champ-content-box의 세번째꺼 가져와
			// element -> 코어템 통계
			WebElement element = list.get(7);
			
			// list는 코어템 통계에 있는 item class 애들
			list = element.findElements(By.className("item"));
			
			String[] value = { "탑", "정글", "미드", "원딜", "서폿" };

			for(WebElement we : list) {
				System.out.print("-");
				
				String[] imgName = new String[4];
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
				String type = "코어템조합";
				String champName = we.findElement(By.xpath("/html/body/main/div[1]/section/div[1]/h3")).getText();
				String position = value[line];
				String itemOne = imgName[0];
				String itemTwo = imgName[1];
				String itemThree = imgName[2];
				String itemFour = imgName[3];
				String itemWinRate = digit[0];
				String itemPickRate = digit[1];
				String itemCount =  digit[2];
				String itemOption = we.findElement(By.xpath("/html/body/main/div[1]/div[2]/section[5]/div/div[2]/div[1]/h5")).getText();
				
				//데이터 베이스에 insert
				pstmt.setString(1, champName);
				pstmt.setString(2, position);
				pstmt.setString(3, itemOption);
				pstmt.setString(4, itemOne);
				pstmt.setString(5, itemTwo);
				pstmt.setString(6, itemThree);
				pstmt.setString(7, itemFour);
				pstmt.setDouble(8, Double.parseDouble(itemWinRate));				
				pstmt.setDouble(9, Double.parseDouble(itemPickRate));				
				pstmt.setString(10, itemCount);
				pstmt.executeUpdate();	
		

				myList2.add(new WholeCraw(type, champName, position, itemOne, itemTwo, itemThree, itemFour, itemWinRate, itemPickRate, itemCount, itemOption));
			}
			
			
			System.out.println();
			for(int i = 0; i < myList2.size(); i++) {
				System.out.println(myList2.get(i));
			}
			
			System.out.println("3코어 조합 가져왔다");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
		
	void fourCoreCombie(ChromeDriver driver, DBmanager db, Connection conn, PreparedStatement pstmt, int line) throws SQLException {
		
		// 크롤링 해서 값 넣어줄 배열 생성
		ArrayList<WholeCraw> myList2 = new ArrayList<WholeCraw>();
		
		driver.get(url);
		try {				
			List<WebElement> list = null;
			
			//driver.findElements --> 전체 페이지에서 className "champ-content-box" 안에 item-list 찾아와라
			list = driver.findElements(By.className("champ-content-box").className("item-list"));
			//element --> champ-content-box의 세번째꺼 가져와
			// element -> 코어템 통계
			WebElement element = list.get(8);
			
			// list는 코어템 통계에 있는 item class 애들
			list = element.findElements(By.className("item"));
			
			String[] value = { "탑", "정글", "미드", "원딜", "서폿" };
			for(WebElement we : list) {
				System.out.print("-");
				
				String[] imgName = new String[4];
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
				String type = "코어템조합";
				String champName = we.findElement(By.xpath("/html/body/main/div[1]/section/div[1]/h3")).getText();
				String position = value[line];
				String itemOne = imgName[0];
				String itemTwo = imgName[1];
				String itemThree = imgName[2];
				String itemFour = imgName[3];
				String itemWinRate = digit[0];
				String itemPickRate = digit[1];
				String itemCount =  digit[2];
				String itemOption = we.findElement(By.xpath("/html/body/main/div[1]/div[2]/section[5]/div/div[3]/div[1]/h5")).getText();
				
				//데이터 베이스에 insert
				pstmt.setString(1, champName);
				pstmt.setString(2, position);
				pstmt.setString(3, itemOption);
				pstmt.setString(4, itemOne);
				pstmt.setString(5, itemTwo);
				pstmt.setString(6, itemThree);
				pstmt.setString(7, itemFour);
				pstmt.setDouble(8, Double.parseDouble(itemWinRate));				
				pstmt.setDouble(9, Double.parseDouble(itemPickRate));				
				pstmt.setString(10, itemCount);
				pstmt.executeUpdate();	
				

				myList2.add(new WholeCraw(type, champName, position, itemOne, itemTwo, itemThree, itemFour,
						itemWinRate, itemPickRate, itemCount, itemOption));
			}
			
			
			System.out.println();
			for(int i = 0; i < myList2.size(); i++) {
				System.out.println(myList2.get(i));
			}
			
			System.out.println("4코어 조합 가져왔다");
		} catch (Exception e) {
			e.printStackTrace();
//						System.out.println("에러");
		} 
		
	}
	
	
	void spell(ChromeDriver driver, DBmanager db, Connection conn, PreparedStatement pstmt, int line) throws SQLException {
		// 크롤링 해서 값 넣어줄 배열 생성
		ArrayList<WholeCraw> myList2 = new ArrayList<WholeCraw>();
		
		try {
			List<WebElement> list = null;

			driver.get(url);
			//driver.findElements --> 전체 페이지에서 className "champ-content-box" 안에 item-list 찾아와라
			list = driver.findElements(By.className("champ-content-box").className("item-list"));
			//element --> champ-content-box의 세번째꺼 가져와
			// element -> 코어템 통계
			WebElement element = list.get(0);
			
			// list는 코어템 통계에 있는 item class 애들
			list = element.findElements(By.className("item"));
			
			String[] value = { "탑", "정글", "미드", "원딜", "서폿" };
			
			for(WebElement we : list) {
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
				
				String type = "스타트아이템";
				String champName = we.findElement(By.xpath("/html/body/main/div[1]/section/div[1]/h3")).getText();
				String position = value[line];
				String itemOne = imgName[0];
				String itemTwo = imgName[1];
				String itemWinRate = digit[0];
				String itemPickRate = digit[1];
				String itemCount =  digit[2];
				String itemOption = we.findElement(By.xpath("/html/body/main/div[1]/div[2]/section[3]/div/div[1]/div[1]/h5")).getText();
				
				//데이터 베이스에 insert
				pstmt.setString(1, champName);
				pstmt.setString(2, position);
				pstmt.setString(3, itemOne);
				pstmt.setString(4, itemTwo);
				pstmt.setDouble(5, Double.parseDouble(itemWinRate));				
				pstmt.setDouble(6, Double.parseDouble(itemPickRate));				
				pstmt.setString(7, itemCount);
				pstmt.setString(8, itemOption);
				pstmt.executeUpdate();	
				

				myList2.add(new WholeCraw(type, champName, position, itemOne, itemTwo, itemWinRate, itemPickRate, itemCount, itemOption));
			}
			
			
			System.out.println();
			for(int i = 0; i < myList2.size(); i++) {
				System.out.println(myList2.get(i));
			}			
			System.out.println("스펠 통계 가져왔다");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	void startItem(ChromeDriver driver, DBmanager db, Connection conn, PreparedStatement pstmt, int line) throws SQLException {
		// 크롤링 해서 값 넣어줄 배열 생성
		ArrayList<WholeCraw> myList2 = new ArrayList<WholeCraw>();
		
		try {
			List<WebElement> list = null;

			driver.get(url);
			//driver.findElements --> 전체 페이지에서 className "champ-content-box" 안에 item-list 찾아와라
			list = driver.findElements(By.className("champ-content-box").className("item-list"));
			//element --> champ-content-box의 세번째꺼 가져와
			// element -> 코어템 통계
			WebElement element = list.get(1);
			
			// list는 코어템 통계에 있는 item class 애들
			list = element.findElements(By.className("item"));
			String[] value = { "탑", "정글", "미드", "원딜", "서폿" };
			for(WebElement we : list) {
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
				String type = "스타트아이템";
				String champName = we.findElement(By.xpath("/html/body/main/div[1]/section/div[1]/h3")).getText();
				String position = value[line];
				String itemOne = imgName[0];
				String itemTwo = imgName[1];
				String itemWinRate = digit[0];
				String itemPickRate = digit[1];
				String itemCount =  digit[2];
				String itemOption = we.findElement(By.xpath("/html/body/main/div[1]/div[2]/section[3]/div/div[2]/div[1]/h5")).getText();
				
				//데이터 베이스에 insert
				pstmt.setString(1, champName);
				pstmt.setString(2, position);
				pstmt.setString(3, itemOne);
				pstmt.setString(4, itemTwo);
				pstmt.setDouble(5, Double.parseDouble(itemWinRate));				
				pstmt.setDouble(6, Double.parseDouble(itemPickRate));				
				pstmt.setString(7, itemCount);
				pstmt.setString(8, itemOption);
				pstmt.executeUpdate();	
				

				myList2.add(new WholeCraw(type, champName, position, itemOne, itemTwo, itemWinRate, itemPickRate, itemCount, itemOption));
			}
			
			
			System.out.println();
			for(int i = 0; i < myList2.size(); i++) {
				System.out.println(myList2.get(i));
			}			
			System.out.println("스타트아이템 통계 가져왔다");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	void shoes(ChromeDriver driver, DBmanager db, Connection conn, PreparedStatement pstmt, int line) throws SQLException {
		// 크롤링 해서 값 넣어줄 배열 생성
		ArrayList<WholeCraw> myList2 = new ArrayList<WholeCraw>();
		
		try {
			List<WebElement> list = null;

			driver.get(url);
			//driver.findElements --> 전체 페이지에서 className "champ-content-box" 안에 item-list 찾아와라
			list = driver.findElements(By.className("champ-content-box").className("item-list"));
			//element --> champ-content-box의 세번째꺼 가져와
			// element -> 코어템 통계
			WebElement element = list.get(2);
			
			// list는 코어템 통계에 있는 item class 애들
			list = element.findElements(By.className("item"));
			
			String[] value = { "탑", "정글", "미드", "원딜", "서폿" };
			
			for(WebElement we : list) {
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
				
				String type = "스타트아이템";
				String champName = we.findElement(By.xpath("/html/body/main/div[1]/section/div[1]/h3")).getText();
				String position = value[line];
				String itemOne = imgName[0];
				String itemTwo = imgName[1];
				String itemWinRate = digit[0];
				String itemPickRate = digit[1];
				String itemCount =  digit[2];
				String itemOption = we.findElement(By.xpath("/html/body/main/div[1]/div[2]/section[3]/div/div[3]/div[1]/h5")).getText();
				
				//데이터 베이스에 insert
				pstmt.setString(1, champName);
				pstmt.setString(2, position);
				pstmt.setString(3, itemOne);
				pstmt.setString(4, itemTwo);
				pstmt.setDouble(5, Double.parseDouble(itemWinRate));				
				pstmt.setDouble(6, Double.parseDouble(itemPickRate));				
				pstmt.setString(7, itemCount);
				pstmt.setString(8, itemOption);
				pstmt.executeUpdate();	
				

				myList2.add(new WholeCraw(type, champName, position, itemOne, itemTwo, itemWinRate, itemPickRate, itemCount, itemOption));
			}
			
			
			System.out.println();
			for(int i = 0; i < myList2.size(); i++) {
				System.out.println(myList2.get(i));
			}			
			System.out.println("신발 통계 가져왔다");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	void oneCore(ChromeDriver driver, DBmanager db, Connection conn, PreparedStatement pstmt, int line) throws SQLException {
		// 크롤링 해서 값 넣어줄 배열 생성
		ArrayList<WholeCraw> myList2 = new ArrayList<WholeCraw>();
		
		try {
			List<WebElement> list = null;

			driver.get(url);
			//driver.findElements --> 전체 페이지에서 className "champ-content-box" 안에 item-list 찾아와라
			list = driver.findElements(By.className("champ-content-box").className("item-list"));
			//element --> champ-content-box의 세번째꺼 가져와
			// element -> 코어템 통계
			WebElement element = list.get(3);
			
			// list는 코어템 통계에 있는 item class 애들
			list = element.findElements(By.className("item"));
			
			String[] value = { "탑", "정글", "미드", "원딜", "서폿" };
			
			for(WebElement we : list) {
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
				
				int type = 1;
				String champName = we.findElement(By.xpath("/html/body/main/div[1]/section/div[1]/h3")).getText();
				String position = value[line];
				String itemOne = imgName[0];
				String itemWinRate = digit[0];
				String itemPickRate = digit[1];
				String itemCount =  digit[2];
				String itemOption = we.findElement(By.xpath("/html/body/main/div[1]/div[2]/section[4]/div/div[1]/div[1]/h5")).getText();
				
				//데이터 베이스에 insert
				pstmt.setString(1, champName);
				pstmt.setString(2, position);
				pstmt.setString(3, itemOption);				
				pstmt.setString(4, itemOne);
				pstmt.setDouble(5, Double.parseDouble(itemWinRate));				
				pstmt.setDouble(6, Double.parseDouble(itemPickRate));				
				pstmt.setString(7, itemCount);
				pstmt.executeUpdate();	
//				

				myList2.add(new WholeCraw(type, champName, position, itemOne, itemWinRate, itemPickRate, itemCount, itemOption));
			}
			
			
			System.out.println();
			for(int i = 0; i < myList2.size(); i++) {
				System.out.println(myList2.get(i));
			}			
			System.out.println("1코어 통계 가져왔다");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	void twoCore(ChromeDriver driver, DBmanager db, Connection conn, PreparedStatement pstmt, int line) throws SQLException {
		// 크롤링 해서 값 넣어줄 배열 생성
		ArrayList<WholeCraw> myList2 = new ArrayList<WholeCraw>();
		
		try {
			List<WebElement> list = null;

			driver.get(url);
			//driver.findElements --> 전체 페이지에서 className "champ-content-box" 안에 item-list 찾아와라
			list = driver.findElements(By.className("champ-content-box").className("item-list"));
			//element --> champ-content-box의 세번째꺼 가져와
			// element -> 코어템 통계
			WebElement element = list.get(4);
			
			// list는 코어템 통계에 있는 item class 애들
			list = element.findElements(By.className("item"));
			
			String[] value = { "탑", "정글", "미드", "원딜", "서폿" };
			
			for(WebElement we : list) {
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
				int type = 1;
				String champName = we.findElement(By.xpath("/html/body/main/div[1]/section/div[1]/h3")).getText();
				String position = value[line];
				String itemOne = imgName[0];
				String itemWinRate = digit[0];
				String itemPickRate = digit[1];
				String itemCount =  digit[2];
				String itemOption = we.findElement(By.xpath("/html/body/main/div[1]/div[2]/section[4]/div/div[2]/div[1]/h5")).getText();
				
				//데이터 베이스에 insert
				pstmt.setString(1, champName);
				pstmt.setString(2, position);
				pstmt.setString(3, itemOption);				
				pstmt.setString(4, itemOne);
				pstmt.setDouble(5, Double.parseDouble(itemWinRate));				
				pstmt.setDouble(6, Double.parseDouble(itemPickRate));				
				pstmt.setString(7, itemCount);
				pstmt.executeUpdate();	
				

				myList2.add(new WholeCraw(type, champName, position, itemOne, itemWinRate, itemPickRate, itemCount, itemOption));
			}
			
			
			System.out.println();
			for(int i = 0; i < myList2.size(); i++) {
				System.out.println(myList2.get(i));
			}			
			System.out.println("2코어 통계 가져왔다");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	void threeCore(ChromeDriver driver, DBmanager db, Connection conn, PreparedStatement pstmt, int line) throws SQLException {
		// 크롤링 해서 값 넣어줄 배열 생성
		ArrayList<WholeCraw> myList2 = new ArrayList<WholeCraw>();
		
		try {
			List<WebElement> list = null;

			driver.get(url);
			//driver.findElements --> 전체 페이지에서 className "champ-content-box" 안에 item-list 찾아와라
			list = driver.findElements(By.className("champ-content-box").className("item-list"));
			//element --> champ-content-box의 세번째꺼 가져와
			// element -> 코어템 통계
			WebElement element = list.get(5);
			
			// list는 코어템 통계에 있는 item class 애들
			list = element.findElements(By.className("item"));
			
			String[] value = { "탑", "정글", "미드", "원딜", "서폿" };
			
			for(WebElement we : list) {
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
				
				int type = 1;
				String champName = we.findElement(By.xpath("/html/body/main/div[1]/section/div[1]/h3")).getText();
				String position = value[line];
				String itemOne = imgName[0];
				String itemWinRate = digit[0];
				String itemPickRate = digit[1];
				String itemCount =  digit[2];
				String itemOption = we.findElement(By.xpath("/html/body/main/div[1]/div[2]/section[4]/div/div[3]/div[1]/h5")).getText();
				
				//데이터 베이스에 insert
				pstmt.setString(1, champName);
				pstmt.setString(2, position);
				pstmt.setString(3, itemOption);				
				pstmt.setString(4, itemOne);
				pstmt.setDouble(5, Double.parseDouble(itemWinRate));				
				pstmt.setDouble(6, Double.parseDouble(itemPickRate));				
				pstmt.setString(7, itemCount);
				pstmt.executeUpdate();	
				

				myList2.add(new WholeCraw(type, champName, position, itemOne, itemWinRate, itemPickRate, itemCount, itemOption));
			}
			
			
			System.out.println();
			for(int i = 0; i < myList2.size(); i++) {
				System.out.println(myList2.get(i));
			}			
			System.out.println("3코어 통계 가져왔다");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	void vsChamp(ChromeDriver driver, DBmanager db, Connection conn, PreparedStatement pstmt, int line){
		ArrayList<WholeCraw> myList2 = new ArrayList<WholeCraw>();

		driver.get(url);
		try {
			List<WebElement> list = null;
			list = driver.findElements(By.className("champ"));
			
			String[] value = { "탑", "정글", "미드", "원딜", "서폿" };
			
			for(WebElement we : list) {
				System.out.print("-");
				String champName = we.findElement(By.xpath("/html/body/main/div[1]/section/div[1]/h3")).getText();				
				String position = value[line];
				String champInfo = we.findElement(By.className("champ-info")).getText();				
				String champWinRate = we.getText();
				String champGameCount = we.findElement(By.className("champ-gamecount")).getText();
				champWinRate = champWinRate.substring(champWinRate.indexOf("게임")+3);
				
				//insert into 
				pstmt.setString(1, champName);
				pstmt.setString(2, position);
				pstmt.setString(3, champInfo);
				pstmt.setDouble(4, Double.parseDouble(champWinRate.substring(0,champWinRate.length()-1)));
				pstmt.setString(5, champGameCount);
				pstmt.executeUpdate();
				myList2.add(new WholeCraw(champName, position, champInfo, champGameCount, champWinRate));
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
		
	public void runeCombie(ChromeDriver driver, DBmanager db, Connection conn, PreparedStatement pstmt, int line) throws SQLException {
		// 크롤링 해서 값 넣어줄 배열 생성
		ArrayList<WholeCraw> myList2 = new ArrayList<WholeCraw>();

		driver.get(url);
		try {
			List<WebElement> list = null;
			
			//driver.findElements --> 전체 페이지에서 className "champ-content-box" 안에 item-list 찾아와라
			list = driver.findElements(By.className("champ-content-box").className("item-list"));
			//element --> champ-content-box의 세번째꺼 가져와
			// element -> 코어템 통계
			WebElement element = list.get(9);
			
			// list는 코어템 통계에 있는 item class 애들
			list = element.findElements(By.className("item"));
			
			String[] value = { "탑", "정글", "미드", "원딜", "서폿" };
			
			for(WebElement we : list) {
				System.out.print("-");
				
				String[] imgName = new String[6];
				int idx = 0;
				for(WebElement itemImg : we.findElements(By.className("rune-img"))) {
					imgName[idx] = itemImg.getAttribute("data-original-title");
					idx++;
				}
				
				//같은 class 녀석들 구분하려고 배열생성해서 for문으로 넣기
				String[] arr = new String[3];
				int idx2 = 0;
				for(WebElement dataDigit : we.findElements(By.className("data-digit"))) {
					arr[idx2] = dataDigit.getText();
					idx2++;
				}
				String type = "조합별룬";
				String champName = we.findElement(By.xpath("/html/body/main/div[1]/section/div[1]/h3")).getText();
				String position = value[line];
				String runeOne = imgName[0];
				String runeTwo = imgName[1];
				String runeThree = imgName[2];
				String runeFour = imgName[3];
				String runeFive = imgName[4];
				String runeSix = imgName[5];
				
				String runeWinRate = arr[0];
				String runePickRate = arr[1];
				
				//데이터 베이스에 insert				
				pstmt.setString(1, champName);
				pstmt.setString(2, position);
				pstmt.setString(3, runeOne);
				pstmt.setString(4, runeTwo);
				pstmt.setString(5, runeThree);
				pstmt.setString(6, runeFour);				
				pstmt.setString(7, runeFive);				
				pstmt.setString(8, runeSix);				
				pstmt.setDouble(9, Double.parseDouble(runeWinRate));				
				pstmt.setDouble(10, Double.parseDouble(runePickRate));				
				pstmt.executeUpdate();	
				
				myList2.add(new WholeCraw(type, champName, position, runeOne, runeTwo, runeThree, runeFour, runeFive, runeSix, runeWinRate, runePickRate));
			}
			
			
			System.out.println();
			for(int i = 0; i < myList2.size(); i++) {
				System.out.println(myList2.get(i));
			}
			
			System.out.println("룬 조합별 통계 가져왔다");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void runeShards(ChromeDriver driver, DBmanager db, Connection conn, PreparedStatement pstmt, int line) throws SQLException {
		// 크롤링 해서 값 넣어줄 배열 생성
		ArrayList<WholeCraw> myList2 = new ArrayList<WholeCraw>();
		
		driver.get(url);
		try {
			List<WebElement> list = null;
			
			//driver.findElements --> 전체 페이지에서 className "champ-content-box" 안에 item-list 찾아와라
			list = driver.findElements(By.className("champ-content-box").className("item-list"));
			//element --> champ-content-box의 세번째꺼 가져와
			// element -> 코어템 통계
			WebElement element = list.get(10);
			
			// list는 코어템 통계에 있는 item class 애들
			list = element.findElements(By.className("item"));
			
			String[] value = { "탑", "정글", "미드", "원딜", "서폿" };
			
			for(WebElement we : list) {
				System.out.print("-");
				
				String[] imgName = new String[3];
				int idx = 0;
				for(WebElement itemImg : we.findElements(By.className("rune-img"))) {
					imgName[idx] = itemImg.getAttribute("data-content");
					idx++;
				}
				
				//같은 class 녀석들 구분하려고 배열생성해서 for문으로 넣기
				String[] arr = new String[3];
				int idx2 = 0;
				for(WebElement dataDigit : we.findElements(By.className("data-digit"))) {
					arr[idx2] = dataDigit.getText();
					idx2++;
				}
				
				int type = 2;
				String champName = we.findElement(By.xpath("/html/body/main/div[1]/section/div[1]/h3")).getText();
				String position = value[line];
				String shardOne = imgName[0];
				String shardTwo = imgName[1];
				String shardThree = imgName[2];
				String shardWinRate = arr[0];
				String shardPickRate = arr[1];
				
				//데이터 베이스에 insert
				pstmt.setString(1, champName);
				pstmt.setString(2, position);
				pstmt.setString(3, shardOne);
				pstmt.setString(4, shardTwo);
				pstmt.setString(5, shardThree);				
				pstmt.setDouble(6, Double.parseDouble(shardWinRate));				
				pstmt.setDouble(7, Double.parseDouble(shardPickRate));				
				pstmt.executeUpdate();	
					
				myList2.add(new WholeCraw(type, champName, position, shardOne, shardTwo, shardThree, shardWinRate, shardPickRate));
			}
			
			
			System.out.println();
			for(int i = 0; i < myList2.size(); i++) {
				System.out.println(myList2.get(i));
			}
			
			System.out.println("룬 파편 통계 가져왔다");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	void skillMaster(ChromeDriver driver, DBmanager db, Connection conn, PreparedStatement pstmt, int line) throws SQLException {
		// 크롤링 해서 값 넣어줄 배열 생성
		ArrayList<WholeCraw> myList2 = new ArrayList<WholeCraw>();
		
		driver.get(url);
		try {
			List<WebElement> list = null;
			
			list = driver.findElements(By.id("skill_master_wrap"));
			WebElement element = list.get(0);
			list = element.findElements(By.className("item"));
			
			String[] value = { "탑", "정글", "미드", "원딜", "서폿" };
			
			
			for(WebElement we : list) {
				System.out.print("-");
				
				String[] imgName = new String[11];
				int idx = 0;
				for(WebElement itemImg : we.findElements(By.className("skill-imgbox"))) {
					imgName[idx] = itemImg.getAttribute("data-original-title");
					idx++;
				}
				
				String[] dataDigit = new String[3];
				int idx2 = 0;
				for(WebElement datadigit : we.findElements(By.className("data-digit"))) {
					dataDigit[idx2] = datadigit.getText();
					idx2++;
				}
				
				
				String type = "스킬마스터순서";
				String champName = we.findElement(By.xpath("/html/body/main/div[1]/section/div[1]/h3")).getText();
				String position = value[line];
				String skillOne = imgName[0];
				String skillTwo = imgName[1];
				String skillThree = imgName[2];
				String skillWinRate = dataDigit[0];
				String skillPickRate = dataDigit[1];
				String skillCount = dataDigit[2];
				
				if(champName.equals("브라움")) {
					skillOne = "동상(브라움)";
				} else if (champName.equals("애니비아")) {
					skillOne = "동상(애니비아)";
				}
				
				//데이터 베이스에 insert
				pstmt.setString(1, champName);
				pstmt.setString(2, position);
				pstmt.setString(3, skillOne);
				pstmt.setString(4, skillTwo);
				pstmt.setString(5, skillThree);				
				pstmt.setDouble(6, Double.parseDouble(skillWinRate));				
				pstmt.setDouble(7, Double.parseDouble(skillPickRate));				
				pstmt.setString(8, skillCount);				
				pstmt.executeUpdate();	
					
				myList2.add(new WholeCraw(type, champName, position, skillOne, skillTwo, skillThree, skillWinRate, skillPickRate, skillCount));
			}
			
			
			System.out.println();
			for(int i = 0; i < myList2.size(); i++) {
				System.out.println(myList2.get(i));
			}
			
			System.out.println("스킬마스터순서 통계 가져왔다");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	void skillTo3(ChromeDriver driver, DBmanager db, Connection conn, PreparedStatement pstmt, int line) throws SQLException {
		// 크롤링 해서 값 넣어줄 배열 생성
		ArrayList<WholeCraw> myList2 = new ArrayList<WholeCraw>();
		
		driver.get(url);
		try {
			List<WebElement> list = null;
			
			list = driver.findElements(By.id("skill_lv3_wrap"));
			WebElement element = list.get(0);
			list = element.findElements(By.className("item"));
			
			String[] value = { "탑", "정글", "미드", "원딜", "서폿" };
			
			for(WebElement we : list) {
				System.out.print("-");
				
				String[] imgName = new String[11];
				int idx = 0;
				for(WebElement itemImg : we.findElements(By.className("skill-imgbox"))) {
					imgName[idx] = itemImg.getAttribute("data-original-title");
					idx++;
				}
				
				String[] dataDigit = new String[3];
				int idx2 = 0;
				for(WebElement datadigit : we.findElements(By.className("data-digit"))) {
					dataDigit[idx2] = datadigit.getText();
					idx2++;
				}
				
				String champName = we.findElement(By.xpath("/html/body/main/div[1]/section/div[1]/h3")).getText();
			
				for(int i = 0; i < 11; i++) {
					
					if(imgName[i] == null ) {
						break;
					}
					
					
					if(champName.equals("브라움") && imgName[i].equals("동상")) {
						imgName[i] = "동상(브라움)";
					} else if(champName.equals("애니비아") && imgName[i].equals("동상")) {
						imgName[i] = "동상(애니비아)";
					}
				}
				
				String position = value[line];
				int toWhatLv = 3;
				String skillOne = imgName[0];
				String skillTwo = imgName[1];
				String skillThree = imgName[2];
				String skillFour = imgName[3];
				String skillFive = imgName[4];
				String skillSix = imgName[5];
				String skillSeven = imgName[6];
				String skillEight = imgName[7];
				String skillNine = imgName[8];
				String skillTen = imgName[9];
				String skillEleven = imgName[10];
				String skillWinRate = dataDigit[0];
				String skillPickRate = dataDigit[1];
				String skillCount = dataDigit[2];
				
				
				//데이터 베이스에 insert
				pstmt.setString(1, champName);
				pstmt.setString(2, position);
				pstmt.setString(3, skillOne);
				pstmt.setString(4, skillTwo);
				pstmt.setString(5, skillThree);	
				pstmt.setString(6, skillFour);				
				pstmt.setString(7, skillFive);	
				pstmt.setString(8, skillSix);				
				pstmt.setString(9, skillSeven);				
				pstmt.setString(10, skillEight);				
				pstmt.setString(11, skillNine);				
				pstmt.setString(12, skillTen);				
				pstmt.setString(13, skillEleven);				
				pstmt.setDouble(14, Double.parseDouble(skillWinRate));				
				pstmt.setDouble(15, Double.parseDouble(skillPickRate));				
				pstmt.setString(16, skillCount);				
				pstmt.setInt(17, toWhatLv);				
				pstmt.executeUpdate();	
			
				myList2.add(new WholeCraw(champName, position, skillOne, skillTwo, skillThree, skillFour, skillFive, skillSix, 
						skillSeven, skillEight, skillNine, skillTen, skillEleven, skillWinRate, skillPickRate, skillCount));
			}
			
			
			System.out.println();
			for(int i = 0; i < myList2.size(); i++) {
				System.out.println(myList2.get(i));
			}
			
			System.out.println("스킬순서 통계 가져왔다");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	void skillTo6(ChromeDriver driver, PreparedStatement pstmt, int line, JavascriptExecutor js) throws SQLException {
		// 크롤링 해서 값 넣어줄 배열 생성
		ArrayList<WholeCraw> myList2 = new ArrayList<WholeCraw>();
		
		driver.get(url);
		try {
			//클릭 이벤트 일으키기
			List<WebElement> list = null;
			list = driver.findElements(By.className("levels"));
			WebElement element = list.get(0);
			WebElement button = element.findElement(By.id("level-6-tab"));
			js.executeScript("arguments[0].click()", button);

			//skill_lv6 크롤링
			List<WebElement> skill_lv6 = null;
			skill_lv6 = driver.findElements(By.id("skill_lv6_wrap"));
			WebElement skill = skill_lv6.get(0);
			skill_lv6 = skill.findElements(By.className("item"));
			
			String[] value = { "탑", "정글", "미드", "원딜", "서폿" };
			
			for(WebElement we : skill_lv6) {
				System.out.print("-");
				
				String[] imgName = new String[11];
				int idx = 0;
				for(WebElement itemImg : we.findElements(By.className("skill-imgbox"))) {
					imgName[idx] = itemImg.getAttribute("data-original-title");
					idx++;
				}
				
				String[] dataDigit = new String[3];
				int idx2 = 0;
				for(WebElement datadigit : we.findElements(By.className("data-digit"))) {
					dataDigit[idx2] = datadigit.getText();
					idx2++;
				}
				
				String champName = we.findElement(By.xpath("/html/body/main/div[1]/section/div[1]/h3")).getText();
				
				for(int i = 0; i < 11; i++) {
					
					if(imgName[i] == null ) {
						break;
					}
					
					
					if(champName.equals("브라움") && imgName[i].equals("동상")) {
						imgName[i] = "동상(브라움)";
					} else if(champName.equals("애니비아") && imgName[i].equals("동상")) {
						imgName[i] = "동상(애니비아)";
					}
				}
				
				if(dataDigit[0].equals("")) {
					dataDigit[0] = "0";
					continue;
				}
				
				if(dataDigit[1].equals("")) {
					dataDigit[1] = "0";
					continue;
				}
				
				if(dataDigit[2].equals("")) {
					dataDigit[2] = "0";
					continue;
				}
				
				String position = value[line];
				int toWhatLv = 6;
				String skillOne = imgName[0];
				String skillTwo = imgName[1];
				String skillThree = imgName[2];
				String skillFour = imgName[3];
				String skillFive = imgName[4];
				String skillSix = imgName[5];
				String skillSeven = imgName[6];
				String skillEight = imgName[7];
				String skillNine = imgName[8];
				String skillTen = imgName[9];
				String skillEleven = imgName[10];
				String skillWinRate = dataDigit[0];
				String skillPickRate = dataDigit[1];
				String skillCount = dataDigit[2];
				
				
				//데이터 베이스에 insert
				pstmt.setString(1, champName);
				pstmt.setString(2, position);
				pstmt.setString(3, skillOne);
				pstmt.setString(4, skillTwo);
				pstmt.setString(5, skillThree);	
				pstmt.setString(6, skillFour);				
				pstmt.setString(7, skillFive);	
				pstmt.setString(8, skillSix);				
				pstmt.setString(9, skillSeven);				
				pstmt.setString(10, skillEight);				
				pstmt.setString(11, skillNine);				
				pstmt.setString(12, skillTen);				
				pstmt.setString(13, skillEleven);				
				pstmt.setDouble(14, Double.parseDouble(skillWinRate));				
				pstmt.setDouble(15, Double.parseDouble(skillPickRate));				
				pstmt.setString(16, skillCount);				
				pstmt.setInt(17, toWhatLv);
				pstmt.executeUpdate();	
				
				myList2.add(new WholeCraw(champName, position, skillOne, skillTwo, skillThree, skillFour, skillFive, skillSix, 
						skillSeven, skillEight, skillNine, skillTen, skillEleven, skillWinRate, skillPickRate, skillCount));
			}
			
			
			System.out.println();
			for(int i = 0; i < myList2.size(); i++) {
				System.out.println(myList2.get(i));
			}
			
			System.out.println("스킬순서 통계 가져왔다");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	void skillTo11(ChromeDriver driver, PreparedStatement pstmt, int line, JavascriptExecutor js) throws SQLException {
		// 크롤링 해서 값 넣어줄 배열 생성
		ArrayList<WholeCraw> myList2 = new ArrayList<WholeCraw>();
		
		driver.get(url);
		try {
			//클릭 이벤트 일으키기
			List<WebElement> list = null;
			list = driver.findElements(By.className("levels"));
			WebElement element = list.get(0);
			WebElement button = element.findElement(By.id("level-11-tab"));
			js.executeScript("arguments[0].click()", button);

			//skill_lv6 크롤링
			List<WebElement> skill_lv11 = null;
			skill_lv11 = driver.findElements(By.id("skill_lv11_wrap"));
			WebElement skill = skill_lv11.get(0);
			skill_lv11 = skill.findElements(By.className("item"));
			
			String[] value = { "탑", "정글", "미드", "원딜", "서폿" };
			
			for(WebElement we : skill_lv11) {
				System.out.print("-");
				
				String[] imgName = new String[11];
				int idx = 0;
				for(WebElement itemImg : we.findElements(By.className("skill-imgbox"))) {
					imgName[idx] = itemImg.getAttribute("data-original-title");
					idx++;
				}
				
				String[] dataDigit = new String[3];
				int idx2 = 0;
				for(WebElement datadigit : we.findElements(By.className("data-digit"))) {
					dataDigit[idx2] = datadigit.getText();
					idx2++;
				}
				
				String champName = we.findElement(By.xpath("/html/body/main/div[1]/section/div[1]/h3")).getText();
				
				for(int i = 0; i < 11; i++) {
					
					if(imgName[i] == null ) {
						break;
					}
					
					
					if(champName.equals("브라움") && imgName[i].equals("동상")) {
						imgName[i] = "동상(브라움)";
					} else if(champName.equals("애니비아") && imgName[i].equals("동상")) {
						imgName[i] = "동상(애니비아)";
					}
				}
				
				if(dataDigit[0].equals("")) {
					dataDigit[0] = "0";
					continue;
				}
				
				if(dataDigit[1].equals("")) {
					dataDigit[1] = "0";
					continue;
				}
				
				if(dataDigit[2].equals("")) {
					dataDigit[2] = "0";
					continue;
				}  
				
				String position = value[line];
				int toWhatLv = 11; 
				String skillOne = imgName[0];
				String skillTwo = imgName[1];
				String skillThree = imgName[2];
				String skillFour = imgName[3];
				String skillFive = imgName[4];
				String skillSix = imgName[5];
				String skillSeven = imgName[6];
				String skillEight = imgName[7];
				String skillNine = imgName[8];
				String skillTen = imgName[9];
				String skillEleven = imgName[10];
				String skillWinRate = dataDigit[0];
				String skillPickRate = dataDigit[1];
				String skillCount = dataDigit[2];
				
				
				//데이터 베이스에 insert
				pstmt.setString(1, champName);
				pstmt.setString(2, position);
				pstmt.setString(3, skillOne);
				pstmt.setString(4, skillTwo);
				pstmt.setString(5, skillThree);	
				pstmt.setString(6, skillFour);				
				pstmt.setString(7, skillFive);	
				pstmt.setString(8, skillSix);				
				pstmt.setString(9, skillSeven);				
				pstmt.setString(10, skillEight);				
				pstmt.setString(11, skillNine);				
				pstmt.setString(12, skillTen);				
				pstmt.setString(13, skillEleven);				
				pstmt.setDouble(14, Double.parseDouble(skillWinRate));				
				pstmt.setDouble(15, Double.parseDouble(skillPickRate));				
				pstmt.setString(16, skillCount);
				pstmt.setInt(17, toWhatLv);
				pstmt.executeUpdate();	
				
				myList2.add(new WholeCraw(champName, position, skillOne, skillTwo, skillThree, skillFour, skillFive, skillSix, 
						skillSeven, skillEight, skillNine, skillTen, skillEleven, skillWinRate, skillPickRate, skillCount));
			}
			
			
			System.out.println();
			for(int i = 0; i < myList2.size(); i++) {
				System.out.println(myList2.get(i));
			}
			
			System.out.println("스킬순서 통계 가져왔다");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	void history(int a,ChromeDriver driver, PreparedStatement pstmt) throws SQLException {
		// 크롤링 해서 값 넣어줄 배열 생성
		ArrayList<CoreEachCraw> myList2 = new ArrayList<CoreEachCraw>();
		 	
		driver.get(url);
		
		try {
			List<WebElement> list = null;
			
			list = driver.findElements(By.className("champ-content-box"));
			
			WebElement element = list.get(0);
			
			list = element.findElements(By.className("ver"));
			
			for(WebElement we : list) {
				System.out.print("-");
				
				String champName = we.findElement(By.xpath("/html/body/main/div[1]/section/div[1]/h3")).getText();
				String version = we.findElement(By.tagName("h4")).getText();
				double versionN = 0;
				
				if(version.contains("b")) {
					versionN = Double.parseDouble(version.replace("b", ""));
				} else {
					versionN = Double.parseDouble(version);
				}
				
				
				String skill = "P";
				String function = we.findElement(By.tagName("ul")).getText();
				
				if(function.contains("Q")) {
					skill = "Q";
				}else if(function.contains("W")) {
					skill = "W";
				}else if(function.contains("E")) {
					skill = "E";
				}else if(function.contains("R")) {
					skill = "R";
				}else if(function.contains("기본 지속 효과")) {
					skill = "P";
				}
				
				pstmt.setString(1, champName);				
				pstmt.setDouble(2, versionN);				
				pstmt.setString(3, version);				
				pstmt.setString(4, skill);				
				pstmt.setString(5, function);
				pstmt.executeUpdate();	
				
				myList2.add(new CoreEachCraw(champName, version, skill, function));
			}
			
			
			for(int i = 0; i < myList2.size(); i++) {
				System.out.println(myList2.get(i));
			}
			
			System.out.println("패치 히스토리 가져왔다");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void basicStat(int a,ChromeDriver driver, PreparedStatement pstmt) throws SQLException {
		// 크롤링 해서 값 넣어줄 배열 생성
		ArrayList<CoreEachCraw> myList2 = new ArrayList<CoreEachCraw>();
		
		driver.get(url);
		try {
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
				
				pstmt.setString(1, champName);				
				pstmt.setString(2, stat);				
				pstmt.setString(3, statStart);				
				pstmt.setString(4, statFinal);				
				pstmt.setString(5, statRank);
				pstmt.setString(6, what);
				pstmt.executeUpdate();	
				
			}
			
			
			for(int i = 0; i < myList2.size(); i++) {
				System.out.println(myList2.get(i));
			}
			
			System.out.println("기본정보 가져왔다");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void linePer(ChromeDriver driver, PreparedStatement pstmt, int line) throws SQLException {
		// 크롤링 해서 값 넣어줄 배열 생성
		ArrayList<CoreEachCraw> myList2 = new ArrayList<CoreEachCraw>();
		
		driver.get(url);
		try {
			List<WebElement> list = null;
			
			list = driver.findElements(By.className("radio-container"));
			
			WebElement element = list.get(line);

			String[] position = { "탑", "정글", "미드", "원딜", "서폿" };

			System.out.print("-");
			
			String champName = element.findElement(By.xpath("/html/body/main/div[1]/section/div[1]/h3")).getText();
			String liner = position[line];
			String pickRate = element.findElement(By.tagName("b")).getText();

			
			pstmt.setString(1, champName);				
			pstmt.setString(2, liner);				
			String pickRate2 = pickRate.replace("%", "");
			pstmt.setDouble(3, Double.parseDouble(pickRate2));				
			pstmt.setString(4, pickRate);				
			pstmt.executeUpdate();	
			
			System.out.println("기본정보 가져왔다");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws SQLException {
		ChampStatistics champStatistics = new ChampStatistics();
		ChromeDriver driver = champStatistics.getCromeDriver();
		JavascriptExecutor js = (JavascriptExecutor)driver; 

	
		// jdbc connection 객체 생성
		DBmanager db = new DBmanager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = db.getConnection();
		} catch (ClassNotFoundException e1) { 
			e1.printStackTrace();
		}
		
		int i = 0;
		for(i = 1; i < 2 ; i++) {
			for(int line = 0; line < 5; line++) {
				champStatistics.setUrl(i, line);
//	 			try {
//					String sql = "insert into c_champ_match values(?,	 ?, ?, ?, ?)";
//					pstmt = conn.prepareStatement(sql);
//					
//					champStatistics.vsChamp(driver, db, conn, pstmt, line);
//					
//				} catch(Exception e) {
//					e.printStackTrace();
//				} finally {
//					pstmt.close(); 
//					System.out.println("PrepareStatement 종료");
//				}
//				
//				try {
//					String sql = "insert into c_spell_item values(?, ?, ?, ?, ?, ?, ?, ?)";
//					pstmt = conn.prepareStatement(sql);
//					
//					champStatistics.spell(driver, db, conn, pstmt, line);
//					champStatistics.startItem(driver, db, conn, pstmt, line);
//					champStatistics.shoes(driver, db, conn, pstmt, line);
//					
//					
//				} catch(Exception e) {
//					e.printStackTrace();
//				} finally {
//					pstmt.close(); 
//					System.out.println("PrepareStatement 종료");
//				}
//				
//				try { //코어템 통계
//					String sql = "insert into c_core_each values(?, ?, ?, ?, ?, ?, ?)";
//					pstmt = conn.prepareStatement(sql);
//					
//					champStatistics.oneCore(driver, db, conn, pstmt, line);
//					champStatistics.twoCore(driver, db, conn, pstmt, line);
//					champStatistics.threeCore(driver, db, conn, pstmt, line);
//					
//				} catch(Exception e) {
//					e.printStackTrace();
//				} finally {
//					pstmt.close(); 
//					System.out.println("PrepareStatement 종료");
//				}
//				
//				try {	// 코어템 조합 통계		테이블에러
//					String sql = "insert into c_core_combine values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//					pstmt = conn.prepareStatement(sql);
//					
//					champStatistics.twoCoreCombie(driver, db, conn, pstmt, line);
//					champStatistics.threeCoreCombie(driver, db, conn, pstmt, line);
//					champStatistics.fourCoreCombie(driver, db, conn, pstmt, line);
//					
//				} catch(Exception e)  {
//					e.printStackTrace();
//				} finally {
//					pstmt.close(); 
//					System.out.println("PrepareStatement 종료");
//				}
//				
//				try {	// 룬 조합별 통계	에러
//					String sql = "insert into c_rune_combine values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//					pstmt = conn.prepareStatement(sql);
//					
//					champStatistics.runeCombie(driver, db, conn, pstmt, line);
//					
//				} catch(Exception e)  {
//					e.printStackTrace();
//				} finally {
//					pstmt.close(); 
//					System.out.println("PrepareStatement 종료");
//				}
//				
//				try {	// 룬 파편 통계	
//					String sql = "insert into c_rune_shard values(?, ?, ?, ?, ?, ?, ?)";
//					pstmt = conn.prepareStatement(sql);
//					
//					champStatistics.runeShards(driver, db, conn, pstmt, line);
//					
//				} catch(Exception e)  {
//					e.printStackTrace();
//				} finally {
//					pstmt.close(); 
//					System.out.println("PrepareStatement 종료");
//				}
//				
//				try {	// 스킬마스터순서
//					String sql = "insert into c_skill_master values(?, ?, ?, ?, ?, ?, ?, ?)";
//					pstmt = conn.prepareStatement(sql);
//					
//					champStatistics.skillMaster(driver, db, conn, pstmt, line);
//					
//				} catch(Exception e)  {
//					e.printStackTrace();
//				} finally {
//					pstmt.close(); 
//					System.out.println("PrepareStatement 종료");
//				}
//				
//				try {	// 스킬순서
//					String sql = "insert into c_skill_seq values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//					pstmt = conn.prepareStatement(sql);
//					
//					champStatistics.skillTo3(driver, db, conn, pstmt, line);
//					champStatistics.skillTo6(driver, pstmt, line, js);
//					champStatistics.skillTo11(driver, pstmt, line, js);
//					
//				} catch(Exception e)  {
//					e.printStackTrace();
//				} finally {
//					pstmt.close(); 
//					System.out.println("PrepareStatement 종료");
//				}
//			}
//		}
//		try {	// 라인별 승률
//			String sql = "insert into c_high_pick values(?, ?, ?, ?)";
//			pstmt = conn.prepareStatement(sql);
//			
//			champStatistics.linePer(driver, pstmt, line);
//			
//		} catch(Exception e)  {
//			e.printStackTrace();
//		} finally {
//			pstmt.close(); 
//			System.out.println("PrepareStatement 종료");
//		}
//	}
//				
//				
//				
//		
//		for(int i = 1; i < 888; i++) {
//			champStatistics.setUrl(i, "패치히스토리");
//			try {
//				String sql = "insert into c_patch_history values(?, ?, ?, ?, ?)";
//				pstmt = conn.prepareStatement(sql);
//				
//				champStatistics.history(i, driver, pstmt);
//				
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		for(int i = 1; i < 888; i++) {
//			champStatistics.setUrl(i, "기본정보");
//			try {
//				String sql = "insert into c_basicstat values(?, ?, ?, ?, ?, ?)";
//				pstmt = conn.prepareStatement(sql);
//				
//				champStatistics.basicStat(i, driver, pstmt);
//				
//			}catch(Exception e) {
//				e.printStackTrace();
			}
		}

		conn.close();
		System.out.println("Connection 종료");
		driver.quit(); // 5. 브라우저 종료
		System.out.println("브라우저 종료");
		
	}
}


	