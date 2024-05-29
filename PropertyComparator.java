import java.util.Comparator;
public class PropertyComparator implements Comparator<Property> {
    
    @Override
    public int compare(Property p1, Property p2) {
        // real estate is always displayed first
        if (p1 instanceof RealEstate && !(p2 instanceof RealEstate)) {  
            return -1;
        }
        if (p2 instanceof RealEstate && !(p1 instanceof RealEstate)) {
            return 1;
        }
        if (p1 instanceof Transportation && p2 instanceof Utilities) {
            return -1;
        }
        if (p2 instanceof Transportation && p1 instanceof Utilities) {
            return 1;
        }
        //if both are realestate or both are not realestate, compare their titles which will start with monopoly for real estate
        String title1 = p1.toString();
        String title2 = p2.toString();
        return title1.compareTo(title2);
    }

    
}
