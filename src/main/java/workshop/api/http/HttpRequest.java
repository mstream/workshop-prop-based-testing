package workshop.api.http;

public class HttpRequest {

  public enum Method {
    Get,
    Post
  }

  private final Method method;
  private final String path;
  private final String body;

  private HttpRequest(Method method, String path, String body) {
    this.method = method;
    this.path = path;
    this.body = body;
  }

  public static HttpRequest get(String path) {
    return new HttpRequest(Method.Get, path, null);
  }

  public static HttpRequest post(String path, String body) {
    return new HttpRequest(Method.Post, path, body);
  }

  public HttpRequest.Method getMethod() {
    return method;
  }

  public String getPath() {
    return path;
  }

  public String getBody() {
    return body;
  }
}

