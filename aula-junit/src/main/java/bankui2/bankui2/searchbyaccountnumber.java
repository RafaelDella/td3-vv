package bankui2.bankui2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
 class searchbyaccountnumber extends JFrame  implements ActionListener{
	JButton jb1,jb;
	JTextField tf1;               
	JFrame frame1;
	static JTable table;
	String [] columnNames={"Account No","Name","Balance"};
	long ss1;
	searchbyaccountnumber(){
		 Container c =getContentPane();
			c.setLayout(null);
			
			c.setBackground(Color.ORANGE);
			
			jb=new JButton("submit ");// button jb,1
			jb.addActionListener(this);
			jb.setBounds(200,370,100,50);
			
			JLabel jl1=new JLabel("account number"); //label jl1
			jl1.setBounds(10,60,100,50);
			
			 tf1= new JTextField(); // text field tf1
			tf1.setBounds(110,70,200,40);
			
			tf1.setText("ACCOUNT OR AADHAR NUMBER ");
			
			c.add(jb);
			c.add(jl1);
			c.add(tf1);
}
	 public void actionPerformed(ActionEvent ae)
	 {
	 		
	 		if (ae.getSource()==jb)
	 		{
	 			ShowTableData();
				
		    }
	 }
	 		public void ShowTableData()
	 		{
	 			
	 			frame1=new JFrame("Database Search Result");
	 			frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	 			String s1 = tf1.getText();
				ss1 = Long.parseLong(s1);
				long accountno=0;
				String name="";
				long amount=0;
				
		      try{
		       Class.forName("oracle.jdbc.driver.OracleDriver");
	           Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "hr");
	           PreparedStatement ps = con.prepareStatement("select name,account,amount from test4 where account="+ss1+"");
	           ResultSet rs =ps.executeQuery();
	           tf1.setText("ACCOUNT OR AADHAR NUMBER ");
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
		
		searchbyaccountnumber ca =new searchbyaccountnumber ();
			ca.setSize(500,500);
			ca.setTitle("balance check");
			ca.setVisible(true);	
			
	}
}

