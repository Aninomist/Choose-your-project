package eiffle.PandaMeiyaReykaSuki.http;

public class AdminChoiceRequest {
	public float daysToDelete;
	
	public float getDaysToDelete() {return daysToDelete;}
	public void setDaysToDelete(float daysToDelete) {this.daysToDelete = daysToDelete;}
	
	public AdminChoiceRequest() {}
	
	public AdminChoiceRequest(float daysToDelete) {
		this.daysToDelete = daysToDelete;
	}
}
