package de.unistuttgart.iaas.pse.ex07.p2;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Provides methods for converting numbers between their binary, decimal, and
 * hexadecimal representation. Provides a simple text interface to these conversion methods.
 * @author Daniel Capkan, Matrikelnummer: 3325960, st156303@stud.uni-stuttgart.de
 * @author Mario Scheich, Matrikelnummer: 3232655 , st151491@stud.uni-stuttgart.de
 * @author Florian Walcher, Matrikelnummer: 3320185, st156818@stud.uni-stuttgart.de
 */
public class NumeralSystems {
	
	/**
	 * Wandelt eine Dezimalzahl in eine Binärzahl um.
	 * @param z - die umzurechnende Dezimalzahl
	 * @return z im Binärystem
	 */
	public static long dezToBin (long z) {
		long result = 0, i = 0;
		while (z != 0) {
			result += Math.pow(10, i) * (z % 2);
			z /= 2;
			i++;
		}
		return result;
	}
	
	/**
	 * Wandelt eine Dezimalzahl in eine Hexadezimalzahl um.
	 * @param z - die umzurechnende Dezimalzahl
	 * @return z im Hexadezimalsystem
	 */
	public static String dezToHex (int z) {
		if(z < 16) {
			switch (z) {
			case 10:
				return "A";
			case 11:
				return "B";
			case 12:
				return "C";
			case 13:
				return "D";
			case 14:
				return "E";
			case 15:
				return "F";
			default:
				String x = String.valueOf(z);
				return x;
			}
		} else {
			return (dezToHex(z / 16)) + (dezToHex(z % 16));
		}
	}

	/**
	 * Wandelt eine Binärzahl in eine Hexadezimalzahl um.
	 * @param z - die umzurechnende Binärzahl
	 * @return z im Hexadezimalsystem
	 * @throws Exception Wenn es keine Binärzahl ist.
	 */
	public static String binToHex (long z) throws InputMismatchException {
		return dezToHex(binToDez(z));
	}

	/**
	 * Wandelt eine Binärzahl in eine Dezimalzahl um.
	 * @param z - die umzurechnende Binärzahl
	 * @return z im Dezimalsystem
	 * @throws Exception Wenn die Zahl nicht binär ist.
	 */
	public static int binToDez (long z) throws InputMismatchException {
		int result = 0;
		int i = 0;
		while(z != 0) {
			if(z % 10 != 0 && z % 10 != 1) {
				throw new InputMismatchException();
			}
			result += (z % 10) * (long)Math.pow(2, i);
			i++;
			z /= 10;
		}
		return result;
	}

	/**
	 * Wandelt eine Hexadezimalzahl in eine Binärzahl um.
	 * @param z - die umzurechnende Hexadezimalzahl
	 * @return z im Binärsystem
	 */
	public static long hexToBin (String z) {
		return dezToBin(hexToDez(z));
	}

