package tests;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

// Essa classe é responsável pela impressão dos resultados dos testes da classe TestSeuBarriga.
public class JUnitHTMLReporter {
	
	// Instanciado JUnitReport e JUnitWriter responsáveis pela impressão e geração do relatório.
	static File junitReport;
	static BufferedWriter junitWriter;

	// 	Esse método ocorre antes do teste, e é onde é instanciado a data e formato, 
	// além de inicializar o JUnitWriter e JUnitReport. Com ambos inicializados, é
	// inicializado a escrita do relatório preenchendo o cabeçalho com data e hora.
	@BeforeClass
	public static void setUp() throws IOException {

		String junitReportFile = System.getProperty("user.dir")
				+ "\\junitReportFile.html";
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		junitReport = new File(junitReportFile);
		junitWriter = new BufferedWriter(new FileWriter(junitReport, true));
		junitWriter.write("<html><body>");
		junitWriter.write("<h1>Resumo do teste: " + dateFormat.format(date)
				+ "</h1>");

	}

	// 	Esse método define as regras do TestWatcher, onde é avaliado o retorno dos testes
	// e retorna a impressão de sucesso ou falha do teste.
	@Rule
	public TestRule watchman = new TestWatcher() {

		@Override
		public Statement apply(Statement base, Description description) {
			return super.apply(base, description);
		}

		@Override
		protected void succeeded(Description description) {
			try {
				junitWriter.write(description.getDisplayName() + " - sucesso!");
				junitWriter.write("<br/>");
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
		}

		@Override
		protected void failed(Throwable e, Description description) {
			try {
				junitWriter.write(description.getDisplayName() + " - erro!");
				junitWriter.write("<br/>");
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	};
	
	// Por fim, o relatório html é concluído e é lançado para exibição.
	@AfterClass
	public static void tearDown() throws IOException {

		junitWriter.write("</body></html>");
		junitWriter.close();
		Desktop.getDesktop().browse(junitReport.toURI());

	}
}
