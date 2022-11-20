package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeiloesTest {

    private LeiloesPage paginaDeLeiloes;
    private CadastroLeilaoPage paginaCadastro;

    private DadosLeilaoPage dadosLeilaoPage;

    @BeforeEach
    void setUp() {
        LoginPage paginaDeLogin = new LoginPage();
        paginaDeLogin.preencheFormularioDeLogin("fulano","pass");
        paginaDeLeiloes = paginaDeLogin.efetuaLogin();

        paginaCadastro = paginaDeLeiloes.carregarFormulario();
    }

    @AfterEach
    void tearDown() {
        paginaDeLeiloes.close();
    }

    @Test
    void deveriaCadastrarLeilao() {

        String data = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilão do dia " + data;
        String valor = "500.00";

        paginaDeLeiloes = paginaCadastro.cadastrarLeilao(nome, valor, data);
        assertTrue(paginaDeLeiloes.isLeilaoCadastrado(nome, valor, data));
    }

    @Test
    void deveriaValidarCadastroDeLeilao() {
        paginaDeLeiloes = paginaCadastro.cadastrarLeilao("", "", "");
        assertFalse(paginaCadastro.isPaginaAtual());
        assertTrue(paginaDeLeiloes.isPaginaAtual());
        assertTrue(paginaCadastro.isErrorsVisiveis());
    }

    @Test
    @Order(1)
    void deveriaAlertarCasoValorAbaixoDoLanceInicial() {
        String valorLance = "5";
        dadosLeilaoPage = paginaDeLeiloes.carregarDadosLeilao(2);
        dadosLeilaoPage.darLanceLeilao(valorLance);

        assertTrue(dadosLeilaoPage.isErrorsVisiveis());
    }

    @Test
    @Order(2)
    void deveriaCadastrarNovoLance() {
        String valorLance = "550";
        dadosLeilaoPage = paginaDeLeiloes.carregarDadosLeilao(2);
        dadosLeilaoPage.darLanceLeilao(valorLance);

        assertTrue(dadosLeilaoPage.isLanceAceito(valorLance));
        assertFalse(dadosLeilaoPage.isErrorsVisiveis());
    }

    @Test
    @Order(3)
    void deveriaAlertarTentativaDeNovoLanceDoMesmoUsuario() {
        String valorLance = "600";
        dadosLeilaoPage = paginaDeLeiloes.carregarDadosLeilao(2);
        dadosLeilaoPage.darLanceLeilao(valorLance);

        assertTrue(dadosLeilaoPage.isErrorsVisiveis());
    }
}
