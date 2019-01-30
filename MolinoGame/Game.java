import java.util.Random;
/*
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
*/
public class Game {
   
   // instance variables - replace the example below with your own
   char board[][];
   char disp[][];
   int n; //dimension del tablero
   char mark[];	
   char empty;
   int x_sourc;
   int y_sourc;
   //V_Calculadora v;

   boolean juega1x;
   int j_idx; //jugador ID
   int j_vs_idx; //ID del jugador en espera	

   boolean player1;
   int player_id;
   int player_vs_id;

   int pieces_init[];
   int pieces[];

   int amount;
   String finalMsg;

   //STATES
   boolean removing;
   boolean phase1;
   boolean phase2;
   boolean moving;
   boolean move_done;

   boolean gameOver;

   Controller controller;

   /*
      Constructor for objects of class Game
   */
   public Game(Controller con){
      controller = con;
      n = 7;		
      board = new char[n][n];
      disp  = new char[n][n];
      mark = new char[2];

      pieces_init = new int[2];
      pieces = new int[2];		
   }

   public void play(){
      initRoutine();
      controller.startGUI();
   }

   public void initRoutine(){
      mark[0] = 'A';
      mark[1] = 'B';
      empty = '.';		
      juega1x = true;		

      amount = 6;


      pieces_init[0] = amount;
      pieces_init[1] = amount;
      pieces[0] = amount;
      pieces[1] = amount;

      player1 = true;
      removing = false;
      moving = false;
      move_done = false;

      phase1 = true;
      phase2 = false;

      gameOver = false;
      finalMsg = "";		
      
      initBoard();
   }
   public void initBoard(){
      for(int i=0; i<n; i++){
         for(int j=0; j<n; j++){
            if( i == 0  || i == 6 ){
               if(j == 0 || j == 3 || j == 6){
                  board[i][j] = empty;
               }else{
                  board[i][j] = ' ';
               }
               }else if( i == 1 || i == 5){
                  if(j == 1 || j == 3 || j == 5){
                  board[i][j] = empty;
               }else{
                  board[i][j] = ' ';
               }									
            }else if( i == 2 || i == 4){
               if(j == 2 || j == 3 || j == 4){
                  board[i][j] = empty;
               }else{
                  board[i][j] = ' ';
               }					
            }else if( i == 3) {
               if(j==3){
                  board[i][j] = '-';
               }else{
                  board[i][j] = empty;
               }	
            }					
         }
      }		
   }

   public char[][] getBoard(){
      return board;
   }
   public void escribaBoard(int i, int j, char m){
      board[i-1][j-1] = m;
   }		

   public boolean esValida(int i, int j){ //fase 1 todavia no hay movimientos de fichas
      if(board[i-1][j-1] != empty ){
         return false;
      }
      return true;
   }

   public boolean esFicha(int i, int j, char m){
      if(board[i-1][j-1] != m){
         return false;
      }
      return true;
   }
	
   public boolean checkMolinosAlrededor(int i, int j, char m){
      //revisar posibles molinos alrededor de esta posicion
      i -= 1; 
      j -= 1;	
      boolean hay_molino = false;
      //derecha e izquierda
      if( ( deCantidadAdj(i,j,1,m) + deCantidadAdj(i,j,3,m) ) == 2 ){
         hay_molino = true;
         System.out.println("Se encontro un molino horizontalmente!");
      }else if(( deCantidadAdj(i,j,0,m) + deCantidadAdj(i,j,2,m) ) == 2){
         hay_molino = true;
         System.out.println("Se encontro un molino verticalmente!");			
      }
      return hay_molino;
   }