	/**
	 * Wandelt eine Hexadezimalzahl in eine Dezimalzahl um.
	 * @param z - die umzurechnende Hexadezimalzahl
	 * @return z im Dezimalsystem
	 */
	public static int hexToDez (String z) {
		if (z.length() == 1) {
			switch (z) {
			case "A":
				return 10;
			case "B":
				return 11;
			case "C":
				return 12;
			case "D":
				return 13;
			case "E":
				return 14;
			case "F":
				return 15;
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "9":
				return Integer.parseInt(z);
			default:
				throw new InputMismatchException();
			}
		} else {
			StringBuilder zahl = new StringBuilder(z);
			String letzteZiffer = zahl.substring(zahl.length() - 1);
			zahl = zahl.deleteCharAt(zahl.length() - 1);
			return hexToDez(zahl.toString()) * 16 + hexToDez(letzteZiffer);
		}
	}
	public static void main(String[] args) {
		boolean menu = true;
		Scanner sc = new Scanner(System.in);
		while (menu) {
			System.out.println("Bitte gib die Basis des Ausgangszahlensystems an. Gib -1 ein um das Programm zu beenden.");
			int ausgang;
			try {
				ausgang = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Dies ist keine Zahl.");
				ausgang = -1;
			}
			switch (ausgang) {
				case 2:
				case 10:
				case 16:
					System.out.println("Bitte gib die Basis des Zielzahlensystems an. Gib -1 ein um das Programm zu beenden.");
					int ziel;
					try {
						ziel = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("Dies ist keine Zahl.");
						ziel = -1;
					}
					switch (ziel) {
						case 2:
						case 10:
						case 16:
							switch (ausgang) {
								case 2:
									System.out.println("Bitte gib die umzuwandelnde Zahl ein.");
									int binZahl;
									try {
										binZahl = sc.nextInt();
										System.out.println("~~~~~~~~~~~~~~~~~~~~RESULTAT~~~~~~~~~~~~~~~~~~~~");
										if (ziel == 2) {
											System.out.println("Die Binärzahl " + binZahl + " ist " + dezToBin(binToDez(binZahl)) + " im Binärsystem.");
										} else if (ziel == 10) {
											System.out.println("Die Binärzahl " + binZahl + " ist " + binToDez(binZahl) + " im Dezimalsystem.");
										} else if (ziel == 16) {
											System.out.println("Die Binärzahl " + binZahl + " ist " + binToHex(binZahl) + " im Hexadezimalsystem.");
										}
										System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
										} catch (InputMismatchException g) {
											System.out.println("Das ist keine Binärzahl.\nProgramm wird beendet.");
											menu = false;
										}	
									break;
								case 10:
									System.out.println("Bitte gib die umzuwandelnde Zahl ein.");
									int dezZahl;
									try {
										dezZahl = sc.nextInt();
										System.out.println("~~~~~~~~~~~~~~~~~~~~RESULTAT~~~~~~~~~~~~~~~~~~~~");
										if (ziel == 2) {
											System.out.println("Die Dezimalzahl " + dezZahl + " ist " + dezToBin(dezZahl) + " im Binärsystem.");
										} else if (ziel == 10) {
											System.out.println("Die Dezimalzahl " + dezZahl + " ist " + dezZahl + " im Dezimalsystem.");
										} else if (ziel == 16) {
											System.out.println("Die Dezimalzahl " + dezZahl + " ist " + dezToHex(dezZahl) + " im Hexadezimalsystem.");
										}
										System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
									} catch (InputMismatchException f) {
										System.out.println("Dies ist keine Dezimalzahl.\nProgramm wird beendet.");
										menu = false;
									}
									break;
								case 16:
									System.out.println("Bitte gib die umzuwandelnde Zahl ein.");
										try {
										String hexZahl = sc.next();
										System.out.println("~~~~~~~~~~~~~~~~~~~~RESULTAT~~~~~~~~~~~~~~~~~~~~");
										if (ziel == 2) {
											System.out.println("Die Hexadezimalzahl " + hexZahl + " ist " + hexToBin(hexZahl) + " im Binärsystem.");
										} else if (ziel == 10) {
											System.out.println("Die Hexadezimalzahl " + hexZahl + " ist " + hexToDez(hexZahl) + " im Dezimalsystem.");
										} else if (ziel == 16) {
											System.out.println("Die Hexadezimalzahl " + hexZahl + " ist " + dezToHex(hexToDez(hexZahl)) + " im Hexadezimalsystem.");
										} 
										System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
									} catch (InputMismatchException q) {
										System.out.println("Das ist keine Hexadezimalzahl.\tProgramm wird beendet.");
										menu = false;
									}
									break;
							}
							break;
						case -1:
							System.out.println("Programm wird beendet.");
							menu = false;
							break;
						default:
							System.out.println("Dies ist kein unterstütztes Zahlensystem.");	
					}
					break;
				case -1:
					menu = false;
					System.out.println("Programm wird beendet.");
					break;
				default:
					System.out.println("Dies ist kein unterstütztes Zahlensystem.");	
			}
		}
		sc.close();
	}
}
