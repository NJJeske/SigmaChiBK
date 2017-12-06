
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

class LimitedCharDocument extends PlainDocument {
  private int limit;
  public LimitedCharDocument(int limit) {
	  
	  this.limit = limit;
  }
  @Override
  public void insertString(int offset, String str, AttributeSet a)
      throws BadLocationException {
    String newString = str;
    if (str != null) {
      int currentLength = this.getLength();
      int newTextLength = str.length();
      if (currentLength + newTextLength > limit) {
        newString = str.substring(0, limit - currentLength);
      }
    }

    super.insertString(offset, newString, a);
  }
}