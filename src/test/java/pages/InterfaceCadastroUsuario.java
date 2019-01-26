package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InterfaceCadastroUsuario {

	// Vari�vel est�tica que define o WebDriver.
	static WebDriver driver;

	// Construtor da classe, recebe e inst�ncia o WebDriver.
	public InterfaceCadastroUsuario(WebDriver webDriver) {
		InterfaceCadastroUsuario.driver = webDriver;
	}

	// Esse m�todo cria um novo usu�rio, preenchendo todos os campos da tela e
	// confirmando.
	public void CriaNovoUsuario() {
		WebElement nome = driver.findElement(By.id("nome"));
		nome.sendKeys("Vinicius Flores");

		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys("testeavaliacao4all@gmail.com");

		WebElement senha = driver.findElement(By.id("senha"));
		senha.sendKeys("m3c0ntr@t@");

		WebElement btnCadastrar = driver.findElement(By.xpath("//input[@type = 'submit'][@value = 'Cadastrar']"));
		btnCadastrar.click();
	}

	// Caso sucesso ao criar o usu�rio, valida se foi exibido mensagem de sucesso.
	public String verificaUsuarioCriado() {
		return driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
	}
}
