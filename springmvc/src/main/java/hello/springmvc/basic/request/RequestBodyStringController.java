package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class RequestBodyStringController {

  @PostMapping("/request-body-string-v1")
  public void requestBodyStringV1(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws IOException {
    ServletInputStream inputStream = request.getInputStream();
    String msgBody = StreamUtils.copyToString(
      inputStream,
      StandardCharsets.UTF_8
    );

    log.info("msgBody ={}", msgBody);

    response.getWriter().write("suwan");
  }

  @PostMapping("/request-body-string-v2")
  public void requestBodyStringV2(
    InputStream inputStream,
    Writer responseWriter
  ) throws IOException {
    String msgBody = StreamUtils.copyToString(
      inputStream,
      StandardCharsets.UTF_8
    );

    log.info("msgBody ={}", msgBody);

    responseWriter.write("suwanV2");
  }

  @PostMapping("/request-body-string-v3")
  public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity)
    throws IOException {
    String body = httpEntity.getBody();
    log.info("msgBody ={}", body);

    // 반환도 메서드의 타입을 바꿔주면 알아서 된다.
    return new HttpEntity("suwnav3");
  }

  // Entity 상속받은 객체들
  @PostMapping("/request-body-string-v3-apply")
  public HttpEntity<String> requestBodyStringV3(
    RequestEntity<String> httpEntity
  ) throws IOException {
    String body = httpEntity.getBody();
    log.info("msgBody ={}", body);

    // 상태코드를 넣을 수 있따.
    return new ResponseEntity("suwan apply", HttpStatus.CREATED);
  }

  @ResponseBody
  @PostMapping("/request-body-string-v4")
  public String requestBodyStringV4(@RequestBody String msgBody)
    throws IOException {
    log.info("msgBody ={}", msgBody);

    return "suwan ResponseBody";
  }
}
