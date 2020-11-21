package ru.itmo.web.lesson4.util;

import ru.itmo.web.lesson4.model.Post;
import ru.itmo.web.lesson4.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DataUtil {
    private static final List<User> USERS = Arrays.asList(
            new User(1, "MikeMirzayanov", "Mike Mirzayanov", User.Color.GREEN),
            new User(6, "pashka", "Pavel Mavrin", User.Color.RED),
            new User(9, "geranazarov555", "Georgiy Nazarov", User.Color.RED),
            new User(11, "tourist", "Gennady Korotkevich", User.Color.BLUE)
    );

    private static final List<Post> POSTS = Arrays.asList(
            new Post(1, "You are win", "Codeforces Round #510 (Div. 2) will start at Monday, September 17, 2018 at 11:05. The round will be\n" +
                    "        rated for Div. 2 contestants (participants with the rating below 2100). Div. 1 participants can take\n" +
                    "        a part out of competition as usual.", 1),
            new Post(2, "You are lost", "<b>kek</b>Codeforces Round #1111 (Div. 2) will start at Monday, September 17, 2018 at 11:05. The round will be\n" +
                    "        rated for Div. 2 contestants (participants with the rating below 2100). Div. 1 participants can take\n" +
                    "        a part out of competition as usual. This round is held on the tasks of the school stage All-Russian Olympiad of Informatics 2018/2019\n" +
                    "        year in city Saratov. The problems were prepared by PikMike, fcspartakm, Ne0n25, BledDest, Ajosteen\n" +
                    "        and Vovuh. Great thanks to our coordinator _kun_ for the help with the round preparation! I also\n" +
                    "        would like to thank our testers DavidDenisov, PrianishnikovaRina, Decibit and Vshining.</p>\n" +
                    "    <p>UPD: The scoring distribution is 500-1000-1500-2000-2250-2750. This round is held on the tasks of the school stage All-Russian Olympiad of Informatics 2018/2019\n" +
                    "        year in city Saratov. The problems were prepared by PikMike, fcspartakm, Ne0n25, BledDest, Ajosteen\n" +
                    "        and Vovuh. Great thanks to our coordinator _kun_ for the help with the round preparation! I also\n" +
                    "        would like to thank our testers DavidDenisov, PrianishnikovaRina, Decibit and Vshining.</p>\n" +
                    "    <p>UPD: The scoring distribution is 500-1000-1500-2000-2250-2750.", 6)
    );

    public static void addData(HttpServletRequest request, Map<String, Object> data) {
        data.put("users", USERS);
        Collections.sort(POSTS);
        data.put("posts", POSTS);
        for (User user : USERS) {
            if (Long.toString(user.getId()).equals(request.getParameter("logged_user_id"))) {
                data.put("user", user);
            }
        }
    }
}
