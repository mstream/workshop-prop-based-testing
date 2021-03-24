package workshop.api.fake;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import jauter.Routed;
import jauter.Router;
import workshop.api.http.HttpRequest;
import workshop.api.http.HttpResponse;

public class FakeWebService {

  private final Map<String, String> texts = new HashMap<>();

  private final ApiRouter router = new ApiRouter()
  .GET("/api/texts/:id", GetText.class)
  .POST("/api/texts", SaveText.class)
  .notFound(NotFound.class);


  public HttpResponse receive(HttpRequest request) {

    Routed<Class<? extends RequestHandler>> routed =
      router.route(request.getMethod(), request.getPath());

    Class<? extends RequestHandler> handlerClass = routed.target();

    RequestHandler requestHandler = createHandler(handlerClass);
    Map<String, String> pathParams = routed.params();
    String body = request.getBody();

    return requestHandler.execute(pathParams, body);
  }

  private RequestHandler createHandler(Class<? extends RequestHandler> handlerClass) {

    Constructor<? extends RequestHandler>[] ctors =
      (Constructor<? extends RequestHandler>[])
      handlerClass.getDeclaredConstructors();

    Constructor<? extends RequestHandler> ctor = ctors[0];

    try {
      if (ctor.getParameterCount() == 0) {
        return ctor.newInstance();
      } else {
        return ctor.newInstance(texts);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}


class ApiRouter extends
  Router<HttpRequest.Method, Class<? extends RequestHandler>, ApiRouter> {

  @Override
  protected ApiRouter getThis() {
    return this;
  }

  @Override
  protected HttpRequest.Method CONNECT() {
    return null;
  }

  @Override
  protected HttpRequest.Method DELETE() {
    return null;
  }

  @Override
  protected HttpRequest.Method GET() {
    return HttpRequest.Method.Get;
  }

  @Override
  protected HttpRequest.Method HEAD() {
    return null;
  }

  @Override
  protected HttpRequest.Method OPTIONS() {
    return null;
  }

  @Override
  protected HttpRequest.Method PATCH() {
    return null;
  }

  @Override
  protected HttpRequest.Method POST() {
    return HttpRequest.Method.Post;
  }

  @Override
  protected HttpRequest.Method PUT() {
    return null;
  }

  @Override
  protected HttpRequest.Method TRACE() {
    return null;
  }
}

interface RequestHandler {
  HttpResponse execute(Map<String, String> pathParams, String body);
}

class SaveText implements RequestHandler {

  private final Map<String, String> texts;

  SaveText(Map<String, String> texts) {
    this.texts = texts;
  }

  public HttpResponse execute(Map<String, String> pathParams, String body) {
    String id = String.format(
                  "%s-%s",
                  UUID.randomUUID().toString(),
                  body);
    texts.put(id, body);
    return HttpResponse.created(id);
  }
}

class GetText implements RequestHandler {

  private final Map<String, String> texts;

  GetText(Map<String, String> texts) {
    this.texts = texts;
  }

  public HttpResponse execute(Map<String, String> pathParams, String body) {
    String id = pathParams.get("id");
    String text = texts.get(id);
    if (text == null) {
      return HttpResponse.notFound(
               String.format("text with id '%s' does not exist", id));
    }
    return HttpResponse.ok(text);
  }
}

class NotFound implements RequestHandler {
  public HttpResponse execute(Map<String, String> pathParams, String body) {
    return HttpResponse.notFound(
             "resource at provided the provided path does not exist");
  }
}
