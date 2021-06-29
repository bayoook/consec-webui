
package co.id.btpn.web.containerMonitoring;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.FormLoginRequestBuilder;
import org.springframework.test.web.servlet.MockMvc;

/**
 *
 * @author Ferry Fadly
 */
@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticatingLdapApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	// @Test 
	// public void loginWithValidUserThenAuthenticated() throws Exception {
	// 	FormLoginRequestBuilder login = formLogin()
	// 		.user("ben")
	// 		.password("benspassword");

	// 	mockMvc.perform(login)
	// 		.andExpect(authenticated());
	// }


	// @Test
	// public void loginWithValidUserThenAuthenticated2() throws Exception {
	// 	FormLoginRequestBuilder login = formLogin()
	// 		.user("bob")
	// 		.password("bobspassword");

	// 	mockMvc.perform(login)
	// 		.andExpect(authenticated());
	// }

	@Test
	public void loginWithValidUserThenAuthenticated3() throws Exception {
		FormLoginRequestBuilder login = formLogin()
			.user("user")
			.password("secret");

		mockMvc.perform(login)
			.andExpect(authenticated());
	}
	

	// @Test
	// public void loginWithInvalidUserThenUnauthenticated() throws Exception {
	// 	FormLoginRequestBuilder login = formLogin()
	// 		.user("invalid")
	// 		.password("invalidpassword");

	// 	mockMvc.perform(login)
	// 		.andExpect(unauthenticated());
	// }
}
