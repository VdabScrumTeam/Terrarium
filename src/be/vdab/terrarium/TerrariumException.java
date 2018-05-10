package be.vdab.terrarium;

public class TerrariumException extends Exception {
	public TerrariumException(){
        super();
    }
    public TerrariumException(String omschrijving){
        super(omschrijving);
    }
}
