package bz.asd.poc.db4o;

import java.util.Random;

public class Modell {
	
	private String a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p;
	private Random rand;
	private static int STR_LENGTH = 10;
	
	public Modell() {
		rand = new Random();
		a=genString(STR_LENGTH);
		b=genString(STR_LENGTH);
		c=genString(STR_LENGTH);
		d=genString(STR_LENGTH);
		e=genString(STR_LENGTH);
		f=genString(STR_LENGTH);
		g=genString(STR_LENGTH);
		h=genString(STR_LENGTH);
		i=genString(STR_LENGTH);
		j=genString(STR_LENGTH);
		k=genString(STR_LENGTH);
		l=genString(STR_LENGTH);
		m=genString(STR_LENGTH);
		n=genString(STR_LENGTH);
		o=genString(STR_LENGTH);
		p=genString(STR_LENGTH);
	}
	
	private String genString(int length) {
		byte bytes[] = new byte[length];
		rand.nextBytes(bytes);
		String gen = new String(bytes);
		return gen;
	}
	
	public String toString() {
		return a+b+c+d+e+f+g+h+i+j+k+l+m+n+o+p;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}

	public String getH() {
		return h;
	}

	public void setH(String h) {
		this.h = h;
	}

	public String getI() {
		return i;
	}

	public void setI(String i) {
		this.i = i;
	}

	public String getJ() {
		return j;
	}

	public void setJ(String j) {
		this.j = j;
	}

	public String getK() {
		return k;
	}

	public void setK(String k) {
		this.k = k;
	}

	public String getL() {
		return l;
	}

	public void setL(String l) {
		this.l = l;
	}

	public String getM() {
		return m;
	}

	public void setM(String m) {
		this.m = m;
	}

	public String getN() {
		return n;
	}

	public void setN(String n) {
		this.n = n;
	}

	public String getO() {
		return o;
	}

	public void setO(String o) {
		this.o = o;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getG() {
		return g;
	}

	public void setG(String g) {
		this.g = g;
	}
	
	

}
