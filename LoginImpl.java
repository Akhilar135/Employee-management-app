package com.gds.ey.akhila.employee;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;



public class LoginImpl {
	
	Scanner scanner = new Scanner(System.in);
	List<Login> loginDetails;
	List<Employee> employee;
	
	{
		
		Login log1 = new Login(111, "Akhila@111", LoginType.ADMIN);
		Login log2 = new Login(222, "Hari@222", LoginType.ADMIN);
		Login log3 = new Login(101, "Mini@101", LoginType.USER);
		Login log4 = new Login(102, "Raj@102", LoginType.USER);
		Login log5 = new Login(103, "Anu@103", LoginType.USER);
		Login log6 = new Login(104, "Rahul@104", LoginType.USER);
		Login log7 = new Login(105, "Aryan@105", LoginType.USER);
		
		loginDetails = new ArrayList<Login>();
		loginDetails.add(log1);
		loginDetails.add(log2);
		loginDetails.add(log3);
		loginDetails.add(log4);
		loginDetails.add(log5);
		loginDetails.add(log6);
		loginDetails.add(log7);
		
		
		
	}
	{
		Employee emp1 = new Employee(101, "Mini", EmployeeRole.CEO, LocalDate.of(1973, 05, 26),"204, ABC Tower, 123 Street", "Delhi");
		Employee emp2 = new Employee(102, "Raj", EmployeeRole.CTO, LocalDate.of(1972, 03, 12),"201, Skyland, Naiwala, Sky street", "Kollam");
		Employee emp3 = new Employee(103, "Anu", EmployeeRole.MANAGER, LocalDate.of(1981, 10, 01),"10, Graceland, Grace street", "Kollam");
		Employee emp4 = new Employee(104, "Rahul", EmployeeRole.DEVELOPER, LocalDate.of(1994, 02, 17),"108, Greenland, Green road", "Kannur");
		Employee emp5 = new Employee(105, "Aryan", EmployeeRole.ANALYST, LocalDate.of(1999, 07, 23),"21, Asset Homes, Asset street", "Trivandrum");

		employee = new ArrayList<Employee>();
		employee.add(emp1);
		employee.add(emp2);
		employee.add(emp3);
		employee.add(emp4);
		employee.add(emp5);
		
	}
	
	public LoginImpl() {
		while(true) {
			
			System.out.println("*****Employee Management System*****");
			System.out.println("*************Home Page**************");
			System.out.println("====****==**Please Login**==****====");
		selectOption();
		}
	}
	
	public Login loginType() {
		
		System.out.println("Enter User Id");
		int userId = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Enter Password");
		String password = scanner.nextLine();
		
		for(Login log : loginDetails) {
			
			if(log.getUserId() == userId && log.getPassword().equals(password) ) {
				
				return new Login(userId, password, log.getRole());
			}
			}
			
			return null;
		
			
		}
	
	public void selectOption() {
		try {
		Login loginResult = loginType();
		String loginType = loginResult.getRole().toString();
		
		
		if(loginType == "ADMIN") {
			selectOptionForAdmin();
		}else if(loginType == "USER") {
			selectOptionForUser(loginResult.getUserId());
			
		}
		}catch(Exception e) {
			System.out.println("Incorrect user id or password");
		}
	}
	
	public void selectOptionForAdmin() {
		boolean admin = true;
		while(admin) {
		System.out.println("1. Add New User Login");
		System.out.println("2. Add Employee");
		System.out.println("3. Delete Employee");
		System.out.println("4. Update Employee");
		System.out.println("5. View Employee By Id");
		System.out.println("6. View Employee By City");
		System.out.println("7. Print All Employee");
		System.out.println("8. View All Employees");
		System.out.println("9. Logout");
		
		int option = scanner.nextInt();
		
		switch (option) {
		case 1: {
			
			addNewUserLogin();
			break;
		}
		case 2: {
			
			addEmployee();
			break;
			
		}
		case 3: {
			
			deleteEmployee();
			break;
		}
		case 4: {
			
			updateEmployee();
			break;
		}
		case 5: {
			
			viewEmployeeById();
			break;
		}
		case 6: {
			
			viewEmployeeByCity();
			break;
		}
		case 7: {
			
			printAllEmployeeInFile();
			break;
		}
		case 8: {
			
			viewAllEmployees();
			break;
		}
		case 9: {
			
			admin = adminLogout();
			break;
		}
		default:
			System.out.println("Please select correct option");
		}
		}
	}
	
	

