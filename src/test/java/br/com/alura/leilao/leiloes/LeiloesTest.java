package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LeiloesTest {

    private LeiloesPage paginaDeLeiloes;

    @AfterEach
    void tearDown() {
        paginaDeLeiloes.close();
    }

    @Test
    void deveriaCadastrarLeilao() {
        LoginPage paginaDeLogin = new LoginPage();
        paginaDeLogin.preencheFormularioDeLogin("fulano","pass");
        paginaDeLeiloes = paginaDeLogin.efetuaLogin();

        CadastroLeilaoPage paginaCadastro = paginaDeLeiloes.carregarFormulario();
        String data = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilão do dia " + data;
        String valor = "500.00";

        paginaDeLeiloes = paginaCadastro.cadastrarLeilao(nome, valor, data);
        Assertions.assertTrue(paginaDeLeiloes.isLeilaoCadastrado(nome, valor, data));
    }
}
