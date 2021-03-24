package workshop.api.http;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import workshop.api.TextApi;

@Tag("api")
public class HttpTextApiTest {

  @Test
  void exampleBasedTest() {
    returnsPreviouslySavedPieceOfText("abc");
  }

  static void returnsPreviouslySavedPieceOfText(String textToSave) {
    TextApi api = new HttpTextApi();
    String id = api.saveText(textToSave);
    Optional<String> returnedTextOpt = api.getText(id);
    Assertions.assertNotNull(returnedTextOpt);
    Assertions.assertEquals(textToSave, returnedTextOpt.orElseThrow());
  }
}
