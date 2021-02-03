
class LinkedList implements ListInterface
{
   Node head;
   public LinkedList ()
   {
      head = null;
   }
   
   public boolean isEmpty()
   {
      // if (head == null) return true;
      // else return false;
      return (head == null);
   
   }
   
   public int size() 
   {
      Node current = head;
      int count = 0;
      
      while (current != null)
         {
            count++;
            current = current.getNext();
         }
      
      return count;
   }
   
   public Object get (int index)
   {
      Node current = head;
      
      while ((index != 0) && (current != null))
         {
            index--;
            current = current.getNext();
         }
         
      if (current == null)
         throw new IndexOutOfBoundsException();
      
      return current.getDatum();
   
   }
   public void add (int index, Object datum)
   {
      Node current = head;
      Node prev = null;
      
      while ((index != 0) && (current != null))
      {
         index--;
         prev = current;
         current = current.getNext();
      }
      
      if (index != 0)
         throw new IndexOutOfBoundsException();
         
      Node splice = new Node();
      splice.setDatum(datum);
      splice.setNext(current);
      
      if (prev != null)
         prev.setNext(splice);
      else
         head = splice;
   }
   
   public Object remove (int index)
   {
      Node current = head;
      Node prev = head;
      
      while ((index != 0) && (current != null))
      {
         index--;
         prev = current;
         current = current.getNext();
      }
      
      if (current == null)
         throw new IndexOutOfBoundsException();
         
      if (prev != null)
         prev.setNext(current.getNext());
      else 
         head = current.getNext();
         
      return current.getDatum();
   }















}