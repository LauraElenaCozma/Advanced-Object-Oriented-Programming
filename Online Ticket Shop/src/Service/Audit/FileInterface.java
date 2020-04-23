package Service.Audit;

import java.io.BufferedWriter;
import java.io.IOException;

public interface FileInterface <T>{
    public void load(String line);
    public void print(BufferedWriter out, T ob) throws IOException;
    public void writeHeader(BufferedWriter out) throws IOException;
}
