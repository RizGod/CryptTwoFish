import twofish.Twofish;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileEncryption {

    public void encrypt(String key, String path) throws Exception {
        try {
            // Получаем путь к рабочему столу
            String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";

            // Создаем файл на рабочем столе
            File fileOnDesktop = new File(desktopPath, "EncryptedFile.txt");

            System.out.println("Program started");
            String encryptionKey192bit = key;

            byte[] fileCiphertext = Twofish.twofishECBEncrypt(
                    Files.readAllBytes(Paths.get(path)),
                    encryptionKey192bit);
            File encryptedFile = fileOnDesktop;
            Files.write(encryptedFile.toPath(), fileCiphertext);
            System.out.println("Program finished");
        } catch (Exception e) {
            System.out.println("Program started");
            throw e;
        }
    }
}
