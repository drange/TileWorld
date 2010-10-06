package no.uib.info282.tileworld;

import java.util.LinkedList;
import java.util.List;

import no.uib.info282.tileworld.solvers.BfsSolver;
import no.uib.info282.tileworld.solvers.Solver;

/**
 * The Main class of our program. In here you change the {@link Solver} the
 * program will use.
 * 
 * @author epa095, pdr081
 * 
 */
public class Main {

	public static void main(String[] args) {
		// Change this function call to use your solver instead
		init(new BfsSolver());
	}

	private static void init(Solver... solvers) {
		if (solvers.length > 0) {
			List<Solver> lst = new LinkedList<Solver>();
			for (int i = 1; i < solvers.length; i++) {
				lst.add(solvers[i]);
			}
			new Game(solvers[0], lst, GameSpec.defaultSpec());
		}

	}
}
