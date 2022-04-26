package org.ElectroGrid.Project.DataBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ElectroGrid.Project.Model.Bills;

public class BillDAO {

	static HashMap<Integer, Bills> billMap=getBillMap();
	 
	 public BillDAO() {
	  super();
	 
	  if(billMap==null) {
	   billMap=new HashMap<Integer, Bills>();
	   
	   Bills bill1=new Bills(1, "Sajith", "A1010", 2000.00f, "2022.04.18");
	   Bills bill2=new Bills(2, "Gajith", "A1020", 1600.00f, "2022.04.22");
	   Bills bill3=new Bills(3, "Peter", "A1040", 1450.00f, "2022.04.24");
	 
	   billMap.put(1,bill1);
	   billMap.put(2,bill2);
	   billMap.put(3,bill3);
	   }
	 }
	 
	 public List<Bills> getAllBills()
	 {
	  List<Bills> bills = new ArrayList<Bills>(billMap.values());
	  return bills;
	 }
	 
	  public Bills getBills(int billno)
	  {
	  Bills bills= billMap.get(billno);
	  return bills;
	  }
	 
	  public Bills addBills(Bills bills)
	  {
	  bills.setBillNo(billMap.size()+1);
	  billMap.put(bills.getBillNo(), bills);
	  return bills;
	  }
	 
	  public Bills updateBills(Bills bills)
	  {
	  billMap.put(bills.getBillNo(), bills);
	  return bills;
	  }
	 
	  public String deleteBills(int id)
	  {
	   billMap.remove(id);
	   return ("Deleted Successfully");
	  }
	 
	  public static HashMap<Integer, Bills> getBillMap() {
	  return billMap;
	  }
}