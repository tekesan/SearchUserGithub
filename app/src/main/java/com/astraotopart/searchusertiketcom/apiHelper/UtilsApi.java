package com.astraotopart.searchusertiketcom.apiHelper;

public class UtilsApi {
    // http://52.40.55.251/ellaundry/APIv2/index.php/auth

    public static final String BASE_URL_API = "https://api.github.com";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService() {
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }


}
