import java.io.*;

public class Controller {
    private Game game;
    private GUI viewer;
    public Controller(){
        // initialise instance variables
        game = new Game(this);
        viewer = new GUI(this); //el GUI crea un listener
        game.play();
        this.updateData(); //llama al inicio
    }

   //public void startGUI() throws IOException{ 
   public void startGUI(){ 
      viewer.createComp();
   }
    
    public void end(){ //Ends the aplication and destroy the frame
        //viewer.end();
    }
	public void someStuff() {
		//game.putCoin();   //<<<<<<<< mine
		this.updateData(); //toma matrix y la manda al viewer
	}
	
	//action command
	public void execAction(String action){
		int row;
		int col;
		System.out.println("HHHHHHHHH received"+action);
		if(action.equals("restart")){
			restart();
		}else if(action.equals("exit")){
			System.exit(0);
		}else{
			String[] coord  = action.split(",");
			row = Integer.parseInt(coord[0]);
			col = Integer.parseInt(coord[1]);
			
			game.move(row, col);
			
			this.updateData();//tambien actualiza el viewer
			if(game.isGameOver()){			
				viewer.end( game.getFinalMsg() );
			}
		}
              	this.updateData();
	}
    /*    
    public void executeAction(String action){ //lo llama el listener
        switch(action){ 
            case "Up":
            game.up();
            break;

            case "Down":
            game.down();
            break;

            case "Left":
            game.left();
            break;

            case "Right":
            game.right();
            break;

        }
		System.out.println(action);

        if(game.isNotFilled())
            game.generateRandom();
        else
            //viewer.end();
            
        //game.printTest(action);

        this.updateData();

    }
    */

	
    public void updateData(){
        //int[][] matrix = game.getMatrix();
        //viewer.changeValues(matrix);  //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		char[][] board = game.getBoard();
		viewer.updateBoard(board);
		if(game.isMoving()){
			int[] coordinates = game.getSourceVals();
			viewer.updateColor(coordinates, 1);
		}
		if(game.isMovingDone()){
			int[] coordinates = game.getSourceVals();
			viewer.updateBoard(board);
			//viewer.updateColor(coordinates, 0);
		}
		viewer.updateStatus(game.getStatus() );
		
        //viewer.changeColor(matrix);
        //viewer.updateScore(game.getScore());
    }
	
	

    public void restart(){
        //game.restart();
		game.initRoutine();
        this.updateData();
        //viewer.updateScore(0);
    }

    public static void main (String [ ] args) {
        Controller main = new Controller();
    }
}