	public void selectOptionForUser(int userId) {
		
		boolean user = true;
		while(user) {
		System.out.println("1. Display Employee Info");
		System.out.println("2. Update Employee");
		System.out.println("3. Update Password");
		System.out.println("4. Logout");
		
		int option = scanner.nextInt();
		
		switch (option) {
		case 1: {
			
			displayEmployeeInfo(userId);
			break;
		}
		case 2: {
			
			updateUserEmployee(userId);
			break;
		}
		case 3: {
			updatePassword(userId);
			break;
		}
		case 4: {
			user = userLogout();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + option);
		}
		}
		
		
	}

	private boolean userLogout() {
		System.out.println("Do you want to logout y/n?");
		scanner.nextLine();
		String ans = scanner.nextLine();
		if(ans.equalsIgnoreCase("y")) {
			
			return false;
		}else {
			return true;
		}
	}

	private void updatePassword(int userId) {
		
		System.out.println("Enter current password");
		scanner.nextLine();
		String currentPassword = scanner.nextLine();
		boolean passwordUpdated = false;
		for(Login log : loginDetails) {
			if(log.getUserId() == userId && log.getPassword().equals(currentPassword)) {
				
				System.out.println("Enter new password");
				String newPassword = scanner.nextLine();
				log.setPassword(newPassword);
				System.out.println("Password Updated");
				passwordUpdated = true;
				break;
			}
		}
		if (!passwordUpdated) {
	        System.out.println("Current password does not match. Password cannot be updated");
	    }
	}

	private void updateUserEmployee(int userId) {
		
		Employee findEmployee = null;
		for(Employee emp : employee) {
			if(emp.getUserId() == userId) {
				findEmployee = emp;
			}
		}
		System.out.println("Enter Name");
		scanner.nextLine();
		String name = scanner.nextLine();
		
		
		System.out.println("Enter Employee Role: CEO/CTO/MANAGER/DEVELOPER/ANALYST/TESTER");
		
		String role = scanner.nextLine().toUpperCase();
		EmployeeRole employeeRole = EmployeeRole.valueOf(role);
		
		System.out.println("Enter Employee Date of Birth in the format dd/MM/yyyy");
		String dateOfBirth = scanner.nextLine();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(dateOfBirth, formatter);
		
		System.out.println("Enter Employee Full Address");
		String address = scanner.nextLine();
		
		System.out.println("Enter Employee City");
		String city = scanner.nextLine();
	
		findEmployee.setName(name);
		findEmployee.setRole(employeeRole);
		findEmployee.setDob(date);
		findEmployee.setFullAddress(address);
		findEmployee.setCity(city);
		System.out.println("Employee info updated");
	}

	private void displayEmployeeInfo(int userId) {
		
		Stream<Employee> stream = employee.stream();
		stream.filter(emp->emp.getUserId() == userId).forEach(e->System.out.println(e));
		
	}

	private void addNewUserLogin() {
		
		boolean ans = true;
		do{
			System.out.println("Enter User Id");
		int userId = scanner.nextInt();
		scanner.nextLine();
		
		boolean userIdExists = loginDetails.stream().anyMatch(log -> log.getUserId() == userId);

        if (userIdExists) {
            System.out.println("User ID already exists. Please enter a different User ID.");
            continue; 
        }
		
		System.out.println("Enter Password");
		String password = scanner.nextLine();
		
		
		System.out.println("Enter UserType: ADMIN/USER");
		
		String type = scanner.nextLine().toUpperCase();
		
		LoginType loginType = LoginType.valueOf(type);
		
		Login log = new Login(userId, password, loginType);
		loginDetails.add(log);
		
		System.out.println("New User Login Added");
		System.out.println("Do you want to continue adding new user login y/n?");
		String ansInput = scanner.nextLine();
		if(!ansInput.equalsIgnoreCase("y")) {
			ans = false;
			}
		}while(ans);
		
	}
	
