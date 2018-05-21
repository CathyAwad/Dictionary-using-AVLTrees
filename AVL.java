import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class AVL {
	
	 static Tree loadFile(Tree t,String fileName){
	        FileReader fr;
	        BufferedReader br;
	        String line;
	        try {
	            fr = new FileReader(fileName);
	            br = new BufferedReader(fr);
	            while((line = br.readLine())!=null){
	                //String[] splitStr = currentLine.trim().split("\\s+");
	                t.root= t.insert( t.root, line.trim());  
	        }
	        } catch (IOException e) {
	            System.out.println("ERROR");
	        }
	        return t;
	        }
	 static Tree deleteBatch(Tree t, String fileName){
		 
		 FileReader fr;
	        BufferedReader br;
	        String line;
	        try {
	            fr = new FileReader(fileName);
	            br = new BufferedReader(fr);
	            while((line = br.readLine())!=null){
	                //String[] splitStr = currentLine.trim().split("\\s+");
	                t.root= t.remove( t.root, line.trim());  
	        }
	        } catch (IOException e) {
	            System.out.println("ERROR file not found");
	        }
	        return t;
	 }
	 static int searchBatch(Tree t, String fileName){
		 FileReader fr;
	        BufferedReader br;
	        String line;
	        int n=0;
	        try {
	            fr = new FileReader(fileName);
	            br = new BufferedReader(fr);
	            while((line = br.readLine())!=null){
	                //String[] splitStr = currentLine.trim().split("\\s+");
	                Boolean f = t.search(t.root, line.trim());
	                if(f)
	                {
	                	System.out.println(line.trim() + " is found");
	                	n++;
	                }
	                else
	                	System.out.println(line.trim() + "is not found");
	        }
	        } catch (IOException e) {
	            System.out.println("ERROR file not found");
	        }
	        return n;
		 
	 }
	 
	 public static void main(String[] args){
	   		
  		 Tree t = new Tree();
	   	
  		int i=1;
  		Scanner c = new Scanner(System.in);
  		
  		char ch;
  		String fileName = "dictionary.txt";
  		
  		do{
  			System.out.println();
  			System.out.println("1. insert ");
              System.out.println("2. search");
              System.out.println("3. delete");
              System.out.println("4. height");
              System.out.println("5. loadFile");
              System.out.println("6. deleteBatch");
              System.out.println("7. searchBatch");
              System.out.println("8. size");
              int choice = c.nextInt();
              c.nextLine();
              switch(choice){
              case 1:
              	System.out.println("Enter element to insert");
                  t.root = t.insert(t.root, c.nextLine() );   
                  //System.out.println("root " + t.root.value);
                  break;                          
              case 2 : 
                  System.out.println("Enter integer element to search");
                 System.out.println("Search result : "+ t.search( t.root, c.nextLine()));
                  break; 
              case 3:
              	System.out.println("Enter integer element to delete");
                  t.root = t.remove( t.root, c.nextLine() );                     
                  break; 
              case 4:
              	System.out.println("height is " + t.root.h);
              	break;
              case 5:
              	loadFile(t, fileName);
              	break;
              case 6:
            	  deleteBatch(t,"deletions.txt");
            	  break;
              case 7:
            	  int n = searchBatch(t, "queries.txt");
            	  System.out.println( n + " items found");
            	  break;
              case 8:
            	  System.out.println("size is " + t.countNodes(t.root));
            	  break;
              }
              
              System.out.print("\nIn order : ");
              t.inorder(t.root);
              
              //t.printLevelOrder();
  		}while(i==1);
  		
  		
  	}

}
