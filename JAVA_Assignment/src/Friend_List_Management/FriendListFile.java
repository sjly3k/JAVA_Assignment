package Friend_List_Management;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FriendListFile {
   FriendList friendlist = new FriendList();
   
   public FriendList readFileToList(String filename)
   {
      try {
         Scanner sc = new Scanner(new File(filename));
         while(sc.hasNextLine())
         {
            String cut = "/";
            String buffer = sc.nextLine();
            if(buffer.contains(cut)) {
               continue;
            }
            else
               split(buffer);
         }
         sc.close();
      } catch (FileNotFoundException e) {
         System.out.println("Unknown File");
         System.exit(1);
      }
      return friendlist;
   }
   
   public void split(String line) {
	   String split[] = line.split(":");
	   if(split.length != 5) {
		   System.out.println("Something is missing. Please check.");
		   return;
	   }
	   String tName = split[0].trim();
	   String tGroup = split[1].trim();
	   String tNumber = split[2].trim();
	   String tEmail = split[3].trim();
	   String tPhoto = split[4].trim();
	   
	    
	   boolean nullCheck = irregularInputErrorCheck(tName, tGroup, tNumber, tEmail, tPhoto);
	   boolean nameCheck = checkNameError(tName);
	   int emailCheck = checkEmailError(tEmail);
	   int illegalCheck = illegalValueErrorCheck(tGroup);
	   
	   int token = checkTotalError(nullCheck, emailCheck, nameCheck, illegalCheck);
	   if(token == 4)  {
		   Friend fr = new Friend(tName, tGroup, tNumber, tEmail ,tPhoto);
		   friendlist.addfriend(fr);
		   token = 0;
	   }
	   else
		   return;
   }
   public int illegalValueErrorCheck(String group) {
	   
		try {
		} 
		catch(NumberFormatException e) {
			   System.out.println("Illegal value appears : " + group);
			   return -1;
		}
		return 1;
	}
   public boolean irregularInputErrorCheck(String tName, String tGroup, String tNumber, String tEmail, String tPhoto) {
	
	boolean nullCheck = true;
	if(tName.length() == 0) {
		nullCheck = false; 
		System.out.println("Your name is missing!");
	}
	else if(tGroup.length() == 0) {
		nullCheck = false; 
		System.out.println("Your group is missing!");
	}
	else if(tNumber.length() == 0) {
		nullCheck = false; 
		System.out.println("Your number is missing!");
	}
	else if(tEmail.length() == 0) {
		nullCheck = false; 
		System.out.println("Your email is missing!");
	}
	else if(tGroup.length() == 0) {
		nullCheck = false; 
		System.out.println("Your photo is missing!");
	}
	return nullCheck;
}

   public boolean checkNameError(String name) {
	   boolean checkName = false;
	   for(int i = 0; i<friendlist.numFriends(); i++) {
		   if(friendlist.getFriend(i).getName().equals(name)) {
			   checkName = true; 
			   System.out.println("There is a same name.");
		   }
	   }
	   return checkName;
   }
   public int checkEmailError(String email) {
	   int check = email.indexOf("@");
	   if(check == -1) { 
		   System.out.println("Check your E-mail format : " + email);
		   return -1;
	   }
	   else 
		   return 1;
   }
   public int checkTotalError(boolean checkNull, int checkEmail, boolean nameCheck, int illegalCheck) {
	   int token = 0;
	   if(checkNull == true)
		   token++;
	   if(checkEmail == 1)
		   token++;
	   if(nameCheck == false)
		   token++;
	   if(illegalCheck == 1)
		   token++;
	   
	   return token;
   }
}

