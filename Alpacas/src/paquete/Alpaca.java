package paquete;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Alpaca {

	private BigInteger[][] base;
	private Map<BigInteger, BigInteger[][]> potencias;

	private BigInteger cero = BigInteger.ZERO;
	private BigInteger uno = BigInteger.ONE;
	private BigInteger dos = uno.add(uno);
	private BigInteger m = BigInteger.valueOf(123456789);

	public Alpaca() {

		base = new BigInteger[2][2];

		base[0][0] = cero;
		base[0][1] = dos;
		base[1][0] = uno;
		base[1][1] = uno;

		potencias = new TreeMap<>();
		potencias.put(uno, base);
		potencias.put(dos, cuadrado(base));
	}
	
	public BigInteger hermosura() {
		BigInteger d = BigInteger.valueOf(23461284L); //234612846789231
		
		BigInteger[][] matriz= potencia(d);
		
		return matriz[1][1].mod(m);
	}
	
	public BigInteger[][] resolver(BigInteger n) {
		
		if( n.equals(uno) )
			potencias.get(n);
		
		if( n.equals(dos) )
			potencias.get(n);
	
		if( n.mod(dos).equals(cero))
			return potencia(n);
		
		return producto(base, potencia(n.subtract(uno)));
			
	}
	
	private BigInteger[][] potencia(BigInteger n){
		
		if( potencias.containsKey(n) )
			return potencias.get(n);
		
		double exp = Math.log10(n.doubleValue()) / Math.log10(2);
		
		if( exp % 1 != 0 ) {
			BigInteger potcuad = BigInteger.valueOf((long)Math.pow(2, (int) exp));
			BigInteger potpar = n.subtract(potcuad);
			
			potencias.put(n, producto(potencia(potcuad), potencia(potpar)));
		}
		else
			potencias.put(n, cuadrado(potencia(n.divide(dos))));
		
		return potencias.get(n);
	}
	
	private BigInteger[][] producto(BigInteger[][] matrizA, BigInteger[][] matrizB) {
		BigInteger[][] producto = new BigInteger[2][2];
		
		producto[0][0] = (matrizA[0][0].multiply(matrizB[0][0])).add(matrizA[0][1].multiply(matrizB[1][0])); //[0][0] * [0][0] + [0][1] * [1][0]
		producto[0][1] = (matrizA[0][0].multiply(matrizB[0][1])).add(matrizA[0][1].multiply(matrizB[1][1])); //[0][0] * [0][1] + [0][1] * [1][1]
		producto[1][0] = (matrizA[1][0].multiply(matrizB[0][0])).add(matrizA[1][1].multiply(matrizB[1][0])); //[1][0] * [0][0] + [1][1] * [1][0]
		producto[1][1] = (matrizA[1][0].multiply(matrizB[0][1])).add(matrizA[1][1].multiply(matrizB[1][1])); //[1][0] * [0][1] + [1][1] * [1][1]

		return producto;
	}

	private BigInteger[][] cuadrado(BigInteger[][] matrizBase) {
		BigInteger[][] cuadrado = new BigInteger[2][2];

		cuadrado[0][0] = (matrizBase[0][0].multiply(matrizBase[0][0])).add(matrizBase[0][1].multiply(matrizBase[1][0])); //[0][0] * [0][0] + [0][1] * [1][0]
		cuadrado[0][1] = (matrizBase[0][0].multiply(matrizBase[0][1])).add(matrizBase[0][1].multiply(matrizBase[1][1])); //[0][0] * [0][1] + [0][1] * [1][1]
		cuadrado[1][0] = (matrizBase[1][0].multiply(matrizBase[0][0])).add(matrizBase[1][1].multiply(matrizBase[1][0])); //[1][0] * [0][0] + [1][1] * [1][0]
		cuadrado[1][1] = (matrizBase[1][0].multiply(matrizBase[0][1])).add(matrizBase[1][1].multiply(matrizBase[1][1])); //[1][0] * [0][1] + [1][1] * [1][1]

		return cuadrado;
	}

	public static void main(String[] args) {

		Alpaca m = new Alpaca();
		
		System.out.println("Hermosura: " + m.hermosura());
	}

}
