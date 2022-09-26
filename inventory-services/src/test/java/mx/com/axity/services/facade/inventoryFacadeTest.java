package mx.com.axity.services.facade;

import mx.com.axity.services.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class inventoryFacadeTest extends BaseTest {

    @Test
    public void ShouldReturnAllUsers() {

        List<UserTO> users = this.inventoryFacade.getAllUsers();

        Assert.assertEquals(1, users.size());
    }
}