package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InterfaceHome {

	// Variável estática que define o WebDriver.
	static WebDriver driver;

	// Construtor da classe, recebe e instância o WebDriver.
	public InterfaceHome(WebDriver webDriver) {
		InterfaceHome.driver = webDriver;
	}

	// Esse método, clica no menu Contas, e na sequência seleciona a opção
	// adicionar, para ir à página desejada.
	public void redirectToCriarContaInterface() {
		WebElement menuContas = driver.findElement(By.xpath("//a[@href='/'][contains(.,'Contas')]"));
		menuContas.click();

		WebElement menuCriarContas = driver.findElement(By.xpath("//a[@href='/addConta'][contains(.,'Adicionar')]"));
		menuCriarContas.click();
	}

	// Esse método, clica no menu Contas, e na sequência seleciona a opção listar,
	// para ir à página desejada.
	public void redirectToListarContaInterface() {
		WebElement menuContas = driver.findElement(By.xpath("//a[@href='/'][contains(.,'Contas')]"));
		menuContas.click();

		WebElement menuListarContas = driver.findElement(By.xpath("//a[@href='/contas'][contains(.,'Listar')]"));
		menuListarContas.click();
	}

	// Esse método, clica no menu Criar Movimentação, para ir à página desejada.
	public void redirectToMovimentoInterface() {
		WebElement menuMovimento = driver
				.findElement(By.xpath("//a[@href='/movimentacao'][contains(.,'Criar Movimentação')]"));
		menuMovimento.click();
	}

	// Esse método, clica no menu Resumo Mensal, para ir à página desejada.
	public void redirectToResumoMensal() {
		WebElement menuResumoMensal = driver
				.findElement(By.xpath("//a[@href='/extrato'][contains(.,'Resumo Mensal')]"));
		menuResumoMensal.click();
	}

	// Esse método, clica no menu Sair, para sair da aplicação.
	public void redirectToSair() {
		WebElement menuLogout = driver.findElement(By.xpath("//a[@href='/logout'][contains(.,'Sair')]"));
		menuLogout.click();
	}

}
