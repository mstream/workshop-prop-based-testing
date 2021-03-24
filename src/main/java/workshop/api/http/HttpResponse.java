package workshop.api.http;

public class HttpResponse {

  private final int statusCode;
  private final String body;

  private HttpResponse(int statusCode, String body) {
    this.statusCode = statusCode;
    this.body = body;
  }

  public static HttpResponse ok(String body) {
    return new HttpResponse(200, body);
  }

  public static HttpResponse created(String body) {
    return new HttpResponse(201, body);
  }

  public static HttpResponse notFound(String body) {
    return new HttpResponse(404, body);
  }

  public int getStatusCode() {
    return statusCode;
  }

  public String getBody() {
    return body;
  }
}
