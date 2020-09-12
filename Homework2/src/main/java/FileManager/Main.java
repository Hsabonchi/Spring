package FileManager;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

//	public static EntityManagerFactory  entityManagerFactory;
//	public static EntityManager entityManager;

	public static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory( "hibernate-HW2" );
						
	public static EntityManager entityManager = entityManagerFactory
						    .createEntityManager();
	
	public static String PARENTFOLDER = null;
	public static String CURRENTFOLDER = null;
	public static Folder folderOBJ;
	
	
	
	
	
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
          

         }	
	}
	
	
	
	
	private static void CreateSubFolder() {
			
		System.out.println("Enter the name of your folder");
		  Scanner in = new Scanner(System.in); 
			String NewFolder = in.nextLine();
			
			Folder Foo = new Folder(NewFolder,folderOBJ);
			entityManager.getTransaction().begin();
			entityManager.persist( Foo );
			entityManager.getTransaction().commit();
			
			ShowSubFolder();	
	}




	public static void CreateParentFolder() {
	
		System.out.println("Enter the name of your folder");
		try (Scanner in = new Scanner(System.in)) {
			String NewFolder = in.nextLine();
			
			Folder Foo = new Folder(NewFolder);
			entityManager.getTransaction().begin();
			entityManager.persist( Foo );
			entityManager.getTransaction().commit();
		}
				   			
	}
	
	
	public static void MainMenu() {
		
		List<Folder> allFolder=entityManager.createQuery("from Folder",
			     Folder.class ).getResultList();

		String header1 = "CS5220 Online File Manager ";
		System.out.println(header1);

		System.out.println();
		System.out.println();

		String N_choice="n) New Folder";
		System.out.println(N_choice);
		String X_choice="x) Exit";
		System.out.println( X_choice);
		
		 //older i : ListFolders
	 	for( int i=0;i<allFolder.size();i++ ) {
	 		if(allFolder.get(i).getParent() == null)
	        System.out.println(i+1+")"+ allFolder.get(i).getName() );
	       }
		 
		System.out.println("Please enter your choice:");
		try (Scanner in = new Scanner(System.in)) {
			String input = in.nextLine();
			System.out.println("You entered string "+input);
				
			if (input.equals("n")) {	
					CreateParentFolder();
			}else if (input.equals("1")) {
				System.out.println("Current folder: Foo");
				PARENTFOLDER="FOO";
				ShowSubFolder();
			}else if (input.equals("2")) {
				System.out.println("Current folder: Bar");
				PARENTFOLDER="Bar";
				ShowSubFolder();
			}
		}

			

	}
	
	public static void ShowSubFolder() {

folderOBJ=entityManager.createQuery(" from Folder e where e.name=:name ",
Folder.class ).setParameter("name", PARENTFOLDER).getSingleResult();

List<Folder> allFolder=entityManager.createQuery(" from Folder where Parent=:Parent ",
	Folder.class ).setParameter("Parent", folderOBJ).getResultList();
			 	
		
		String b_choice="b) Back to parent";
		System.out.println(b_choice);
		String n_choice="n) New Folder";
		System.out.println( n_choice);
		String d_choice="d) Delete current folder";
		System.out.println( d_choice);
		String X_choice="x) Exit";
		System.out.println( X_choice);
		
		 if (allFolder.isEmpty()) {
			 
			System.out.println("There is no sub folder for " +PARENTFOLDER);
			System.out.println("Please back up , Press b or n or d" );
			  
			 Scanner in = new Scanner(System.in);
			 String userInput = in.nextLine();
			 SelectChoice(userInput);
          }
		
		
		
		//older i : ListFolders
	   for( int i=0;i<allFolder.size();i++ ) {
	        System.out.println(i+1+")"+  allFolder.get(i).getName() );
	       }
	    
	 
	 	   Scanner in = new Scanner(System.in);
		   String userInput = in.nextLine();
		   
		   if (!userInput.equals("b") && !userInput.equals("n")&&  !userInput.equals("x")) {
			   CURRENTFOLDER=allFolder.get(Integer.parseInt(userInput)-1).getName();
			   System.out.println("You are in the "+ CURRENTFOLDER );
			   PARENTFOLDER=CURRENTFOLDER;
			   ShowSubFolder();   
		   }
		  
		   SelectChoice(userInput);
		   
	 }
	
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

	public static void main(String args[]) {    
		
	  // show the main Menu
	  MainMenu();
	



	

	}


	
	
	


//		     
//		     Folder Bar = new Folder("Bar" );
//	    	 System.out.println(Bar.getName());
//	      entityManager.getTransaction().begin();
//	     entityManager.persist( Bar );
//	     entityManager.getTransaction().commit();
		
		
		
	     // find by primary key
//		Folder folder=entityManager.createQuery(" from Folder e where e.name=:name ",
//	                Folder.class ).setParameter("name", "Foo").getSingleResult();
//		
//			 Folder Childfolder = new Folder("Folder2",folder);
//		   	 System.out.println(Childfolder.getName());
//		     entityManager.getTransaction().begin();
//		     entityManager.persist( Childfolder );
//		     entityManager.getTransaction().commit();
//	
//	
		
		
	
		
//		 System.out.println(folder.getId());
		
		
		    


	
	
}
