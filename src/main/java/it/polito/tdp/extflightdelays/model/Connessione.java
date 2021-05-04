package it.polito.tdp.extflightdelays.model;

public class Connessione {
Airport a1;
Airport a2;
double distanza;
int tot;

public Connessione(Airport a1, Airport a2, double distanza, int tot) {
	super();
	this.a1 = a1;
	this.a2 = a2;
	this.distanza = distanza;
	this.tot = tot;
}

public Airport getA1() {
	return a1;
}

public void setA1(Airport a1) {
	this.a1 = a1;
}

public Airport getA2() {
	return a2;
}

public void setA2(Airport a2) {
	this.a2 = a2;
}

public double getDistanza() {
	return distanza;
}

public void setDistanza(double distanza) {
	this.distanza = distanza;
}

public int getTot() {
	return tot;
}

public void setTot(int tot) {
	this.tot = tot;
}

}
