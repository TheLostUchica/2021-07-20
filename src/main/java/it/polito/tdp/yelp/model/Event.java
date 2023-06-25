package it.polito.tdp.yelp.model;

public class Event implements Comparable<Event>{
	
	enum tipo{
		I,//casistica 1
		III,//casistica 3
		FINE//fine
	}
	
	tipo evento;
	int giornalista;
	User user;
	int time;
	
	public Event(tipo evento, int giornalista, User user, int time) {
		super();
		this.evento = evento;
		this.giornalista = giornalista;
		this.user = user;
		this.time = time;
	}

	public tipo getEvento() {
		return evento;
	}

	public void setEvento(tipo evento) {
		this.evento = evento;
	}

	public int getGiornalista() {
		return giornalista;
	}

	public void setGiornalista(int giornalista) {
		this.giornalista = giornalista;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Event [evento=" + evento + ", giornalista=" + giornalista + ", user=" + user + ", time=" + time + "]";
	}

	@Override
	public int compareTo(Event o) {
		return this.time-o.getTime();
	}
	
	
	

}
