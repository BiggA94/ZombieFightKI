package de.uks.se1.ss15.dtritus.zombiefighter.KI;

import java.util.concurrent.ExecutionException;

public class Main {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		KI ki = new KI(args);
		ki.close();
	}

}
