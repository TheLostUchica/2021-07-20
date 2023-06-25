package it.polito.tdp.yelp.model;

public class Arco {

	User u1;
	User u2;
	int peso;
	
	public Arco(User u1, User u2, int peso) {
		super();
		this.u1 = u1;
		this.u2 = u2;
		this.peso = peso;
	}

	public User getU1() {
		return u1;
	}

	public void setU1(User u1) {
		this.u1 = u1;
	}

	public User getU2() {
		return u2;
	}

	public void setU2(User u2) {
		this.u2 = u2;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	@Override
	public boolean equals(Object obj) {
		Arco o = (Arco) obj;
		if(this.getU1().equals(o.getU1()) && this.getU2().equals(o.getU2())) {
			return true;
		}else if(this.getU1().equals(o.getU2()) && this.getU2().equals(o.getU1())) {
			return true;
		}else {
			return super.equals(obj);
		}
	}
	
	
	
}
