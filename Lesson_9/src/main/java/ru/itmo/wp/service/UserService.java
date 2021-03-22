package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.*;
import ru.itmo.wp.form.PostForm;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.repository.RoleRepository;
import ru.itmo.wp.repository.TagRepository;
import ru.itmo.wp.repository.UserRepository;

import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TagRepository tagRepository;


    /** @noinspection FieldCanBeLocal, unused */
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, TagRepository tagRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;

        this.roleRepository = roleRepository;
        for (Role.Name name : Role.Name.values()) {
            if (roleRepository.countByName(name) == 0) {
                roleRepository.save(new Role(name));
            }
        }

    }

    public User register(UserCredentials userCredentials) {
        User user = new User();
        user.setLogin(userCredentials.getLogin());
        userRepository.save(user);
        userRepository.updatePasswordSha(user.getId(), userCredentials.getLogin(), userCredentials.getPassword());
        return user;
    }

    public boolean isLoginVacant(String login) {
        return userRepository.countByLogin(login) == 0;
    }

    public User findByLoginAndPassword(String login, String password) {
        return login == null || password == null ? null : userRepository.findByLoginAndPassword(login, password);
    }

    public User findById(Long id) {
        return id == null ? null : userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAllByOrderByIdDesc();
    }

    public void writePost(User user, PostForm postForm) {
        Post post = new Post();
        post.setText(postForm.getText());
        post.setTitle(postForm.getTitle());
        String[] temp = postForm.getTags().split("\\s+");
        Set<String> tags = new TreeSet<>(Arrays.asList(temp));
        SortedSet<Tag> newTags = new TreeSet<>();
        for (String tag : tags) {
            if (tag.equals("")) {
                continue;
            }
            Tag cur = tagRepository.findByName(tag).orElse(null);
            if (cur == null) {
                cur = new Tag(tag);
                tagRepository.save(cur);
            }
            newTags.add(cur);
        }
        post.setTags(newTags);
        user.addPost(post);
        userRepository.save(user);
    }
}
