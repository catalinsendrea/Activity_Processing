
public class MainClass {

	public static void main(String[] args) {
		
	MonitoredData m = new MonitoredData(null, null, null);
	
	m.Printare();
	System.out.println();
	m.numara_zile(m.createList());
	System.out.println();
	System.out.println();
	m.numara_activitati(m.createList());
	}

}
