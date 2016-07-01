package refactor.functional;

import java.util.ArrayList;
import java.util.List;

/**
 * Extract Variable: loopStart = authors;
 * Move this to the pipeline with a filter operation.
 * Map operation
 * Move to a filter operation.
 * Remove the loop and just return the pipeline result.
 */
public class Author {

    public String Name;
    public String TwitterHandle;
    public String Company;

    static public List<String> TwitterHandles(List<Author> authors, String company) {
        List result = new ArrayList<String>();
        for (Author a : authors) {
            if (a.Company == company) {
                String handle = a.TwitterHandle;
                if (handle != null) result.add(handle);
            }
        }
        return result;
    }
}
