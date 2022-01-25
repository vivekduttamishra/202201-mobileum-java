package in.conceptarchitect.ems;

public class Manager extends Employee{

	String project;
	
	public Manager(int id, String name, String project, double salary) {
		
		super(id, name, salary);		
		//subclass memebers must be initailized after super class members have been
		this.project=project;
		
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	
	public String info() {
		
		String employeeInfo= super.info(); //calls overridden super class method
		
		employeeInfo="Manager"+employeeInfo.replace("]", String.format(",%s]", project));		
		
		return employeeInfo;
	}
	
}
