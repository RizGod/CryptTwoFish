import twofish.Twofish;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileDecryption {

    public void decrypt(String key, String path) throws Exception {
        try {
            // Получаем путь к рабочему столу
            String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";

            // Создаем файл на рабочем столе
            File fileOnDesktop = new File(desktopPath, "DecryptedText.txt");

            System.out.println("Program started");
            String encryptionKey192bit = key;

            byte[] filePlaintext = Twofish.twofishECBDecrypt(
                    Files.readAllBytes(Paths.get(path)),
                    encryptionKey192bit);
            File decryptedFile = fileOnDesktop;
            Files.write(decryptedFile.toPath(), filePlaintext);
            System.out.println("Program finished");
        } catch (Exception e) {
            System.out.println("Program started");
            throw e;
        }
    }
}
