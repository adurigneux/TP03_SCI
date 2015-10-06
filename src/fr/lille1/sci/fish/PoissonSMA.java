package fr.lille1.sci.fish;

import fr.lille1.sci.core.Agent;
import fr.lille1.sci.core.Environnement;
import fr.lille1.sci.core.SMA;
import fr.lille1.sci.main.PoissonSimulation;

import java.util.*;

public class PoissonSMA extends SMA {

	private Timer timer;

	@Override
	public void init(int tailleX, int tailleY, int nombreAgents) {
		this.env = new Environnement(tailleX, tailleY);
		this.env.setSMA(this);

		this.setAgents(new ArrayList<Agent>(nombreAgents));
	}

	public void initThons(int nombreThons, int tempsReproductionPoisson) {
		for (int i = 0; i < nombreThons; i++) {
			int x, y;
			do {
				Random random = new Random();
				x = random.nextInt(env.getTailleX());
				y = random.nextInt(env.getTailleY());
			} while (!this.env.estVide(x, y));

			Thon p = new Thon(env, i, x, y, tempsReproductionPoisson);
			this.env.put(p.getX(), p.getY(), p);
			this.getAgents().add(p);
		}
	}

	public void initRequins(int nombreRequins, int tempsReproductionRequin,
			int tempsSansManger) {
		for (int i = 0; i < nombreRequins; i++) {
			int x, y;
			do {
				Random random = new Random();
				x = random.nextInt(env.getTailleX());
				y = random.nextInt(env.getTailleY());
			} while (!this.env.estVide(x, y));

			Requin p = new Requin(env, i, x, y, tempsReproductionRequin,
					tempsSansManger);
			this.env.put(p.getX(), p.getY(), p);
			this.getAgents().add(p);
		}
	}

	@Override
	public void run(final int sleepTime) {

		timer = new Timer();

		timer.schedule(new TimerTask() {
			public void run() {

				List<Agent> currentAgents = new ArrayList<Agent>(getAgents());
				Collections.shuffle(currentAgents);
				

				for (Agent a : currentAgents) {
					if(a.isAlive()){
						a.decide();
					}
					// System.out.println(a.toString());
				}
				PoissonSimulation.enregisterPopulations(PoissonSMA.this);
				setChanged();
				notifyObservers();

			}
		}, 0, sleepTime);

	}

}
