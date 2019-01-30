
import javax.swing.*;     //maneja elementos graficos de la GUI
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.imageio.ImageIO;
import java.awt.*;        //maneja Layouts y otras cosas mas
import java.awt.event.*;  //manejar eventos de los elementos graficos
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.ByteArrayInputStream;
import java.io.File;


public class GUI extends JFrame{
   JPanel jp_Board;
   JPanel scorePanel;
   JLabel scoreLabel;

   Listener listener;
   JFrame mainFrame;
   //ActionListener jugada = new InsertAction();

   JButton[][] button_l;	

   ImageIcon iconA;
   ImageIcon iconB;
   ImageIcon iconE;
   ImageIcon iconS;


   public GUI(Controller con) {
      listener = new Listener(con);
      try{ //????????????????????
         initIcon();
      }catch(IOException e){
         System.out.println("heeeey");
         e.printStackTrace();
      }
   }    

   public void initIcon() throws IOException {
      try{
         iconA = new ImageIcon("images/A.png");
      } catch (Exception e) { //??????????????????????????
      };			
      Image img 	 = iconA.getImage() ;  
      Image newimg = img.getScaledInstance( 20, 20,  java.awt.Image.SCALE_SMOOTH ) ;  
      iconA = new ImageIcon( newimg );			

      iconB = new ImageIcon("images/B.png");
      img = iconB.getImage();
      newimg = img.getScaledInstance( 20, 20,  java.awt.Image.SCALE_SMOOTH ) ;  
      iconB = new ImageIcon( newimg );

      iconE = new ImageIcon("images/E.png");
      img = iconE.getImage();
      newimg = img.getScaledInstance( 20, 20,  java.awt.Image.SCALE_SMOOTH ) ;  
      iconE = new ImageIcon( newimg );	

      iconS = new ImageIcon("images/S.png");
      img = iconS.getImage();
      newimg = img.getScaledInstance( 20, 20,  java.awt.Image.SCALE_SMOOTH ) ;  
      iconS = new ImageIcon( newimg );		
   }
	
   //public void createComp() throws IOException {
   public void createComp() {
      //super("Molino"); // this.setTitle("primera ventana")
      this.setSize(900,300);
      this.setResizable(false);

      //BufferedImage img = ImageIO.read(new File("images/vert.png") );				
      //BufferedImage bi = new BufferedImage(10,10, BufferedImage.TYPE_INT_ARGB);

      //BufferedImage img = null;
      //
      //try {
      //	img = ImageIO.read(new File("images/vert.png") );
      //} catch (IOException e) {
      //}		

      //BufferedImage img = null;
      //
      //try{
      //	img = ImageIO.read(new File("images/vert.png") );
      //} catch (IOException e) {
      //};		


      //jp_Board = new JPanel();
      final int xWi = 900;
      final int yHe = 162;
      jp_Board = new JPanel() {

         protected void paintComponent(Graphics g){
            super.paintComponent(g);

            BufferedImage img = null;				
            File f = new File("images/board.png");
            //try{
            //	img = ImageIO.read(new File("images/vert.png") );
            //} catch (IOException e) {
            //};	
            try{
               img = ImageIO.read(f);
            }catch(IOException e){					
            };
            int iWidth2 	= img.getWidth();
            int iHeight2 	= img.getHeight();
            //int x = this.getParent().getWidth() - iWidth2;
            //int y = this.getParent().getHeight()- iHeight2;				
            int x = xWi- iWidth2;
            int y = yHe- iHeight2;			
            //System.out.println("iw "+iWidth2+" iHeight2 "+iHeight2+" x "+this.getParent().getWidth()+" y "+this.getParent().getHeight());
            //https://stackoverflow.com/questions/19125707/simplest-way-to-set-image-as-jpanel-background
            g.drawImage(img,x,y, null);
            //g.drawImage(img,0,0, null);
         }

         @Override
         public Dimension getMinimumSize() {
            return new Dimension(xWi, yHe);
         }			
         @Override
         public Dimension getPreferredSize() {
         	return new Dimension(xWi, yHe);
         }
         
         @Override
         public Dimension getMaximumSize() {
         	return new Dimension(894, yHe);
         }			

      };

      Container c = this.getContentPane();

      scorePanel = new JPanel();


      jp_Board.setLayout(new GridLayout(7,7, 5, 5));


      //jp_Board.setMaximumSize( jp_Board.getPreferredSize() );   ??
      //jp_Board.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

      //button_l = new JButton[7][7];
      button_l = new JButton[7][7];

      for(int i=0; i<7; i++){
         for(int j=0; j<7; j++){
            button_l[i][j] = new JButton();
            button_l[i][j].setName(i+","+j);
         }
      }	
      //ImageIcon icon0 = new ImageIcon("images/vert.png");
      ImageIcon icon0 = new ImageIcon("images/fui.png");
      Image img = icon0.getImage() ;  
      Image newimg = img.getScaledInstance( 20, 20,  java.awt.Image.SCALE_SMOOTH ) ;  
      icon0 = new ImageIcon( newimg );
      //ImageIcon icon0 = new ImageIcon(getClass().getResource("images/vert.png"));
      //JLabel ll = new JLabel("baiba");
      //ll.setIcon(icon0);	

      button_l[0][0].addActionListener(listener);
      button_l[0][3].addActionListener(listener);
      button_l[0][6].addActionListener(listener);		
      button_l[6][0].addActionListener(listener);
      button_l[6][3].addActionListener(listener);
      button_l[6][6].addActionListener(listener);

      button_l[1][1].addActionListener(listener);
      button_l[1][3].addActionListener(listener);
      button_l[1][5].addActionListener(listener);		
      button_l[5][1].addActionListener(listener);
      button_l[5][3].addActionListener(listener);
      button_l[5][5].addActionListener(listener);		

      button_l[2][2].addActionListener(listener);
      button_l[2][3].addActionListener(listener);		
      button_l[2][4].addActionListener(listener);		
      button_l[4][2].addActionListener(listener);
      button_l[4][3].addActionListener(listener);
      button_l[4][4].addActionListener(listener);

      button_l[3][0].addActionListener(listener);
      button_l[3][1].addActionListener(listener);
      button_l[3][2].addActionListener(listener);
      button_l[3][4].addActionListener(listener);
      button_l[3][5].addActionListener(listener);
      button_l[3][6].addActionListener(listener);				


      for(int i=0; i<7; i++){
         for(int j=0; j<7; j++){		
            if( ( (i == 0 || i == 6) && (j==0 || j==3 || j==6) ) ||
               ( (i==1 || i ==5) && (j==1 || j==3 || j==5)) 	 ||
               ( (i==2 || i==4) && (j==2 || j==3 || j==4))		 ||
               ( i==3 && j!=3) 									){ 	
               button_l[i][j].setContentAreaFilled(false);
               //button_l[i][j].setBorder(new RoundedBorder(10));															
               button_l[i][j].setIcon(icon0);
            }else{
               button_l[i][j].setContentAreaFilled(false);
            }
               button_l[i][j].setBorder(null);
         }
      }					


      for(int i=0; i<7; i++){
         for(int j=0; j<7; j++){		
            jp_Board.add(button_l[i][j]);
         }
      }		
      scoreLabel = new JLabel();
      scoreLabel.setBorder(BorderFactory.createLineBorder(Color.blue));

      scoreLabel.setFont(new Font("Calibri", Font.BOLD, 12));
      

      JMenuBar bar = new JMenuBar();
      JMenu fileMenu = new JMenu("Game");

      setJMenuBar(bar);

      JMenuItem itemRest = new JMenuItem("Restart");	
      itemRest.setName("restart");
      itemRest.addActionListener(listener);

      JMenuItem itemExit = new JMenuItem("Exit");	
      itemExit.setName("exit");
      itemExit.addActionListener(listener);		

      bar.add(fileMenu);
      fileMenu.add(itemRest);
      fileMenu.add(itemExit);

      scorePanel.add(scoreLabel);			

      c.add(scorePanel,BorderLayout.NORTH);

      c.add(jp_Board,BorderLayout.SOUTH);        

      this.setVisible(true);		
   }

