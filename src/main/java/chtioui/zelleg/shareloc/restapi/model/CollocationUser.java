package chtioui.zelleg.shareloc.restapi.model;

public class CollocationUser {
	
	 private int idcollocation;
	 private int iduser;
	 
	public CollocationUser() {
		
	}
	public CollocationUser(int idcollocation, int iduser) {
		super();
		this.idcollocation = idcollocation;
		this.iduser = iduser;
	}

	public int getIdcollocation() {
		return idcollocation;
	}
	public void setIdcollocation(int idcollocation) {
		this.idcollocation = idcollocation;
	}
	public int getIduser() {
		return iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	 
	 

}
