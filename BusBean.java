package com.jsp.booking;

public class BusBean {
	private int busid;
	private String from;
	private String to;
	private String time;
	private int price;
	
	public void setBusid(int busid) {
		this.busid = busid;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getBusid() {
		return busid;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getTime() {
		return time;
	}

	public int getPrice() {
		return price;
	}

	public BusBean(int busid, String from, String to, String time, int price) {
		
		
		this.busid = busid;
		this.from = from;
		this.to = to;
		this.time = time;
		this.price = price;
	}
	public BusBean()
	{
		
	}
	public void busDetails()
	{
		System.out.println("*");
		System.out.println("Bus id : "+busid);
		System.out.println("Starting from : "+from);
		System.out.println("Destination : "+to);
		System.out.println("Time : "+time);
		System.out.println("Ticket Price : "+price);
		System.out.println("*");
	}

	@Override
	public String toString() {
		return "BusBean [busid=" + busid + ", from=" + from + ", to=" + to + ", time=" + time + ", price=" + price
				+"]";
	}

	

}
