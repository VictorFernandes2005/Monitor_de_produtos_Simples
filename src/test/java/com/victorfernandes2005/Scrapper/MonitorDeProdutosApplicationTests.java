package com.victorfernandes2005.Scrapper;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.victorfernandes2005.Scrapper.model.ProductModel;
import com.victorfernandes2005.Scrapper.service.MagaluProductService;
import com.victorfernandes2005.Scrapper.service.ProductService;
import com.victorfernandes2005.Scrapper.service.ProductServiceFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;;

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

	@Test
	void shouldReturnAProduct(){
		// O produto usado precisa ser conferido antes de ser testado,
		// pois os dados vem de terceiros e não é garantido que os dados continuarão sempre os mesmos.
		ProductService mps = ProductServiceFactory.getService("magazine");
		String url = "https://www.magazineluiza.com.br/martelo-31mm-bellota-prof-cb-longo/p/cfb45af6cc/fs/fmar/?seller_id=uaiexpress1";
		ProductModel product = mps.makeProduct(new FirefoxDriver(),url);

		String expectedName = "Martelo 31mm bellota prof.cb longo";
		Double expectedPrice = 109.82;
		String expectedImgPath = "https://m.magazineluiza.com.br/a-static/420x420/martelo-31mm-bellota-prof-cb-longo/uaiexpress1/116077/38818cc4b76d3178baf7a986bbbbbca8.jpeg";

		assertThat(product.getName()).isEqualTo(expectedName);
		assertThat(product.getPrice()).isCloseTo(expectedPrice, offset(0.3));
		assertThat(product.getImgPath()).isEqualTo(expectedImgPath);

	}

}
