package Friend_List_Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class GUI {

   private JFrame fr = new JFrame("Friend Management Program");
   private JPanel p1;
   private JPanel p2;
   private JPanel p3;

   JLabel[] main_label = new JLabel[6];
   String[] main_label_name = { "Check Box", "Name", "Group", "Telephone Num", "E-mail", "Picture" };

   JButton[] main_button = new JButton[5];
   String[] main_button_name = { "Add", "Delete", "Modify", "Save File" };

   int i, j = 0;

   FriendListFile flf = new FriendListFile();
   FriendList fl = new FriendList();
   String fileName = "/Users/jamie/대학교 관련 자료/2019년 2-1 대학교 과제/소프트웨어프로젝트1/make 2/src/Friend_List_Management/friendlist-norm.data";
   String saveFile = "/Users/jamie/대학교 관련 자료/2019년 2-1 대학교 과제/소프트웨어프로젝트1/make 2/src/Friend_List_Management/saveData.txt";

   InputText it;

   	public void preGUI() {

      fl = flf.readFileToList(fileName);
      it = new InputText(fl);
      fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // GUI turn-off when press close
      fr.setLayout(new BorderLayout());
      fr.setSize(700, 500);

      p1 = new JPanel(); // 4 buttons will be included
      p2 = new JPanel();  // 6 labels will be included
      p3 = new JPanel(); // p2 panel and TextField will be included

      p1.setLayout(new GridLayout(4, 1)); 
      p2.setLayout(new GridLayout(1, 6)); 
      p3.setLayout(new BorderLayout()); 

      p3.add(p2, BorderLayout.NORTH);

      fr.add(p1, BorderLayout.EAST);
      fr.add(p3, BorderLayout.CENTER);

      showGUI(fl);
   }

   	public void showGUI(FriendList fl) {

      MyActionListener btn = new MyActionListener();
      for (i = 0; i < main_button_name.length; i++) {
         main_button[i] = new JButton(main_button_name[i]);
         main_button[i].addActionListener(btn);
         p1.add(main_button[i]);
      }
      
      for (i = 0; i < main_label_name.length; i++) {
         main_label[i] = new JLabel(main_label_name[i], JLabel.CENTER);
         p2.add(main_label[i]);
      }

      p3.add(it, BorderLayout.CENTER);
      fr.setVisible(true);
   }

   class MyActionListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {

         JButton select_btn = (JButton) e.getSource();
         for (i = 0; i < main_button_name.length; i++) {
            if (select_btn.getText().equals(main_button_name[i])) {
               int itemNum = i + 1;
               System.out.println(Arrays.toString(it.getCheckList()));
               choiceAction(itemNum);
            }
         }
      }

      private void choiceAction(int itemNum) {
         switch (itemNum) {
         case 1:
            add();
            break;
         case 2:
            delete();
            break;
         case 3:
            modify();
            break;
         case 4:
            saveFile(saveFile);
            break;
         default:
            close();
         }
      }

      private void add() {

         System.out.println("One of your friend is added.");

         JFrame add_frame = new JFrame("Add Friend");
         add_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���Ḧ ������ ������
         add_frame.setLayout(new BorderLayout());
         add_frame.setSize(700, 200);
         add_frame.setVisible(true);

         JPanel p1_add = new JPanel();
         p1_add.setLayout(new GridLayout(2, 5));

         JButton button_add = new JButton("Add");

         JLabel name = new JLabel("Name", JLabel.CENTER);
         p1_add.add(name);

         JLabel group = new JLabel("Group", JLabel.CENTER);
         p1_add.add(group);

         JLabel teleNum = new JLabel("Telephone Number", JLabel.CENTER);
         p1_add.add(teleNum);

         JLabel email = new JLabel("E-mail", JLabel.CENTER);
         p1_add.add(email);

         JLabel photo = new JLabel("Photo", JLabel.CENTER);
         p1_add.add(photo);

         JTextField mem_name = new JTextField(9);
         p1_add.add(mem_name);

         JTextField mem_group = new JTextField(9);
         p1_add.add(mem_group);

         JTextField mem_teleNum = new JTextField(9);
         p1_add.add(mem_teleNum);

         JTextField mem_email = new JTextField(9);
         p1_add.add(mem_email);

         JTextField mem_photo = new JTextField(9);
         p1_add.add(mem_photo);

         add_frame.add(p1_add, BorderLayout.CENTER);
         add_frame.add(button_add, BorderLayout.EAST);
         
         button_add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               add_frame.dispose();
               Friend f = new Friend(mem_name.getText(), mem_group.getText(), 
            		   mem_teleNum.getText(), mem_email.getText(), 
            		   mem_photo.getText());
               fl.addfriend(f);
               saveFile(saveFile);
               FriendList fl = new FriendList();
               fl = flf.readFileToList(saveFile);
               it = new InputText(fl); 
               restartGUI();
               showGUI(fl);
            }
         });
      }

      private void delete() {
         System.out.println("One of your friend is deleted.");
         for(int i = 0; i < fl.numFriends(); i++) {
			
			int[] checkArray = new int[fl.numFriends()];
			checkArray = it.getCheckList().clone();
			
			if(checkArray[i] == 1) {
				fl.delFriend(i);
				saveFile(saveFile);
				FriendList fl = new FriendList();
				fl = flf.readFileToList(saveFile);
				it = new InputText(fl); 
				restartGUI();
				showGUI(fl);
			}
		 }
	 }

      private void modify() {
         System.out.println("One of your friend information is modified.");
         
    	 JFrame modify_frame = new JFrame("Modify Friend");
		 modify_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���Ḧ ������ ������
		 modify_frame.setLayout(new BorderLayout());
		 modify_frame.setSize(700, 200);
		 modify_frame.setVisible(true);

         JPanel p1_modify = new JPanel();
         p1_modify.setLayout(new GridLayout(2, 5));

         JButton button_modify = new JButton("Modify");

         JLabel name = new JLabel("Name", JLabel.CENTER);
         p1_modify.add(name);

         JLabel group = new JLabel("Group", JLabel.CENTER);
         p1_modify.add(group);

         JLabel teleNum = new JLabel("Telephone Number", JLabel.CENTER);
         p1_modify.add(teleNum);

         JLabel email = new JLabel("E-mail", JLabel.CENTER);
         p1_modify.add(email);

         JLabel photo = new JLabel("Photo", JLabel.CENTER);
         p1_modify.add(photo);

         JTextField mem_name = new JTextField(9);
         p1_modify.add(mem_name);

         JTextField mem_group = new JTextField(9);
         p1_modify.add(mem_group);

         JTextField mem_teleNum = new JTextField(9);
         p1_modify.add(mem_teleNum);

         JTextField mem_email = new JTextField(9);
         p1_modify.add(mem_email);

         JTextField mem_photo = new JTextField(9);
         p1_modify.add(mem_photo);

         modify_frame.add(p1_modify, BorderLayout.CENTER);
         modify_frame.add(button_modify, BorderLayout.EAST);
         
         button_modify.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	
                for(int i = 0; i < fl.numFriends(); i++) {
               	 
         			int[] checkArray = new int[fl.numFriends()];
         			checkArray = it.getCheckList().clone();

         			if(checkArray[i] == 1) {
         				
                        Friend f = new Friend(fl.getFriend(i).getName(), mem_group.getText(), 
                     		   mem_teleNum.getText(), mem_email.getText(), mem_photo.getText());
                        
         				fl.modifyfriend(i, f);
        				saveFile(saveFile);
        				FriendList fl = new FriendList();
        				fl = flf.readFileToList(saveFile);
        				it = new InputText(fl); 
        				restartGUI();
        	            showGUI(fl);
         			}
         		 }
                fl.printAllFriend();
              }
          });
      }

      private void saveFile(String saveFile) {
         System.out.println("Friend List is saved.");
         FileWriter fw = null;
         try {
            fw = new FileWriter(saveFile);
            for (int i = 0; i < fl.numFriends(); i++) {
               fw.write(fl.getFriend(i).getName());
               fw.write(" : ");
               fw.write(fl.getFriend(i).getGroup());
               fw.write(" : ");
               fw.write(fl.getFriend(i).getphoneNumber());
               fw.write(" : ");
               fw.write(fl.getFriend(i).getEmail());
               fw.write(" : ");
               fw.write(fl.getFriend(i).getPhoto());
               fw.write("\r\n");
            }
         } catch (Exception e) {
            e.printStackTrace();
         } finally {
            try {
               fw.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      }

      private void close() {
         System.exit(0);
      }
      
   }
   void restartGUI() {
       fr.dispose();
       p1.removeAll();
       p2.removeAll();
       p3.removeAll();
   }
}