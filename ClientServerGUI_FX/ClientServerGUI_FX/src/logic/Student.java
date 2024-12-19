package logic;


public class Student {

	private int id;
	private String name;
	private int history;
	private String number;
	private String email;
	
	
	/**
	 * @param id
	 * @param name
	 * @param name2
	 * @param fc
	 */
    // Default constructor
    public Student() {
        this.id = -1; // Default value for invalid student
        this.name = "";
        this.history = 0;
        this.number = "";
        this.email = "";
    }
	
	public Student(int id, String name, int history, String number, String email) {
		super();
		this.id = id;
		this.name = name;
		this.history = history;
		this.number = number;
		this.email = email;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
		//System.out.println("ID set to "+id);
	}
	/**
	 * @return the lName
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @param name the lName to set
	 */
	public void setName(String name) {
		this.name = name;
		//System.out.println("Last name set to "+name);
	}
	/**
	 * @return the pName
	 */
	public String getNumber() {
		return this.number;
	}
	/**
	 * @param name the pName to set
	 */
	public void setNumber(String number) {
		this.number = number;
		//System.out.println("Private name set to "+name);
	}
	
	/**
	 * @return the pName
	 */
	public int getHistory() {
		return this.history;
	}
	/**
	 * @param name the pName to set
	 */
	public void setHistory(int history) {
		this.history = history;
		//System.out.println("Private name set to "+name);
	}
	
	/**
	 * @return the pName
	 */
	public String getEmail() {
		return this.email;
	}
	/**
	 * @param name the pName to set
	 */
	public void setEmail(String email) {
		this.email = email;
		//System.out.println("Private name set to "+name);
	}
	
	
	public String toString(){
		return String.format("%s %s %s %s %s\n",id, name, history, number, email);
	}
	
}
