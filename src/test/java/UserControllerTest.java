import controller.UserController;
import model.Repository.UserRepo;
import model.logic.User;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    UserController userController;
    UserRepo userRepo;
    private static final Long ID = 1L;

    @Before
    public void setUp() {
        this.userController = new UserController();
        this.userController = mock(UserController.class);
        this.userRepo = new UserRepo();
        this.userRepo = mock(UserRepo.class);
    }

    @Test
    public void shouldGetUserById() {
        User expected = new User();
        when(this.userController.getById((long) 1))
                .thenReturn(expected);
        User actual = this.userController.getById(ID);
        Assert.assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldRemoveUser() {
        User user = new User();
        doNothing().when(this.userController).delete(Matchers.anyObject());
        this.userController.delete(user.getId());
        verify(this.userController).delete(user.getId());
    }

    @Test
    public void shouldSaveUser() {
        User user = new User();
        doNothing().when(this.userController).create(Matchers.anyObject());
        this.userController.create(user);
        verify(this.userController).create(user);
    }

}
