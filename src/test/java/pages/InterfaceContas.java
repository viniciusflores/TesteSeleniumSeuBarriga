package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InterfaceContas {

	// Variável estática que define o WebDriver.
	static WebDriver driver;

	// Construtor da classe, recebe e instância o WebDriver.
	public InterfaceContas(WebDriver webDriver) {
		InterfaceContas.driver = webDriver;
	}

	// Esse método recebe o nome da conta, e cria o registro.
	public void CriarContas(String conta) {
		WebElement nomeConta = driver.findElement(By.id("nome"));
		nomeConta.sendKeys(conta.toString());

		WebElement btnSalvar = driver.findElement(By.xpath("//button[@type='submit'][contains(.,'Salvar')]"));
		btnSalvar.click();
	}

	// Caso sucesso ao criar a conta, valida se foi exibido mensagem de sucesso.
	public String verificaContaCriada() {
		return driver
				.findElement(
						By.xpath("//div[@class='alert alert-success'][contains(.,'Conta adicionada com sucesso!')]"))
				.getText();
	}

	// Esse método deleta uma conta, a primeira que encontrar, de forma aleatória.
	public void excluiContaAleatoria() {
		WebElement deleted = driver
				.findElement(By.xpath("(//span[contains(@class,'glyphicon glyphicon-remove-circle')])[1]"));
		deleted.click();
	}

	// Caso sucesso ao deletar o registro, valida se foi exibido mensagem de
	// sucesso.
	public String verificaContaExcluida() {
		return driver
				.findElement(By.xpath("//div[@class='alert alert-success'][contains(.,'Conta removida com sucesso!')]"))
				.getText();
	}

}
