package br.com.alura.leilao.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private LoginPage paginaDeLogin;

    @BeforeEach
    void setUp() {
        this.paginaDeLogin = new LoginPage();
    }

    @AfterEach
    void tearDown() {
        this.paginaDeLogin.close();
    }

    @Test
    void deveriaEfetuarLoginComDadosValidos() {
        paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");

        paginaDeLogin.efetuaLogin();

        assertFalse(paginaDeLogin.isPaginaDeLogin());
        assertEquals("fulano", paginaDeLogin.getNomeUsuarioLogado());
    }

    @Test
    void naoDeveriaLogarComDadosInvalidos() {
        paginaDeLogin.preencheFormularioDeLogin("invalido", "1234");

        paginaDeLogin.efetuaLogin();

        assertTrue(paginaDeLogin.isPaginaDeErroLogin());
        assertNull(paginaDeLogin.getNomeUsuarioLogado());
        assertTrue(paginaDeLogin.contains("Usuário e senha inválidos"));
    }

    @Test
    void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
        paginaDeLogin.navegaParaPaginaDeLances();


        assertTrue(paginaDeLogin.isPaginaDeLogin());
        assertFalse(paginaDeLogin.contains("Dados do Leilão"));
    }
}
