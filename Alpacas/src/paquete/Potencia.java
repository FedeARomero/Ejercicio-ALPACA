package paquete;

import java.math.BigInteger;
import java.util.Map;
import java.util.TreeMap;

public class Potencia {

	private BigInteger base;
	private Map<BigInteger, BigInteger> potencias;

	public Potencia(BigInteger base) {
		this.potencias = new TreeMap<>();
		this.base = base;

		this.potencias.put(BigInteger.ZERO, BigInteger.ONE);
		this.potencias.put(BigInteger.ONE, base);
		this.potencias.put(BigInteger.valueOf(2L), cuadrado(base));
	}

	public BigInteger resolver(BigInteger n) {

		if (n.compareTo(BigInteger.ZERO) < 0)
			throw new RuntimeException("Valos n debe ser positivo");

		if (n.compareTo(BigInteger.valueOf(3L)) < 0)//(n < 3)
			return this.potencias.get(n);

		if (n.mod(BigInteger.valueOf(2L)).equals(BigInteger.ZERO))
			return potencia(n);

		return producto(base, potencia(n.subtract(BigInteger.ONE)));
	}

	private BigInteger potencia(BigInteger n) {

		if (potencias.containsKey(n)) 
			return potencias.get(n);
		
		double exp = Math.log10(n.doubleValue()) / Math.log10(2);

		if (exp % 1 != 0) {
			BigInteger potcuad = BigInteger.valueOf((long)Math.pow(2, (int) exp));
			BigInteger potpar = n.subtract(potcuad);
			potencias.put(n, producto(potencia(potcuad), potencia(potpar)));
		} else
			potencias.put(n, cuadrado(potencia(n.divide(BigInteger.valueOf(2L)))));

		return potencias.get(n);
	}

	public void ver(BigInteger n) {
		potencia(n);

		for (BigInteger key : potencias.keySet()) {
			System.out.println(key + " " + potencias.get(key));
		}
	}

	private BigInteger producto(BigInteger a, BigInteger b) {
		return a.multiply(b);
	}

	private BigInteger cuadrado(BigInteger base) {
		return base.multiply(base);
	}

	public static void main(String[] args) {

		new Potencia(BigInteger.valueOf(8L)).ver(BigInteger.valueOf(32L));
	}

}
