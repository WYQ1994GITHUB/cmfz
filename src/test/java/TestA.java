import com.baizhi.cmfzAPP;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Album;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Menu;
import com.baizhi.service.AdminService;
import com.baizhi.service.AlbumService;
import com.baizhi.service.BannerService;
import com.baizhi.service.MenuService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by dell on 2018/10/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = cmfzAPP.class)
public class TestA {

    @Autowired
    private AdminService adminService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private BannerService bannerService;
    @Autowired
    private AlbumService albumService;

    @Test
    public void test1() {
        Admin admin = new Admin();
        admin.setName("小黑");
        admin.setPassword("123456");
        Admin admin1 = adminService.login(admin);
        System.out.println(admin1);
    }

    @Test
    public void test2() {
        List<Menu> list = menuService.getMenus();
        for (Menu menu : list) {
            System.out.println(menu);
        }
    }

    @Test
    public void test3() {
        List<Album> allAlbum = albumService.getAllAlbum();
        for (Album album : allAlbum) {
            System.out.println(album);
        }
    }
}
