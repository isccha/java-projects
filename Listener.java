import javax.swing.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Listener implements ActionListener,KeyListener
{
    // instance variables - replace the example below with your own
    Controller controller;

    public Listener(Controller con)
    {
        // initialise instance variables
        controller = con;
    }

    public void actionPerformed(ActionEvent e) {
        //game.printTest();
		System.out.println("something received ...");
		System.out.println("something received1 ..."+e.getActionCommand());
		System.out.println("something received2 ..."+e.getSource());
		
		System.out.println("something received3 ..."+( (JComponent) e.getSource()).getName());
		//System.out.println("something received3 ..."+e.getComponent());		
		
		//controller.execAction( ( (JButton) e.getSource()).getName() );
		controller.execAction( ( (JComponent) e.getSource()).getName() );
        //controller.executeAction(e.getActionCommand()); //<<<<
		
		//controller.someStuff(); //yours
    }

    public void keyTyped(KeyEvent e){

    }

   //nooo
    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        System.out.println(keyCode);
        switch( keyCode ) { 
        }
    }

    public void keyReleased(KeyEvent e){
    }

    public void endAction(){
        controller.restart();
    }

}
