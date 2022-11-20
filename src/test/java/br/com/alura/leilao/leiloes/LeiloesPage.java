package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LeiloesPage extends PageObject {

    private static final String URL_LEILOES_FORM = "http://localhost:8080/leiloes/new";
    private static final String URL_LEILOES = "http://localhost:8080/leiloes";


    public LeiloesPage(WebDriver browser) {
        super(browser);
    }

    public CadastroLeilaoPage carregarFormulario() {
        this.browser.navigate().to(URL_LEILOES_FORM);
        return new CadastroLeilaoPage(browser);
    }

    public DadosLeilaoPage carregarDadosLeilao(int idLeilao) {
        this.browser.navigate().to(String.format(URL_LEILOES + "/%d", idLeilao));
        return new DadosLeilaoPage(browser);
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

    public boolean isPaginaAtual() {
        return browser.getCurrentUrl().equals(URL_LEILOES);
    }

}