   //arriba 0, derecha 1, abajo 2, izquierda 3
   // Da cantidad de fichas adyacentes que hacen match con 'm', pero se detiene si en el camino se encuentra con algo que 
   // no sea precisamente 'm'
   public int deCantidadAdj(int i, int j, int dir, char m){		
      boolean pare;
      int counter = 0;
      //borrar luego
      //i -= 1; 
      //j -= 1;			

      if(dir  == 0){
         pare = false;
         while(!pare){
            if( (i-1) >= 0 ){
               i--;
               if(board[i][j] == m){
                  counter++;					
               }else if(board[i][j] != ' '){
                  pare = true; //hay algo que no es un espacio vacio
               }
            }else{
               pare = true;
            }					

         }
      }else if(dir == 2){
         pare = false;
         while(!pare){
            if( (i+1) < n ){
               i++;
               if(board[i][j] == m){
                  counter++;					
               }else if(board[i][j] != ' '){
                  pare = true; //hay algo que no es un espacio vacio
               }
            }else{
               pare = true;
            }					
         }			
      }else if(dir == 1){
         pare = false;
         while(!pare){
            if( (j+1) < n ){
               j++;
               if(board[i][j] == m){
                  counter++;					
               }else if(board[i][j] != ' '){
                  pare = true; //hay algo que no es un espacio vacio
               }
            }else{
               pare = true;
            }					
         }
      }else if(dir == 3){
         pare = false;
         while(!pare){
            if( (j-1) >= 0 ){
               j--;
               if(board[i][j] == m){
                  counter++;					
               }else if(board[i][j] != ' '){
                  pare = true; //hay algo que no es un espacio vacio
                  System.out.println("--debug-- izquierda se detiene porque encuentra un "+board[i][j] );
               }
            }else{
               pare = true;
               System.out.println("--debug-- izquierda se detiene no hay nada");
            }					
         }
      }
      return counter;
   }
	

   public boolean checkIfHayJuego(char m) {
      boolean game_done = true;
      for(int i=0; i<n; i++){
         for(int j=0; j<n; j++){		
            if(board[i][j] == m){
               if( deCantidadAdj(i,j,0,empty) >= 1  ||  deCantidadAdj(i,j,1,empty) >= 1  ||
                  deCantidadAdj(i,j,2,empty) >= 1  ||  deCantidadAdj(i,j,3,empty) >= 1  	 ){
                  game_done = false;
               }
            }
         }
      }
      return !game_done;
   }
							
					
					
	
   public boolean esMovValido(int i, int j, int i_dest, int j_dest){

      boolean valid=true;
      int peq;
      int grande;
      i--;
      j--;
      i_dest--;
      j_dest--;
      if( ((i != i_dest) && (j != j_dest)) || (board[i_dest][j_dest] != empty)  ){ //fila o columna se tiene que conservar 
                                                                                   //o si la posicion no esta libre
         valid = false;
         System.out.println("--debug-- no hay un cero o es diagonal");
      }else{
         if( i == i_dest){ //cambio en columna
            peq = Math.min(j, j_dest);
            grande = (peq == j) ? j_dest : j;
            for(int x = 1; x < (grande-peq); x++){
               if(board[i][peq+x] != ' '){
                  valid = false;
                  System.out.println("--debug-- moviendose en la col hay algo");
               }
            }
         }else{ //cambio en fila
            peq = Math.min(i, i_dest);
            grande = (peq == i) ? i_dest : i;
            for(int x = 1; x < (grande-peq); x++){
               if(board[peq+x][j] != ' '){
                  valid = false;
                  System.out.println("--debug-- moviendse en la fila hay algo");
               }
            }			
         }
      }

      return valid;			
   }
	
   public boolean todosSonMolino(char m){ // Revisa si todas las fichas pertenecen a un molino. En ese caso, cuando se va
                                          // a quitar una ficha no importa cuál sea
      boolean todos_son = true;
      for(int i=0; i<n; i++){
         for(int j=0; j<n; j++){
            if(board[i][j] == m){
               if(!checkMolinosAlrededor(i+1,j+1,m)){
                  todos_son = false;
               }
            }
         }
      }
      return todos_son;
   }
					
					
	
	
   public boolean remuevaFicha(int i,int j, char m){	//FIXME si todas son de un molino =0 ???

      boolean done = true;
      if(board[i-1][j-1] == m){ //es una ficha valida
         //Hay almenos una ficha que no pertenezca a un molino?
         if(!todosSonMolino(m)){
            //hay al menos una que no esta en molino => proteger las otras
            //pertenece a molino?
            if(checkMolinosAlrededor(i,j,m)){
               System.out.println(">>> La pieza por mover pertenece a un molino y no puede ser removida! <<< ");
               done = false;
            }else{
               board[i-1][j-1] = empty;
            }
         }else{
            System.out.println("> Todas las fichas estaban en molino <");
            board[i-1][j-1] = empty;
         }
      }else{
         done = false;
      }
      return done;
   }
	
