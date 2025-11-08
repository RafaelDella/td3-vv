package bankui2.bankui2;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.*;
import javax.imageio.*;
class createaccount extends JFrame  implements ActionListener {
	JButton jb,jb1;
	 JTextArea ta;
	 JTextField tf1,tf9,tf2,tf3,tf4,tf5,tf6,tf7,tf8;
	 createaccount(){
		Container c =getContentPane();

		c.setLayout(null);
		jb=new JButton("Submit");  
		jb.addActionListener(this);
		jb.setBounds(150,500,100,50);
		
		c.setBackground(Color.ORANGE);

		
		JLabel jl1=new JLabel("account number");
		jl1.setBounds(10,10,130,50);
		JLabel jl2=new JLabel("name");
		jl2.setBounds(10,64,130,50);
		JLabel jl3=new JLabel("Aadhar number");
		jl3.setBounds(10,118,130,50);
		JLabel jl4=new JLabel("pan no");
		jl4.setBounds(10,172,130,50);
		JLabel jl5=new JLabel("amount");
		jl5.setBounds(10,212,130,50);
		JLabel jl6=new JLabel("address");
		jl6.setBounds(10,264,130,50);
		JLabel jl7=new JLabel("age");
		jl7.setBounds(10,310,130,50);
		JLabel jl8=new JLabel("gender");
		jl8.setBounds(10,360,130,50);
		JLabel jl9=new JLabel("email id");
		jl9.setBounds(10,410,130,50);
	
		tf1= new JTextField();
		tf1.setBounds(110,20,200,40);
		tf2= new JTextField();
		tf2.setBounds(110,70,200,40);
		tf3= new JTextField();
		tf3.setBounds(110,120,200,40);
		tf4= new JTextField();
		tf4.setBounds(110,170,200,40);
		tf5= new JTextField();
		tf5.setBounds(110,220,200,40);
		tf6= new JTextField();
		tf6.setBounds(110,270,200,40);
		tf7= new JTextField();
		tf7.setBounds(110,320,200,40);
		tf8= new JTextField();
		tf8.setBounds(110,370,200,40);
		tf9= new JTextField();
		tf9.setBounds(110,420,200,40);
		
        tf1.setText(" ENETR AADHAR NUMBER ");
        tf2.setText(" FULL NAME ");
        tf3.setText(" AADHAR NUMBER ");
        tf4.setText(" PAN NUMBER  ");
        tf5.setText(" AMOUNT  ");
        tf6.setText(" FULL  ADDRESS   ");
        tf7.setText(" AGE  ");
        tf8.setText("  MALE OR FEMALE ");
        tf9.setText(" EMAIL ID  ");
        
		c.add(tf1);c.add(tf2);c.add(tf3);c.add(tf4);
		c.add(tf5);c.add(tf6);c.add(tf7);c.add(tf8);
		c.add(tf9);
		
		c.add(jb);
		
		c.add(jl1);c.add(jl2);c.add(jl3);c.add(jl4);
		c.add(jl5);c.add(jl6);c.add(jl7);c.add(jl8);
		c.add(jl9);
		
		
		

	
	}
	


	
public void actionPerformed(ActionEvent e){
	 
	if (e.getSource()==jb){
			String s1 = tf1.getText();
			long ss1 = Long.parseLong(s1);
	     	String s2 = tf2.getText();
	     	String s3 = tf3.getText();
	     	long ss3 = Long.parseLong(s3);
	     	String s4 = tf4.getText();
	     	String s5 = tf5.getText();
	     	long ss5 = Long.parseLong(s5);
	     	String s6 = tf6.getText();
	     	String s7 = tf7.getText();
	     	int ss7 = Integer.parseInt(s7);
	     	String s8 = tf8.getText();
	     	String s9 = tf9.getText();
	     	
	      try{
	       Class.forName("oracle.jdbc.driver.OracleDriver");
           Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott",	"hr");
           PreparedStatement ps = con.prepareStatement("insert into test4 values(?,?,?,?,?,?,?,?,?)");
           ps.setLong(1, ss1);
           ps.setString(2, s2);
           ps.setLong(3, ss3);
           ps.setString(4, s4);
           ps.setLong(5, ss5);
           ps.setString(6, s6);
           ps.setInt(7, ss7);
           ps.setString(8, s8);
           ps.setString(9, s9);
           
           ResultSet rs = ps.executeQuery();
      
           tf1.setText(" ENETR AADHAR NUMBER ");
           tf2.setText(" FULL NAME ");
           tf3.setText(" AADHAR NUMBER ");
           tf4.setText(" PAN NUMBER  ");
           tf5.setText(" AMOUNT  ");
           tf6.setText(" FULL  ADDRESS   ");
           tf7.setText(" AGE  ");
           tf8.setText("  MALE OR FEMALE ");
           tf9.setText(" EMAIL ID  ");
	      }
	      catch(Exception ex) 
          {
              System.out.println(ex);
          }
	}
}
	public static void main(String[] args) {
		
		createaccount af =new createaccount();
		af.setSize(600,600);
		
		af.setTitle("create new account");
		af.setVisible(true);
		
	
	}

	
}
