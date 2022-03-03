package br.com.alura.tdd.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.tdd.modelo.Funcionario;

class BonusServiceTest {

	private BonusService service;
	private Funcionario funcionario;

	@BeforeAll	
	static void exemploBeforeAll() {
		System.out.println("Inicializando Teste");
	}
	@AfterAll	
	static void exemploAfterAll() {
		System.out.println("Finalizando Teste");
	}
	
	@BeforeEach
	public void inicializar() {
		this.service = new BonusService();
		this.funcionario = new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal("25000"));
	}

	@Test
	void bonusDeveriaSerZeroParaFuncionarioComSalarioMuitoAlto() {

		try {
//			funcionario = new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal("25000"));
			funcionario.setSalario(new BigDecimal("25000"));
			service.calcularBonus(funcionario);
			fail("nao deu exception");
		} catch (IllegalArgumentException e) {
			assertEquals("Funcionario com salario maior do que R$1000 nao pode receber bonus!", e.getMessage());
		}

	}

	@Test
	void bonusDeveriaSer10PorCentoDoSalario() {
//		funcionario = new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal("2500"));
		funcionario.setSalario(new BigDecimal("2500"));
		BigDecimal bonus = service.calcularBonus(funcionario);

		assertEquals(new BigDecimal("250.00"), bonus);
	}

	@Test
	void bonusDeveriaSerDezPorCentoParaSalarioDeExatamente10000() {
//		funcionario = new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal("10000"));
		funcionario.setSalario(new BigDecimal("10000"));
		BigDecimal bonus = service.calcularBonus(funcionario);

		assertEquals(new BigDecimal("1000.00"), bonus);
	}

}
