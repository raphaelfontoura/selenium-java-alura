package br.com.alura.leilao.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private static final String URL_LOGIN = "http://localhost:8080/login";
    private WebDriver browser;

    @BeforeAll
    static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
    }

    @BeforeEach
    void setUp() {
        browser = new ChromeDriver();
        browser.navigate().to(URL_LOGIN);
    }

    @AfterEach
    void tearDown() {
        this.browser.quit();
    }

    @Test
    void deveriaEfetuarLoginComDadosValidos() {

        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        assertNotEquals(URL_LOGIN, browser.getCurrentUrl());
        assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());

    }

    @Test
    void naoDeveriaLogarComDadosInvalidos() {
        browser.findElement(By.id("username")).sendKeys("invalido");
        browser.findElement(By.id("password")).sendKeys("1234");
        browser.findElement(By.id("login-form")).submit();

        assertEquals("http://localhost:8080/login?error", browser.getCurrentUrl());
        assertTrue(browser.getPageSource().contains("Usuário e senha inválidos"));
        assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")).getText());

    }
}
