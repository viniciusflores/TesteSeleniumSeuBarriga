package pages;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class InterfaceResumoMensal {

	// Variável estática que define o WebDriver.
	static WebDriver driver;

	// Abaixo é definido a data, formato e padrões para os filtros da pesquisa.
	Date data = new Date();
	Locale local = new Locale("pt", "BR");
	SimpleDateFormat dateFormatMes = new SimpleDateFormat("MMMM", local);
	SimpleDateFormat dateFormatAno = new SimpleDateFormat("yyyy");
	private String mesAtual = dateFormatMes.format(data).toString();
	private String anoAtual = dateFormatAno.format(data).toString();

	// Construtor da classe, recebe e instância o WebDriver.
	public InterfaceResumoMensal(WebDriver webDriver) {
		InterfaceResumoMensal.driver = webDriver;
	}

	// Este método preenche e realiza o filtro para o mês atual.
	public void filtraResumoMensal() {
		Select mes = new Select(driver.findElement(By.id("mes")));
		mes.selectByVisibleText(mesAtual);

		Select ano = new Select(driver.findElement(By.id("ano")));
		ano.selectByVisibleText(anoAtual);

		WebElement btnBuscar = driver.findElement(By.xpath("//input[contains(@type,'submit')]"));
		btnBuscar.click();
	}

	// Caso a tabela possua um registro com o texto similar a descrição do
	// lançamento gerado, confirmamos que o filtro foi realizado.
	public String validaResumo() {
		return (driver.findElement(By.xpath("(//td[contains(text(),'Pagamento do Boleto')])"))).getText();
	}

}
