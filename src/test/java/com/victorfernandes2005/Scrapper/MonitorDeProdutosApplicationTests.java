package com.victorfernandes2005.Scrapper;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;;

@SpringBootTest
class MonitorDeProdutosApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void shouldExecuteSelenium(){
		WebDriver driver = new FirefoxDriver();
		driver.get("https://google.com");
		driver.quit();
	}

	@Test
	void seleniumShouldReturnAHelloWorld(){
		// Esse teste foi escrito em Janeiro de 2026. A página utilizada para esse teste pode ser alterada posteriormente,
		// por isso sempre que necessário, esse teste deve ser conferido antes de ser executado. 
		WebDriver driver = new FirefoxDriver();
		driver.get("https://pt.wikipedia.org/wiki/Programa_Ol%C3%A1_Mundo");
		String title = driver.getTitle();
		assertThat(title).isEqualTo("Programa Olá Mundo – Wikipédia, a enciclopédia livre");

		WebElement hello = driver.findElement(By.className("mw-page-title-main"));
		assertThat(hello.getText()).isEqualTo("Programa Olá Mundo");

		driver.quit();
	}

}
