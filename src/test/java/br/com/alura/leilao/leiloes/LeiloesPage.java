package br.com.alura.leilao.leiloes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LeiloesPage {

    private static final String URL_LEILOES_FORM = "http://localhost:8080/leiloes/new";

    private WebDriver browser;

    public LeiloesPage(WebDriver browser) {
        this.browser = browser;
    }

    public void close() {
        this.browser.quit();
    }

    public CadastroLeilaoPage carregarFormulario() {
        this.browser.navigate().to(URL_LEILOES_FORM);
        return new CadastroLeilaoPage(browser);
    }
}
