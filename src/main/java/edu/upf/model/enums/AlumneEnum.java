package edu.upf.model.enums;
 
public enum AlumneEnum {
     
    CERCA("alumne/alumneCerca"),
	LLISTA("alumne/alumneLlista");
     
    private String pagina;
     
    private AlumneEnum(String pagina) {
        this.pagina = pagina;
    }
 
    public String getView() {
        return pagina;
    }
}