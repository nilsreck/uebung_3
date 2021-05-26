import java.util.*;
public class Minesweeper {

	public static void main (String [] args){

		/*
		Kurze Erklärung zum Vorgehen hier:
		Es wird eine boolean Ausgabe von der Methode zur Spielausführung erwartet.
		Diese dient dazu festzustellen, ob das Spiel einmal zu Ende gespielt wurde.
		Eigentlich kann man diese Variable auslassen und einfach eine Endlosschleife machen,
		dies ist nur probehalbar implementiert.
		*/
		boolean spass = false;																			//boolean-Variable spass wird erzeugt und 																												//auf false gesetzt
		String s = "";																					//String-Variable s wird erzeugt und 																												//auf einen leeren String gesetzt
		System.out.println("Möchten Sie beginnen: Ja? Nein?");											//Ausgabe an den Nutzer
		s = StdIn.readString();																			//s wird auf die Eingabe des Nutzers gesetzt
		if(s.equals("Ja") || s.equals("J") ||s.equals("j") || s.equals("y") || s.equals("ja")){			//Für bestimmte Eingaben wird das Spiel 																											//gestartet
			spass = ausfuehrung();																		//Methode für Spielvers. 2 wird aufgerufen
			//StdDraw.show(500);																			//Animation wird gestoppt
		}																								//
		else{																							//
			System.out.println("Warum klickst du auf das Spiel, wenn du es nicht spielen willst?");		//Spassausgabe
		}
		while(spass == true){																			//
			System.out.println("Erneut spielen: Ja? Nein?");											//Ausgabe an den Nutzer
			StdDraw.clear();																			//
			System.out.println("Natürlich willst du nochmal spielen, ich sehe doch wie viel Spass dir das Spiel gemacht hat"); //Spassausgabe
			//StdDraw.show();																				//
			spass = ausfuehrung();																		//Methode für Spielvers. 2 wird aufgerufen
		}

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static boolean ausfuehrung(){

		double x=0;																	//später x-Wert durch Mauseingabe
		double y=0;																	//später y-Wert durch Mauseingabe 
		int k=0;																	//Zählvariable
		int ko = 0; 																//Zählvariable
		int random = 20;															//Breite/Höhe des Feldes
		int minen = 5;
		int spalte=-1;																//angeklickte Spalte
		int zeile=-1;																//angeklickte Zeile
		double nnew = (double) random;												//Variable zum Rechnen
		double linien = 1.0/nnew;													//Variable mit Abständen zwischen Linien als Wert
		double halb = linien/2;														//Variable mit Mitte von Abstand von 2 Linien als Wert

		StdDraw.setScale(-0.05, 1.05);									// Um einen Rand um das Spielfeld zu erstellen
		for(int i=0;i<random;i++){										// for zum Erstellen von Beschriftungen, die die Positionen der Felder anzeigen
			StdDraw.text(-0.025, 1-(linien*i+linien/2), ""+i);
			StdDraw.text(linien*i+linien/2, 1-(-0.025), ""+i);
		}
		for(int p1=0;p1<random+1;p1++){												//for-schleife die das
			for(int p2=0;p2<random+1;p2++){											//Raster für das Feld zeichnet
				StdDraw.line(linien*p2,linien*p2,linien*p2,linien*p1);				//Erklärung entfällt, da zu zeitaufwändig
				StdDraw.line(linien*p1,linien*p2,linien*p2,linien*p2);				//Erklärung entfällt, da zu zeitaufwändig

			}
		}
		boolean [][] bool = new boolean [random+1][random+1];						//2-dim boolean Array mit zusätzlicher Spalte und Zeile zur 																					//Überprüfung für Spielende
		boolean [][] bool2 = new boolean [random+1][random+1];
		int [][] boar = new int [random][random];									//Minesweeper-Feld mit Größe random*random 
		int [][] boar2 = new int [random][random];									//Minesweeper-Feld mit Größe random*random 
		boar = makeRandomBoard(random,random,minen);								//zufälliges Feld mit Minen wird erstellt mit Größe random² und 																					//random-vielen Minen


		/*
		Kurze Erläuterung zum weiterführenden Code:
		Da das Minesweeper-Board von oben nach unten gelesen wird, 
		aber die Koordinaten für die Mauseingabe ihren Ursprung unten links haben,
		war es notwendig das Array in ihrer Zeile und Spalte zu invertieren.
		Hätte man auch anders lösen können, aber so war es weniger aufwändig den bereitsgeschriebenen Code zu ändern.	
		*/

		for(int t=0; t<random;t++){													//Zeilen werden durchgegangen
			for(int s=0;s<random;s++){												//Spalten werden durchgegangen
				boar2[(random-1)-t][(random-1)-s]=boar[t][s];						//boar-Array wird umgedreht und als boar2 gespeichert
			}
		}

		k=0;
		boolean first = true;														//EDIT: der boolean, der beim ersten Spielzug true ist:)
		while(bool2[random][random]==false && (random*random)-(minen+ko)>0){		//Solange keine Mine geöffnet wurde oder eine nicht Mine noch nicht 																					//geöffnet wurde 
			while(zeile==-1 && spalte==-1){											//Zeile und Spalte soll zur Eingabe des Nutzers werden
				System.out.print("Zeile: ");										//Ausgabe an den Nutzer
				boolean ctch = true;												//EDIT: Boolean, der der while sagt, ob die Eingabe wiederholt werden muss
				while(ctch) {
					ctch = false;
					try {																						//Try: Eingabe soll erfolgen und alles au�er Integer-Werte abfangen
						zeile=StdIn.readInt();																	//Eingabe f�r den Nutzer
					} catch(InputMismatchException e) {															//Catch f�ngt dann die ung�ltigen Formate ab
						System.out.println("Wo soll das denn sein? Das ist hier keine Buchstabensuppe!");		//Fehlerausgabe :P
						ctch = true;																			//Zur Wiederholung
					}
				}
				while(zeile>=random){												//zeile soll aber immernoch im Array liegen
					k++;															//k wird um eins erhöht
					if(k>3){														//wenn der Nutzer 3 falsche Eingaben macht
						System.out.println("Sag mal kannst du nicht lesen?");		//Spassausgabe
					}
					System.out.print("Zahl zu groß, bitte neue Zahl eingeben: ");	//Neue Eingabe wird erwartet
					zeile=StdIn.readInt();											//zeile wird zur Eingabe des Nutzers
				}
				System.out.print("Spalte: ");										//Ausgabe an den Nutzer
				ctch = true;
				while(ctch) {														//EDIT: Selbes Prinzip wie oben!
					ctch = false;
					try {
						spalte = StdIn.readInt();
					} catch(InputMismatchException e) {
						System.out.println("Wo soll das denn sein? Das ist hier keine Buchstabensuppe!");
						ctch = true;
					}
				}

				k=0;																//k wird wieder als Zählvariable genutzt
				while(spalte>=random){												//zeile soll aber immernoch im Array liegen
					k++;															//k wird um eins erhöht
					if(k>3){														//wenn der Nutzer 3 falsche Eingaben macht
						System.out.println("Sag mal kannst du nicht lesen?");		//Spassausgabe
					}
					System.out.print("Zahl zu groß, bitte neue Zahl eingeben: ");	//Neue Eingabe wird erwartet
					spalte=StdIn.readInt();											//spalte wird zur Eingabe des Nutzers
				}
			}
			k=0;
			if(first == true) {														//EDIT FOLGT: Wenn der erste Spielzug ist 
				if(isMine(boar,zeile,spalte)==true) {								//Wenn auf der momentan ausgew�hlten Position eine Mine liegt
					boar[zeile][spalte] = 0;										//Mine wird gel�scht
					for(int i = 0; i < 1; i++) {									//Ein Durchlauf, damit eine neue Position gesucht wird
						int[] pos = selectRandomPosition(random, random);			//selbes Prinzip wie beim makeRandomBoard
						if(pos[0] == zeile && pos[1] == spalte) i--;				//Falls er nochmal auf dieselbe Position springt
						else {
							if(isMine(boar, pos[0], pos[1])==true) i--;				//Wenn auf der neuen Position eine andere Mine liegt
							else {
								boar[pos[0]][pos[1]] = 1;							//Mine wird endlich neu platziert :)
							}
						}
					}
				}
				first = false;															//Damit der erste Spielzug vorbei ist!
			}

			for(int t=0; t<random;t++){													//Zeilen werden durchgegangen
				for(int s=0;s<random;s++){												//Spalten werden durchgegangen
					boar2[(random-1)-t][(random-1)-s]=boar[t][s];						//boar-Array wird umgedreht und als boar2 gespeichert
				}
			}

			bool = uncover(bool,boar,zeile,spalte);
			for(int t=0; t<random;t++){
				for(int s=0;s<random;s++){
					bool2[(random-1)-t][(random-1)-s]=bool[t][s];
				}
			}
			for(int t=0; t<random;t++){
				for(int s=0;s<random;s++){
					String sp = Integer.toString(countMines(boar2,t,s));					//hier gucken ob boar oder boar2 eingesetzt werden müssen
					if(bool2[t][s]==true){												//wird geprüft welche Felder schon geöffnet wurden
						if(Integer.parseInt(sp)==-1){
							StdDraw.text(1-(halb+(linien*s)),halb+(linien*t),"TOD");
							bool2[random][random]=true;
						}
						else{
							StdDraw.text(1-(halb+(linien*s)),(halb+(linien*t)),sp);
						}
						ko++;
					}
				}
			}
			if((random*random)-(minen+ko)==0){
				k=10;
			}
			if(bool2[random][random]==true){
				k=100;
			}
			zeile=-1;
			spalte=-1;
		}		// gro�e while ENDE!!
		//StdDraw.show(200);												// EDIT: StdDraw.show(int) ist veraltet, wenn es drin ist, tritt das graphische Problem auf, nachdem das Spiel nochmal neu beginnt!

		StdDraw.clear();
		for(int p1=0;p1<random+1;p1++){										//for-schleife die das
			for(int p2=0;p2<random+1;p2++){									//Raster für das Feld zeichnet
				StdDraw.line(linien*p2,linien*p2,linien*p2,linien*p1);		//Erklärung entfällt, da zu zeitaufwändig
				StdDraw.line(linien*p1,linien*p2,linien*p2,linien*p2);		//Erklärung entfällt, da zu zeitaufwändig
			}
		}
		for(int t=0; t<random;t++){											//EDIT: Man muss boar2 benutzen und s und t einmal verdrehen
			for(int s=0;s<random;s++){
				String sp = Integer.toString(countMines(boar2,s,t));			//hier gucken ob boar oder boar2 eingesetzt werden müssen
				StdDraw.text(1-(halb+(linien*t)),halb+(linien*s),sp);
			}
		}

		//System.out.println((random*random)-(minen+ko));						//Testausgabe
		if(k==10){															//aus vorherigen Zeilen ergibt sich: Wird aufgerufen, wenn alle nicht-Minen 																			//geöffnet wurden
			System.out.println("Victory!");									//Ausgabe
			StdDraw.show(10000);   										//EDIT: 10 Sekunden wird das Spielfeld angezeigt
			StdDraw.clear();
		}
		else{																//Wenn auf eine Mine gedrückt wurde
			System.out.println("Verkackt!");								//Ausgabe
			StdDraw.show(10000);
			StdDraw.clear();
		}
		return true;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



	public static boolean[][] uncover(boolean [][] uncovered, int [][] board, int zeile, int spalte) {
		int z = board.length;
		int s = board[0].length;
		//boolean [][] out = new boolean [z+1][z+1];
		if(isMine(board,zeile,spalte)==true){
			uncovered[zeile][spalte]=true;
			uncovered[z][s]=true;
		}
		else{
			if(countMines(board,zeile,spalte)>0){
				uncovered[zeile][spalte]=true;
			}
			if(countMines(board,zeile,spalte)==0){
				uncovered[zeile][spalte]=true;
				if(zeile>0 && uncovered[zeile-1][spalte] != true){
					uncover(uncovered,board,zeile-1,spalte);

					if(spalte<s-1 && uncovered[zeile-1][spalte+1] != true){
						uncover(uncovered,board,zeile-1,spalte+1);
					}
					if(spalte>0 && uncovered[zeile-1][spalte-1] != true){
						uncover(uncovered,board,zeile-1,spalte-1);
					}
				}
				if(zeile<z-1 && uncovered[zeile+1][spalte] != true){
					uncover(uncovered,board,zeile+1,spalte);

					if(spalte<s-1 && uncovered[zeile+1][spalte+1] != true){
						uncover(uncovered,board,zeile+1,spalte+1);
					}
					if(spalte>0 && uncovered[zeile+1][spalte-1] != true){
						uncover(uncovered,board,zeile+1,spalte-1);
					}
				}
				if(spalte<s-1 && uncovered[zeile][spalte+1] != true){
					uncover(uncovered,board,zeile,spalte+1);
				}
				if(spalte>0 && uncovered[zeile][spalte-1] != true){
					uncover(uncovered,board,zeile,spalte-1);
				}
			}
		}
		//out = uncovered;
		return uncovered;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static boolean isMine(int[][] board, int i, int j) {
		return board[i][j] == 1;
	}

	public static int countMines(int[][] board, int z, int s) {
		if (board[z][s] == 1) { return -1; }
		int height = board.length;
		int width = board[0].length;
		int count = 0;
		for (int i=z-1;i<=z+1;i++) {
			for (int j=s-1;j<=s+1;j++) {
				if (
						i < height && i >= 0 &&  // zeile ist gueltig
								j < width && j >= 0 &&   // spalte ist gueltig
								board[i][j] == 1         // es ist eine Mine
				) { count++; }
			}
		}
		return count;
	}

	public static int[][] makeRandomBoard(int height, int width, int mines) {
		int[][] board = new int[height][width];
		for (int i =0; i<mines; i++) {
			int[] pos = selectRandomPosition(height,width);
			if(board[pos[0]][pos[1]] == 1) i--; //EDIT: Nochmal (klappt, aber ist doof :D) besser: Liste
			else board[pos[0]][pos[1]] = 1;
		}
		return board;
	}
	public static int[] selectRandomPosition(int height, int width) {
		int spalte = StdRandom.uniform(0,width);
		int zeile = StdRandom.uniform(0,height);
		return new int[]{zeile,spalte};
	}

/*////////////////////////////////////////////////////////////////////////////////////////////
Test- und Debugging Methoden
////////////////////////////////////////////////////////////////////////////////////////////*/

	public static void output(boolean [][] b){
		int n = b.length;
		for (int i=0;i<n;i++) {
			for (int ko=0;ko<n;ko++) {
				System.out.print(b[i][ko]+" ");
			}
			System.out.println();
		}
	}
	public static void intoutput(int [][] b){
		int n = b.length;
		for (int i=0;i<n;i++) {
			for (int ko=0;ko<n;ko++) {
				System.out.print(b[i][ko]+" ");
			}
			System.out.println();
		}
	}
}