package bankui2.bankui2;
import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
class closeaccount extends JFrame implements ActionListener {
	JButton jb1,jb;
	JTextField tf,tf1;
	
	closeaccount (){
		 Container c =getContentPane();
			c.setLayout(null);
			

			jb1=new JButton("submit");
			jb1.addActionListener(this);
			jb1.setBounds(180,370,100,50);
			c.setBackground(Color.ORANGE);
			
			JLabel jl1=new JLabel("account number");
			jl1.setBounds(10,60,100,50);
			
			 tf= new JTextField();//text fields tf,1
			tf.setBounds(110,20,200,40);
			tf1= new JTextField();
			tf1.setBounds(110,70,200,40);
			tf1.setText("ACCOUNT OR AADHAR NUMBER ");
			
			
			c.add(tf1);
			c.add(jl1);
			c.add(jb1);
	}
	long getamount(long ss1)
	{	long i=0;
	long acc=0;
		 ResultSet rs;
		try{ Class.forName("oracle.jdbc.driver.OracleDriver");
         Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "hr");
         PreparedStatement ps = con.prepareStatement("select account from test4 where account="+ss1+"");
         rs =ps.executeQuery();
         while(rs.next())
         {
         acc=rs.getInt("account");
         }
		 }catch(Exception ex) 
        {
            System.out.println(ex);
        }
		
		   return acc ;
		 
	}
	
	
	public void actionPerformed(ActionEvent ae){
	 		
	 		
	 			if (ae.getSource()==jb1){
	 				String s1 = tf1.getText();
	 				long ss1 = Long.parseLong(s1);
	 				 int i=0;
	 				long acc=getamount(ss1);
	 		      try{
	 		       Class.forName("oracle.jdbc.driver.OracleDriver");
	 	           Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "hr");
	 	           PreparedStatement ps = con.prepareStatement("delete from test4 where account="+ss1+"");	 	
	 	           int rs = ps.executeUpdate();
	 	          
	 	          tf1.setText("ACCOUNT OR AADHAR NUMBER ");
	 			if(ss1!=acc){
	 				JOptionPane.showMessageDialog(null," no account found");
	 					
	 			}
	 	     
	 			if (ss1==acc){
	 				JOptionPane.showMessageDialog(null,"  account found");
	 			}
	 		      }catch(Exception ex) 
	 	          {
	 		    	 
	 	              System.out.println(ex);
	 	          }
	 		}
	 		
	 }
	public static void main(String[] args) {
		
		closeaccount caa =new closeaccount ();
			caa.setSize(500,500);
			caa.setTitle(" close account ");
			caa.setVisible(true);	
			
	}

}

