package FileManager;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.persistence.Persistence;

import org.hibernate.engine.jdbc.internal.DDLFormatterImpl;
import org.hibernate.engine.jdbc.internal.Formatter;
public class CreateSchema 
{    
	public static void main(String args[]) {       
	
		 StringWriter stringWriter = new StringWriter();
	        Map<String, Object> properties = new HashMap<String, Object>();
	        properties.put( "javax.persistence.schema-generation.scripts.action",
	            "create" );
	        properties.put(
	                "javax.persistence.schema-generation.scripts.create-target",
	                stringWriter );
	      
	        Persistence.generateSchema( "hibernate-HW2", properties );
	     // Use Hibernate's SQL formatter to format each statement
	        Formatter formatter = new DDLFormatterImpl();
	        Scanner scanner = new Scanner( stringWriter.toString() );
	        while( scanner.hasNextLine() )
	            System.out.println( formatter.format( scanner.nextLine() ) );
	        scanner.close();

	        System.exit( 0 );
		
		
		
		
		
		
		
	}
	
}