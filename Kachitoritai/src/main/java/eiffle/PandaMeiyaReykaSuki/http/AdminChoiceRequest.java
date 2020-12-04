package eiffle.PandaMeiyaReykaSuki.http;

public class AdminChoiceRequest {
	public int daysToDelete;
	
	public int getDaysToDelete() {return daysToDelete;}
	public void setDaysToDelete(int daysToDelete) {this.daysToDelete = daysToDelete;}
	
	public AdminChoiceRequest() {}
	
	public AdminChoiceRequest(int daysToDelete) {
		this.daysToDelete = daysToDelete;
	}
}
