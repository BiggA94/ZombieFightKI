package de.uks.se1.ss15.dtritus.zombiefighter.KI.networking.handler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import de.uks.se1.ss15.dtritus.zombiefighter.KI.global.Mediator;

public class ByteHandler {

	public static boolean handle(byte[] byteCode) {
		// Create zip file
		try {
			String fileName = Mediator.getInstance().getZombieFighter().getServerMessageHandler()
					.getCurrentDownloadingMapName();
			String workingDirectory = Mediator.getWorkingDirectory();
			FileOutputStream fos = new FileOutputStream(workingDirectory + fileName);
			fos.write(byteCode);
			fos.close();

			Mediator.getInstance().getZombieFighter().getServerMessageHandler()
					.setLastDownloadedZip(workingDirectory + fileName);

			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}
}
