package com.lec.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lec.domain.Member;
import com.lec.repository.MemberRepository;

@Service
public class BoardUserDetailService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Member> optional =  memberRepository.findById(username);
		
		if(!optional.isPresent()) {
			throw new UsernameNotFoundException(username + " is not exist");
		} else {
			Member member = optional.get();
			return new SecurityUser(member);
		}
		
	}

}







