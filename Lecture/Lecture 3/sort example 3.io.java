import java.io.File;
public class FileSorter
{
    public static void main(String[] args)
    {
        File directory = new File(args[0]);
        File[] file = firectory.listFiles();
        Insertion.sort(files);
        for(int i = 0; i<files.length;i++)
        {
            StdOut.println(files[i].getNames());
        }
    }
}