	private void addEmployee() {
		
		boolean ans = true;
		do{
		System.out.println("Enter User Id");
		int userId = scanner.nextInt();
		scanner.nextLine();
		
		boolean userIdExists = employee.stream().anyMatch(emp -> emp.getUserId() == userId);

        if (userIdExists) {
            System.out.println("User ID already exists. Please enter a different User ID.");
            continue; 
        }
		System.out.println("Enter Name");
		String name = scanner.nextLine();
		
		
		System.out.println("Enter Employee Role: CEO/CTO/MANAGER/DEVELOPER/ANALYST/TESTER");
		//scanner.nextLine();
		String role = scanner.nextLine().toUpperCase();
		EmployeeRole employeeRole = EmployeeRole.valueOf(role);
		
		System.out.println("Enter Employee Date of Birth in the format dd/MM/yyyy");
		String dateOfBirth = scanner.nextLine();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(dateOfBirth, formatter);
		
		System.out.println("Enter Employee Full Address");
		String address = scanner.nextLine();
		
		System.out.println("Enter Employee City");
		String city = scanner.nextLine();
	
		Employee emp = new Employee(userId, name, employeeRole, date, address, city);
		employee.add(emp);
		
		System.out.println("Employee Added");
		
		System.out.println("Do you want to continue adding new employee y/n?");
		String ansInput = scanner.nextLine();
		if(!ansInput.equalsIgnoreCase("y")) {
			ans = false;
			}
		
	}while(ans);
		
	}
	
	private void deleteEmployee() {
		
		System.out.println("Enter Employee id");
		int empId = scanner.nextInt();
		
		for(Employee emp: employee) {
			
			if(emp.getUserId() == empId) {
				
				employee.remove(emp);
				System.out.println("Employee with User Id "+empId+" deleted");
				break;
			}
		}
	}
	
	private void updateEmployee() {
		
		System.out.println("Enter employee user id");
		int empUserId = scanner.nextInt();
		scanner.nextLine();
		
		try {
		Employee findEmployee = null;
		for(Employee emp : employee) {
			if(empUserId == emp.getUserId()) {
				
				findEmployee = emp;
				break;
			}
		}
				
		
			
			if(findEmployee != null) {
		
		System.out.println("Enter Name");
		String name = scanner.nextLine();
		
		
		System.out.println("Enter Employee Role: CEO/CTO/MANAGER/DEVELOPER/ANALYST/TESTER");
		
		String role = scanner.nextLine().toUpperCase();
		EmployeeRole employeeRole = EmployeeRole.valueOf(role);
		
		System.out.println("Enter Employee Date of Birth in the format dd/MM/yyyy");
		String dateOfBirth = scanner.nextLine();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(dateOfBirth, formatter);
		
		System.out.println("Enter Employee Full Address");
		String address = scanner.nextLine();
		
		System.out.println("Enter Employee City");
		String city = scanner.nextLine();
	
		findEmployee.setName(name);
		findEmployee.setRole(employeeRole);
		findEmployee.setDob(date);
		findEmployee.setFullAddress(address);
		findEmployee.setCity(city);
		
		System.out.println("Employee with user id "+empUserId+" updated");
			}
		}catch(Exception e) {
			System.out.println("Employee User Id does not exist");
		}
	}
	
	private void viewEmployeeById() {
		
		System.out.println("Enter employee user id");
		int empUserId = scanner.nextInt();
		try {
		System.out.println("=====================================================================================");
		Stream<Employee> stream = employee.stream();
		stream.filter(emp->emp.getUserId() == empUserId).forEach(e->System.out.println(e));
		}catch(Exception e) {
			System.out.println("Employee does not exist");
		}
//		Employee findEmployee = null;
//		for(Employee emp: employee) {
//			if(emp.getUserId() == empUserId) {
//				
//				findEmployee = emp;
//				break;
//				
//			}
//		}
//		
//		if(findEmployee != null) {
//			System.out.println(findEmployee.getUserId()+" "+ findEmployee.getName()+"  "+findEmployee.getRole()+" "+findEmployee.getDob()+" "+Period.between(findEmployee.getDob(),LocalDate.now()).getYears()+" "+findEmployee.getFullAddress()+" "+findEmployee.getCity());
//		}else {
//			System.out.println("Employee with user id "+empUserId+" does not exist");
//		}
		System.out.println("======================================================================================");
	}
	
