package Friend_List_Management;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InputText extends JPanel {
	
   private static final long serialVersionUID = 1L;

   private FriendList src;
   private int[] checkList;
   
   JCheckBox check_box;
   
   InputText(FriendList src) {
	   
      super(new GridLayout(src.numFriends(), 1));
      this.src = src;
      
      checkList = new int[src.numFriends()];
      
      for (int i = 0; i < src.numFriends(); i++) {

         check_box = new JCheckBox();
         src.getFriend(i).setHashCode(check_box.hashCode());
         this.add(check_box);
         check_box.setHorizontalAlignment(SwingConstants.CENTER);
         check_box.addItemListener(new ItemHandler());
         
         JLabel label_name = new JLabel();
         this.add(label_name);
         label_name.setHorizontalAlignment(SwingConstants.CENTER);
         label_name.setText(src.getFriend(i).getName());
         
         JTextField text_group = new JTextField(9);
         this.add(text_group);
         text_group.setHorizontalAlignment(SwingConstants.CENTER);
         text_group.setText(src.getFriend(i).getGroup());
         
         JTextField text_teleNum = new JTextField(9);
         this.add(text_teleNum);
         text_teleNum.setHorizontalAlignment(SwingConstants.CENTER);
         text_teleNum.setText(src.getFriend(i).getphoneNumber());

         JTextField text_email = new JTextField(9);
         this.add(text_email);
         text_email.setHorizontalAlignment(SwingConstants.CENTER);
         text_email.setText(src.getFriend(i).getEmail());

         JTextField text_photo = new JTextField(9);
         this.add(text_photo);
         text_photo.setHorizontalAlignment(SwingConstants.CENTER);
         text_photo.setText(src.getFriend(i).getPhoto());
      }
   }
   
   public int[] getCheckList() {
      return checkList;
   }

   class ItemHandler implements ItemListener {
	  @Override
      public void itemStateChanged(ItemEvent e) {
         JCheckBox checkbox = (JCheckBox) e.getSource();
         for (int i = 0; i < src.numFriends(); i++) {
            if (checkbox.hashCode() == src.getFriend(i).getHashCode()) {
               if (checkbox.isSelected()) {
            	  checkList[i] = 1; } 
            }
         }
      }
   }
}