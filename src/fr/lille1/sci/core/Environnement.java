package fr.lille1.sci.core;

import fr.lille1.sci.dijkstra.Attracteur;
import fr.lille1.sci.fish.Thon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Environnement {

	private SMA sma;
	private Agent[][] espace;
	private int[][] dijkstraMap;
	private int tailleX;
	private int tailleY;

	public Environnement(int tailleX, int tailleY) {
		this.tailleX = tailleX;
		this.tailleY = tailleY;

		this.espace = new Agent[tailleX][tailleY];
		this.dijkstraMap = new int[tailleX][tailleY];
		initDijkstraMap();
	}

	public int getTailleX() {
		return this.tailleX;
	}
	
	public void initDijkstraMap(){
		for (int i = 0; i < dijkstraMap.length; i++) {
			for (int j = 0; j < dijkstraMap[i].length; j++) {
				dijkstraMap[i][j] = Integer.MAX_VALUE;
			}
		}
	}

	public boolean estAttracteur(int x, int y) {
		return this.espace[x][y] != null && this.espace[x][y] instanceof Attracteur;
	}

	public int getTailleY() {
		return this.tailleY;
	}

	public boolean put(int x, int y, Agent agent) {
		this.espace[x][y] = agent;
		return true;
	}

	public void remove(int x, int y) {
		Agent agent = this.espace[x][y];
		agent.setAlive(false);
		this.sma.removeAgent(agent);
		clear(x, y);
	}

	public Agent get(int x, int y) {
		return this.espace[x][y];
	}

	public boolean estEnDehors(int x, int y) {
		return (estDehorsX(x) || estDehorsY(y));
	}

	public boolean estDehorsX(int x) {
		return !(x >= 0 && x < this.tailleX);
	}

	public boolean estDehorsY(int y) {
		return !(y >= 0 && y < this.tailleY);
	}

	public boolean estVide(int x, int y) {
		return this.get(x, y) == null || !this.get(x, y).isAlive;
	}

	public void clear(int x, int y) {
		this.espace[x][y] = null;
	}

	public boolean estThon(int x, int y) {
		return this.espace[x][y] != null && this.espace[x][y] instanceof Thon;
	}

	public Position getPlaceLibre(int x, int y) {

		List<Position> positions = new ArrayList<Position>();

		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {

				if (!this.estEnDehors(i, j) && this.estVide(i, j)) {
					positions.add(new Position(i, j));
				}
			}
		}

		if (positions.isEmpty()) {
			return null;
		}

		Collections.shuffle(positions);
		return positions.get(0);
	}

	public Position getThonDispo(int x, int y) {

		List<Position> positions = new ArrayList<Position>();

		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {

				if (!this.estEnDehors(i, j) && this.estThon(i, j)) {
					positions.add(new Position(i, j));
				}

			}
		}

		if (positions.isEmpty()) {
			return null;
		}

		Collections.shuffle(positions);
		return positions.get(0);
	}

	public void addAgent(int x, int y, Agent agent) {
		this.put(x, y, agent);
		this.sma.addAgent(agent);
	}

	public void setSMA(SMA sma) {
		this.sma = sma;
	}

	public int[][] getDikstraMap() {
		return dijkstraMap;
	}

	public String toStringDijkstraMap() {
		String s = "";
		for(int i = 0; i < dijkstraMap.length; i++) {
			for(int j = 0; j < dijkstraMap.length; j++) {
				s += dijkstraMap[i][j] + "\t";

			}
			s += "\n";
		}
		return s;
	}

}
