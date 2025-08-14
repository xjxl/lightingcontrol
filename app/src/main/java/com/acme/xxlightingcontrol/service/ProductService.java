package com.acme.xxlightingcontrol.service;

import com.acme.xxlightingcontrol.dto.ProductDto;
import com.acme.xxlightingcontrol.lib.net.http.XResponse;
import com.acme.xxlightingcontrol.net.HttpUrl;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author jx9@msn.com
 */
public interface ProductService {

    @GET(HttpUrl.GET_PRODUCTS_URL)
    Call<XResponse<List<ProductDto>>> getProducts();

}