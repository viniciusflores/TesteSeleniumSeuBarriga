package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InterfaceHome {

	// Vari�vel est�tica que define o WebDriver.
	static WebDriver driver;

	// Construtor da classe, recebe e inst�ncia o WebDriver.
	public InterfaceHome(WebDriver webDriver) {
		InterfaceHome.driver = webDriver;
	}

	// Esse m�todo, clica no menu Contas, e na sequ�ncia seleciona a op��o
	// adicionar, para ir � p�gina desejada.
	public void redirectToCriarContaInterface() {
		WebElement menuContas = driver.findElement(By.xpath("//a[@href='/'][contains(.,'Contas')]"));
		menuContas.click();

		WebElement menuCriarContas = driver.findElement(By.xpath("//a[@href='/addConta'][contains(.,'Adicionar')]"));
		menuCriarContas.click();
	}

	// Esse m�todo, clica no menu Contas, e na sequ�ncia seleciona a op��o listar,
	// para ir � p�gina desejada.
	public void redirectToListarContaInterface() {
		WebElement menuContas = driver.findElement(By.xpath("//a[@href='/'][contains(.,'Contas')]"));
		menuContas.click();

		WebElement menuListarContas = driver.findElement(By.xpath("//a[@href='/contas'][contains(.,'Listar')]"));
		menuListarContas.click();
	}

	// Esse m�todo, clica no menu Criar Movimenta��o, para ir � p�gina desejada.
	public void redirectToMovimentoInterface() {
		WebElement menuMovimento = driver
				.findElement(By.xpath("//a[@href='/movimentacao'][contains(.,'Criar Movimenta��o')]"));
		menuMovimento.click();
	}

	// Esse m�todo, clica no menu Resumo Mensal, para ir � p�gina desejada.
	public void redirectToResumoMensal() {
		WebElement menuResumoMensal = driver
				.findElement(By.xpath("//a[@href='/extrato'][contains(.,'Resumo Mensal')]"));
		menuResumoMensal.click();
	}

	// Esse m�todo, clica no menu Sair, para sair da aplica��o.
	public void redirectToSair() {
		WebElement menuLogout = driver.findElement(By.xpath("//a[@href='/logout'][contains(.,'Sair')]"));
		menuLogout.click();
	}

}
