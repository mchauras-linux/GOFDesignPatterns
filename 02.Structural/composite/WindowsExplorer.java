package com.jpm.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class WindowsExplorer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Directory d1= new Directory("D1");
		Directory d12= new Directory("D12",d1);
		Directory d13= new Directory("D13");
		Directory d2= new Directory("D2");
		Directory d21= new Directory("D21",d2);
		Directory d22= new Directory("D22");
		
		File f1 = new File("File1",100);
		File f111 = new File("File111",200);
		File f121 = new File("File121",300);
		File f112 = new File("File112",400);
		File f113 = new File("File113",500);
		File f122 = new File("File122",600);
		File f132 = new File("File132",700);
		File f131 = new File("File131",800);
		File f130 = new File("File130",900);
		File f2 = new File("File2",150);
		File f212 = new File("File212",250);
		File f213 = new File("File213",350);
		File f223 = new File("File223",450);
		File f210 = new File("File210",550);
		File f214 = new File("File214",650);
		
		d1.add(d13);
		d2.add(d22);
		d1.add(f1);
		d1.add(f111);
		d12.add(f121);
		d12.add(f122);
		d13.add(f132);
		d13.add(f131);
		
		d1.getSize();		
	}
}

interface StorageUnit {
	
	public void rename(String name);
	public int getSize();
	public boolean delete();
	public String getName();

}

class File implements StorageUnit {	
	private int size;
	private String name; 	
	public File(String name, int size){
		this.size = size;
		this.name = name;
	}
	@Override
	public boolean delete() {
		
		return false;
	}
	@Override
	public int getSize() {		
		return size;
	}
	@Override
	public void rename(String name) {
		this.name = name;		
	}
	@Override
	public String getName() {		
		return this.name;
	}
}

class Directory implements StorageUnit {
	//composite of other Storage units
	protected List<StorageUnit> childStorageUnits = 
		new ArrayList<StorageUnit>(); 
	private String folderName; 
	
	public Directory(String folderName)
	{
		this.folderName = folderName;
	}
	
	public Directory(String folderName, Directory d)
	{
		this.folderName = folderName;
	}
	
	@Override
	public boolean delete() {		
		for (StorageUnit childStorageUnit : childStorageUnits) {
			childStorageUnit.delete();					
		}
		deleteFolder(); // delete the folder itself
		return true;
	}
	@Override
	public int getSize() {
		int foldersize =0; 		 
		for (StorageUnit childStorageUnit : childStorageUnits) {
			int childStorageUnitSize = childStorageUnit.getSize();			
			foldersize += childStorageUnitSize; 			
		} 		
		System.out.println(this.folderName +" - "+ foldersize);
		return foldersize;
	}
	@Override
	public void rename(String folderName) {
		this.folderName = folderName;
	}
	@Override
	public String getName() {
		return folderName;
	}	
	public void add(StorageUnit childStorageUnit) { 
		childStorageUnits.add(childStorageUnit); 
	}	
	public void delete(StorageUnit childStorageUnit) {
		childStorageUnits.remove(childStorageUnit);
	}
	
	private boolean deleteFolder() {
		// delete self
		return true;		
	}

}
