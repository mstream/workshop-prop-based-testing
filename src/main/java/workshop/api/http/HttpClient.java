package workshop.api.http;

public interface HttpClient {
  HttpResponse send(HttpRequest request);
}
