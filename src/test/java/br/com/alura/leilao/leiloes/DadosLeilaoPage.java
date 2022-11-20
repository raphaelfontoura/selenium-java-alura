package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DadosLeilaoPage extends PageObject {

    private static final String URL_DADOS_LEILAO = "http://localhost:8080/leiloes/";

    public DadosLeilaoPage(WebDriver browser) {
        super(browser);
    }

    public void darLanceLeilao(String valor) {
        browser.findElement(By.id("valor")).sendKeys(valor);
        browser.findElement(By.id("btnDarLance")).click();
    }

    public boolean isLanceAceito(String valor) {
        String pageSource = browser.getPageSource();
        return pageSource.contains("R$ " + valor);
    }

    public boolean isErrorsVisiveis() {
        String pageSource = browser.getPageSource();
        return pageSource.contains("Lance invalido!");
    }
}