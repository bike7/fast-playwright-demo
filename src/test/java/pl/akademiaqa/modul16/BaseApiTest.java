package pl.akademiaqa.modul16;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import pl.akademiaqa.common.TestFixturesAPI;

import static pl.akademiaqa.modul16.ApiConstants.USERS_ENDPOINT;

public abstract class BaseApiTest extends TestFixturesAPI {

    protected final ObjectMapper mapper = new ObjectMapper();

    protected User createUser(User user) throws JsonProcessingException {
        APIResponse response = context.post(USERS_ENDPOINT, RequestOptions.create().setData(user));
        return mapper.readValue(response.text(), User.class);
    }
}