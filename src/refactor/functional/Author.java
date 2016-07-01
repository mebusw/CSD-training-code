package refactor.functional;

import java.util.ArrayList;
import java.util.List;

/**
 authors.stream()
 .filter(a -> a.Company == company)
 .map(a -> a.TwitterHandle)
 .filter(x -> x != null)
 .forEach(x -> {
 result.add(x);
 });
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
