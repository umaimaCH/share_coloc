package chtioui.zelleg.shareloc.restapi.model;

public class Collocation {
	
    private int idcollocation;
    private String name;
    private String iduser;
    public Collocation() {}
    
    public Collocation(String name, String iduser) {
        super();
        this.name = name;
        this.iduser = iduser;
    }
    public int getIdcollocation() {
        return idcollocation;
    }
    public void setIdcollocation(int idcollocation) {
        this.idcollocation = idcollocation;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIduser() {
        return iduser;
    }
    public void setIduser(String iduser) {
        this.iduser = iduser;
    }


}
