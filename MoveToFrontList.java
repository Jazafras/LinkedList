public class MoveToFrontList 
{

	private StringCountElement head; // the head reference
	private StringCountElement tail; // the tail reference
	private int size; // the size of the list (number of valid items)

	/**
	 * Create a new, initially empty MoveToFrontList. This list is a
	 * linked data structure.
	 */
	public MoveToFrontList() 
	{
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * This method increments the count associated with the specified string
	 * key. If no corresponding key currently exists in the list, a new list
	 * element is created for that key with the count of 1. When this method
	 * returns, the key will have rank 0 (i.e., the list element associated with
	 * the key will be at the front of the list)
	 * 
	 * @param key
	 *            the string whose count should be incremented
	 * @return the new count associated with the key
	 */
	
	
	public int incrementCount(String key) {
		StringCountElement s = find(key);
		if (s != null) 
		{
			// found the key, splice it out and increment the count
			spliceOut(s);
			s.count++;
		} 
		else 
		{
			// need to create a new element
			s = new StringCountElement();
			s.key = key;
			s.count = 1;
	
		}
		// move it to the front
		spliceIn(s, 0);
		return s.count;
	}    
	
	/**@return the number of items in the list**/
	public int size() {
		return size;
	}

	/**
	 * Find the list element associated with the specified string.
	 * 
	 * @param key
	 *            the key to look for
	 * @return a StringCountElement in the list with the specified key or null
	 *         if no such element exists.
	 */
	public StringCountElement find(String key) {
		StringCountElement string = head; //start at beginning
		while (string != null)
		{
			if (string.key.equals(key)) //if the content of the list is equal to the key
			{
				return string;
			}
		string = string.next;
		}
		return null;
	}

	/**
	 * Compute the rank of the specified key.
	 * The rank of any item in an empty list is 0.
	 * 
	 * @param key
	 *            the key to look for
	 * @return the rank of that item in the rank 0...size() inclusive.
	 */
	public int rank(String key) {
		StringCountElement string = head; //start at beginning
		int rank = 0;
		while (string != null)
		{
			if (string.key.equals(key)) //if the content of the list is equal to the key
			{
				return rank;
			}
		rank++; //check the next rank if the if statement doesn't pass
		string = string.next; //check next string
		}
		return size;
	}

	/**
	 * Splice an element into the list at a position such that it will obtain
	 * the desired rank.
	 * 
	 * @param s
	 *            the element to be spliced in to the list
	 * @param desiredRank
	 *            the desired rank of the element
	 */
	public void spliceIn(StringCountElement s, int desiredRank) 
	{
		StringCountElement oldStr = head;
		int i = 0;
		if (head == null) //list is empty
		{
			head = s;
			tail = s;
			size++;
			return;
		}
		if (desiredRank == 0) //desired rank is the very beginning
		{
			s.next = oldStr; 
			oldStr.prev = s;
			head = s;
			size++;
			return;
		}
		if(desiredRank == size){ //desired rank is the very end
			s.prev = tail;
			s.next = null;
			tail.next = s;
			tail = s;
			size++;
			return;
		}
		while (i < desiredRank)
		{
			i++; 
			oldStr = oldStr.next;
		}
		s.next = oldStr;
		s.prev = oldStr.prev;
		oldStr.prev.next = s;
		oldStr.prev = s;
		size++;

	}

	/**
	 * Splice an element out of the list. When the element is spliced out, its
	 * next and prev references are set to null so that it can safely be
	 * splicedIn later.
	 * 
	 * @param s
	 *            the element to be spliced out of the list
	 */
	public void spliceOut(StringCountElement s) {
		// TODO: implement this
		if (s.prev == null && s.next == null) //only one item is in the list
		{
			head = null;
			tail = null;
			size--;
			return;
		}
		if (s.prev != null)   //this is not the first item
		{
			if (s.next == null) //element being removed was the tail
			{
				tail = s.prev; //re-assign the tail
			}
			s.prev.next = s.next; //re-assign the previous element's next pointer
			s.prev = null;
			size--;
		}
		if (s.next != null) //this is not the last item
		{
			if (s.prev == null) //element being removed was the head
			{
				head = s.next; //re-assign the head
			}
			s.next.prev = s.prev; //re-assign the next element's previous pointer 
			s.next = null;
			size--;
		}
		
	}

}
