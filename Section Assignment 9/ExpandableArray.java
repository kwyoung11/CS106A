/**
* This class provides methods for working with an array that expands
* to include any positive index value supplied by the caller.
*/
public class ExpandableArray {
/**
* Creates a new expandable array with no elements.
*/
public ExpandableArray() {
	myList = new Object[10];
}
/**
* Sets the element at the given index position to the specified
* value. If the internal array is not large enough to contain that
* element, the implementation expands the array to make room.
*/
public void set(int index, Object value) {
	if (index > myList.length) {
		int origSize = myList.length;
		int diff = index - origSize;
		int sum = origSize + diff;
		Object[] temp = new Object[sum + 1];
		for (int i = 0; i < myList.length; i++) {
			temp[i] = myList[i];
		}
		myList = temp;
		myList[index] = value;
	} else {
		myList[index] = value;
	}
}
/**
* Returns the element at the specified index position, or null if
* no such element exists. Note that this method never throws an
* out-of-bounds exception; if the index is outside the bounds of
* the array, the return value is simply null.
*/
public Object get(int index) {
	if (index < 0 || index > myList.length) {
		return null;
	} else {
		return myList[index];
	}
}

private Object[] myList;

}