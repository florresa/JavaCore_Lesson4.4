import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        String successMsgDir = "Директория создана";
        String successMsgFile = "Файл создан";
        StringBuilder builder = new StringBuilder();

        // создаем директории

        File dir1 = new File("C://Games", "src");
        if (dir1.mkdir()) {
            builder.append(dir1.getName() + ": " + successMsgDir + System.lineSeparator());
        }

        File dir2 = new File("C://Games", "res");
        if (dir2.mkdir()) {
            builder.append(dir2.getName() + ": " + successMsgDir + System.lineSeparator());
        }

        File dir3 = new File("C://Games", "savegames");
        if (dir3.mkdir()) {
            builder.append(dir3.getName() + ": " + successMsgDir + System.lineSeparator());
        }


        File dir4 = new File("C://Games", "temp");
        if (dir4.mkdir()) {
            builder.append(dir4.getName() + ": " + successMsgDir + System.lineSeparator());
        }

        File dir5 = new File("C://Games/src", "main");
        if (dir5.mkdir()) {
            builder.append(dir5.getName() + ": " + successMsgDir + System.lineSeparator());
        }

        File dir6 = new File("C://Games/src", "test");
        if (dir6.mkdir()) {
            builder.append(dir6.getName() + ": " + successMsgDir + System.lineSeparator());
        }

        File dir7 = new File("C://Games/res", "drawables");
        if (dir7.mkdir()) {
            builder.append(dir7.getName() + ": " + successMsgDir + System.lineSeparator());
        }

        File dir8 = new File("C://Games/res", "vectors");
        if (dir8.mkdir()) {
            builder.append(dir8.getName() + ": " + successMsgDir + System.lineSeparator());
        }

        File dir9 = new File("C://Games/res", "icons");
        if (dir9.mkdir()) {
            builder.append(dir9.getName() + ": " + successMsgDir + System.lineSeparator());
        }

        // создаем файлы

        File file1 = new File("C://Games/src/main", "Main.java");
        try {
            if (file1.createNewFile()) {
                builder.append(file1.getName() + ": " + successMsgFile + System.lineSeparator());
            }
        } catch (IOException ex) {
            builder.append(ex.getMessage() + System.lineSeparator());
        }

        File file2 = new File("C://Games/src/main", "Utils.java");
        try {
            if (file2.createNewFile()) {
                builder.append(file2.getName() + ": " + successMsgFile + System.lineSeparator());
            }
        } catch (IOException ex) {
            builder.append(ex.getMessage() + System.lineSeparator());
        }

        File file3 = new File("C://Games/temp", "temp.txt");
        try {
            if (file3.createNewFile()) {
                builder.append(file3.getName() + ": " + successMsgFile + System.lineSeparator());
            }
        } catch (IOException ex) {
            builder.append(ex.getMessage() + System.lineSeparator());
        }

        // записываем лог в файл

        try (FileWriter writer = new FileWriter("C://Games/temp/temp.txt", false)) {
            writer.write(builder.toString());
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        // создаем экзепляры класса GameProgress

        GameProgress progress1 = new GameProgress(88, 3, 4, 56.86);
        GameProgress progress2 = new GameProgress(52, 6, 8, 125.58);
        GameProgress progress3 = new GameProgress(71, 9, 19, 289.12);

        // сохраняем прогресс

        saveGame("C://Games/savegames/save1.dat", progress1);
        saveGame("C://Games/savegames/save2.dat", progress2);
        saveGame("C://Games/savegames/save3.dat", progress3);

        // архивируем сохраненные файлы

        zipFiles("C://Games/savegames/savings.zip", "C://Games/savegames/save1.dat", "C://Games/savegames/save2.dat", "C://Games/savegames/save3.dat");

        // удаляем ненужные файлы

        File file4 = new File("C://Games/savegames/", "save1.dat");
        file4.delete();
        File file5 = new File("C://Games/savegames/", "save2.dat");
        file5.delete();
        File file6 = new File("C://Games/savegames/", "save3.dat");
        file6.delete();
    }

    public static void saveGame(String path, GameProgress progress) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(progress);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String pathZip, String pathFile1, String pathFile2, String pathFile3) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(pathZip))) {
            FileInputStream fis1 = new FileInputStream(pathFile1);
            {
                ZipEntry entry1 = new ZipEntry("save1.dat");
                zout.putNextEntry(entry1);
                byte[] buffer1 = new byte[fis1.available()];
                fis1.read(buffer1);
                zout.write(buffer1);
                zout.closeEntry();
            }
            FileInputStream fis2 = new FileInputStream(pathFile2);
            {
                ZipEntry entry2 = new ZipEntry("save2.dat");
                zout.putNextEntry(entry2);
                byte[] buffer2 = new byte[fis2.available()];
                fis2.read(buffer2);
                zout.write(buffer2);
                zout.closeEntry();
            }
            FileInputStream fis3 = new FileInputStream(pathFile3);
            {
                ZipEntry entry3 = new ZipEntry("save3.dat");
                zout.putNextEntry(entry3);
                byte[] buffer3 = new byte[fis3.available()];
                fis3.read(buffer3);
                zout.write(buffer3);
                zout.closeEntry();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}


