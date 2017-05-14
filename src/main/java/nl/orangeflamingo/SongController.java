package nl.orangeflamingo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class SongController {

    private static final String template = "Title: %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/song")
    public Song greeting(@RequestParam(value = "title", defaultValue = "Roxanne") String title) {
        return new Song(counter.incrementAndGet(),
                String.format(template, title));
    }

    @RequestMapping("/customer")
    public Customer getCustomer() {
        return new Customer("test", "test");
    }
}
