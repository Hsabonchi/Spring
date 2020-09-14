package FileManager;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

	public static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory( "hibernate-HW2" );
						
	public static EntityManager entityManager = entityManagerFactory
						    .createEntityManager();
	
	public static String PARENTFOLDER = null;
	public static String CURRENTFOLDER = null;
	public static String Parrent = null;
	public static Folder folderOBJ;
	// root of deleted tree of folder
	public static String  Delroot;
	static List<String> ArrChildren=new ArrayList<String>();
	//flag that the folder has list of folders
	static boolean Flag = false;
	 // follow list index,
	static int DelCounter;
	
	public static List<Folder> findParent(String folder) {
	List<Folder> Folders;
	
	 if (PARENTFOLDER == null) {
		   Folders=entityManager.createQuery(" from Folder where Parent=:Parent ",
					Folder.class ).setParameter("Parent", PARENTFOLDER).getResultList();
		    return Folders;
	 }else {
		
		 folderOBJ=entityManager.createQuery(" from Folder e where e.name=:name ",
					Folder.class ).setParameter("name", folder).getSingleResult();
		 Folders=entityManager.createQuery(" from Folder where Parent=:Parent ",
					Folder.class ).setParameter("Parent", folderOBJ).getResultList();
		return Folders;
	 }
	 
	
	}
	public static void SelectChoice(String UserChoice) {
		
	
		if (UserChoice.equals("x")) {
			System.out.println(UserChoice);
			 System.exit(0);  
			  entityManager.close();
    	        entityManagerFactory.close();
			}
	    else if (UserChoice.equals("b")) {
		   
		   //System.out.println("back to ") ;
		   Backtoparent();
		   
	       }
		else if (UserChoice.equals("n")) {
			System.out.println(PARENTFOLDER);
			CreateSubFolder();
		
		 } 
		else if (UserChoice.equals("d")) {
			//System.out.println("deleting a folder "+ "Parrent is "+Parrent+"  PARENTFOLDER  is"+Parrent);
			ArrChildren.add(PARENTFOLDER);
			// assign the current folder that being removed to Delroot
			Delroot=PARENTFOLDER;
			//reset the counter back to zero
			DelCounter=0;
			try {
				DeleteFolder();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }	
	}
	

	private static void CreateSubFolder() {
			
		System.out.println("Enter the name of your folder:  ");
		  Scanner in = new Scanner(System.in); 
			String NewFolder = in.nextLine();
			
			Folder Foo = new Folder(NewFolder,folderOBJ);
			entityManager.getTransaction().begin();
			entityManager.persist( Foo );
			entityManager.getTransaction().commit();
			
			ShowSubFolder();	
	}


	public static void CreateParentFolder() {
	
		System.out.println("Current folder : top-level folders  "+"\n");
		System.out.println("Enter the name of your folder :  ");
		try (Scanner in = new Scanner(System.in)) {
			String NewFolder = in.nextLine();
		
	//Create a new parent folder. passing only the name of the folder without parent
			Folder Foo = new Folder(NewFolder);
			entityManager.getTransaction().begin();
			entityManager.persist( Foo );
			entityManager.getTransaction().commit();
			// show all the top-level folders
			MainMenu();  
		}	 			
	}
	
	//PART 1:)  main menu
	public static void MainMenu() {
		
		// get a list of folders where parent is null --Parents folder only  
		List<Folder> ListFolder=entityManager.createQuery("from Folder where Parent IS NULL" ,
			     Folder.class ).getResultList();

		String header1 = "CS5220 Online File Manager ";
		System.out.println(header1);

		System.out.println();
		System.out.println();

		String N_choice="n) New Folder";
		System.out.println(N_choice);
		String X_choice="x) Exit";
		System.out.println( X_choice);
		
		// map for mapping user input as a key with value as folder name
		Map<Integer, String> map = new HashMap<>();
		
		for( int i=0;i<ListFolder.size();i++ ) {
			// initate a map from the list
             map.put(i+1 , ListFolder.get(i).getName());			
        } 
		// print the key and value to the user
		for (Map.Entry entry : map.entrySet())
		{
			
		    System.out.println(entry.getKey() +" ) " + entry.getValue());
		}
		
		System.out.println("Please enter your choice:");
		try (Scanner in = new Scanner(System.in)) {
			String input = in.nextLine();
//			System.out.println("You entered string "+input);
			
			// if the user only press enter without selecting any choice
			if( input.isEmpty()) {
				System.out.println("wrong Input Tray Again !!!");
				// call the main fuction again
				MainMenu();
				}
	// check if the user input is alphabetic
	if ((!input.equals("")) && (input != null) && (input.matches("^[a-zA-Z]*$"))) {
			// if the user select n -- create a new folder at the parent level
			if (input.equals("n")) {	
				CreateParentFolder();
			}else if (input.equals("x")){
				    System.exit(0);  
				    entityManager.close();
	    	        entityManagerFactory.close();	
			      }
			else {
				    System.out.println("wrong Input !!!");
				    MainMenu();	
			      }
			
	            }
			// if non of the above, it means the user entered a numeric number
     else 
			{
//				System.out.println("value from map "+map.get(Integer.parseInt(input)));
				if (map.get(Integer.parseInt(input)) == null) {
					System.out.println("wrong Input !!!");
					MainMenu();	
				}else {
					// variable that hold the parent folder name/ current folder
					Parrent=PARENTFOLDER;
					PARENTFOLDER=map.get(Integer.parseInt(input));
					System.out.println("Current folder : "+PARENTFOLDER+ "\n");
					ShowSubFolder();
				   }
			}
		}
	}
	
	public static void ShowSubFolder() {
		
		List<Folder> allFolder=findParent(PARENTFOLDER);
		
		System.out.println ("Current folder:  " +PARENTFOLDER);	 		
		String b_choice="b) Back to parent";
		System.out.println(b_choice);
		String n_choice="n) New Folder";
		System.out.println( n_choice);
		String d_choice="d) Delete current folder";
		System.out.println( d_choice);
		String X_choice="x) Exit";
		System.out.println( X_choice);
		
		 if (allFolder.isEmpty()) {			 
			System.out.print ("There is no sub folder for ' " +PARENTFOLDER);
			System.out.println(" '   , Please back up , Press b or n or d" );
			System.out.println("Please enter your choice:");
			  
			 Scanner in = new Scanner(System.in);
			 String userInput = in.nextLine();
			 SelectChoice(userInput);
          }
		
		//print all subfolders
	   for( int i=0;i<allFolder.size();i++ ) {
	        System.out.println(i+1+")"+  allFolder.get(i).getName() );
	       }
	       //prompt for user input
	 	   Scanner in = new Scanner(System.in);
		   String userInput = in.nextLine();
		   
//		   !userInput.equals("b") && !userInput.equals("n")&&  !userInput.equals("x")
		   // if the userInput is  numeric 1,2,3
		   if (! userInput.matches("^[a-zA-Z]*$")) {
			   
			   CURRENTFOLDER=allFolder.get(Integer.parseInt(userInput)-1).getName();
//			   System.out.println("Current folder  "+ CURRENTFOLDER +"\n " );
			   Parrent=PARENTFOLDER;
			   PARENTFOLDER=CURRENTFOLDER;
			   //System.out.println("After"+ "Parrent is "+Parrent+"  PARENTFOLDER  is"+Parrent);
			   ShowSubFolder();   
		   }
		   else {
			   SelectChoice(userInput);
		     }	   
	 }
	
	
	// PART 2: a user can enter b to go back to the parent folder
	private static void Backtoparent() {

	folderOBJ=entityManager.createQuery(" from Folder e where e.name=:name ",
			Folder.class ).setParameter("name", PARENTFOLDER).getSingleResult();
	
	 // case1 : reached   parent Folder then show  MainMenu
	 if (folderOBJ.getParent()== null) {
		 System.out.println("Hit null parent index");
		 MainMenu();
	     }
	 else {
		 System.out.println("I'm back to "+folderOBJ.getParent().getName());
		 PARENTFOLDER=folderOBJ.getParent().getName();
		 ShowSubFolder(); 
		 
	     } 	
	}

	
	private static void DeleteFolder() throws InterruptedException {
		
	    folderOBJ=entityManager.createQuery(" from Folder e where e.name=:name ",
				Folder.class ).setParameter("name", PARENTFOLDER).getSingleResult();
					
	   // List of  all folders where parent folder is folderOBJ whic the object of name  PARENTFOLDER
		List<Folder> Folders=entityManager.createQuery(" from Folder where Parent=:Parent ",
			Folder.class ).setParameter("Parent", folderOBJ).getResultList();
						 	
		
		// case 1: leaf folder of subtree
		// delete the root folder of subtree 
		if((PARENTFOLDER.equals(Delroot))&&  (Folders.size()==0)&&(Flag==true)) {
			
			//System.out.println("Case 1 leaf folder of subtree  "+"PARENTFOLDER   " +PARENTFOLDER+"  Parrent "+Parrent);
			List<Folder>f=findParent(PARENTFOLDER);
			
			 entityManager.getTransaction().begin();
			 entityManager.remove(folderOBJ);
			 entityManager.getTransaction().commit();
			 PARENTFOLDER=Parrent;
			 // increase no.of deleted folder
			 DelCounter++;
			 System.out.println("< "+ DelCounter + " > folder(s) deleted");
			 //go to main menu .it is root folder
			 
			 //System.out.println(f.isEmpty()+"  fisEmpty()");
			 
			 if (f.isEmpty()) {
				 MainMenu();
			 }
			 else {
				 ShowSubFolder(); 
			 }
			 
		}
	     // leaf folder.delete it without 	
		 if((Flag==false)&& (Folders.size()==0)) {
			 //System.out.println(" leaf folder.delete it without ");
			// System.out.println("before   "+"PARENTFOLDER  "+PARENTFOLDER+" Parrent "+Parrent);
			 
			 entityManager.getTransaction().begin();
			 entityManager.remove(folderOBJ);
			 entityManager.getTransaction().commit();
			 
			 PARENTFOLDER=Parrent;
			 //System.out.println("After  "+"PARENTFOLDER  "+PARENTFOLDER+" Parrent "+Parrent);
			 System.out.println("<1> folder(s) deleted");
			 
			 if (PARENTFOLDER==null) {
				 MainMenu(); 
			 }else {
				 ShowSubFolder();
			  }
			  
			 
           // leaf of subtree folder.			 
		 }else if((Flag==true)&& (Folders.size()==0)) {
			 //System.out.println(" leaf of subtree folder. ");
			 
			 int index = ArrChildren.size() - 1;
			 String foldername=ArrChildren.remove(index);
			 
			 //System.out.println("Deleted Folder is  "+folderOBJ.getName());
			 entityManager.getTransaction().begin();
			 entityManager.remove(folderOBJ);
			 entityManager.getTransaction().commit();
			 // increase no.of deleted folder
			 DelCounter++;
			 PARENTFOLDER=ArrChildren.get(index-1);
			 //System.out.println("PARENTFOLDER after delete   "+PARENTFOLDER+" ArrChildren.size()  "+ArrChildren.size());
			 DeleteFolder();
			 
		 // a folder has subfolder(s) 
		 }else if(Folders.size()>0) {
			 // sign that folder has list of folders
			 Flag= true;
			 
			 for(int i = 0; i < Folders.size(); i++) {
				 String ChildLevel=Folders.get(i).getName();
		            //System.out.println("currrentChild "+ChildLevel);
				   //System.out.println(  " PARENTFOLDER  "+PARENTFOLDER);
		            ArrChildren.add(ChildLevel);			
		        }
			  			
			for(int i=(ArrChildren.size()-1); i >=0 ; i-- ) {
		         //System.out.println("Processing Folder "+ArrChildren.get(i)+ "ArrChildren.size()"+ArrChildren.size());
		            PARENTFOLDER=ArrChildren.get(i);
		            DeleteFolder();
		        }
		 }
				
	}
	
	public static void main(String args[]) {    
		
	  // show the main Menu
	  MainMenu();
	


	}	
}
