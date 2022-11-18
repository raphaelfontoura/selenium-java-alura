package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public boolean isLeilaoCadastrado(String nome, String valor, String data) {
        WebElement element = browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
        WebElement colunaNome = element.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement colunaData = element.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement colunaValor = element.findElement(By.cssSelector("td:nth-child(3)"));

        return colunaNome.getText().equals(nome)
                && colunaData.getText().equals(data)
                && colunaValor.getText().equals(valor);
    }
}
