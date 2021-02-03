/**************************************************************/
/* CJ Karinen */
/* Login ID: kari6535 */
/* CS-102, Winter 2021 */
/* Programming Assignment 2 */
/* ListInterface interface: Provides methods  */
/* for implementation by the LinkedList class */
/**************************************************************/

public interface ListInterface
{
   public boolean isEmpty();
   public int size();
   public Object get (int index);
   public void add (int index, Object obj);
   public Object remove (int index);
}