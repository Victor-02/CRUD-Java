package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		Department dpt = new Department(1, "TI");
		Seller sel = new Seller(1, "Maria", "maria@maria.com", new Date(), 2000.00, dpt);

		System.out.println(dpt);
		System.out.println(sel);
	}

}
