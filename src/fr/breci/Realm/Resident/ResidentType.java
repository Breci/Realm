package fr.breci.Realm.Resident;

public enum ResidentType{
	RESIDENT("Resident"),
	MAYOR("Maire"),
	GENERAL("General");
	
	String name = "";
	
	ResidentType(String name){
		this.name = name;
	}
	
	public ResidentType getStatus(String s){
		return ResidentType.valueOf(s);
	}
}
