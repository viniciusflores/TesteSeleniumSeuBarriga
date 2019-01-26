package tests;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.InterfaceCadastroUsuario;
import pages.InterfaceContas;
import pages.InterfaceHome;
import pages.InterfaceLogin;
import pages.InterfaceMovimento;
import pages.InterfaceResumoMensal;



/*  Para a execu��o  dos testes, na minha opini�o seria o ideal utilizar mais de um navegador,
 * acredito que os 4 maiores seria o ideal, alinhado com Roberta, e o exerc�cio foi feito somente com o Google Chrome.
 * 	Nessa classe iremos definir o navegador a ser utilizado e tamb�m o caminho que ele se encontra.
 *  Utilizei o drive anexo a partir do link: https://chromedriver.storage.googleapis.com/index.html?path=2.45/ e o disponibilizei na raiz da minha unidade C:.
 *  Para executar o teste, � necess�rio que o drive seja inclu�do no mesmo diret�rio.
 * 
 */

// A anota��o @FixMethodOrder define como ser� a execu��o dos testes, neste caso, pelo nome em ordem alfab�tica.
// A classe TestSeuBarriga � uma extens�o da classe JUnitHTMLReporter, utilizada para a impress�o dos resultados em um relat�rio.
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSeuBarriga extends JUnitHTMLReporter {

	// Vari�veis est�ticas, utilizada para definir o WebDriver e as demais classes a
	// serem consumidas.
	static WebDriver driver;
	static InterfaceLogin interfaceLogin;
	static InterfaceCadastroUsuario interfaceCadastroUsuario;
	static InterfaceHome interfaceHome;
	static InterfaceContas interfaceContas;
	static InterfaceMovimento interfaceMovimento;
	static InterfaceResumoMensal interfaceResumoMensal;

	// Esse � o m�todo que iremos parametrizar o navegador e o inicializ�-lo junto
	// do site a ser testado.
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webDriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://seubarriga.wcaquino.me");

		// Al�m do navegador, esse m�todo inicializa as demais classes auxiliares.
		interfaceLogin = new InterfaceLogin(driver);
		interfaceCadastroUsuario = new InterfaceCadastroUsuario(driver);
		interfaceContas = new InterfaceContas(driver);
		interfaceHome = new InterfaceHome(driver);
		interfaceMovimento = new InterfaceMovimento(driver);
		interfaceResumoMensal = new InterfaceResumoMensal(driver);
	}

	// Esse teste valida se o site foi inicializado corretamente na tela de login.
	@Test
	public void a_inicializacaoBemSucedida() {
		Assert.assertEquals(driver.getTitle(), "Seu Barriga - Log in");
	}

	// Este teste iremos para a p�gina de cadastro, e chamaremos os m�todos que
	// criam o usu�rio.
	// Al�m de validar a interface posicionada.
	@Test
	public void criarUsuario() {
		interfaceLogin.redirectToCadastroUsuarioInterface();
		Assert.assertEquals(driver.getTitle(), "Seu Barriga - Novo Usu�rio");
		interfaceCadastroUsuario.CriaNovoUsuario();
		Assert.assertEquals(interfaceCadastroUsuario.verificaUsuarioCriado(), "Usu�rio inserido com sucesso");
	}

	// Este teste retorna a guia de login e o efetua, al�m de validar a interface
	// posicionada.
	@Test
	public void b_realizarLoginNaConta() {
		interfaceLogin.redirectToLoginInterface();
		Assert.assertEquals(driver.getTitle(), "Seu Barriga - Log in");
		interfaceLogin.efetuarLogin();
		Assert.assertEquals(interfaceLogin.verificaUsuarioLogado(), "Bem vindo, Vinicius!");
	}

	// Esse teste direciona para a tela de criar contas, e cria dois registros
	// distintos para criar as contas � necess�rio passar o nome das mesmas.
	// Por fim, o m�todo valida a interface posicionada.
	@Test
	public void c_criarContas() {
		interfaceHome.redirectToCriarContaInterface();
		Assert.assertEquals(driver.getTitle(), "Seu Barriga - Adicionar Conta");
		interfaceContas.CriarContas("Banco Itau");
		Assert.assertEquals(interfaceContas.verificaContaCriada(), "Conta adicionada com sucesso!");
		interfaceHome.redirectToCriarContaInterface();
		interfaceContas.CriarContas("Banco do Brasil");
		Assert.assertEquals(interfaceContas.verificaContaCriada(), "Conta adicionada com sucesso!");
	}

	// Esse teste direciona para a tela de lista de contas, e exclui um registro
	// aleat�rio (o primeiro que encontra).
	// Al�m disso, ele valida a interface posicionada.
	@Test
	public void d_excluirConta() {
		interfaceHome.redirectToListarContaInterface();
		Assert.assertEquals(driver.getTitle(), "Seu Barriga - Contas");
		interfaceContas.excluiContaAleatoria();
		Assert.assertEquals(interfaceContas.verificaContaExcluida(), "Conta removida com sucesso!");
	}

	// Esse teste direciona para a tela de movimentos e cria uma movimenta��o de
	// receita.
	// � necess�rio passar o tipo da despesa conforme o texto do select.
	// Al�m disso, o teste valida a interface correta.
	@Test
	public void e_criaMovimentoReceita() {
		interfaceHome.redirectToMovimentoInterface();
		Assert.assertEquals(driver.getTitle(), "Seu Barriga - Movimenta��es");
		interfaceMovimento.criarMovimento("Receita");
		Assert.assertEquals(interfaceMovimento.verificaMovimentoCriado(), "Movimenta��o adicionada com sucesso!");
	}

	// Esse teste direciona para a tela de movimentos e cria uma movimenta��o de
	// despesa.
	// � necess�rio passar o tipo da despesa conforme o texto do select.
	// Al�m disso, o teste valida a interface correta.
	@Test
	public void f_criaMovimentoDespesa() {
		interfaceHome.redirectToMovimentoInterface();
		Assert.assertEquals(driver.getTitle(), "Seu Barriga - Movimenta��es");
		interfaceMovimento.criarMovimento("Despesa");
		Assert.assertEquals(interfaceMovimento.verificaMovimentoCriado(), "Movimenta��o adicionada com sucesso!");
	}

	// Este teste direciona para a interface do resumo mensal e filtra pelo m�s e
	// ano vigente.
	// Al�m de validar a interface posicionada.
	@Test
	public void g_verificaResumoMensal() {
		interfaceHome.redirectToResumoMensal();
		Assert.assertEquals(driver.getTitle(), "Seu Barriga - Extrato");
		interfaceResumoMensal.filtraResumoMensal();
		Assert.assertEquals(interfaceResumoMensal.validaResumo(), "Pagamento do Boleto");
	}

	// Esse teste realiza o logout da aplica��o e valida a URL posicionada.
	@Test
	public void h_sairDoSistema() {
		interfaceHome.redirectToSair();
		Assert.assertEquals(driver.getCurrentUrl(), "https://seubarriga.wcaquino.me/logout");
	}

	// Esse m�todo encerra o processo, fechando o driver do navegador.
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

}
