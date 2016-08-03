package algocode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Tennis {
	
	static ArrayList<String> input = new ArrayList<String>();
	static ArrayList<String> output = new ArrayList<String>();
	
	public static void main(String[] args) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			int lines = Integer.parseInt(in.readLine());
			
			while(lines>0){
				String s = in.readLine();
				String[] chars = s.split(" ");
				for(String c: chars){
				input.add(c);
				}
				lines--;
			}
			Tennis t = new Tennis();
			t.processInput(input);
			for(String s1: output){
				System.out.println(s1);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	void processInput(ArrayList<String> inString){
		Player N = new Player();
		Player D = new Player();
		N.name = "N";
		D.name = "D";
		for(String player: inString){
			if(player.equals("N")){
				updateGameScore(N, D);
			}
			
			if(player.equals("D")){
				updateGameScore(D, N);
			}
		}
	}
	
	
	void initializeGameScores(Player player){
		player.gameScore = 0;
	}
	void initializeSetScores(Player player){
		player.setScore = 0;
	}
	void initializeTieBreakScores(Player player){
		player.tieBreakScore = 0;
		player.tie = false;
	}
	void updateGameScore(Player player1, Player player2){
		//System.out.println(player1.name+ " set score "+ player1.setScore + " game "+player1.gameScore);
		//System.out.println(player2.name+ " set score "+ player2.setScore + " game "+player2.gameScore);
		
		
		if(player1.tie || player2.tie){
			updateTieBreakScore(player1, player2);
		}
		if((player1.gameScore<45)){
			player1.gameScore += 15;
			return;
		}
		if((player1.gameScore==45) && (player2.gameScore<45)){
			initializeGameScores(player1);
			initializeGameScores(player2);
			updateSetScore(player1, player2);
			return;
		}
		
		// Deuce to player1 adv
		if((player1.gameScore==player2.gameScore) && (player1.gameScore==45)){
			player1.gameScore = 50;
			return;
		}
		// Adv to player 1 duece
		if((player1.gameScore==45) && (player2.gameScore==50)){
			player2.gameScore = 45;
			return;
		}
		// Adv to game
		if((player1.gameScore==50) && (player2.gameScore==45)){
			initializeGameScores(player1);
			initializeGameScores(player2);
			updateSetScore(player1, player2);
			return;
		}
	}
	
	void updateSetScore(Player player1, Player player2){
		if(player1.setScore<6){
			player1.setScore +=1;
			if(player1.setScore == 6 && player2.setScore < 5){
				output.add(player1.name+ " " +player1.setScore);
				//output.add(player2.name+ " " +player2.setScore+"\n");
				output.add(player2.name+ " " +player2.setScore);
				initializeSetScores(player1);
				initializeSetScores(player2);
				initializeGameScores(player1);
				initializeGameScores(player2);
				initializeTieBreakScores(player1);
				initializeTieBreakScores(player2);
			}
			//6-6, tiebreak
			if((player1.setScore == 6) && (player2.setScore == 6)){
				player1.tie = true;
				player2.tie = true;
				return;
			}
			return;
		}
		
		
		// 6-5
		if((player1.setScore == 6) && (player2.setScore == 5)){
			player1.setScore +=1;
			output.add(player1.name+ " " +player1.setScore);
//			output.add(player2.name+ " " +player2.setScore+"\n");
			output.add(player2.name+ " " +player2.setScore);
			initializeSetScores(player1);
			initializeSetScores(player2);
			return;
		}
		
	}
	
	void updateTieBreakScore(Player p1, Player p2){
	 p1.tieBreakScore +=1;
	 if((p1.tieBreakScore>= 7) &&((p1.tieBreakScore - p2.tieBreakScore) > 1)){
			p1.setScore +=1;
			output.add(p1.name+ " " +p1.setScore + "("+p1.tieBreakScore+"-"+p2.tieBreakScore+")");
//			output.add(player2.name+ " " +player2.setScore+"\n");
			output.add(p2.name+ " " +p2.setScore);
			initializeSetScores(p1);
			initializeSetScores(p2);
			initializeTieBreakScores(p1);
			initializeTieBreakScores(p2);
			return;
		 }
		 return;
	}
	
	
	class Player{
		int gameScore;
		int setScore;
		int tieBreakScore;
		boolean tie;
		String name;
		
		Player(){
			gameScore=0;
			setScore=0;
			tieBreakScore=0;
			tie = false;
		}
		
	}
}


/*
 * 
5
N D N D N D N N N N N N N N N N N N N N N N N N N N N N
D D D D D D D D D D D D D D D D D D D D D D D D
N N N N N N N N N N N N N N N N N N N N D D D D D D D D N N N N
D D D D N N N N D D D D N N N N D D D D N N N N D D D D N N N N D D D D N N N N D D D D N N N N D D D D D D N N N N N D
N N N N D D D D N N N N D D D D N N N N D D D D N N N N D D D D N N N N D D D D N D N D N D N D N D N N D N D N D N D N D N D D N D N D N D N D N D N D N D N D N N
*/
