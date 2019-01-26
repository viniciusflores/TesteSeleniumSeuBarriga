package pages;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class InterfaceMovimento {

	// Variável estática que define o WebDriver.
	static WebDriver driver;

	// Abaixo é definido a data e formato.
	Date data = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	// Construtor da classe, recebe e instância o WebDriver.
	public InterfaceMovimento(WebDriver driver) {
		InterfaceMovimento.driver = driver;
	}

	// Esse método recebe o tipo, e cria um movimento estático para a data atual.
	public void criarMovimento(String tipoMovimento) {
		Select tipoMov = new Select(driver.findElement(By.id("tipo")));
		tipoMov.selectByVisibleText(tipoMovimento);

		WebElement dataMov = driver.findElement(By.id("data_transacao"));
		dataMov.sendKeys(dateFormat.format(data).toString());

		WebElement dataPagMov = driver.findElement(By.id("data_pagamento"));
		dataPagMov.sendKeys(dateFormat.format(data).toString());

		WebElement descricao = driver.findElement(By.id("descricao"));
		descricao.sendKeys("Pagamento do Boleto");

		WebElement interessado = driver.findElement(By.id("interessado"));
		interessado.sendKeys("João da Silva");

		WebElement value = driver.findElement(By.id("valor"));
		value.sendKeys("120.45");

		Select conta = new Select(driver.findElement(By.id("conta")));
		conta.selectByIndex(0);

		WebElement situacao = driver.findElement(By.id("status_pago"));
		// WebElement situacao =
		// driver.findElement(By.xpath("//input[contains(@id,'status_pago')]"));
		situacao.click();

		WebElement btnSalvarMov = driver.findElement(By.xpath("//button[@type='submit'][contains(.,'Salvar')]"));
		btnSalvarMov.click();

	}

	// Caso sucesso ao criar o movimento, valida se foi exibido mensagem de sucesso.
	public String verificaMovimentoCriado() {
		return driver
				.findElement(By.xpath(
						"//div[@class='alert alert-success'][contains(.,'Movimentação adicionada com sucesso!')]"))
				.getText();
	}

}
