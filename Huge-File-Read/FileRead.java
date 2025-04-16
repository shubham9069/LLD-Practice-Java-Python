import java.io.IOException;  // Import for File I/O
import java.io.RandomAccessFile;  // Import for NIO Buffer
import java.nio.MappedByteBuffer;  // Import for FileChannel (used for mmap)
import java.nio.channels.FileChannel;
import java.util.concurrent.ExecutorService; 
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


class FileProcessor implements Runnable {

    private MappedByteBuffer buffer;
    private int start;
    private int end;
    private FileRead fileRead;

    public FileProcessor(MappedByteBuffer buffer, int start, int end, FileRead fileRead) {
        this.buffer = buffer;
        this.start = start;
        this.end = end;
        this.fileRead = fileRead;
    }

    @Override
    public void run() {
        buffer.position(start);
        byte[] bytes = new byte[end - start]; 
        buffer.get(bytes);

        String content = new String(bytes);
        String[] words = content.split("\\s+");
        for (String word : words) {
            fileRead.addWords(word.length());
        }
    }
}
    

public class FileRead {
    private static final int THREAD = Runtime.getRuntime().availableProcessors();
    public int totalWords = 0;

    public synchronized void addWords(int count) {
        this.totalWords += count;
    }

    private void readFile(String filePath) {
        try {
            RandomAccessFile file = new RandomAccessFile(filePath, "r");
            FileChannel channel = file.getChannel();
            
            long fileSize = channel.size();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, fileSize);

            ExecutorService executorService = Executors.newFixedThreadPool(THREAD); 
            
            int start = 0;
        
            for (int i = 0; i < THREAD; i++) {
                int end = (int) ((i + 1) * (fileSize / THREAD));
                
                // Adjust end position to not split words
                
                executorService.execute(new FileProcessor(buffer, start, end, this));
                start = end;
            }
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.HOURS);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileRead fileRead = new FileRead();
        fileRead.readFile("huge-text.txt");
        System.out.println("Total words: " + fileRead.totalWords);
    }
}
