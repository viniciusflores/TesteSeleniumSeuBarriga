package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InterfaceLogin {

	// Variável estática que define o WebDriver.
	static WebDriver driver;

	// Construtor da classe, recebe e instância o WebDriver.
	public InterfaceLogin(WebDriver driver) {
		InterfaceLogin.driver = driver;
	}

	// Esse método, clica no menu Login, para ir à página desejada.
	public void redirectToLoginInterface() {
		WebElement btnLogin = driver.findElement(By.xpath("//a[@href='/login'][contains(.,'Login')]"));
		btnLogin.click();
	}

	// Esse método, clica no menu Novo Usuário?, para ir à página desejada.
	public void redirectToCadastroUsuarioInterface() {
		WebElement btnNovoUsuario = driver.findElement(By.xpath("//a[@href='/cadastro'][contains(.,'Novo usuário?')]"));
		btnNovoUsuario.click();
	}

	// Esse método preenche os dados do usuário e realiza o login do sistema.
	public void efetuarLogin() {
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys("testeavaliacao4all@gmail.com");

		WebElement senha = driver.findElement(By.id("senha"));
		senha.sendKeys("m3c0ntr@t@");

		WebElement btnEntrar = driver.findElement(By.xpath("//button[@type='submit'][contains(.,'Entrar')]"));
		btnEntrar.click();
	}

	// Caso sucesso ao realizar o login, valida se foi exibido mensagem de sucesso.
	public String verificaUsuarioLogado() {
		return driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
	}

}
