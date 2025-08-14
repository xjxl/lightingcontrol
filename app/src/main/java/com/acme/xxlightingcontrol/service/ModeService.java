package com.acme.xxlightingcontrol.service;

import com.acme.xxlightingcontrol.dto.ModeDto;
import com.acme.xxlightingcontrol.lib.net.XResponse;
import com.acme.xxlightingcontrol.net.HttpUrl;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author jx9@msn.com
 */
public interface ModeService {

    @GET(HttpUrl.GET_MODES_URL)
    Call<XResponse<List<ModeDto>>> getModes();

}