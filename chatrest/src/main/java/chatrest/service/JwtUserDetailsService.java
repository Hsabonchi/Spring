package chatrest.service;

import java.util.ArrayList;


import chatrest.entity.Member;
import chatrest.repository.MemberRepository;
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
    /*
      How can I save new user tio the database
     */
    /*
      if the user exist in the database , How to handle the exixt user.
      It Looks like the password is hardcoded on line 41
    */
    Member member = memberRepository.getByName(username);

    if (member != null) {

      //System.out.println("  member.getUserName()         "+member.getUserName()+"    member.getPassword() -->  "+ member.getPassword() +"encryped pawword   --> "+ passwordEncoder.encode(member.getPassword()));
      if ((member.getUserName()).equals(username)) {
        return new User(member.getUserName(),member.getPassword() ,new ArrayList<>());
      } else {
        throw new UsernameNotFoundException("User not found with username: " + username);
      }
    }
    else{
      throw new UsernameNotFoundException("User not found with username: " + username);
    }

  }
}
