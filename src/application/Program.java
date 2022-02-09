package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		Department dep = new Department(6, null);

		System.out.println("---Test findById---");
		SellerDao sellerDao = DaoFactory.createSellerDao();
		Seller sel = sellerDao.findById(7);
		System.out.println(sel);

		System.out.println("\n---Test update---");
		sel = sellerDao.findById(10);
		sel.setName("Jhon");
		sellerDao.update(sel);
		System.out.println("Updated!");

		System.out.println("\n---Test deleteById---");
		sellerDao.deleteById(9);
		System.out.println("Deleted!");

		System.out.println("\n---Test insert---");
		Seller newsel = new Seller(null, "Victor", "victor@victor.com", new Date(), 2000.00, dep);
		sellerDao.insert(newsel);
		System.out.println("Insert Complete!");

	}

}
