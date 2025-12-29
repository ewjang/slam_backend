package org.slam.slam_backend.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slam.slam_backend.domain.Member;
import org.slam.slam_backend.dto.MemberDTO;
import org.slam.slam_backend.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("================loadUserByUsername==================");

        Member member = memberRepository.getWithRoles(username);

        if(member == null) {
            throw new UsernameNotFoundException("Not Found");

        }

        MemberDTO memberDTO = new MemberDTO(
                member.getEmail(),
                member.getPw(),
                member.getNickname(),
                member.isSocial(),
                member.getMemberRoleList().stream().map(memberRole-> memberRole.name()).collect(Collectors.toList()));

        log.info(memberDTO);

        return memberDTO;
    }
}
