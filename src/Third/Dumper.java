package Third;

//import Third.Person.Gender;

/**
 * Classes that implement this interface dump a family tree to some
 * destination.
 */
public interface Dumper {

  /**
   * Dumps a family tree to some destination.
   */
  public void dump(FamilyTree tree);

}
