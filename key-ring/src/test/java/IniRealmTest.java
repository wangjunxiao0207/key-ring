import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class IniRealmTest {
    @Test
    public void test() {
        IniRealm realm = new IniRealm("classpath:shiro.ini");

        DefaultSecurityManager manager = new DefaultSecurityManager();

        manager.setRealm(realm );


        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();

        subject.login(new UsernamePasswordToken("hello", "world"));

        System.out.println(subject.isAuthenticated());
    }
}
