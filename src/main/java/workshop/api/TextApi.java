package workshop.api;

import java.util.Optional;


public interface TextApi {

  /**
  * Saves a piece of text and returns an indentifier which can be used
  * to retrieve it back.
  *
  * It does not save and returns null when a null or empty
  * text value is provided.
  */

  String saveText(String text);

  /**
   * It returns a piece of text associated with a provided identifier.
   *
   * It returns an empty optional when the text could not be found.
   * it returns an empty optional when null or empty id is provided.
   */
  Optional<String> getText(String id);
}