	private void viewEmployeeByCity() {
		
		System.out.println("Enter city");
		scanner.nextLine();
		String empCity = scanner.nextLine();
		System.out.println("=======================================================================================");
		
		try {
		Stream<Employee> stream = employee.stream();
		stream.filter(emp->emp.getCity().equals(empCity)).forEach(e->System.out.println(e));
		}catch(Exception e) {
			System.out.println("Employee with city "+empCity+" does not exist");
		}
//		Employee findEmployee = null;
//		for(Employee emp: employee) {
//			if(emp.getCity().equals(empCity)) {
//				
//				findEmployee = emp;
////				break;
//			}
//			if(findEmployee != null) {
//				System.out.println(findEmployee.getUserId()+" "+ findEmployee.getName()+"  "+findEmployee.getRole()+" "+findEmployee.getDob()+" "+Period.between(findEmployee.getDob(),LocalDate.now()).getYears()+" "+findEmployee.getFullAddress()+" "+findEmployee.getCity());
//			}
//		}
		System.out.println("========================================================================================");
	}
	private void printAllEmployeeInFile(){
		
		
			
			Thread t1 = new Thread(new Runnable() {
	            public void run() 
	            {
	            	File file = new File("employeebasket.txt");
	        		try(FileOutputStream fos = new FileOutputStream(file);
	        		ObjectOutputStream oos = new ObjectOutputStream(fos);){
	        			synchronized (employee) {
	        				oos.writeObject(employee);
						}
	        			
	        			System.out.println("Employee data added to Employee-Basket");
	        		} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }
			});
			
			Thread t2 = new Thread(new Runnable() {
	            public void run() 
	            {
	            	try(FileInputStream fis = new FileInputStream("employeebasket.txt");
	        				ObjectInputStream ois = new ObjectInputStream(fis);){
	        			
	        			Object object = ois.readObject();
	        			synchronized (employee) {
	        				employee = (List<Employee>)object;
						}
	        			System.out.println(employee);
	        			 
	        	} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

	            }
			});
			t1.start();
			t2.start();
		}
	
	
	private void viewAllEmployees() {
		System.out.println("==================================================================================================================================");
		System.out.println("User Id\t:\tName\t:\tRole\t:\t\tDob\t:\tAge\t:\tFull Address\t:\t\tCity");
		System.out.println("==================================================================================================================================");
		System.out.println("===================================================================================================================================");
		for(Employee emp: employee) {
			
			System.out.println(emp.getUserId()+"\t\t "+ emp.getName()+" \t\t "+emp.getRole()+" \t\t"+emp.getDob()+" \t\t"+Period.between(emp.getDob(),LocalDate.now()).getYears()+"\t\t "+emp.getFullAddress()+" \t\t"+emp.getCity());
		}System.out.println("==================================================================================================================================");
	}
	
	private boolean adminLogout() {
		System.out.println("Do you want to logout y/n?");
		scanner.nextLine();
		String ans = scanner.nextLine();
		if(ans.equalsIgnoreCase("y")) {
			
			return false;
		}else {
			return true;
		}
	}
	
//	private void employeeSerialization() throws IOException {
//		File file = new File("employeebasket.txt");
//		try(FileOutputStream fos = new FileOutputStream(file);
//		ObjectOutputStream oos = new ObjectOutputStream(fos);){
//			
//			oos.writeObject(employee);
//			System.out.println("Employee data added to Employee-Basket");
//		}
//	}
	
//	private void employeeDeserialization() throws IOException, ClassNotFoundException {
//		try(FileInputStream fis = new FileInputStream("employeebasket.txt");
//				ObjectInputStream ois = new ObjectInputStream(fis);){
//			
//			Object object = ois.readObject();
//			 employee = (List<Employee>)object;
//	}
//
//}

}
	
	
	


