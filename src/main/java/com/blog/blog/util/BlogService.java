package com.blog.blog.util;


import com.blog.blog.exceptions.GeneralException;
import com.blog.blog.response.Response;

public interface BlogService {

    BlogServiceType getServiceType ();

    <T, U> Response executeService (T t, U u) throws GeneralException;

}
