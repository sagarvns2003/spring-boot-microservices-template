package com.vidya.payment.feignclient;

import com.vidya.payment.dto.Post;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "jsonPlaceholderClient", url = "https://jsonplaceholder.typicode.com/")
public interface JSONPlaceholderClient {

  @RequestMapping(method = RequestMethod.GET, value = "/posts")
  List<Post> getPosts();

  @RequestMapping(
      method = RequestMethod.GET,
      value = "/posts/{postId}",
      produces = "application/json")
  Post getPostById(@PathVariable("postId") Long postId);
}
