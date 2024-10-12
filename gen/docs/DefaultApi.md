# DefaultApi

All URIs are relative to *https://blog_ms*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createPost**](DefaultApi.md#createPost) | **POST** /posts | POST posts
[**deleteMyPost**](DefaultApi.md#deleteMyPost) | **DELETE** /posts/{postId} | DELETE posts/{postId}
[**getAllPost**](DefaultApi.md#getAllPost) | **GET** /posts | GET posts
[**getMyPost**](DefaultApi.md#getMyPost) | **GET** /posts/my-post | GET posts/my-post
[**getPostById**](DefaultApi.md#getPostById) | **GET** /posts/{postId} | GET posts/{postId}
[**updateMyPost**](DefaultApi.md#updateMyPost) | **PUT** /posts/{postId} | PUT posts/{postId}


<a name="createPost"></a>
# **createPost**
> String createPost(postRequestDto)

POST posts

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://blog_ms");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    PostRequestDto postRequestDto = new PostRequestDto(); // PostRequestDto | 
    try {
      String result = apiInstance.createPost(postRequestDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#createPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **postRequestDto** | [**PostRequestDto**](PostRequestDto.md)|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | Created |  -  |

<a name="deleteMyPost"></a>
# **deleteMyPost**
> String deleteMyPost(postId)

DELETE posts/{postId}

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://blog_ms");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Long postId = 56L; // Long | 
    try {
      String result = apiInstance.deleteMyPost(postId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#deleteMyPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **postId** | **Long**|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="getAllPost"></a>
# **getAllPost**
> List&lt;PostResponseDto&gt; getAllPost(page, size)

GET posts

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://blog_ms");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Integer page = 56; // Integer | 
    Integer size = 56; // Integer | 
    try {
      List<PostResponseDto> result = apiInstance.getAllPost(page, size);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#getAllPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **page** | **Integer**|  | [optional]
 **size** | **Integer**|  | [optional]

### Return type

[**List&lt;PostResponseDto&gt;**](PostResponseDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="getMyPost"></a>
# **getMyPost**
> List&lt;MyPostResponseDto&gt; getMyPost(page, size)

GET posts/my-post

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://blog_ms");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Integer page = 56; // Integer | 
    Integer size = 56; // Integer | 
    try {
      List<MyPostResponseDto> result = apiInstance.getMyPost(page, size);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#getMyPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **page** | **Integer**|  | [optional]
 **size** | **Integer**|  | [optional]

### Return type

[**List&lt;MyPostResponseDto&gt;**](MyPostResponseDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="getPostById"></a>
# **getPostById**
> PostResponseDto getPostById(postId)

GET posts/{postId}

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://blog_ms");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Long postId = 56L; // Long | 
    try {
      PostResponseDto result = apiInstance.getPostById(postId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#getPostById");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **postId** | **Long**|  |

### Return type

[**PostResponseDto**](PostResponseDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="updateMyPost"></a>
# **updateMyPost**
> String updateMyPost(postId, postRequestDto)

PUT posts/{postId}

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://blog_ms");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Long postId = 56L; // Long | 
    PostRequestDto postRequestDto = new PostRequestDto(); // PostRequestDto | 
    try {
      String result = apiInstance.updateMyPost(postId, postRequestDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#updateMyPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **postId** | **Long**|  |
 **postRequestDto** | [**PostRequestDto**](PostRequestDto.md)|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

