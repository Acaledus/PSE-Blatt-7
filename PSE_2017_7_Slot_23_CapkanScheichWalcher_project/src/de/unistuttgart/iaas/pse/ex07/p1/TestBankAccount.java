package de.unistuttgart.iaas.pse.ex07.p1;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit tests for {@link BankAccount}.
 * @author Daniel Capkan, Matrikelnummer: 3325960, st156303@stud.uni-stuttgart.de
 * @author Mario Scheich, Matrikelnummer: 3232655 , st151491@stud.uni-stuttgart.de
 * @author Florian Walcher, Matrikelnummer: 3320185, st156818@stud.uni-stuttgart.de
 */
public class TestBankAccount {
	
	@Test
	public void testNewBankAccount() {
		BankAccount konto1 = new BankAccount(99.99);
		BankAccount konto2 = new BankAccount(-99.99);
		assertEquals(konto1.balance, 99.99);
		assertEquals(konto2.balance, 0.00);		
	}
	
	@Test
	public void testDeposit() {
		BankAccount konto1 = new BankAccount(0.0), konto2 = new BankAccount(0.0);
		konto1.deposit(999.99);
		konto2.deposit(-999.99);
		assertEquals(konto1.balance, 999.99);
		assertEquals(konto2.balance, 0.00);	
	}
	
	@Test
	public void testWithdraw() {
		BankAccount konto1 = new BankAccount(100.0), konto2 = new BankAccount(0.0);
		konto1.withdraw(50.0);
		assertEquals(konto1.balance, 50.0);
		konto2.withdraw(123.4);
		assertEquals(konto2.balance, 0.0);
		konto1.withdraw(-7634.23);
		assertEquals(konto1.balance, 50.0);
	}
}