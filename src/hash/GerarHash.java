package hash;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GerarHash {
	
    public static void main(String[] args) {
        String pasta = "C:\\Users\\Rodrigo\\eclipse-workspace\\JavaHash\\arquivosHash";
        File diretorio = new File(pasta);

        File[] arquivos = diretorio.listFiles();

        for (File arquivo : arquivos) {
            if (arquivo.isFile() && arquivo.getName().endsWith(".txt")) {
                String hash = gerarHash(arquivo);
                System.out.println(arquivo.getName() + " - Hash: " + hash);
            }
        }
    }

    private static String gerarHash(File arquivo) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            FileInputStream inputStream = new FileInputStream(arquivo);
            DigestInputStream digestInputStream = new DigestInputStream(inputStream, digest);

            byte[] ler = new byte[8192];
            while (digestInputStream.read(ler) != -1) {
            }
            digestInputStream.close();
            byte[] hashBytes = digest.digest();

            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
