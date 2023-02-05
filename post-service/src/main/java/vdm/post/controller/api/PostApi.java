package vdm.post.controller.api;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import vdm.post.domen.Post;
import vdm.post.domen.dto.PostMessageDto;

public interface PostApi {

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "400",
          description = "Validation error or request body is an invalid"),
      @ApiResponse(responseCode = "500", description = "Internal server error occurred")})
  Post createUser(PostMessageDto postDto);

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "404", description = "Post doesn’t exist with given id"),
      @ApiResponse(responseCode = "500", description = "Internal server error occurred")})
  Post findPost(Long postId);

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "404", description = "Not Found"),
      @ApiResponse(responseCode = "500", description = "Internal server error occurred")})
  void deletePost(Long postId);

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Ok"),
      @ApiResponse(responseCode = "400",
          description = "Validation error or request body is an invalid"),
      @ApiResponse(responseCode = "404", description = "Post doesn’t exist with given id"),
      @ApiResponse(responseCode = "500", description = "Internal server error occurred")})
  Post updateUser(Long postId, PostMessageDto postDto);
}
