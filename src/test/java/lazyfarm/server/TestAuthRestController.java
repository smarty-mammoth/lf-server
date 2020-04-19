/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package lazyfarm.server;

import lazyfarm.server.controllers.AuthRestController;
import lazyfarm.server.entities.User;
import lazyfarm.server.services.UserSerivce;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
//import static org.junit.Assert.*;
import org.springframework.test.web.servlet.ResultMatcher;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.UriComponents;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest(AuthRestController.class)
public class TestAuthRestController {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserSerivce userSerivce;

    @Test
    public void testSignUpPasswordIsEmpty() throws Exception {
        Mockito.when(userSerivce.userExists("test")).thenReturn(false);
        mockMvc.perform(get("/auth/sign-up").param("login", "test"))
            .andDo(print())
            .andExpect(content().json("{'success': false}"));
    }

    @Test
    public void testSignUpLoginExists() throws Exception {
        Mockito.when(userSerivce.userExists("test")).thenReturn(true);
        mockMvc.perform(get("/auth/sign-up").param("login", "test").param("password", "123"))
                .andExpect(content().json("{'success': false}"));
    }

    @Test
    public void testSignUpSuccess() throws Exception {
        Mockito.when(userSerivce.userExists("test")).thenReturn(false);
        mockMvc.perform(get("/auth/sign-up").param("login", "test").param("password", "123"))
                .andExpect(content().json("{'success': true}"));
    }
    @Test
    public void testSignIn_Success() throws Exception {
        String login = "test";
        String pwd = "123";
        String hash = "calculated-hash";
        String token = "calculated-token";
        User mockedUser = new User(login, hash);
        Mockito.when(userSerivce.findUserByLoginAndHash(mockedUser.getLogin(), mockedUser.getHash()))
                .thenReturn(mockedUser);
        Mockito.when(userSerivce.calculateHash(pwd)).thenReturn(hash);
        Mockito.when(userSerivce.calculateToken(mockedUser)).thenReturn(token);

        mockMvc.perform(get("/auth/sign-in")
                .param("login", login)
                .param("password", pwd))
                .andExpect(content().json("{'success': true, token: 'calculated-token'}"));
    }

    @Test
    public void testSignIn_IncorrectLoginOrPassword() throws Exception {
        String login = "test";
        String pwd = "123";
        String incorrectPwd = "1234";
        String hash = "calculated-hash";
        User mockedUser = new User(login, hash);
        Mockito.when(userSerivce.findUserByLoginAndHash(mockedUser.getLogin(), mockedUser.getHash()))
                .thenReturn(mockedUser);
        Mockito.when(userSerivce.calculateHash(pwd)).thenReturn(hash);
        Mockito.when(userSerivce.calculateHash(incorrectPwd)).thenReturn("incorrect-hash");

        mockMvc.perform(get("/auth/sign-in").param("login", login).param("password", incorrectPwd))
                .andExpect(content().json("{'success': false}"));
    }

//    @Test public void testAppHasAGreeting() {
//        App classUnderTest = new App();
//        assertNotNull("app should have a greeting", classUnderTest.getGreeting());
//    }


}
