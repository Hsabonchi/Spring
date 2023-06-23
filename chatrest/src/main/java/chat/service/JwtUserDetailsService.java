package chat.service;

import java.util.ArrayList;


import chat.entity.Member;
import chat.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /*
     * new ArrayList<>() extract user identity information based on credentials from a database and
     * then perform validation
     *
     * accepts username as a parameter and returns the user identity object.
     * How can I get the sent password over through
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.getByName(username);

        if (member != null) {

            if ((member.getUserName()).equals(username)) {
                return new User(member.getUserName(), member.getPassword(), new ArrayList<>());
            } else {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

    }
}
