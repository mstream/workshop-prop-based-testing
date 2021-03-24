package workshop.api.http;

import java.util.Optional;

import workshop.api.TextApi;
import workshop.api.fake.FakeHttpClient;

class HttpTextApi implements TextApi {

  private final HttpClient httpClient = new FakeHttpClient();

  @Override
  public String saveText(String text) {
    if (text == null || text.trim().length() == 0) {
      return null;
    }

    HttpResponse response =
      httpClient.send(HttpRequest.post("/api/texts", text));

    if (response.getStatusCode() != 201) {
      throw new RuntimeException("uknown error during text write");
    }

    return response.getBody();
  }

  @Override
  public Optional<String> getText(String id) {
    if (id == null || id.trim().length() == 0) {
      return Optional.empty();
    }

    HttpResponse response =
      httpClient.send(HttpRequest.get("/api/texts/" + id));

    if (response.getStatusCode() == 404) {
      return Optional.empty();
    }

    if (response.getStatusCode() == 200) {
      return Optional.ofNullable(response.getBody());
    }

    throw new RuntimeException("unknown error during text read");
  }
}
