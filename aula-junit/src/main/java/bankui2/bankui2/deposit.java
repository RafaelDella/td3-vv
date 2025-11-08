package bankui2.bankui2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.sql.*;
 class deposit  extends JFrame implements ActionListener {
	JButton jb,jb1;
	JTextField tf3,tf1,tf2;
	JFrame frame1;
	static JTable table;
	String [] columnNames={"Account No","Name","Balance"};
	long ss1,ss2,balance,amount;
	int i;
	deposit(){
		Container c =getContentPane();
		c.setLayout(null);
		c.setBackground(Color.orange);
		
		jb=new JButton("submit");//  button jb ,1
		jb.setBounds(200,370,100,50);
		jb.addActionListener(this);
		
		
		JLabel jl2=new JLabel("account number");
		jl2.setBounds(10,60,100,50);
		JLabel jl3=new JLabel("amount");
		jl3.setBounds(10,110,100,50);
		
		tf2= new JTextField();
		tf2.setBounds(110,70,200,40);
		tf3= new JTextField();
		tf3.setBounds(110,120,200,40);
		tf2.setText("ACCOUNT OR AADHAR NUMBER ");
		tf3.setText(" AMOUNT ");
		
		c.add(tf2);c.add(tf3);
		
		c.add(jl2);c.add(jl3);
		c.add(jb);
}
	long getamount(long ss1)
	{
		 long bal=0;
		 ResultSet rs;
		try{ Class.forName("oracle.jdbc.driver.OracleDriver");
         Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "hr");
         PreparedStatement ps = con.prepareStatement("select amount from test4 where account="+ss1+"");
         rs =ps.executeQuery();
         while(rs.next())
         {
         bal=rs.getInt("amount");
         }
		 }catch(Exception ex) 
        {
            System.out.println(ex);
        }
		
		return bal ;
		
	}
	

public void actionPerformed(ActionEvent e){
	
	if(e.getSource()==jb){
		ShowTableData();
		ShowTableData1();
	}
	

}
public void ShowTableData()
	{
	     
		
		String s1 = tf2.getText();
		ss1 = Long.parseLong(s1);
		String s2 =tf3.getText();
		ss2 =Long.parseLong(s2);
		
		
		long bal=getamount(ss1);
		bal=bal+ss2;
		  try{
		       Class.forName("oracle.jdbc.driver.OracleDriver");
	           Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "hr");
	           PreparedStatement ps = con.prepareStatement("update test4 set amount ="+bal+"where account="+ss1 +" ");
	           ResultSet rs =ps.executeQuery();
	        
	   		
		  }catch(Exception ex) 
          {
			  JOptionPane.showMessageDialog(null,"Amount can not be DEPOSITED");
			  System.out.println(ex);
          }
	

	}
public void ShowTableData1()
	{
		
		frame1=new JFrame("Database Search Result");
		
		frame1.setLayout(new BorderLayout());
		DefaultTableModel model=new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		table=new JTable();
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		JScrollPane scroll=new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		String s1 = tf2.getText();
	ss1 = Long.parseLong(s1);
	long accountno=0;
	String name="";
	long amount=0;
	
  try{
   Class.forName("oracle.jdbc.driver.OracleDriver");
   Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "hr");
   PreparedStatement ps = con.prepareStatement("select name,account,amount from test4 where account="+ss1+"");
   ResultSet rs =ps.executeQuery();
   tf2.setText("ACCOUNT OR AADHAR NUMBER ");
	tf3.setText(" AMOUNT ");
	
   int i=0;
   if(rs.next())
   {
	   accountno=rs.getLong("account");
	   name=rs.getString("name");
	   amount=rs.getLong("amount");
	   model.addRow(new Object[]{accountno,name,amount});
	   i++;
   }
   if(i<1)
   {
	  JOptionPane.showMessageDialog(null,"No Record Found" ,"Error",JOptionPane.ERROR_MESSAGE);
	  
	 
	
   }
   if(i==1)
   {
	   System.out.println(i+"Record Found");
   }
   else
   {
	   System.out.println(i+"Records Found");
   }
	  }catch(Exception ex) 
  {
      System.out.println(ex);
  }
  frame1.add(scroll);
  frame1.setVisible(true);
  frame1.setSize(400,300);

	}
	
	public static void main(String[] args) {
		
		deposit h=new deposit();
        h.setSize(500,500);
		h.setTitle("deposite");
		h.setVisible(true);
		
		
	}

}
