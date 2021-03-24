package workshop.api.fake;

import workshop.api.http.HttpClient;
import workshop.api.http.HttpRequest;
import workshop.api.http.HttpResponse;

public class FakeHttpClient implements HttpClient {

  private final FakeWebService webService = new FakeWebService();

  @Override
  public HttpResponse send(HttpRequest request) {
    return webService.receive(request);
  }
}
