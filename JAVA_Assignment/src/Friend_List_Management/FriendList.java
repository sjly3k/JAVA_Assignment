package Friend_List_Management;
import java.util.ArrayList;

public class FriendList
{
   private ArrayList<Friend> data;
   
   public FriendList() {
	   data = new ArrayList<Friend>();
   }
   
   
   public void addfriend(Friend friend) {
	   data.add(friend);
   }
   
   public ArrayList<Friend> getData() {
	   return data;
   }
   
   public void printAllFriend() {
      for(Friend f : data) {
         f.print();
      }
   }

   public int numFriends() {
      return data.size();
   }

   public Friend getFriend(int i) {
      return data.get(i);
   }
   public Friend delFriend(int i) {
	   return data.remove(i);
   }
   public Friend modifyfriend(int i, Friend friend) {
	   return data.set(i, friend);
   }
 
}

