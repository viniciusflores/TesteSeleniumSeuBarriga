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



/*  Para a execução  dos testes, na minha opinião seria o ideal utilizar mais de um navegador,
 * acredito que os 4 maiores seria o ideal, alinhado com Roberta, e o exercício foi feito somente com o Google Chrome.
 * 	Nessa classe iremos definir o navegador a ser utilizado e também o caminho que ele se encontra.
 *  Utilizei o drive anexo a partir do link: https://chromedriver.storage.googleapis.com/index.html?path=2.45/ e o disponibilizei na raiz da minha unidade C:.
 *  Para executar o teste, é necessário que o drive seja incluído no mesmo diretório.
 * 
 */

// A anotação @FixMethodOrder define como será a execução dos testes, neste caso, pelo nome em ordem alfabética.
// A classe TestSeuBarriga é uma extensão da classe JUnitHTMLReporter, utilizada para a impressão dos resultados em um relatório.
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSeuBarriga extends JUnitHTMLReporter {

	// Variáveis estáticas, utilizada para definir o WebDriver e as demais classes a
	// serem consumidas.
	static WebDriver driver;
	static InterfaceLogin interfaceLogin;
	static InterfaceCadastroUsuario interfaceCadastroUsuario;
	static InterfaceHome interfaceHome;
	static InterfaceContas interfaceContas;
	static InterfaceMovimento interfaceMovimento;
	static InterfaceResumoMensal interfaceResumoMensal;

	// Esse é o método que iremos parametrizar o navegador e o inicializá-lo junto
	// do site a ser testado.
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webDriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://seubarriga.wcaquino.me");

		// Além do navegador, esse método inicializa as demais classes auxiliares.
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

	// Este teste iremos para a página de cadastro, e chamaremos os métodos que
	// criam o usuário.
	// Além de validar a interface posicionada.
	@Test
	public void criarUsuario() {
		interfaceLogin.redirectToCadastroUsuarioInterface();
		Assert.assertEquals(driver.getTitle(), "Seu Barriga - Novo Usuário");
		interfaceCadastroUsuario.CriaNovoUsuario();
		Assert.assertEquals(interfaceCadastroUsuario.verificaUsuarioCriado(), "Usuário inserido com sucesso");
	}

	// Este teste retorna a guia de login e o efetua, além de validar a interface
	// posicionada.
	@Test
	public void b_realizarLoginNaConta() {
		interfaceLogin.redirectToLoginInterface();
		Assert.assertEquals(driver.getTitle(), "Seu Barriga - Log in");
		interfaceLogin.efetuarLogin();
		Assert.assertEquals(interfaceLogin.verificaUsuarioLogado(), "Bem vindo, Vinicius!");
	}

	// Esse teste direciona para a tela de criar contas, e cria dois registros
	// distintos para criar as contas é necessário passar o nome das mesmas.
	// Por fim, o método valida a interface posicionada.
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
	// aleatório (o primeiro que encontra).
	// Além disso, ele valida a interface posicionada.
	@Test
	public void d_excluirConta() {
		interfaceHome.redirectToListarContaInterface();
		Assert.assertEquals(driver.getTitle(), "Seu Barriga - Contas");
		interfaceContas.excluiContaAleatoria();
		Assert.assertEquals(interfaceContas.verificaContaExcluida(), "Conta removida com sucesso!");
	}

	// Esse teste direciona para a tela de movimentos e cria uma movimentação de
	// receita.
	// É necessário passar o tipo da despesa conforme o texto do select.
	// Além disso, o teste valida a interface correta.
	@Test
	public void e_criaMovimentoReceita() {
		interfaceHome.redirectToMovimentoInterface();
		Assert.assertEquals(driver.getTitle(), "Seu Barriga - Movimentações");
		interfaceMovimento.criarMovimento("Receita");
		Assert.assertEquals(interfaceMovimento.verificaMovimentoCriado(), "Movimentação adicionada com sucesso!");
	}

	// Esse teste direciona para a tela de movimentos e cria uma movimentação de
	// despesa.
	// É necessário passar o tipo da despesa conforme o texto do select.
	// Além disso, o teste valida a interface correta.
	@Test
	public void f_criaMovimentoDespesa() {
		interfaceHome.redirectToMovimentoInterface();
		Assert.assertEquals(driver.getTitle(), "Seu Barriga - Movimentações");
		interfaceMovimento.criarMovimento("Despesa");
		Assert.assertEquals(interfaceMovimento.verificaMovimentoCriado(), "Movimentação adicionada com sucesso!");
	}

	// Este teste direciona para a interface do resumo mensal e filtra pelo mês e
	// ano vigente.
	// Além de validar a interface posicionada.
	@Test
	public void g_verificaResumoMensal() {
		interfaceHome.redirectToResumoMensal();
		Assert.assertEquals(driver.getTitle(), "Seu Barriga - Extrato");
		interfaceResumoMensal.filtraResumoMensal();
		Assert.assertEquals(interfaceResumoMensal.validaResumo(), "Pagamento do Boleto");
	}

	// Esse teste realiza o logout da aplicação e valida a URL posicionada.
	@Test
	public void h_sairDoSistema() {
		interfaceHome.redirectToSair();
		Assert.assertEquals(driver.getCurrentUrl(), "https://seubarriga.wcaquino.me/logout");
	}

	// Esse método encerra o processo, fechando o driver do navegador.
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

}