   public void updateStatus(String m){
      scoreLabel.setText(m);
   }	

   private class InsertAction implements ActionListener {

      public void actionPerformed(ActionEvent event) {
         // String input = event.getActionCommand();
         // if (start) {
         //     text.setText("");
         //     start = false;
         // }
         // text.setText(text.getText() + input);
         //boton[Integer.parseInt(input)].setBackground(Color.BLUE);
         //boton[Integer.parseInt(input)].setText("8");
         // text.revalidate();
      }
   }

   public void changeValues(int[][] v){ //lo llama el controler en updateData()
      if(v[0][0] == 7){
         button_l[0][0].setBackground(Color.RED);
         button_l[0][0].setText("X");
      }else if(v[0][0] == 11){
         button_l[0][0].setBackground(Color.BLUE);
         button_l[0][0].setText("O");
      }else{
         System.out.println("something wrong ...");
      }
   }	
   public void updateBoard(char[][] b){
      String x;
      for(int i=0; i < 7; i++){			
         for(int j=0; j < 7; j++){
            x = String.valueOf(b[i][j]);
            if(x.equals("A")){
               button_l[i][j].setIcon(iconA);
            }else if(x.equals("B")){
               button_l[i][j].setIcon(iconB);					
            }else if(x.equals(".")){
               button_l[i][j].setIcon(iconE);
            }
            //button_l[i][j].setText(x);
         }
      }
   }
   public void updateColor(int[] coord, int mode){

      if(mode==1){
            //button_l[coord[0]][coord[1]].setBackground(Color.BLUE);
            button_l[coord[0]][coord[1]].setIcon(iconS);
      }else{
            //button_l[coord[0]][coord[1]].setBackground(null);
            //button_l[coord[0]][coord[1]].setIcon(iconTmp);
      }
   }

   public void end(String m){
      Object[] options = {"Play again", "Close"};
      try {
         //int n = JOptionPane.showOptionDialog(mainFrame,"No moves available","GAME OVER", JOptionPane.YES_NO_OPTION,
         int n = JOptionPane.showOptionDialog(mainFrame,"No moves available. "+m,"GAME OVER", JOptionPane.YES_NO_OPTION,
         JOptionPane.WARNING_MESSAGE,
         null,
         options,
         options[0]);
         if(n == 0){
            listener.endAction();
         }else{
            System.exit(0);
         }

      } catch (NumberFormatException e) {
         System.exit(0);
      }

   }	
   //public static void main(String[] args) {
   //    GuiPaver gui1 = new GuiPaver();
   //    gui1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   //}    
}
