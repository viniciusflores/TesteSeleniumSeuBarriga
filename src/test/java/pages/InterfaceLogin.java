package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InterfaceLogin {

	// Vari�vel est�tica que define o WebDriver.
	static WebDriver driver;

	// Construtor da classe, recebe e inst�ncia o WebDriver.
	public InterfaceLogin(WebDriver driver) {
		InterfaceLogin.driver = driver;
	}

	// Esse m�todo, clica no menu Login, para ir � p�gina desejada.
	public void redirectToLoginInterface() {
		WebElement btnLogin = driver.findElement(By.xpath("//a[@href='/login'][contains(.,'Login')]"));
		btnLogin.click();
	}

	// Esse m�todo, clica no menu Novo Usu�rio?, para ir � p�gina desejada.
	public void redirectToCadastroUsuarioInterface() {
		WebElement btnNovoUsuario = driver.findElement(By.xpath("//a[@href='/cadastro'][contains(.,'Novo usu�rio?')]"));
		btnNovoUsuario.click();
	}

	// Esse m�todo preenche os dados do usu�rio e realiza o login do sistema.
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