   public String getStatus(){
      String msg;
      int current_id = player1 ? 1 : 2;
      int ph = phase1 ? 1 : 2;
      msg = "<html><body> <b>PHASE "+ph+". ";
      if(!removing){
         msg += "Player "+current_id+" turn!</b>";
      }else{
         msg += "Player "+current_id+" removes a piece!</b>";
      }
      if(ph == 1){
         msg += "<br>Pieces left for Player 1: "+pieces_init[0];
         msg += "<br>Pieces left for Player 2: "+pieces_init[1];
      }else{
         msg += "<br>Move your pieces to valid positions!";
      }
      msg += "</body></html>";
      return msg;
   }
   public boolean isMoving(){
      return moving;
   }
   public boolean isMovingDone(){
      return move_done;
   }

   public int[] getSourceVals(){
      int[] coord = {x_sourc-1, y_sourc-1};
      return coord;
   }

   public boolean isGameOver(){
      return gameOver;
   }
   public String getFinalMsg(){
      return finalMsg;
   }
   public void ph2_moves(int x, int y){

      move_done = false;

      if(removing){
         if(remuevaFicha(x, y, mark[player_vs_id])){
            //done
            removing = false;
            player1 = !player1;
            pieces[player_vs_id]--;
            //CHECK for game
            if(pieces[player_vs_id] == 2){
               finalMsg = "Only 2 pieces left. Player "+(player_id+1)+" wins!!!!!";
               gameOver = true;
            }
         }else{
            System.out.println("Posicion o ficha por remover invalida. Intente otra vez");
         }			
      }else{
         //source
         if(!moving){
            if(!esFicha(x,y, mark[player_id])){
               System.out.println("La direccion no es valida o la ficha no le pertenece");				
               moving = false;
            }else{
               moving = true; //source has been selected
               x_sourc = x;
               y_sourc = y;
            }
         }else{
            //dest
            if( (x_sourc == x) && (y_sourc == y) ){
               //De-selecting piece
               moving = false;
               move_done = true; //but no change of player...
            }else if(!esMovValido(x_sourc, y_sourc, x, y) ) {
               System.out.println("La direccion destino no es valida o esta ocupada");				

            }else{
               escribaBoard(x, y, mark[player_id]);
               escribaBoard(x_sourc, y_sourc, empty);						
               moving = false; //piece has been placed
               move_done = true;
               if(!checkIfHayJuego(mark[player_vs_id])){
                  finalMsg = "Opponent surrounded. Player "+player_id+" wins!!!!!";
                  gameOver = true;
               }else{
                  if(checkMolinosAlrededor(x,y,mark[player_id])){
                     removing = true; //new state, player doesn't change
                  }else{
                     player1 = !player1;
                  }
               }

            }
         }
      }
   }


   public void move(int x, int y){
      x += 1;
      y += 1; 
      boolean valid_move;

      player_id = player1 ? 0 : 1;
      player_vs_id = player1 ? 1 : 0;
      if(phase2){
         ph2_moves(x,y);
      }else{
         if(removing){
            if(remuevaFicha(x, y, mark[player_vs_id])){
               //done
               removing = false;
               pieces[player_vs_id]--;
               player1 = !player1;
            }else{
               System.out.println("Posicion o ficha por remover invalida. Intente otra vez");
            }
         }else{
            //Put
            if(!esValida(x, y)){
               System.out.println("La direccion no es valida o esta ocupada. Intente otra");
               valid_move = false;
            }else{
               valid_move = true;
            }		
            if(valid_move){
               pieces_init[player_id] -= 1 ;
               escribaBoard(x,y,mark[player_id]);				

               if(checkMolinosAlrededor(x,y,mark[player_id])){
                  removing = true; //new state, player doesn't change
               }else{
                  player1 = !player1;
               }
               if( (pieces_init[0] == 0) && (pieces_init[1] == 0) ){
                  phase1 = false;
                  phase2 = true;
               }
            }
         }		
      }

   }

}        
